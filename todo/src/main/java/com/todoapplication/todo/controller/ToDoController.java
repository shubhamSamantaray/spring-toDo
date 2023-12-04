package com.todoapplication.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapplication.todo.dao.ToDoDao;
import com.todoapplication.todo.models.ToDo;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {

    @Autowired
    ToDoDao toDoDao;

    @GetMapping("/dailyactivity")
    public List<ToDo> getAllToDo() {
        Sort sortbyCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return toDoDao.findAll(sortbyCreatedAtDesc);
    }

    @PostMapping("/dailyactivity")
    public ToDo createToDo(@Valid @RequestBody ToDo todo) {
        todo.setStatuus(false);
        return toDoDao.save(todo);
    }

    @GetMapping(value = "/dailyactivity/{id}")
    public ResponseEntity<ToDo> getTodoById(@PathVariable("id") String id) {
        return toDoDao.findById(id).map(todo -> ResponseEntity.ok().body(todo)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/dailyactivity/{id}")
    public ResponseEntity<ToDo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody ToDo todo){
        return toDoDao.findById(id).map(todoData -> { todoData.setDesc(todo.getDesc());
        todoData.setStatuus((todo.getStatus()));
        ToDo updatedTodo = toDoDao.save(todoData);
        return ResponseEntity.ok().body(updatedTodo);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/dailyactivity/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return toDoDao.findById(id).map(todo -> {
            toDoDao.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
