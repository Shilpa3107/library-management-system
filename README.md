## 📚 Digital Library Book Management System
This is a Spring Boot-based Digital Library Book Management System that allows librarians to efficiently manage book records. The system provides RESTful APIs to add, view, search, update, and delete books.
##  Features
- Add a new book with details like title, author, genre, and availability status
- View all books with their details
- Search for books by ID or title
- Update book details
- Delete a book from the catalog
- Gracefully shut down the application
## 🛠️ Tech Stack
- Java (Version 21)
- Spring Boot
- Gradle
- H2 Database (in-memory)
- Thymeleaf (for UI)
## Installation and Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/Shilpa3107/library-management-system.git
   cd library-management-system
   cd library
   ```
2. Build and Run:
   ```bash
     gradlew clean build
     gradlew bootRun
   ```
3. Access the Application:
  #### Frontend UI: http://localhost:8080/books
## API Endpoints
1.  Add a Book
      ```bash
         curl -X POST http://localhost:8080/api/books \
        -H "Content-Type: application/json" \
        -d '{
             "title": "The Catcher in the Rye",
             "author": "J.D. Salinger",
             "genre": "Fiction",
             "status": "AVAILABLE"
            }'
         ```
2. View All Books
   ```bash
      curl -X GET http://localhost:8080/api/books
   ```
3. Search Book
   - By ID: GET /api/books/search?id=1
   - By Title: GET /api/books/search?title=Book Title
     ```bash
        curl -X GET "http://localhost:8080/api/books/search?title=The Catcher in the Rye"
     ```
4. Update a Book
    ```bash
       curl -X PUT http://localhost:8080/api/books/1 \
       -H "Content-Type: application/json" \
      -d '{
         "title": "New Title",
         "author": "New Author",
         "genre": "New Genre",
        "status": "AVAILABLE"
         }'
    ```
5. Delete a Book
   ```bash
      curl -X DELETE http://localhost:8080/api/books/1
   ```
6. Shutdown Application
    ```bash
       curl -X POST http://localhost:8080/api/books/shutdown
    ```

### 🏗️ Project Structure
```plaintext
📂 src
├── 📂 main
│   ├── 📂 java
│   │   ├── 📂 com.library
│   │   │   ├── 📂 controller
│   │   │   ├── 📂 model
│   │   │   ├── 📂 repository
│   │   │   ├── 📂 service
│   │   │   └── LibraryManagementApplication.java
│   ├── 📂 resources
│   │   ├── 📂 templates
│   │   └── 📂 static
├── 📂 test
```

### Validation Rules
- Book ID should be unique
- Title and Author should not be empty
- Status should be either AVAILABLE or CHECKED_OUT

### Challenges and Improvements
##### Challenges Faced
- Setting up the Thymeleaf template
- Handling data binding for enum values

##### Future Improvements
- Add pagination for large datasets
- Add user authentication and authorization
    
### How to Run Tests
Run unit tests using Gradle:
```cmd
    gradlew test
```
### Assumptions
- Books are identified by a unique numeric ID
- Status is stored as an enum (AVAILABLE, CHECKED_OUT)

### Contributing
- Fork the repository
- Create a new branch (feature/new-feature)
- Commit your changes
- Create a Pull Request


    
