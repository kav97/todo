package com.example.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findToDoByCreatedBy(String creatingBy);

    @Modifying
    @Query("delete ToDo t where t.createdBy = :createdBy")
    int deleteToDoByCreatedBy(String createdBy);

}
