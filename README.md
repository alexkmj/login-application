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
