CREATE TABLE processo_dock.conta (
	idConta INT auto_increment NOT NULL,
	idPessoa INT NOT NULL,
	saldo DOUBLE NOT NULL,
	limiteSaqueDiario DOUBLE NOT NULL,
	flagAtivo BOOL NOT NULL,
	tipoConta INT NOT NULL,
	dataCriacao DATETIME NOT NULL,
	CONSTRAINT conta_PK PRIMARY KEY (idConta)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

