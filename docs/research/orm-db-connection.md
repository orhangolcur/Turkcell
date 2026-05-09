# Spring Boot'ta ORM ve Veritabanı Bağlantısı

## ORM Nedir?

ORM (Object-Relational Mapping), nesne yönelimli programlama ile ilişkisel veritabanları arasında köprü kuran bir tekniktir. Temel fikir şudur: veritabanı tablolarını doğrudan Java sınıflarıyla eşleştirmek, böylece SQL yazmadan nesneler üzerinden veritabanı işlemi yapabilmek.

```
Java Sınıfı  ←→  Veritabanı Tablosu
Java Nesnesi ←→  Tablo Satırı
Alan (Field) ←→  Sütun (Column)
```

---

## JPA, Hibernate ve Spring Data JPA

Bu üç kavram birbiriyle karıştırılır ama ayrı şeylerdir:

| Kavram | Açıklama |
|---|---|
| **JPA** | Java'nın resmi ORM standardı — sadece arayüz tanımlar |
| **Hibernate** | JPA'yı hayata geçiren kütüphane, asıl SQL'i o üretir |
| **Spring Data JPA** | Hibernate üzerine Spring'in eklediği kolaylık katmanı |

> Kısaca: JPA kural koyar, Hibernate uygular, Spring Data JPA kolaylaştırır.

---

## Veritabanı Bağlantısı Nasıl Kurulur?

Spring Boot'ta bağlantı bilgileri `application.properties` dosyasına yazılır:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/veritabani_adi
spring.datasource.username=root
spring.datasource.password=sifre

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

`ddl-auto` ayarı özellikle önemlidir — Hibernate'in tabloları otomatik oluşturup oluşturmayacağını belirler. `update` geliştirme için uygundur ama üretim ortamında `none` kullanılmalıdır.

---

## Connection Pool: Bağlantılar Nasıl Yönetilir?

Uygulama her veritabanı isteğinde sıfırdan bir bağlantı açsaydı bu hem yavaş hem de maliyetli olurdu. Bu sorunu çözmek için **Connection Pool** (bağlantı havuzu) kullanılır: uygulama başlarken belirli sayıda bağlantı açılır ve havuzda bekletilir. Gelen her istek bu havuzdan boş bir bağlantı alır, işi bitince geri bırakır.

Spring Boot varsayılan olarak **HikariCP** adlı connection pool kütüphanesini kullanır. Ekstra bir yapılandırma gerekmez, otomatik devreye girer. Havuz boyutu gibi ayarlar `application.properties` üzerinden yapılabilir:

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
```

---

## Session ve EntityManager: ORM'nin Kalbi

Hibernate, veritabanıyla doğrudan JDBC bağlantısı üzerinden değil, bir **Session** (oturum) aracılığıyla konuşur. Session; sorgu çalıştırma, nesne takibi ve değişiklikleri veritabanına yansıtma gibi işlemleri yönetir.

JPA dünyasında Session'ın karşılığı **EntityManager**'dır. Spring Data JPA kullanıldığında EntityManager arka planda otomatik yönetilir, çoğu zaman doğrudan kullanmak gerekmez. Ancak kavramsal olarak şöyle çalışır:

```
İstek gelir
   → EntityManager açılır
   → Sorgular çalışır, nesneler takip edilir
   → Transaction kapanınca değişiklikler DB'ye yazılır
   → EntityManager kapatılır
```

---

## @Transactional: İşlem Bütünlüğü

Birden fazla veritabanı işleminin **ya hep birden başarılı olmasını ya da hep birden geri alınmasını** istediğimizde `@Transactional` anotasyonu kullanılır. Örneğin bir banka transferinde para çekme ve yatırma işlemlerinden biri başarısız olursa diğeri de geri alınmalıdır.

```java
@Transactional
public void transferYap(Long kaynakId, Long hedefId, double miktar) {
    // Bu iki işlem tek bir transaction içinde çalışır.
    // Biri hata verirse ikisi de geri alınır.
    hesapService.düş(kaynakId, miktar);
    hesapService.ekle(hedefId, miktar);
}
```

Spring, `@Transactional` gördüğünde metod başında bir transaction açar, metod başarıyla biterse **commit**, hata fırlarsa **rollback** yapar.

---

## Entity: Tablo ↔ Sınıf Eşlemesi

Bir Java sınıfını veritabanı tablosuna bağlamak için `@Entity` anotasyonu yeterlidir. Spring Boot uygulaması başlarken bu sınıfları tarar ve tablolara eşler.

```java
@Entity
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String email;
}
```

---

## Repository: Sorgu Katmanı

Spring Data JPA'nın en güçlü özelliği, `JpaRepository` arayüzünü extend etmenin yeterli olmasıdır — `save`, `findAll`, `deleteById` gibi onlarca metod otomatik olarak kullanılabilir hale gelir. Özel sorgular için ise metod adı yeterlidir:

```java
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {
    List<Ogrenci> findByAd(String ad); // Spring bunu SQL'e çevirir
}
```

---

## Genel Mimari Akış

```
HTTP İsteği
     │
     ▼
[ Controller ]      → İsteği karşılar
     │
     ▼
[  Service   ]      → İş mantığı, @Transactional burada
     │
     ▼
[ Repository ]      → Veritabanı işlemleri
     │
     ▼
[ Hibernate  ]      → EntityManager/Session açar, SQL üretir
     │
     ▼
[ HikariCP   ]      → Havuzdan bir bağlantı alır
     │
     ▼
[ Veritabanı ]
```
