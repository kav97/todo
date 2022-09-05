package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Integer.parseInt;

@CrossOrigin
@RestController
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    @GetMapping("/todos/ids")
    public ResponseEntity<List<Integer>> getToDoIds () {
        List<Integer> toDoIds = toDoService.extractIds();
        return ResponseEntity.status(HttpStatus.OK).body(toDoIds);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        ToDo toDo = toDoService.getById(parseInt(id));
        HttpStatus status = toDo == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return ResponseEntity.status(status).body(toDo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getToDos() {
        List<ToDo> toDos = toDoService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(toDos);
    }

    @GetMapping("/todos/created-by/{createdBy}")
    public ResponseEntity<List<ToDo>> getToDosByCreatedBy(@PathVariable String createdBy) {
        List<ToDo> toDos = toDoService.getToDosCreatedBy(createdBy);
        return ResponseEntity.status(HttpStatus.OK).body(toDos);
    }

    @PostMapping("todo")
    public ResponseEntity<?> createToDo(@RequestBody ToDo toDo) {
        try {
            ToDo newToDo = toDoService.createToDo(toDo);
            return ResponseEntity.status(HttpStatus.OK).body(newToDo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable String id) {
        boolean isDeleted = toDoService.deleteById(parseInt(id));

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find a todo to delete with that id");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Todo deleted successfully!");
    }

    @DeleteMapping("/todos/created-by/{createdBy}")
    @Transactional
    public ResponseEntity<Integer> deleteToDosByCreatedBy(@PathVariable String createdBy){
        int count = toDoService.deleteToDosByCreatedBy(createdBy);
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}
