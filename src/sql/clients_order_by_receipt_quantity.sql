SELECT cli.*, COUNT(rec.receipt_id) AS "Cantidad facturas"
FROM client cli
JOIN receipt rec ON cli.client_id = rec.client_id
GROUP BY cli.client_id, cli.name, cli.email
ORDER BY "Cantidad facturas" DESC;