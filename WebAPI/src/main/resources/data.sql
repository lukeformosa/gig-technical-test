-- Create the Accounts table if it doesn't exist
CREATE TABLE IF NOT EXISTS Accounts_Table (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    balance DECIMAL(15, 2)
);

-- Empty the Accounts table if it exists
TRUNCATE TABLE Accounts_Table;

-- Insert dummy data
INSERT INTO Accounts_Table (first_name, last_name, balance)
VALUES
    ('Luke', 'Formosa', 198.56),
    ('Anthony', 'Mifsud', 1452.03),
    ('Karl', 'Gauci', 570.94),
    ('Jane', 'Doe', 550.75);