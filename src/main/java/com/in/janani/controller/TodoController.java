package com.in.janani.controller;

import com.in.janani.service.TodoService;
import com.in.janani.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;


    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodoPaged(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getAllTodosByPages(page,size),HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo){
        Todo createdtodo = todoService.createTodo(todo);
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id){
        try {
            Todo createdtodo = todoService.getTodoById(id);
            return new ResponseEntity<>(todoService.getTodoById(id),HttpStatus.OK);
        }
        catch (RuntimeException exception) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<>(todoService.getTodos(),HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<Todo> updateTodo(@RequestBody Todo todo){
        Todo updatedTodo = todoService.updateTodo(todo);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id){
    todoService.deleteTodoById(id);
    }
}
