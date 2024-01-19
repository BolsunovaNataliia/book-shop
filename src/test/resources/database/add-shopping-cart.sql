-- Insert categories
INSERT INTO categories (id, name, description, is_deleted) VALUES (3, 'History', 'About historical events of the past', false);

-- Insert books
INSERT INTO books (id, title, author, isbn, price, description, cover_image, is_deleted) VALUES (4, 'A short course in the history of Ukraine', 'Y. Hrytsak', '234-1-235-6-1', 1000, 'About Ukrainian history', 'Cover image 1', false);

-- Insert book_category relations
INSERT INTO books_categories (book_id, category_id) VALUES (4, 3);

-- Insert users
INSERT INTO users (id, email, password, first_name, last_name, shipping_address, is_deleted) VALUES (4, 'john@gmail.com', '$2a$10$Y4PPXA2tkW1VGLBSDYEtFuQoGFZQVBolKlp25x6KIBrHupP53dzWm', 'John', 'Fisher', 'City Street 1', false);

-- Insert shopping_carts
INSERT INTO shopping_carts (id, user_id, is_deleted) VALUES (2, 4, false);

-- Insert cart_items
INSERT INTO cart_items (id, shopping_cart_id, book_id, quantity, is_deleted) VALUES (2, 2, 4, 2, false);