# JPQL (Java Persistence Query Language)

## JPQL Nedir?

JPQL, JPA standardının bir parçası olan sorgu dilidir. SQL'e çok benzer ama temel farkı şudur: **veritabanı tablolarını değil, Java sınıflarını ve alanlarını hedef alır.**

```
SQL:   SELECT * FROM ogrenciler WHERE yas > 18
JPQL:  SELECT o FROM Ogrenci o WHERE o.yas > 18
```

Bu sayede veritabanı bağımsızlığı korunur — MySQL'den PostgreSQL'e geçilse bile sorgular değişmez.

---

## SQL'den Farkı

| | SQL | JPQL |
|---|---|---|
| **Hedef** | Tablo ve sütun adları | Java sınıfı ve alan adları |
| **Büyük/küçük harf** | Genellikle önemsiz | Sınıf adları büyük harf ile başlar |
| **Veritabanı bağımlı mı?** | Evet | Hayır |
| **`*` kullanımı** | `SELECT *` | Desteklenmez, alias kullanılır |

---

## Temel Sözdizimi

JPQL sorguları SQL'e çok benzediği için öğrenmesi kolaydır. Önemli olan tablo adı yerine **entity sınıfı adı**, sütun adı yerine **alan adı** yazılmasıdır.

```
SELECT o FROM Ogrenci o                        → tüm öğrenciler
SELECT o FROM Ogrenci o WHERE o.yas > 18       → yaşa göre filtreleme
SELECT o FROM Ogrenci o ORDER BY o.ad ASC      → ada göre sıralama
SELECT COUNT(o) FROM Ogrenci o                 → kayıt sayısı
```

---

## Spring Data JPA'da JPQL: @Query

Spring Data JPA'da özel sorgular yazmak için `@Query` anotasyonu kullanılır. Repository metodunun üstüne yazılır.

```java
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    // Basit JPQL sorgusu
    @Query("SELECT o FROM Ogrenci o WHERE o.yas > :yas")
    List<Ogrenci> yasindanBuyukOlanlar(@Param("yas") int yas);

    // Birden fazla parametre
    @Query("SELECT o FROM Ogrenci o WHERE o.ad = :ad AND o.yas > :yas")
    List<Ogrenci> adVeYasaGore(@Param("ad") String ad, @Param("yas") int yas);

    // Sadece belirli alanları getir
    @Query("SELECT o.ad, o.email FROM Ogrenci o WHERE o.yas > 18")
    List<Object[]> adVeEmailGetir();
}
```

---

## Metod Adıyla Sorgu vs @Query

Spring Data JPA'da iki farklı sorgu yazma yöntemi vardır:

| Yöntem | Ne Zaman Kullanılır |
|---|---|
| `findByAd(String ad)` | Basit, tek koşullu sorgular için |
| `@Query(...)` | Karmaşık, çok koşullu veya özel sorgular için |

Metod adı uzamaya başladığında (`findByAdAndYasGreaterThanAndEmailContaining`) `@Query` çok daha okunabilir hale gelir.

---

## Native Query

JPQL yerine doğrudan SQL yazmak gerektiğinde `nativeQuery = true` parametresi kullanılır. Bu veritabanı bağımlılığı yaratır, bu yüzden zorunlu olmadıkça tercih edilmez.

```java
@Query(value = "SELECT * FROM ogrenciler WHERE yas > :yas", nativeQuery = true)
List<Ogrenci> nativeSorgu(@Param("yas") int yas);
```

---

## Güncelleme ve Silme Sorguları

JPQL sadece veri okumak için değil, güncellemek ve silmek için de kullanılabilir. Bu durumda `@Modifying` ve `@Transactional` anotasyonları eklenmesi gerekir.

```java
@Modifying
@Transactional
@Query("UPDATE Ogrenci o SET o.email = :email WHERE o.id = :id")
int emailGuncelle(@Param("id") Long id, @Param("email") String email);

@Modifying
@Transactional
@Query("DELETE FROM Ogrenci o WHERE o.yas < :yas")
void kucukYaslilariSil(@Param("yas") int yas);
```

---

## Özet

```
Basit sorgu      → Metod adı yeterli   (findByAd, findByYasGreaterThan)
Karmaşık sorgu   → @Query ile JPQL     (JOIN, çok koşul, projeksiyon)
DB'ye özel SQL   → nativeQuery = true  (zorunlu durumlarda)
Güncelleme/Silme → @Modifying + @Query
```
