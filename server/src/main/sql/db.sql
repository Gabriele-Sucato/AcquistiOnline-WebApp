
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
    unit_price DECIMAL(8, 2) NOT NULL,
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

-- Insert a client
INSERT INTO clients (code_client, name, last_name) VALUES ('123', 'Gianni', 'Pippo');

-- Insert some articles
INSERT INTO articles (code_article, article_name, price, description, available_qty) VALUES 
  ('ABC', 'Article 1', 19.99, 'Description article 1', 50),
  ('DEF', 'Article 2', 29.99, 'Description article 2', 30),
  ('GHI', 'Article 3', 9.99, 'Description article 3', 100);

-- Insert some datas in carts
INSERT INTO carts (code_client, payment_method, article_qty, code_article) VALUES 
  ('123', 'Credit Card', 2, 'ABC'),
  ('123', 'Prepaid Card', 1, 'DEF');
  
-- Insert some datas in warehouses
INSERT INTO warehouses (available_qty, code_article) VALUES 
  (20, 'ABC'),
  (10, 'DEF'),
  (50, 'GHI');
  


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