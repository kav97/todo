package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public List<Integer> extractIds () {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(toDo -> toDo.getId()).collect(Collectors.toList());
    }

    public ToDo getById(int id) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        return toDo;
    }

    public List<ToDo> getAll() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos;
    }

    public List<ToDo> getToDosCreatedBy(String createdBy){
        List<ToDo> toDos = toDoRepository.findToDoByCreatedBy(createdBy);
        return toDos;
    }

    public ToDo createToDo(ToDo toDo) {
        if (toDo.getText() == null || toDo.getText().length() < 1 || toDo.getText().length() > 255) {
            throw new RuntimeException("ToDo must have text between 1 - 255 characters long");
        }

        if (toDo.getCreatedBy() == null || toDo.getCreatedBy().length() < 1 || toDo.getCreatedBy().length() > 255) {
            throw new RuntimeException("ToDo must have created by");
        }

        return toDoRepository.save(toDo);
    }

    public boolean deleteById(int id){
        ToDo toDoToDelete = toDoRepository.findById(id).orElse(null);

        if(toDoToDelete == null){
            return false;
        }

        toDoRepository.delete(toDoToDelete);
        return true;
    }

    public int deleteToDosByCreatedBy(String createdBy) {
        int deletedCount = toDoRepository.deleteToDoByCreatedBy(createdBy);
        return deletedCount;
    }
}
