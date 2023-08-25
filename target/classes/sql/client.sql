CREATE TABLE IF NOT EXISTS client (
	client_id int,
	client_name varchar(500),
	email varchar(150),
	CONSTRAINT pk_client_id PRIMARY KEY (client_id)
)