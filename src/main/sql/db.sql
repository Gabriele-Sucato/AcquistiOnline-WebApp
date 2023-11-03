-- Tabella dei Clienti
CREATE TABLE Clienti (
    CodiceCliente INT PRIMARY KEY,
    Cognome VARCHAR(50),
    Nome VARCHAR(50),
    -- Altri campi dati del cliente, se necessario
);
 
-- Tabella degli Articoli
CREATE TABLE Articoli (
    CodiceArticolo INT PRIMARY KEY,
    NomeArticolo VARCHAR(100),
    Prezzo DECIMAL(10, 2),
    Descrizione TEXT,
    QuantitaDisponibile INT,

);
 
-- Tabella degli Acquisti
CREATE TABLE Acquisti (
    CodiceOperazione INT PRIMARY KEY,
    CodiceCliente INT,
    CodiceArticolo INT,
    TipoPagamento VARCHAR(20),
    QuantitaAcquistata INT,
    FOREIGN KEY (CodiceCliente) REFERENCES Clienti(CodiceCliente),
    FOREIGN KEY (CodiceArticolo) REFERENCES Articoli(CodiceArticolo)
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