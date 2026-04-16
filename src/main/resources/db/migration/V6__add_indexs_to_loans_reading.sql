CREATE INDEX idx_loans_book ON loans(book_id);
CREATE INDEX idx_loans_user ON loans(user_id);

CREATE INDEX idx_reading_book ON reading(book_id);
CREATE INDEX idx_reading_user ON reading(user_id);