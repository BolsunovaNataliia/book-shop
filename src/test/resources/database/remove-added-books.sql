-- Delete book_category relations
DELETE FROM books_categories WHERE book_id = 1;
DELETE FROM books_categories WHERE book_id = 2;
DELETE FROM books_categories WHERE book_id = 3;

-- Delete books
DELETE FROM books WHERE id = 1;
DELETE FROM books WHERE id = 2;
DELETE FROM books WHERE id = 3;

-- Delete categories
DELETE FROM categories WHERE id = 1;
DELETE FROM categories WHERE id = 2;
DELETE FROM categories WHERE id = 3;
