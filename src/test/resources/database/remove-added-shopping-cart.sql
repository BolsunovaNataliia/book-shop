-- Delete cart_item
DELETE FROM cart_items WHERE id = 2;

-- Delete shopping_cart
DELETE FROM shopping_carts WHERE id = 2;

--Delete user
DELETE FROM users WHERE id = 4;

-- Delete book_category relations
DELETE FROM books_categories WHERE book_id = 4;

-- Delete books
DELETE FROM books WHERE id = 4;

-- Delete categories
DELETE FROM categories WHERE id = 3;
