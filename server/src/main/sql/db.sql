
 CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code_client VARCHAR(4) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
    code_article VARCHAR(4) PRIMARY KEY,
    article_name VARCHAR(50) NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    description TEXT,
    available_qty SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS warehouses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    available_qty SMALLINT NOT NULL,
    code_article VARCHAR(4) NOT NULL,
    FOREIGN KEY (code_article) REFERENCES articles(code_article)
);

CREATE TABLE IF NOT EXISTS purchases (
    operation_code INT AUTO_INCREMENT PRIMARY KEY,
    code_client VARCHAR(4) NOT NULL,
    code_article VARCHAR(4) NOT NULL,
    payment_type VARCHAR(20),
    purchase_qty SMALLINT NOT NULL,
    FOREIGN KEY (code_client) REFERENCES clients(code_client),
    FOREIGN KEY (code_article) REFERENCES articles(code_article)
);

CREATE TABLE IF NOT EXISTS carts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code_client VARCHAR(4) NOT NULL,
    payment_method VARCHAR(50),
    article_qty SMALLINT,
    code_article VARCHAR(4),
    FOREIGN KEY (code_client) REFERENCES clients(code_client),
    FOREIGN KEY (code_article) REFERENCES articles(code_article)
);



-- CREATE VIEW Carrello AS
-- SELECT
--     C.Cognome,
--     C.Nome,
--     A.NomeArticolo,
--     A.Prezzo AS PrezzoUnitario,
--     Acq.QuantitaAcquistata AS Quantita,
--     (A.Prezzo * Acq.QuantitaAcquistata) AS PrezzoTotale,
--     Acq.TipoPagamento
-- FROM Acquisti Acq
-- JOIN Clienti C ON Acq.CodiceCliente = C.CodiceCliente
-- JOIN Articoli A ON Acq.CodiceArticolo = A.CodiceArticolo;