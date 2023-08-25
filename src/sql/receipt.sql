CREATE TABLE IF NOT EXISTS receipt (
	receipt_id int,
	client_id int,
	CONSTRAINT pk_receipt_id PRIMARY KEY (receipt_id),
	CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client
)