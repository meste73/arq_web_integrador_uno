SELECT rec.product_id, rec.name, SUM(rec.quantity) AS "Cantidad", pro.value "Valor", (SUM(rec.quantity) * pro.value) AS "Valor total"
FROM receipt_product rec 
JOIN product pro ON rec.product_id = pro.product_id
GROUP BY rec.product_id, pro.value, pro.name
ORDER BY "Valor total" DESC
LIMIT 1;