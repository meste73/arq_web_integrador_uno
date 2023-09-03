SELECT c.*, SUM(rp.quantity*p.value) as amount
FROM client c 
JOIN receipt r ON r.client_id = c.client_id
JOIN receipt_product rp ON rp.receipt_id = r.receipt_id
JOIN product p ON p.product_id = rp.product_id
GROUP BY c.client_id, c.name, c.email
ORDER BY amount DESC;