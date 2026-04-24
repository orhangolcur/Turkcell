-- Alias -> Takma ad
SELECT * FROM customers c WHERE c.contact_name LIKE '%a%';


-- JOIN
SELECT * FROM orders o
INNER JOIN customers c
ON o.customer_id = c.customer_id;

SELECT * FROM orders o
RIGHT JOIN customers c
ON o.customer_id = c.customer_id;

SELECT * FROM orders o
FULL OUTER JOIN customers c
ON o.customer_id = c.customer_id;
--

INSERT INTO customers(customer_id, company_name, contact_name, contact_title, address, city, postal_code,country,phone,fax)
VALUES ('ORHAN', 'Deneme', 'Orhan Gölcür', 'Abc','Abc','Eskişehir','26170','Türkiye','+90', 'abc');

---
SELECT * FROM orders o
INNER JOIN employees e
ON o.employee_id = e.employee_id;
---

SELECT * FROM orders o
INNER JOIN customers c
ON o.customer_id = c.customer_id
INNER JOIN order_details od
ON o.order_id = od.order_id
INNER JOIN products p
ON od.product_id = p.product_id
WHERE od.quantity > 10
ORDER BY c.contact_name

-- GROUP BY
SELECT c.country, c.city, COUNT(*) FROM customers c
GROUP BY c.country, c.city;

SELECT c.country, c.city, COUNT(*) FROM customers c
GROUP BY c.country, c.city
ORDER BY COUNT(*) DESC
--


--
SELECT s.company_name, COUNT(*) FROM shippers s
INNER JOIN orders o
ON s.shipper_id = o.ship_via
GROUP BY s.shipper_id, s.company_name;

-- COUNT kullanımına dikkat !!
SELECT s.company_name, COUNT(o.order_id) FROM shippers s
LEFT JOIN orders o
ON s.shipper_id = o.ship_via
GROUP BY s.shipper_id, s.company_name
HAVING COUNT(o.order_id) > 250
ORDER BY COUNT(o.order_id) DESC;
--

-- Hangi müşteriler 10'dan fazla sipariş vermiş ?
SELECT c.contact_name, COUNT(*) AS total_orders FROM customers c
JOIN orders o -- INNER JOIN'i sadece JOIN ile yazabiliriz
ON o.customer_id = c.customer_id
GROUP BY c.customer_id, c.contact_name
HAVING COUNT(*) > 10 -- burada total_orders diyip karşılaştırma yapamayız. buraya dikkat !!!
ORDER BY total_orders DESC;
--


-- HW

-- Toplam cirosu 50k'dan büyük müşteriler
SELECT 
    c.contact_name,
    ROUND(SUM(od.unit_price * od.quantity)::numeric, 2) AS total_revenue
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_details od ON o.order_id = od.order_id
GROUP BY c.customer_id, c.contact_name
HAVING SUM(od.unit_price * od.quantity) > 50000
ORDER BY total_revenue DESC;


-- Her kategori için en az 5 farklı ürün satan kategoriler
SELECT
    c.category_name,
    COUNT(DISTINCT od.product_id) AS product_count
FROM categories c
JOIN products p ON c.category_id = p.category_id
JOIN order_details od ON p.product_id = od.product_id
GROUP BY c.category_id, c.category_name
HAVING COUNT(DISTINCT od.product_id) >= 5
ORDER BY product_count DESC;


-- Çalışan bazlı toplam satış tutarı (birim fiyat)
SELECT
    e.first_name || ' ' || e.last_name AS employee_name,
    ROUND(SUM(od.unit_price * od.quantity)::numeric, 2) AS total_sales
FROM employees e
JOIN orders o ON e.employee_id = o.employee_id
JOIN order_details od ON o.order_id = od.order_id
GROUP BY e.employee_id, e.first_name, e.last_name
ORDER BY total_sales DESC;



-- Sayfalama (Pagination)
SELECT * from products p
LIMIT 10
--
Select * from products p
LIMIT 10 OFFSET 5
--

-- LIMIT {sayfa_başı_element} OFFSET {(aktif_sayfa-1) * sayfa_başı_element}
Select * from products p
LIMIT 10 OFFSET 0

Select * from products p
LIMIT 10 OFFSET 10