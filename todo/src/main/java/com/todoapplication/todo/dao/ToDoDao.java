package com.todoapplication.todo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.todoapplication.todo.models.ToDo;

@Repository
public interface ToDoDao extends MongoRepository<ToDo, String> {

}
