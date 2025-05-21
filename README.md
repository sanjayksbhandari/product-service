# 🛍️ Product Service API

This Spring Boot microservice provides a RESTful API for managing products in a catalog. It supports full CRUD operations, searching, filtering, and category-based queries.


## 🔗 Base URL

http://localhost:8080/api/products


## 📚 Endpoints

### 1. Get All Products

**GET** `api/products`

- **Description**: Retrieve all products.
- **Response**: `200 OK`
json
{
  "status": 200,
  "message": "Products retrieved successfully",
  "data": [ /* List of ProductDTO */ ]
}

2. Get Product by ID
GET /api/products/{id}

Path Variable: id (Long) – Product ID

Response: 200 OK or 404 Not Found

json
{
  "status": 200,
  "message": "Product retrieved successfully",
  "data": { /* ProductDTO */ }
}

3. Create a Product
POST /api/products (postman)

Body: ProductDTO (JSON)

Validation: Fields must be valid

Response: 201 Created

json
{
  "status": 201,
  "message": "Product created successfully",
  "data": { /* Created ProductDTO */ }
}

4. Update a Product
PUT /api/products/{id}

Path Variable: id (Long)

Body: ProductDTO (JSON)

Response: 200 OK

json
{
  "status": 200,
  "message": "Product updated successfully",
  "data": { /* Updated ProductDTO */ }
}

5. Delete a Product
DELETE /api/products/{id}

Path Variable: id (Long)

Response: 200 OK

json
{
  "status": 200,
  "message": "Product deleted successfully"
}

6. Get Products by Category
GET /api/products/category/{category}

Path Variable: category (String)

Response: 200 OK

json
{
  "status": 200,
  "message": "Products retrieved successfully",
  "data": [ /* List of ProductDTOs */ ]
}

7. Search Products by Name
GET /api/products/search?keyword={keyword}

Query Param: keyword (String)

Response: 200 OK

json
{
  "status": 200,
  "message": "Products retrieved successfully",
  "data": [ /* Matching ProductDTOs */ ]
}
8. Get Available Products
GET /api/products/available

Description: Retrieves only the products marked as available = true.

Response: 200 OK

json
{
  "status": 200,
  "message": "Available products retrieved successfully",
  "data": [ /* List of available ProductDTOs */ ]
}

## 📝 Notes
All responses are wrapped in a consistent ApiResponse<T> format.

Use application/json for all request and response bodies.


## 🛠️ Technologies
##Java 11

##Spring Boot 2.7.14

##Spring Web

##Spring Data JPA

##H2 Database

##Lombok (optional)

##SpringDoc OpenAPI (Swagger)

### 🚀 Author
Sanjay S Bhandari