-- Insert categories
INSERT INTO categories (id, name, description, is_deleted) VALUES (1, 'History', 'About historical events of the past', false);
INSERT INTO categories (id, name, description, is_deleted) VALUES (2, 'Fantasy', 'About fictional fantastic events', false);
INSERT INTO categories (id, name, description, is_deleted) VALUES (3, 'Detective', 'About detective investigations', false);

-- Insert books
INSERT INTO books (id, title, author, isbn, price, description, cover_image, is_deleted) VALUES (1, 'A short course in the history of Ukraine', 'Y. Hrytsak', '234-1-235-6-1', 1000, 'About Ukrainian history', 'Cover image 1', false);
INSERT INTO books (id, title, author, isbn, price, description, cover_image, is_deleted) VALUES (2, 'The Hobbit', 'J.R.R. Tolkien', '234-1-244-6-7', 1100, 'About fantasy story', 'Cover image 2', false);
INSERT INTO books (id, title, author, isbn, price, description, cover_image, is_deleted) VALUES (3, 'The Girl with the Dragon Tattoo', 'Stieg Larsson', '111-1-235-1-9', 1200, 'About the history of detective research', 'Cover image 3', false);

-- Insert book_category relations
INSERT INTO books_categories (book_id, category_id) VALUES (1, 1);
INSERT INTO books_categories (book_id, category_id) VALUES (2, 2);
INSERT INTO books_categories (book_id, category_id) VALUES (3, 3);
