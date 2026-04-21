-- SQL -> Structured Query Lahguage --
-- Yapılandırılmış Sorgu Dili --

-- DDL --
-- Data Definition Language --
CREATE DATABASE eticaret;

CREATE TABLE users(
	-- isim tür(SINIR) özel-durumlar --
	id SERIAL PRIMARY KEY, -- SERIAL, otomatik artan kolonlara yazılır.
	name VARCHAR(100) NOT NULL, --varsayılan olarak her metinsel alan nullabledır.
	surname VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE carts(
	id SERIAL PRIMARY KEY,
	user_id INTEGER UNIQUE NOT NULL, -- Her kullanıcının 1 sepeti olur.O yüzden unique ekleyerek one-to-one ilişki kurarız.
	FOREIGN KEY (user_id) REFERENCES users(id) -- foreign key bu şekilde tanımlanır. Hangi tabloya referans vereceksek yazarız.Diğer tablonun PK'sı yazılır.
);

ALTER TABLE users ADD COLUMN gsm VARCHAR(20); -- Tabloyu bu şekilde güncelleyebiliriz.Yeni eklenen alanlar ya DEFAULT ya da NULLABLE olur.
-- Eğer tabloyu güncellemeden önce tabloda veri varsa ve biz yeni ekleyeceğimiz alanı nullable yapmak istiyorsak önce alanı eklemeli, daha sonra ise 
-- o alanı default bi değerle manuel olarak doldurmalı(çok veri varsa nullable kalabilir) ve aşağıdaki komutla NULLABLE yapmalıyız.

ALTER TABLE users ALTER COLUMN gsm SET NOT NULL; -- Kolon için güncelleme yaptık.

-- DML --
-- Data Manipulation Language --

-- Insert 
INSERT INTO users(name, surname, email, password, gsm) 
VALUES ('Orhan', 'Gölcür', 'orhan1@gmail.com', '123', '+90');
--

-- UPDATE
UPDATE users SET gsm = '+90111', surname = 'Kara' 
WHERE id = 3; --Update ve Delete işlemlerinde WHERE şartı yazmazsak tüm veri üzerinde işlem yaparız. O yüzden dikkat etmeliyiz!! 
--

-- DELETE
DELETE FROM users 
WHERE id = 4; -- Silmek istediğimiz veriyi başka bir tabloda referans alan bir veri varsa direkt olarak silemeyiz.
-- (örn: kullanıcının cart tablosunda verisi varsa) Önce bağlı verileri ya silmeliyiz ya da null atamalıyız. Senaryoya göre seçilir.

-- SELECT [istenilen_kolonlar] FROM [tablo_adı]
SELECT * FROM users; -- * -> ALL columns

SELECT id,name FROM users;

SELECT * FROM users WHERE name = 'Orhan'; 

-- ASC -> Küçükten büyüğe, a-z
-- DESC -> Büyükten Küçüğe, z-a
SELECT * FROM users ORDER BY register_date DESC;

ALTER TABLE users ADD COLUMN age INTEGER;

UPDATE users SET age=24 WHERE id = 3;

SELECT * FROM users ORDER BY age DESC;

-- Aggregate Functions
-- Bir tablodaki veri sayısı
SELECT COUNT(*) FROM users;

-- Bir kolondaki min değeri al
SELECT MIN(age) FROM users;

-- Bir kolondaki max değeri al
SELECT MAX(age) FROM users;

-- Bir kolondaki ortalama değeri al
SELECT AVG(age) FROM users;

-- Bir kolondaki tüm kolonları topla
SELECT SUM(age) FROM users;

-- LIKE
SELECT * FROM users WHERE name LIKE 'O%'; -- %'yi koyduğumuz yerdeki yazanlar önemli değil diyoruz. yani burada O ile başlasın dedik.
SELECT * FROM users WHERE name LIKE '%a%'; -- içinde a geçenler
SELECT * FROM users WHERE name LIKE '%an'; -- sonunda an olanlar

-- _ sembolü
SELECT * FROM users WHERE name LIKE '_r%'; -- r harfinden önce sadece 1 tane eleman olacak

SELECT * FROM users WHERE LOWER(name) LIKE LOWER('o%'); -- büyük küçük hassasiyetine takılmamak için LOWER yaparız 

-- ILIKE
SELECT * FROM users WHERE name ILIKE 'o%'; -- ILIKE çoğu teknolojide bulunmaz.Yukarıda LOWER ile yaptığımızı burada ILIKE ile otomatik yaparız
