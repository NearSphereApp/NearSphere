# Mangalitsa Litsa

<hr></hr>


A Spring Boot application that provides a backend API for user authentication, password reset, favorites management, and retrieval of nearby places using the Google Places API. This project demonstrates common patterns for secure access (JWT-based authentication), user profile management, and external API integration.

<hr></hr>


## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Environment Variables](#environment-variables)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
    - [Testing the Application](#testing-the-application)
- [API Endpoints](#api-endpoints)
    - [Authentication Endpoints](#authentication-endpoints)
    - [User Endpoints](#user-endpoints)
    - [Favorites Endpoints](#favorites-endpoints)
    - [Place Endpoints](#place-endpoints)
    - [Password Reset Endpoints](#password-reset-endpoints)
- [Database Entities](#database-entities)
- [Further Enhancements](#further-enhancements)


<hr></hr>


## Overview

This application provides a RESTful API that supports:

1. **User registration** (sign up) and **login** (using JWT-based authentication).
2. **Fetching and managing** user favorites in a relational database (H2, by default).
3. **Password reset** capability via email token.
4. **Retrieving nearby places** from the [Google Places API](https://developers.google.com/maps/documentation/places/web-service/overview).

<hr></hr>


## Features

- **JWT Authentication**: Secures endpoints and ensures only authenticated users can access protected routes.
- **H2 In-Memory Database** (default for development).
- **RESTful Endpoints** for user management and places retrieval:
    - Sign up, login, update user profile
    - Password reset workflow
    - Favorites management
    - Integration with Google Places
- **Error Handling**: Global exception handling using a `HandlerExceptionResolver`.
- **Modular Services**: Each responsibility (e.g., favorites, users, place retrieval, authentication) is organized into its own service.

<hr></hr>


## Architecture

1. **Controllers**: Expose RESTful endpoints and delegate business logic to services.
2. **Services**: Encapsulate the application logic (favorites, users, place retrieval, authentication, etc.).
3. **Repositories**: Provide database CRUD operations (Spring Data JPA).
4. **Security**:
    - `JwtAuthenticationFilter` to validate JWT tokens for each request.
    - `SecurityConfiguration` sets up which endpoints are publicly available and which require authentication.
    - `AuthenticationService` manages credential validation and token issuance.
5. **Integration**:
    - `PlaceService` interacts with the Google Places API to fetch location data.
    - `EmailSender` sends emails for password reset tokens.

<hr></hr>


## Project Structure
```
src/main/java/com/mangalitsa/litsa
│
├─ configs/
│  ├─ ApplicationConfiguration.java
│  ├─ JwtAuthenticationFilter.java
│  └─ SecurityConfiguration.java
│
├─ controllers/
│  ├─ AuthController.java
│  ├─ FavouritesController.java
│  ├─ PasswordController.java
│  ├─ PlaceController.java
│  └─ UserController.java
│
├─ controllers/model/
│  ├─ AuthRequest.java
│  ├─ AuthResponse.java
│  ├─ ChangeUserInfoRequest.java
│  ├─ ConfirmPasswordResetRequest.java
│  ├─ FavouritesRequest.java
│  ├─ FavouritesResponse.java
│  ├─ NewUserRequest.java
│  ├─ PasswordResetRequest.java
│  ├─ PlacesRequest.java
│  └─ PlacesResponse.java
│
├─ model/
│  ├─ Favourites.java
│  ├─ Password.java
│  ├─ PasswordResetToken.java
│  └─ User.java
│
├─ repositories/
│  ├─ FavouritesRepository.java
│  ├─ PasswordRepository.java
│  ├─ PasswordResetTokenRepository.java
│  ├─ UserFavouritePlaceRepository.java
│  └─ UserRepository.java
│
├─ services/
│  ├─ auth/
│  │  ├─ AuthenticationService.java
│  │  ├─ CustomUserDetails.java
│  │  ├─ JwtService.java
│  │  └─ UserDetailsServiceImpl.java
│  ├─ googleapimodel/
│  │  ├─ DisplayName.java
│  │  ├─ Photo.java
│  │  ├─ Place.java
│  │  ├─ PlacesApiResponse.java
│  │  └─ RequestBody.java
│  ├─ EmailSender.java
│  ├─ FavouritesService.java
│  ├─ FavouritesServiceImpl.java
│  ├─ PasswordResetTokenService.java
│  ├─ PasswordResetTokenServiceImpl.java
│  ├─ PasswordService.java
│  ├─ PasswordServiceImpl.java
│  ├─ PlaceService.java
│  ├─ PlaceServiceImpl.java
│  ├─ UserService.java
│  └─ UserServiceImpl.java
│
└─ util/
   └─ KeywordMapper.java
   ```

<hr></hr>

## Technologies Used

- **Java 17** (or higher)
- **Spring Boot** (Web, Security, Data JPA)
- **H2 Database** (for development)
- **Maven** (build tool)
- **JWT** (JSON Web Token) for authentication
- **WebClient** for external API calls (Google Places)
- **Spring Mail** for password reset emails
- **PostgreSQL** 



<hr></hr>

## Getting Started

### Prerequisites

- **Java 17** (or higher)
- **Maven 3.8+**
- (Optional) **Docker** (if you plan to containerize)

### Environment Variables

In your `application.properties`  you need to define:
```
security.jwt.secret-key: # A Base64-encoded secret for JWT 
security.jwt.expiration-time: # JWT token expiration in milliseconds 
google.places.api.key: # Your Google Places API Key
```
Note: Keep your API keys and secrets out of version control, e.g., store them in environment variables or a secure credentials manager.

### Installation

1. **Clone the repository**:
```
git clone [https://github.com/NearSphereApp/NearSphere.git]
cd NearSphere
``` 
2.	**Configure your environment** (properties or YAML) to include the necessary variables described above.
3.	**Install dependencies using Maven**:
```
mvn clean install
```
### Running the Application
You can run the project via Maven:
```
mvn spring-boot:run
```
The application will start on http://localhost:8080.

### Accessing H2 Console
- Visit http://localhost:8080/h2-console
-  Make sure the JDBC URL, username, and password match what’s in your application.properties file (default memory database: jdbc:h2:mem:testdb).  
### Testing the Application
- For testing controllers/services, add JUnit tests in the src/test/java folder.
- Example endpoints can be tested using tools like Postman.

<hr></hr>

## API Endpoints
### Authentication Endpoints
1. **Login**:
   - `POST /api/auth/login`
   - Request Body: `AuthRequest`
```
   {
   "username": "user",
   "password": "password"
   }
```
   - Response Body: `AuthResponse`
```
   {
    "token": "<JWT token>"
    "expiresIn": "<expiration time in milliseconds>"
}
```
2. **Sign Up**:
   - `POST /api/auth/signup`
   - Request Body: `NewUserRequest`
```
   {
   "displayName": "user",
   "password": "password",
   "email": "email@domain.com"
   }
```
- Response Body: `201 Created` (No content)

### User Endpoints
1. **Get Current User**:
   - `GET /api/user`
   - Response Body: `User`
   - Requires JWT token in the Authorization header.
```
    {
    "id": 1,
    "displayName": "user",
    "email": "email@email.com"
    }

```
2. **Update User Profile**:
   - `PATCH /api/user{userId}`
   - Request Body: `ChangeUserInfoRequest`
   - Requires JWT token in the Authorization header.
```
   {
   "displayName": "newName",
   "password": "newPassword"
    }
```
- Response Body: `200 OK` (No content)
### Favorites Endpoints
1. **Get User Favorites**:
   - `GET /api/favourites/{userId}`
   - Headers: `Authorization: Bearer <jwt-token>`
   - Response Body: `List<FavouritesResponse>`
```
  [
  {
    "id": 10,
    "displayName": "Favorite Place",
    "photoLink": "http://maps.google.com/...",
    "address": "123 Some Street",
    "website": "http://placewebsite.com",
    "priceLevel": "2",
    "types": ["restaurant", "bar"]
  }
]
```
2. **Add Favorite**:
   - `POST /api/favourites/{userId}`
   - Request Body: `FavouritesRequest`
   - Headers: `Authorization: Bearer <jwt-token>`
```
   {
  "userId": 1,
  "displayName": "New Favorite",
  "photoLink": "http://maps.google.com/...",
  "address": "123 Some Street",
  "website": "http://placewebsite.com",
  "priceLevel": "2",
  "types": ["restaurant", "bar"]
}
```
- Response Body: `201 Created` (No content)
3. **Delete Favorite**:
   - `DELETE /api/favourites/{userId}/{favouriteId}`
   - Headers: `Authorization: Bearer <jwt-token>`
   - Response Body: `204 OK` (No content)
### Place Endpoints
1. **Get Nearby Places**:
   - `GET /api/places`
   - Query Params:
   - `latitude` (required)
   - `longitude` (required)
   - `radius` (required)
   - `keywords` (optional)
#### Example: 
`/api/places?latitude=37.7749&longitude=-122.4194&radius=1000&keywords=restaurant`
#### Response:
```
[
  {
    "id": "placeIdFromGoogle",
    "displayName": "Famous Restaurant",
    "photoLink": "http://maps.google.com/...photoUri",
    "address": "Some Address, City, Country",
    "website": "http://restaurant-website.com",
    "priceLevel": "2",
    "types": ["restaurant", "food"]
  }
]
```
### Password Reset Endpoints
1. **Request Password Reset**:
   - `POST /api/password/request-password-reset`
   - Request Body: `PasswordResetRequest`
```
   {
   "email": "email@email.com"
   }
```
- Response Body: `200 OK` (No content). An email will be sent to the user with a password reset link.
2. **Reset Password**:
   - `POST /api/password/reset-password`
   - Request Body: `ConfirmPasswordResetRequest`
```
   {
  "email": "user@example.com",
  "token": "11111111-2222-3333-4444-555555555555",
  "password": "newSecurePassword123"
}
```
#### Response: `200 OK`  
3. **Reset Password Form**:
   - `GET /api/v1/password/reset-password-form`
   #### Query Params: `email`, `token`. This returns an HTML page (Thymeleaf template) to input a new password.

<hr></hr>

## Database Entities
1. **User** (users table)
id, displayName, email, timestamps
2. **Password** (passwords table)
id, user_id, password_hash, timestamps
3. **PasswordResetToken** (password_reset_token table)
id, reset_token, user_id, expires_at, used
4. **Favourites** (user_favourite_places table)
id, user_id, displayName, photoLink, formattedAddress, website, priceLevel, types, addedAt

#### By default, the application is configured to use an in-memory H2 database for development. Check the `application.properties` for the relevant configuration.

<hr></hr>

## Further Enhancements
- Containerization: Create a Dockerfile and containerize the Spring Boot app.
- Unit and Integration Tests: Expand coverage for services and controllers.
- Validation: Add stronger validation on incoming requests.
  
<hr></hr>
