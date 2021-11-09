CREATE TABLE ENCRYPTED_TRANSACTION (ENCODED_PAYLOAD BLOB NOT NULL, PAYLOAD_CODEC VARCHAR(50) NOT NULL, TIMESTAMP NUMBER(19), HASH BLOB NOT NULL, PRIMARY KEY (HASH));
CREATE TABLE ENCRYPTED_RAW_TRANSACTION (ENCRYPTED_KEY BLOB NOT NULL, ENCRYPTED_PAYLOAD BLOB NOT NULL, NONCE BLOB NOT NULL, SENDER BLOB NOT NULL, TIMESTAMP NUMBER(19), HASH BLOB NOT NULL, PRIMARY KEY (HASH));
CREATE TABLE PRIVACY_GROUP(ID BLOB NOT NULL, LOOKUP_ID BLOB NOT NULL, DATA BLOB NOT NULL, TIMESTAMP NUMBER(19), PRIMARY KEY (ID));
CREATE TABLE ST_TRANSACTION(ID NUMBER(19) NOT NULL PRIMARY KEY, PAYLOAD_CODEC VARCHAR(50) NOT NULL, HASH VARCHAR NOT NULL, PAYLOAD BLOB, PRIVACY_MODE NUMBER(10), TIMESTAMP NUMBER(19), VALIDATION_STAGE NUMBER(19));
CREATE TABLE ST_AFFECTED_TRANSACTION(ID NUMBER(19) NOT NULL PRIMARY KEY, AFFECTED_HASH VARCHAR NOT NULL, TXN_ID NUMBER(19) NOT NULL, CONSTRAINT FK_ST_AFFECTED_TRANSACTION_TXN_ID FOREIGN KEY (TXN_ID) REFERENCES ST_TRANSACTION (ID));
CREATE INDEX IF NOT EXISTS ST_TRANSACTION_VALSTG ON ST_TRANSACTION(VALIDATION_STAGE);
