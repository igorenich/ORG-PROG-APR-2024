CREATE TABLE Persons (
                         PersonID int UNIQUE AUTO_INCREMENT NOT NULL,
                         LastName varchar(255) NOT NULL,
                         FirstName varchar(255) NOT NULL,
                         Gender varchar(80) NOT NULL,
                         Title varchar(255) NOT NULL,
                         Nat varchar(255) NOT NULL
);

SELECT * from Persons;

INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) VALUES ('Jane', 'Doe', 'female', 'Mrs', 'US');