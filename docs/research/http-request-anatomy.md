# HTTP İsteğinin Anatomisi

## İçindekiler
1. [HTTP Nedir?](#http-nedir)
2. [HTTP İsteği Nedir?](#http-isteği-nedir)
3. [HTTP İsteğinin Yapısı](#http-isteğinin-yapısı)
   - [İstek Satırı (Request Line)](#1-i̇stek-satırı-request-line)
   - [Başlıklar (Headers)](#2-başlıklar-headers)
   - [Boş Satır](#3-boş-satır)
   - [Gövde (Body)](#4-gövde-body)
4. [HTTP Metotları](#http-metotları)
5. [HTTP Durum Kodları](#http-durum-kodları)
6. [Örnek HTTP İstekleri](#örnek-http-i̇stekleri)
7. [HTTP vs HTTPS](#http-vs-https)
8. [Özet](#özet)

---

## HTTP Nedir?

**HTTP** (HyperText Transfer Protocol), istemci (client) ile sunucu (server) arasında veri iletimi için kullanılan bir uygulama katmanı protokolüdür. 1991 yılında Tim Berners-Lee tarafından geliştirilmiş olup günümüz web altyapısının temel taşını oluşturmaktadır.

HTTP, **durumsuz (stateless)** bir protokoldür. Yani her istek birbirinden bağımsızdır; sunucu, önceki isteklere dair bilgi tutmaz.

```
İstemci (Tarayıcı)  ──── HTTP İsteği ────►  Sunucu
İstemci (Tarayıcı)  ◄─── HTTP Yanıtı ────  Sunucu
```

---

## HTTP İsteği Nedir?

Bir **HTTP isteği (HTTP Request)**, istemcinin sunucudan bir şey talep etmek için gönderdiği mesajdır. Bu talep şunları içerebilir:

- Bir web sayfasını görüntüleme
- Bir forma veri gönderme
- Bir dosya indirme
- Bir API'den veri çekme

---

## HTTP İsteğinin Yapısı

Bir HTTP isteği dört ana bölümden oluşur:

```
┌─────────────────────────────────────┐
│         İSTEK SATIRI                │  ← 1. Bölüm
│  GET /index.html HTTP/1.1           │
├─────────────────────────────────────┤
│         BAŞLIKLAR (HEADERS)         │  ← 2. Bölüm
│  Host: www.example.com              │
│  User-Agent: Mozilla/5.0            │
│  Accept: text/html                  │
│  ...                                │
├─────────────────────────────────────┤
│         BOŞ SATIR                   │  ← 3. Bölüm
│  (CRLF: \r\n)                       │
├─────────────────────────────────────┤
│         GÖVDE (BODY)                │  ← 4. Bölüm
│  (İsteğe bağlı veri)                │
└─────────────────────────────────────┘
```

---

### 1. İstek Satırı (Request Line)

İstek satırı, HTTP isteğinin ilk satırıdır ve üç temel bileşen içerir:

```
GET /sayfa/hakkinda.html HTTP/1.1
 │          │                │
 │          │                └── HTTP Versiyonu
 │          └─────────────────── İstek Yolu (URI/Path)
 └────────────────────────────── HTTP Metodu
```

#### Bileşenleri:

| Bileşen | Açıklama | Örnek |
|---|---|---|
| **HTTP Metodu** | Yapılacak işlemi belirtir | `GET`, `POST`, `PUT` |
| **URI / Path** | Hedef kaynağın adresi | `/api/kullanicilar` |
| **HTTP Versiyonu** | Kullanılan protokol sürümü | `HTTP/1.1`, `HTTP/2` |

---

### 2. Başlıklar (Headers)

Başlıklar, istek hakkında ek meta bilgi taşıyan anahtar-değer çiftleridir.

**Söz dizimi:**
```
Başlık-Adı: Değer
```

#### Sık Kullanılan İstek Başlıkları:

| Başlık | Açıklama | Örnek |
|---|---|---|
| `Host` | Hedef sunucunun adı | `Host: www.example.com` |
| `User-Agent` | İstemci yazılımı bilgisi | `User-Agent: Mozilla/5.0` |
| `Accept` | Kabul edilen içerik türleri | `Accept: application/json` |
| `Accept-Language` | Tercih edilen dil | `Accept-Language: tr-TR, en` |
| `Content-Type` | Gövdenin veri türü | `Content-Type: application/json` |
| `Content-Length` | Gövdenin byte cinsinden uzunluğu | `Content-Length: 348` |
| `Authorization` | Kimlik doğrulama bilgisi | `Authorization: Bearer <token>` |
| `Cookie` | Tarayıcı çerezleri | `Cookie: session_id=abc123` |
| `Connection` | Bağlantı durumu | `Connection: keep-alive` |
| `Cache-Control` | Önbellek yönetimi | `Cache-Control: no-cache` |
| `Referer` | İsteğin geldiği sayfa | `Referer: https://google.com` |

---

### 3. Boş Satır

Başlıklar bittikten sonra, başlıkları gövdeden ayıran **zorunlu bir boş satır** bulunur.  
Bu satır `\r\n` (Carriage Return + Line Feed) karakterlerinden oluşur.

> ⚠️ Bu boş satır olmadan HTTP isteği geçersiz sayılır.

---

### 4. Gövde (Body)

Gövde, sunucuya gönderilmek istenen veriyi içerir. Her istekte gövde bulunmaz:

- **GET** isteklerinde genellikle gövde **yoktur**.
- **POST**, **PUT**, **PATCH** isteklerinde gövde **bulunur**.

#### Yaygın Gövde Formatları:

**JSON:**
```json
{
  "ad": "Ahmet",
  "soyad": "Yılmaz",
  "email": "ahmet@example.com"
}
```

**Form Data:**
```
ad=Ahmet&soyad=Y%C4%B1lmaz&email=ahmet%40example.com
```

**XML:**
```xml
<kullanici>
  <ad>Ahmet</ad>
  <soyad>Yılmaz</soyad>
</kullanici>
```

---

## HTTP Metotları

HTTP metotları, istemcinin sunucuda ne yapmak istediğini belirtir.

| Metot | Açıklama | Gövde | Güvenli* | Idempotent** |
|---|---|---|---|---|
| `GET` | Veri okuma/getirme | Hayır | ✅ | ✅ |
| `POST` | Yeni veri oluşturma | Evet | ❌ | ❌ |
| `PUT` | Var olan veriyi tamamen güncelleme | Evet | ❌ | ✅ |
| `PATCH` | Var olan veriyi kısmen güncelleme | Evet | ❌ | ❌ |
| `DELETE` | Veri silme | Hayır | ❌ | ✅ |
| `HEAD` | Sadece başlıkları getirme (gövde yok) | Hayır | ✅ | ✅ |
| `OPTIONS` | Desteklenen metotları sorgulama | Hayır | ✅ | ✅ |

> *Güvenli (Safe): Sunucuda değişikliğe neden olmaz.  
> **Idempotent: Aynı istek birden fazla kez gönderilse de sonuç aynıdır.

---

## HTTP Durum Kodları

Sunucu, isteğe verdiği yanıtta bir **durum kodu** (status code) döner. Bu kodlar 3 haneli sayılardır ve 5 gruba ayrılır:

| Grup | Aralık | Anlamı |
|---|---|---|
| Bilgi | 1xx | İstek alındı, işlem devam ediyor |
| Başarı | 2xx | İstek başarıyla tamamlandı |
| Yönlendirme | 3xx | Farklı bir URL'ye yönlendirme gerekiyor |
| İstemci Hatası | 4xx | İstemci kaynaklı hata |
| Sunucu Hatası | 5xx | Sunucu kaynaklı hata |

#### Sık Karşılaşılan Durum Kodları:

| Kod | Ad | Açıklama |
|---|---|---|
| `200` | OK | İstek başarılı |
| `201` | Created | Yeni kaynak oluşturuldu |
| `204` | No Content | Başarılı, ama dönen içerik yok |
| `301` | Moved Permanently | Kalıcı yönlendirme |
| `304` | Not Modified | İçerik değişmedi (önbellekten kullan) |
| `400` | Bad Request | Hatalı istek sözdizimi |
| `401` | Unauthorized | Kimlik doğrulama gerekli |
| `403` | Forbidden | Erişim yasak |
| `404` | Not Found | Kaynak bulunamadı |
| `405` | Method Not Allowed | Bu metoda izin verilmiyor |
| `429` | Too Many Requests | Çok fazla istek gönderildi |
| `500` | Internal Server Error | Sunucu iç hatası |
| `503` | Service Unavailable | Sunucu geçici olarak kullanılamıyor |

---

## Örnek HTTP İstekleri

### Örnek 1: Basit GET İsteği

```http
GET /api/urunler HTTP/1.1
Host: api.example.com
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)
Accept: application/json
Accept-Language: tr-TR
Connection: keep-alive
```

### Örnek 2: JSON Gövdeli POST İsteği

```http
POST /api/kullanicilar HTTP/1.1
Host: api.example.com
Content-Type: application/json
Content-Length: 72
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Accept: application/json

{
  "ad": "Ahmet",
  "soyad": "Yılmaz",
  "email": "ahmet@example.com"
}
```

### Örnek 3: Form Verisi ile POST İsteği

```http
POST /giris HTTP/1.1
Host: www.example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 38

kullanici=ahmet&sifre=gizli123
```

### Örnek 4: Kaynak Güncelleme (PUT)

```http
PUT /api/kullanicilar/42 HTTP/1.1
Host: api.example.com
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Length: 45

{
  "email": "yeni-email@example.com"
}
```

### Örnek 5: Kaynak Silme (DELETE)

```http
DELETE /api/kullanicilar/42 HTTP/1.1
Host: api.example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## HTTP vs HTTPS

| Özellik | HTTP | HTTPS |
|---|---|---|
| Açılımı | HyperText Transfer Protocol | HTTP Secure |
| Port | 80 | 443 |
| Şifreleme | ❌ Yok | ✅ TLS/SSL ile şifreli |
| Güvenlik | Düşük | Yüksek |
| Sertifika | Gerekmiyor | SSL/TLS sertifikası gerekli |
| Kullanım | Eski/test sistemler | Tüm modern web siteleri |

> 💡 Günümüzde tüm web siteleri HTTPS kullanmalıdır. Tarayıcılar HTTP sitelerini "Güvenli Değil" olarak işaretler.

---

## Özet

Bir HTTP isteği şu dört bölümden oluşur:

```
1. İSTEK SATIRI  → [Metot] [URI] [HTTP Versiyonu]
2. BAŞLIKLAR     → Anahtar: Değer çiftleri (meta bilgi)
3. BOŞ SATIR     → Başlıkları gövdeden ayırır (\r\n)
4. GÖVDE         → Gönderilecek veri (POST/PUT için)
```

HTTP, web iletişiminin temel protokolüdür. İstemci-sunucu mimarisinde nasıl çalıştığını, başlıkların ne anlama geldiğini ve hangi metotların ne zaman kullanıldığını anlamak; web geliştirme, API entegrasyonu ve ağ güvenliği gibi birçok alanda kritik öneme sahiptir.

---

*Bu döküman, HTTP/1.1 standardı esas alınarak hazırlanmıştır.*  
*Referans: [RFC 7230 - HTTP/1.1](https://tools.ietf.org/html/rfc7230)*
