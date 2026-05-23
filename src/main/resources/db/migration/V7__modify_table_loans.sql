ALTER TABLE loans
MODIFY COLUMN status ENUM('ACTIVE', 'EXPIRED', 'RETURNED');

ALTER TABLE loans
ADD COLUMN  actual_return_date DATETIME;

ALTER TABLE loans
RENAME COLUMN expiration_date TO expected_return_date;
