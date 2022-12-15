package com.afs.todolist.service;

import com.afs.todolist.entity.Todo;
import com.afs.todolist.exception.InvalidIdException;
import com.afs.todolist.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        todo.setDone(false);
        return todoRepository.save(todo);
    }

    private void validateObjectId(String id){
        if(!ObjectId.isValid(id)){
            throw new InvalidIdException(id);
        }
    }

    public Todo updateTodo(String id, Todo todo) {
        validateObjectId(id);
        todo.setId(id);
        return todoRepository.save(todo);
    }

    public void deleteTodo(String id) {
        validateObjectId(id);
        todoRepository.deleteById(id);
    }
}
