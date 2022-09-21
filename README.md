# To-do list backend
This was built in Java using the Spring Boot framework.

## About the project 💭
This is an API service that is used for my to-do [front-end](https://github.com/kav97/todo-frontend) project.
</br></br>
Following a CRUD methodology; it allows users to Create, Read, Update and Delete any to-do by using `POST`, `GET`, `PUT` and `DELETE` endpoints respectfully.

## Code structure 👨‍💻
Several classes with different roles were used in this API:
- `ToDo` - a class that holds the data about a to-do object created.
- `ToDoController` - setting up the relevant endpoints needed.
- `ToDoService` - handles all the functional logic of the API.
- `ToDoRepository` - communicates with the MySQL database and stores the information.
- `ToDoController` - runs the live API.

## To-do model
Every to-do object is created using the `ToDo` Class structure using; `id`, `createdBy`, `text` and `dateCreated`. The user will submit a form on the front-end, which is converted into .JSON format with the body:
```
{
    "id": unique number,
    "createdBy": "user",
    "text": "text",
    "dateCreated": "time-stamp of when the object was created"
}
```