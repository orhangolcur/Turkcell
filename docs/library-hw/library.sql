-- KÜTÜPHANE SİSTEMİ VERİTABANI
-- ============================================================


-- DDL - TABLO OLUŞTURMA

CREATE TABLE KATEGORI (
    kategori_id   INT           PRIMARY KEY,
    kategori_adi  VARCHAR(100)  NOT NULL,
    aciklama      TEXT
);

CREATE TABLE YAZAR (
    yazar_id  INT          PRIMARY KEY,
    ad        VARCHAR(100) NOT NULL,
    soyad     VARCHAR(100) NOT NULL
);

CREATE TABLE KITAP (
    kitap_id     INT          PRIMARY KEY,
    isbn         VARCHAR(20)  NOT NULL,
    baslik       VARCHAR(255) NOT NULL,
    kategori_id  INT,
    yayin_yili   INT,
    FOREIGN KEY (kategori_id) REFERENCES KATEGORI(kategori_id)
);

CREATE TABLE KITAP_YAZAR (
    kitap_id  INT NOT NULL,
    yazar_id  INT NOT NULL,
    PRIMARY KEY (kitap_id, yazar_id),
    FOREIGN KEY (kitap_id) REFERENCES KITAP(kitap_id),
    FOREIGN KEY (yazar_id) REFERENCES YAZAR(yazar_id)
);

CREATE TABLE SUBE (
    sube_id   INT          PRIMARY KEY,
    sube_adi  VARCHAR(100) NOT NULL,
    adres     TEXT,
    telefon   VARCHAR(20)
);

CREATE TABLE KITAP_KOPYA (
    kopya_id  INT         PRIMARY KEY,
    kitap_id  INT         NOT NULL,
    sube_id   INT         NOT NULL,
    barkod    VARCHAR(50) NOT NULL,
    durum     VARCHAR(20) NOT NULL,
    FOREIGN KEY (kitap_id) REFERENCES KITAP(kitap_id),
    FOREIGN KEY (sube_id)  REFERENCES SUBE(sube_id)
);

CREATE TABLE OGRENCI (
    ogrenci_id    INT          PRIMARY KEY,
    ad            VARCHAR(100) NOT NULL,
    soyad         VARCHAR(100) NOT NULL,
    email         VARCHAR(150) NOT NULL,
    uyelik_tarihi DATE         NOT NULL
);

ALTER TABLE OGRENCI ADD COLUMN telefon VARCHAR(20);

CREATE TABLE GOREVLI (
    gorevli_id  INT          PRIMARY KEY,
    ad          VARCHAR(100) NOT NULL,
    soyad       VARCHAR(100) NOT NULL,
    rol         VARCHAR(50)  NOT NULL
);

CREATE TABLE REZERVASYON (
    rezervasyon_id     INT         PRIMARY KEY,
    ogrenci_id         INT         NOT NULL,
    kitap_id           INT         NOT NULL,
    rezervasyon_tarihi DATE        NOT NULL,
    durum              VARCHAR(20) NOT NULL,
    FOREIGN KEY (ogrenci_id) REFERENCES OGRENCI(ogrenci_id),
    FOREIGN KEY (kitap_id)   REFERENCES KITAP(kitap_id)
);

CREATE TABLE ODUNC_ISLEM (
    islem_id      INT         PRIMARY KEY,
    ogrenci_id    INT         NOT NULL,
    kopya_id      INT         NOT NULL,
    odunc_tarihi  DATE        NOT NULL,
    beklenen_iade DATE        NOT NULL,
    durum         VARCHAR(20) NOT NULL,
    FOREIGN KEY (ogrenci_id) REFERENCES OGRENCI(ogrenci_id),
    FOREIGN KEY (kopya_id)   REFERENCES KITAP_KOPYA(kopya_id)
);

CREATE TABLE IADE_ISLEM (
    iade_id       INT         PRIMARY KEY,
    islem_id      INT         NOT NULL,
    gorevli_id    INT         NOT NULL,
    iade_tarihi   DATE        NOT NULL,
    kitap_durumu  VARCHAR(20) NOT NULL,
    FOREIGN KEY (islem_id)   REFERENCES ODUNC_ISLEM(islem_id),
    FOREIGN KEY (gorevli_id) REFERENCES GOREVLI(gorevli_id)
);

CREATE TABLE CEZA_KAYDI (
    ceza_id       INT          PRIMARY KEY,
    ogrenci_id    INT          NOT NULL,
    islem_id      INT          NOT NULL,
    miktar        DECIMAL(8,2) NOT NULL,
    odeme_durumu  VARCHAR(20)  NOT NULL,
    ceza_tarihi   DATE         NOT NULL,
    FOREIGN KEY (ogrenci_id) REFERENCES OGRENCI(ogrenci_id),
    FOREIGN KEY (islem_id)   REFERENCES ODUNC_ISLEM(islem_id)
);


-- DML - VERİ İŞLEMLERİ

-- KATEGORI

INSERT INTO KATEGORI VALUES (1, 'Roman',      'Kurmaca edebi eserler');
INSERT INTO KATEGORI VALUES (2, 'Bilim',      'Bilim ve teknoloji kitaplari');
INSERT INTO KATEGORI VALUES (3, 'Tarih',      'Tarihsel olaylar ve biyografiler');
INSERT INTO KATEGORI VALUES (4, 'Felsefe',    'Felsefi dusunce ve akimlar');
INSERT INTO KATEGORI VALUES (5, 'Bilgisayar', 'Yazilim ve programlama kitaplari');

SELECT * FROM KATEGORI;
SELECT * FROM KATEGORI WHERE kategori_id = 1;

-- ORDER BY: Kategorileri ada göre A-Z sırala
SELECT * FROM KATEGORI ORDER BY kategori_adi ASC;

UPDATE KATEGORI SET aciklama = 'Turk ve dunya edebiyati' WHERE kategori_id = 1;

DELETE FROM KATEGORI WHERE kategori_id = 5;


-- YAZAR

INSERT INTO YAZAR VALUES (1, 'Orhan',      'Pamuk');
INSERT INTO YAZAR VALUES (2, 'Sabahattin', 'Ali');
INSERT INTO YAZAR VALUES (3, 'Yuval Noah', 'Harari');
INSERT INTO YAZAR VALUES (4, 'George',     'Orwell');
INSERT INTO YAZAR VALUES (5, 'Franz',      'Kafka');

SELECT * FROM YAZAR;
SELECT * FROM YAZAR WHERE yazar_id = 3;

SELECT * FROM YAZAR WHERE soyad LIKE '%a%';

SELECT * FROM YAZAR ORDER BY soyad ASC;

UPDATE YAZAR SET soyad = 'PAMUK' WHERE yazar_id = 1;

DELETE FROM KITAP_YAZAR WHERE yazar_id = 5;
DELETE FROM YAZAR WHERE yazar_id = 5;


-- KITAP

INSERT INTO KITAP VALUES (1, '9789750800011', 'Kar',               1, 2002);
INSERT INTO KITAP VALUES (2, '9789750800022', 'Icimizdeki Seytan', 1, 1940);
INSERT INTO KITAP VALUES (3, '9789750800033', 'Sapiens',           2, 2011);
INSERT INTO KITAP VALUES (4, '9789750800044', '1984',              1, 1949);
INSERT INTO KITAP VALUES (5, '9789750800055', 'Donusum',           4, 1915);

SELECT * FROM KITAP;
SELECT * FROM KITAP WHERE yayin_yili > 2000;

SELECT * FROM KITAP ORDER BY yayin_yili ASC;

SELECT * FROM KITAP WHERE baslik LIKE 'S%';

-- Aggregate Functions
SELECT COUNT(*) FROM KITAP;
SELECT MIN(yayin_yili) FROM KITAP;
SELECT MAX(yayin_yili) FROM KITAP;

UPDATE KITAP SET kategori_id = 3 WHERE kitap_id = 3;

DELETE FROM KITAP_YAZAR WHERE kitap_id = 5;
DELETE FROM KITAP WHERE kitap_id = 5;


-- KITAP_YAZAR

INSERT INTO KITAP_YAZAR VALUES (1, 1);
INSERT INTO KITAP_YAZAR VALUES (2, 2);
INSERT INTO KITAP_YAZAR VALUES (3, 3);
INSERT INTO KITAP_YAZAR VALUES (4, 4);

SELECT * FROM KITAP_YAZAR;
SELECT * FROM KITAP_YAZAR WHERE kitap_id = 1;

UPDATE KITAP_YAZAR SET yazar_id = 2 WHERE kitap_id = 2;


-- SUBE

INSERT INTO SUBE VALUES (1, 'Merkez Kutuphane',   'Universite Cad. No:1', '0222 111 1111');
INSERT INTO SUBE VALUES (2, 'Muhendislik Subesi', 'Kampus B Blok',        '0222 111 1112');
INSERT INTO SUBE VALUES (3, 'Tip Subesi',         'Hastane Binasi',       '0222 111 1113');
INSERT INTO SUBE VALUES (4, 'Egitim Subesi',      'Kampus C Blok',        '0222 111 1114');
INSERT INTO SUBE VALUES (5, 'Hukuk Subesi',       'Hukuk Fakultesi',      '0222 111 1115');

SELECT * FROM SUBE;
SELECT * FROM SUBE WHERE sube_id = 1;

-- LIKE: Adında 'Subesi' geçen şubeler
SELECT * FROM SUBE WHERE sube_adi LIKE '%Subesi%';

-- ORDER BY: Şubeleri ada göre sırala
SELECT * FROM SUBE ORDER BY sube_adi ASC;

-- Aggregate: Toplam şube sayısı
SELECT COUNT(*) FROM SUBE;

UPDATE SUBE SET telefon = '0222 222 2222' WHERE sube_id = 1;

DELETE FROM SUBE WHERE sube_id = 5;


-- KITAP_KOPYA

INSERT INTO KITAP_KOPYA VALUES (1, 1, 1, 'BRK-0001', 'rafta');
INSERT INTO KITAP_KOPYA VALUES (2, 1, 2, 'BRK-0002', 'oduncte');
INSERT INTO KITAP_KOPYA VALUES (3, 2, 1, 'BRK-0003', 'rafta');
INSERT INTO KITAP_KOPYA VALUES (4, 3, 1, 'BRK-0004', 'rafta');
INSERT INTO KITAP_KOPYA VALUES (5, 4, 2, 'BRK-0005', 'oduncte');

SELECT * FROM KITAP_KOPYA;
SELECT * FROM KITAP_KOPYA WHERE durum = 'rafta';

-- ORDER BY: Kopyaları barkoda göre sırala
SELECT * FROM KITAP_KOPYA ORDER BY barkod ASC;

-- Aggregate: Rafta bekleyen kopya sayısı
SELECT COUNT(*) FROM KITAP_KOPYA WHERE durum = 'rafta';

UPDATE KITAP_KOPYA SET durum = 'rafta' WHERE kopya_id = 2;

DELETE FROM KITAP_KOPYA WHERE kopya_id = 5;


-- OGRENCI

INSERT INTO OGRENCI VALUES (1, 'Ayse',   'Kaya',   'ayse.kaya@uni.edu.tr',    '2023-09-01');
INSERT INTO OGRENCI VALUES (2, 'Mehmet', 'Demir',  'mehmet.demir@uni.edu.tr', '2022-09-01');
INSERT INTO OGRENCI VALUES (3, 'Zeynep', 'Celik',  'zeynep.celik@uni.edu.tr', '2023-09-01');
INSERT INTO OGRENCI VALUES (4, 'Emre',   'Yildiz', 'emre.yildiz@uni.edu.tr',  '2021-09-01');
INSERT INTO OGRENCI VALUES (5, 'Selin',  'Arslan', 'selin.arslan@uni.edu.tr', '2024-09-01');

SELECT * FROM OGRENCI;
SELECT * FROM OGRENCI WHERE ogrenci_id = 2;

-- ORDER BY: Öğrencileri üyelik tarihine göre en yeniden eskiye sırala
SELECT * FROM OGRENCI ORDER BY uyelik_tarihi DESC;

-- LIKE: Adı 'A' ile başlayan öğrenciler
SELECT * FROM OGRENCI WHERE ad LIKE 'A%';

-- ILIKE: Büyük/küçük harf duyarsız arama (adı 'a' ile başlayanlar)
SELECT * FROM OGRENCI WHERE ad ILIKE 'a%';

-- Aggregate: Toplam öğrenci sayısı
SELECT COUNT(*) FROM OGRENCI;

UPDATE OGRENCI SET email = 'ayse.kaya2@uni.edu.tr' WHERE ogrenci_id = 1;

UPDATE OGRENCI SET telefon = 'bilinmiyor' WHERE telefon IS NULL;
ALTER TABLE OGRENCI ALTER COLUMN telefon SET NOT NULL;

DELETE FROM OGRENCI WHERE ogrenci_id = 5;


-- GOREVLI

INSERT INTO GOREVLI VALUES (1, 'Ali',   'Yilmaz', 'Kutuphaneci');
INSERT INTO GOREVLI VALUES (2, 'Fatma', 'Sahin',  'Yonetici');
INSERT INTO GOREVLI VALUES (3, 'Hasan', 'Koc',    'Kutuphaneci');
INSERT INTO GOREVLI VALUES (4, 'Merve', 'Kurt',   'Kutuphaneci');
INSERT INTO GOREVLI VALUES (5, 'Burak', 'Aydin',  'Teknisyen');

SELECT * FROM GOREVLI;
SELECT * FROM GOREVLI WHERE rol = 'Kutuphaneci';

-- ORDER BY: Görevlileri soyada göre A-Z sırala
SELECT * FROM GOREVLI ORDER BY soyad ASC;

-- LIKE: Adı 'H' ile başlayan görevliler
SELECT * FROM GOREVLI WHERE ad LIKE 'H%';

-- Aggregate: Toplam görevli sayısı
SELECT COUNT(*) FROM GOREVLI;

UPDATE GOREVLI SET rol = 'Kidemli Kutuphaneci' WHERE gorevli_id = 1;

DELETE FROM GOREVLI WHERE gorevli_id = 5;


-- REZERVASYON

INSERT INTO REZERVASYON VALUES (1, 1, 4, '2024-11-01', 'bekliyor');
INSERT INTO REZERVASYON VALUES (2, 2, 3, '2024-11-03', 'bekliyor');
INSERT INTO REZERVASYON VALUES (3, 3, 1, '2024-11-05', 'tamamlandi');
INSERT INTO REZERVASYON VALUES (4, 4, 2, '2024-11-07', 'iptal');
INSERT INTO REZERVASYON VALUES (5, 1, 3, '2024-11-10', 'bekliyor');

SELECT * FROM REZERVASYON;
SELECT * FROM REZERVASYON WHERE durum = 'bekliyor';

-- ORDER BY: Rezervasyonları tarihe göre en yeniden eskiye sırala
SELECT * FROM REZERVASYON ORDER BY rezervasyon_tarihi DESC;

-- Aggregate: Bekleyen rezervasyon sayısı
SELECT COUNT(*) FROM REZERVASYON WHERE durum = 'bekliyor';

UPDATE REZERVASYON SET durum = 'tamamlandi' WHERE rezervasyon_id = 1;

DELETE FROM REZERVASYON WHERE rezervasyon_id = 4;


-- ODUNC_ISLEM

INSERT INTO ODUNC_ISLEM VALUES (1, 1, 2, '2024-11-01', '2024-11-15', 'aktif');
INSERT INTO ODUNC_ISLEM VALUES (2, 2, 5, '2024-11-02', '2024-11-16', 'aktif');
INSERT INTO ODUNC_ISLEM VALUES (3, 3, 3, '2024-10-01', '2024-10-15', 'iade edildi');
INSERT INTO ODUNC_ISLEM VALUES (4, 4, 4, '2024-10-10', '2024-10-24', 'iade edildi');
INSERT INTO ODUNC_ISLEM VALUES (5, 1, 1, '2024-09-01', '2024-09-15', 'gecikti');

SELECT * FROM ODUNC_ISLEM;
SELECT * FROM ODUNC_ISLEM WHERE durum = 'aktif';

-- ORDER BY: İşlemleri ödünç tarihine göre en yeniden eskiye sırala
SELECT * FROM ODUNC_ISLEM ORDER BY odunc_tarihi DESC;

-- Aggregate: Toplam işlem sayısı
SELECT COUNT(*) FROM ODUNC_ISLEM;

UPDATE ODUNC_ISLEM SET durum = 'iade edildi' WHERE islem_id = 1;

-- DELETE düzeltmesi: Önce bağlı CEZA_KAYDI kaydını sil, sonra ODUNC_ISLEM'i sil
DELETE FROM CEZA_KAYDI WHERE islem_id = 5;
DELETE FROM ODUNC_ISLEM WHERE islem_id = 5;


-- IADE_ISLEM

INSERT INTO IADE_ISLEM VALUES (1, 3, 1, '2024-10-14', 'iyi');
INSERT INTO IADE_ISLEM VALUES (2, 4, 2, '2024-10-23', 'iyi');
INSERT INTO IADE_ISLEM VALUES (3, 1, 3, '2024-11-14', 'hasarli');
INSERT INTO IADE_ISLEM VALUES (4, 2, 1, '2024-11-15', 'iyi');
INSERT INTO IADE_ISLEM VALUES (5, 4, 2, '2024-09-20', 'eksik sayfa');

SELECT * FROM IADE_ISLEM;
SELECT * FROM IADE_ISLEM WHERE kitap_durumu = 'iyi';

-- ORDER BY: İade işlemlerini tarihe göre en yeniden eskiye sırala
SELECT * FROM IADE_ISLEM ORDER BY iade_tarihi DESC;

-- LIKE: Durumu 'iyi' ile tam eşleşenler (örnek _ kullanımı)
SELECT * FROM IADE_ISLEM WHERE kitap_durumu LIKE 'iyi';

-- Aggregate: Toplam iade sayısı
SELECT COUNT(*) FROM IADE_ISLEM;

UPDATE IADE_ISLEM SET kitap_durumu = 'iyi' WHERE iade_id = 3;

DELETE FROM IADE_ISLEM WHERE iade_id = 5;


-- CEZA_KAYDI

INSERT INTO CEZA_KAYDI VALUES (1, 1, 5, 25.00, 'odenmedi', '2024-09-21');
INSERT INTO CEZA_KAYDI VALUES (2, 2, 2, 10.00, 'odendi',   '2024-11-17');
INSERT INTO CEZA_KAYDI VALUES (3, 3, 3, 50.00, 'odenmedi', '2024-11-15');
INSERT INTO CEZA_KAYDI VALUES (4, 4, 4,  5.00, 'odendi',   '2024-10-25');
INSERT INTO CEZA_KAYDI VALUES (5, 1, 1, 15.00, 'odenmedi', '2024-11-16');

SELECT * FROM CEZA_KAYDI;
SELECT * FROM CEZA_KAYDI WHERE odeme_durumu = 'odenmedi';

-- ORDER BY: Cezaları miktara göre büyükten küçüğe sırala
SELECT * FROM CEZA_KAYDI ORDER BY miktar DESC;

-- LIKE: Ödeme durumu 'odenmedi' olanlar (ILIKE ile büyük/küçük harf duyarsız)
SELECT * FROM CEZA_KAYDI WHERE odeme_durumu ILIKE 'odenmedi';

-- Aggregate Functions
SELECT COUNT(*) FROM CEZA_KAYDI;                                    -- Toplam ceza kaydı sayısı
SELECT COUNT(*) FROM CEZA_KAYDI WHERE odeme_durumu = 'odenmedi';   -- Ödenmemiş ceza sayısı
SELECT SUM(miktar) FROM CEZA_KAYDI;                                 -- Tüm cezaların toplamı
SELECT SUM(miktar) FROM CEZA_KAYDI WHERE odeme_durumu = 'odenmedi';-- Ödenmemiş cezaların toplamı
SELECT MAX(miktar) FROM CEZA_KAYDI;                                 -- En yüksek ceza
SELECT MIN(miktar) FROM CEZA_KAYDI;                                 -- En düşük ceza
SELECT AVG(miktar) FROM CEZA_KAYDI;                                 -- Ortalama ceza miktarı

UPDATE CEZA_KAYDI SET odeme_durumu = 'odendi' WHERE ceza_id = 1;

DELETE FROM CEZA_KAYDI WHERE ceza_id = 5;
