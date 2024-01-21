# 📚📚📚  Book Shop 📚📚📚
> ### _It's a user-friendly Simple Online Bookshop project_
* People involved:
  * Shopper (User 🤓): Someone who looks at books, puts them in a basket (shopping cart), and buys them.
  * Manager (Admin 😎): Someone who arranges the books on the shelf and watches what gets bought.

🤓 User can log in or register, order books(book) in himself order.
In the order user point information about book (title, author, ISBN, price, description, cover image and category) and point its quantity.
User can add in and remove books from shopping cart, increase and decrease the quantity of books in the shopping cart.
User can see the list of your orders.
This order putting in user shopping cart.
User can look inside the basket, take a book out of the basket.

😎 The project can also be used by the administrator, who can add new books, remove books, change the price of the books as well as change order status and maintain the selling history of books in general.

___
## 🧩 Technology stack 🧩
* Programming Language: Java
* Application Configuration: Spring Boot, Spring, Lombok, Mapstruct, Maven
* Accessing Data: Spring Data JPA, Hibernate, MySQL
* Web Development: Spring MVC, Servlets, JSP, Tomcat
* Security: Spring Security, JWT
* Testing and Documentation: JUnit, Mockito, Swagger, TestContainers
* Version Control: Git
___
## 👽 Entities 👽

* 📕 Book: Represents a book available in the store.
* 🤓 Contains information about the registered user.
* 💼 Role: Represents the role of a user in the system (admin / user).
* 🎨 Category: Represents a category that a book can belong to.
* 🎁 Order: 
* 📚 Order item: 
* 🛒 Shopping cart:
* 📚 Cart item: 
---
## 🎯 Functionality 🎯

1. **🔑Auth Controller**:
```
- POST: /api/auth/register - User registration
- POST: /api/auth/login - User authentication
```
2. **📕Books Controller**: Managing authentication and user registration
```
- GET: /api/books - Retrieve book catalog
- GET: /api/books/{id} - Retrieve book details
- POST: /api/books - Create a new book
- PUT: /api/books/{id} - Update a specific book
- DELETE /api/books/{id} - Delete a specific book
- GET /api/books/search - Retrieve the book catalog based on search params
```
3. **🎨Category Controller**:
```
- POST: /api/categories - Create a new category
- GET: /api/categories - Retrieve all categories
- GET: /api/categories/{id} - Retrieve a specific category by its ID
- PUT: /api/categories/{id} - Update a specific category
- DELETE: /api/categories/{id} - Delete a specific category
- GET: /api/categories/{id}/books - Retrieve books by a specific category
```
4. **🎁Orders**:
```
- GET: /api/orders - Retrieve orders history
- POST: /api/orders - Create orders
- PATCH: /api/orders/{id} - Update orders status
- GET: /api/orders/{orderId}/items - Retrieve order items from specific order
- GET: /api/orders/{orderId}/items/{itemId} - Retrieve specific item from certain order
```
5. **🛒Cart**:
```
- GET: /api/cart - Retrieve all items in your shopping cart
- POST: /api/cart - Add items in user shopping cart
- PUT: /api/cart/cart-items/{itemId} - Update items quantity
- DELETE: /api/cart/cart-items/{itemId} - Delete items from user shopping cart
```
---
## 🎀 Features 🎀
* CustomGlobalExceptionHandler class
* Meaningful validation for existing DTO classes
* Pagination, Sorting, and Swagger to the existing controllers
* Liquibase to create a test database 
* Soft delete approach
---
## 👭Contributing👭
1. Ensure you have Java, Maven, and your preferred IDE installed.
2. Clone repository: clone from the console with the command: git clone <https://github.com/BolsunovaNataliia/book-shop>
3. Check customize the database settings in the application.properties file.
4. Create your feature branch (`git checkout -b your-branch`)
5. Build and run project: mvn spring-boot:run
6. Commit your changes (`git commit -am 'Add some feature'`)
7. Push to the branch (`git push origin your-branch`)
8. Create a new Pull Request
___
## 👨‍🚀 Postman 👨‍🚀
To send request to the endpoint using Postman click [here](https://web.postman.co/workspace/My-Workspace~142e05f2-4fe8-42c9-8bbd-58cbb915eb32/folder/18818461-b74d499c-2062-4712-93af-3933540cf3ce).
It's a collection of prepared requests where you can test functionality of the controllers.
Likely, you need to log in. After that, you must pass the token in the header authorization in the Bearer Token field.
___
## 🐳 Docker🐳
To run the Book Shop application in a Docker container follow these steps:

1. Clone repository: Clone the repository from the console with the command: git clone https://github.com/BolsunovaNataliia/book-shop
2. Check Docker Compose file: Ensure that the ***docker-compose.yml*** file in the root of the project is configured appropriately. You can customize environment variables, ports, and other settings in this file.
3. Build and run the project: Execute the following commands in the project root directory:
   ```bash
   docker-compose build
   docker-compose up
___
## ⌛ History of creating the project ⌛
The application was created in stages. First, the User entity was created. Then user authentication was added using a JWT token. 

Then the Book and Category entities were added. When the endpoints for these entities were implemented, there was a need to add the Role entity, add annotations that take Role into account. 

After adding the Cart and Cart Item entities, there were problems with their relationship with previously added entities. When these problems were successfully overcome, it was time to add the last entities to the application - Order and Order Item.

It was very difficult for Docker. There was a lot of information, but he did not want to work. Many options were considered and finally, after consulting experts, it worked.

In retrospect, while these challenges were demanding, the overall experience of creating this project was immensely rewarding. I look forward to applying the knowledge gained and tackling more complex projects in the future.
___
## 📈 Possible improvements 📈
1. Connecting a system for receiving and processing electronic payments, such as Stripe. This modern technological solution would allow users to pay for orders quickly and safely.
2. Integration of Google API services for user authentication. This would significantly reduce the time between the first login to the application and the start of using the full functionality, which should encourage new users.
3. To keep users informed about changes in their task status, should  implement a Telegram bot for efficient and rapid communication. This addition could provide users with a quick and convenient means of receiving information about their orders.
---
## 📚 Thanks for your attention!📚
![App Screenshot](https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExNDM1M2ZpN3hxd2ozdnZ3a3pvbG44Y2g2eW8zeWhzczJ4N2FnczdlcyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/AIJgRPlNDzDz5oAxZH/giphy.gif)