# Login Application

```
mvn spring-boot:run
```

# Instructions

By default, the application will run on port 8080. It will also create two
users, namely `user` and `admin`. Both users have the password `password`.

Users will have to login to access the application. Managers will have access
to the restricted page. Managers can set other users as managers.

# Pages

- `/` - Home page that can be accessed by anyone
- `/login` - Login page
- `/logout` - Logout page
- `/welcome` - Welcome page that can only be accessed by logged in users
- `/restricted` - Restricted page that can only be accessed by managers

# Stack

The backend is written in Java using Spring Boot. Specifically, Spring Boot Web
and Spring Boot Security are used. The frontend is written in HTML and CSS with
the help of React.js and Material UI.

# Explaination

The application is a simple login application that allows users to login and
access a welcome page. Managers can also access a restricted page. Managers can
also set other users as managers. The application uses Spring Boot Security to
handle authentication and authorization. The application uses h2 as a database
to store users and their roles. The application uses React.js and Material UI
to create the frontend. The application uses Spring Boot Web to create the
REST API endpoints.

# Pages

- `/` - Home page that can be accessed by anyone
- `/login` - Login page
- `/logout` - Logout page
- `/welcome` - Welcome page that can only be accessed by logged in users
- `/restricted` - Restricted page that can only be accessed by managers
- `/signup` - Signup page that allows users to create a new account

# API

Table

| API                 | Method | Roles          | Description          |
| ------------------- | ------ | -------------- | -------------------- |
| `/api/v1/users`     | `GET`  | `ROLE_USER`    | Get user information |
| `/api/v1/users`     | `POST` | None           | Create a new user    |
| `/api/v1/users`     | `PUT`  | `ROLE_MANAGER` | Update a user        |
| `/api/v1/all-users` | `GET`  | `ROLE_MANAGER` | Get all users        |