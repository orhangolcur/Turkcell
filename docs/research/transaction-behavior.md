# Transaction Behavior

## Transaction Nedir?

Transaction, birden fazla veritabanı işlemini **tek bir bütün** olarak ele alan mekanizmadır.

Ya hepsi başarılı olur → **Commit** (değişiklikler kaydedilir)  
Ya da biri bile başarısız olur → **Rollback** (tüm değişiklikler geri alınır)

### Gerçek Hayat Örneği

Bir banka havalesi düşünün:

1. A hesabından 1000₺ düş
2. B hesabına 1000₺ ekle

Eğer 1. adım başarılı olup 2. adım başarısız olursa A hesabından para gider ama B hesabına ulaşmaz. Transaction olmadan bu mümkün. Transaction ile bu iki işlem bir bütündür, biri başarısız olursa diğeri de geri alınır.

---

## ACID Özellikleri

Transaction'lar dört temel özelliği garanti eder:

| Özellik | Açıklama |
|---|---|
| **A**tomicity | Tüm işlemler ya hep ya hiç çalışır |
| **C**onsistency | Veritabanı her zaman tutarlı bir durumda kalır |
| **I**solation | Eş zamanlı işlemler birbirini etkilemez |
| **D**urability | Commit edilen veriler kalıcıdır |

---

## Commit ve Rollback

```
Transaction başlar
    ↓
İşlem 1 → başarılı ✅
İşlem 2 → başarılı ✅
İşlem 3 → HATA ❌
    ↓
Rollback → tüm değişiklikler geri alınır
```

```
Transaction başlar
    ↓
İşlem 1 → başarılı ✅
İşlem 2 → başarılı ✅
İşlem 3 → başarılı ✅
    ↓
Commit → tüm değişiklikler veritabanına yazılır
```

---

## Pipeline'da Transaction Behavior

CQRS mimarisinde Transaction Behavior, her Command handler'ı otomatik olarak bir transaction içinde çalıştırır.

```
Command gelir
    ↓
Transaction Behavior devreye girer → Transaction başlar
    ↓
Handler çalışır (veritabanı işlemleri yapılır)
    ↓
Başarılı → Commit ✅
Hata     → Rollback ❌
```

### Neden Sadece Command'lerde?

| | Command | Query |
|---|---|---|
| **Amacı** | Veriyi değiştirir | Veriyi okur |
| **Transaction gerekir mi?** | ✅ Evet | ❌ Hayır |

Query sadece okuma yapar, veritabanını değiştirmez. Bu yüzden Transaction Behavior'da `supports()` metodu ile sadece Command'ler için çalışması sağlanır.

---

## Spring'de Transaction Yönetimi

Spring, `PlatformTransactionManager` interface'i üzerinden transaction yönetimini soyutlar. Altta hangi veritabanı olursa olsun (PostgreSQL, MySQL vb.) aynı kod çalışır.

```
PlatformTransactionManager
    ↓
Transaction başlat → getTransaction()
Transaction onayla → commit()
Transaction geri al → rollback()
```

---

## Özet

- Transaction → birden fazla işlemi tek bütün olarak ele alır
- Commit → her şey başarılı, değişiklikleri kaydet
- Rollback → bir şeyler ters gitti, her şeyi geri al
- Pipeline'da Transaction Behavior → Command handler'larını otomatik transaction içine alır
- Query'lerde gerekmez → sadece okuma yapıyorlar
