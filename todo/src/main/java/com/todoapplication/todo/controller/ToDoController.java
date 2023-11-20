package com.todoapplication.todo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todoapplication.todo.dao.ToDoDao;
import com.todoapplication.todo.models.ToDo;

@RestController
@RequestMapping("/api")
@CrossOrigin
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

  @GetMapping(value="dailyactivity/{id}")
  public ResponseEntity<ToDo> getTodoById(@PathVariable("id") String id){
    return toDoDao.findById(id).map(todo -> ResponseEntity.ok().body(todo).orElse(ResponseEntity.notFound().build());
  }

}
