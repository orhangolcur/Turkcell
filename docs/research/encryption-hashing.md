# Encryption ve Hashing

## Temel Fark

Bu iki kavram sık sık karıştırılır ama amaçları tamamen farklıdır:

| | Encryption | Hashing |
|---|---|---|
| **Amaç** | Veriyi gizlemek | Veriyi doğrulamak |
| **Geri alınabilir mi?** | Evet (şifre çözülebilir) | Hayır (tek yönlü) |
| **Anahtar gerekir mi?** | Evet | Hayır |
| **Kullanım yeri** | Veri iletimi, dosya şifreleme | Şifre saklama, bütünlük kontrolü |

> Kısaca: Encryption "sonra geri okuyacağım" derken, Hashing "sadece doğrulayacağım" der.

---

## Encryption (Şifreleme)

Encryption, bir veriyi anahtar kullanarak okunamaz hale getirme işlemidir. Doğru anahtar olmadan şifrelenmiş veri anlamsızdır. İki ana türü vardır:

**Simetrik Şifreleme:** Şifrelemek ve çözmek için aynı anahtar kullanılır. Hızlıdır, büyük veri için uygundur. Örnek: AES.

**Asimetrik Şifreleme:** Biri public (herkese açık), biri private (gizli) olmak üzere iki anahtar kullanılır. Public key ile şifrelenen veri yalnızca private key ile çözülür. Örnek: RSA. HTTPS bu yöntemi kullanır.

```
Simetrik:   Veri + Anahtar → Şifreli Veri → Anahtar → Orijinal Veri
Asimetrik:  Veri + Public Key → Şifreli Veri → Private Key → Orijinal Veri
```

---

## Hashing

Hashing, bir veriyi sabit uzunlukta ve geri döndürülemez bir değere (hash) dönüştürme işlemidir. Aynı girdi her zaman aynı hash'i üretir; ancak hash'ten girdiyi elde etmek mümkün değildir.

```
"sifre123"  →  $2a$10$Xyz...  (hash)
"sifre124"  →  $2a$10$Abc...  (tamamen farklı hash)
```

Yaygın hashing algoritmaları: **MD5** (artık güvensiz), **SHA-256**, **bcrypt**, **Argon2**.

Şifre saklamada özellikle **bcrypt** ve **Argon2** tercih edilir çünkü bunlar kasıtlı olarak yavaş çalışır — bu, kaba kuvvet saldırılarını zorlaştırır.

### Salt Nedir?

Aynı şifre her zaman aynı hash'i üretirse saldırgan önceden hesaplanmış hash tablolarıyla (rainbow table) şifreleri bulabilir. Bunu önlemek için hash'e rastgele bir değer eklenir — buna **salt** denir. bcrypt bunu otomatik olarak yapar.

---

## Ne Zaman Hangisi Kullanılır?

- **Kullanıcı şifresi saklamak** → Hashing (bcrypt/Argon2)
- **API anahtarı veya token saklamak** → Hashing
- **Kredi kartı bilgisi saklamak** → Encryption (geri okunması gerekiyor)
- **İki servis arasında güvenli veri iletimi** → Encryption (HTTPS/TLS)
- **Dosya bütünlüğünü kontrol etmek** → Hashing (SHA-256)

---

---

## Spring Boot'ta Hashing: BCrypt ile Şifre Saklama

Spring Security, şifre hashleme için hazır bir altyapı sunar. En yaygın kullanılan yöntem **BCryptPasswordEncoder**'dır.

### Bağımlılık

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### Kullanım

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

```java
// Kayıt sırasında şifreyi hashle
String hashlenmisSifre = passwordEncoder.encode("kullanici_sifresi");

// Giriş sırasında doğrula
boolean eslesiyorMu = passwordEncoder.matches("kullanici_sifresi", hashlenmisSifre);
```

BCrypt her seferinde farklı bir hash üretir (salt otomatik eklenir), ama `matches()` yine de doğru sonucu verir.

---

## Spring Boot'ta Encryption: Hassas Verileri Şifrelemek

Şifrelenip tekrar okunması gereken veriler (örn. kredi kartı numarası, API anahtarı) için Encryption kullanılır. Spring'de bu genellikle **Jasypt** kütüphanesi veya **Java Cryptography Architecture (JCA)** ile yapılır.

### application.properties İçindeki Hassas Verileri Şifrelemek (Jasypt)

```xml
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```

```properties
# Şifreli değer ENC(...) içine yazılır
spring.datasource.password=ENC(şifrelenmiş_değer)
jasypt.encryptor.password=gizli_anahtar
```

Böylece `application.properties` dosyasındaki şifreler açık metin olarak durmaz.

---

## Genel Mimari Bakış

```
Kullanıcı şifre girer
        │
        ▼
[ BCrypt ile hashlenir ]
        │
        ▼
[ Hash veritabanına kaydedilir ]  ← Asıl şifre hiç saklanmaz

─────────────────────────────────

Kullanıcı giriş yapar
        │
        ▼
[ Girilen şifre + DB'deki hash karşılaştırılır ]
        │
        ▼
[ matches() → true/false ]
```
