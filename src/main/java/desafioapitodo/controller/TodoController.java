package desafioapitodo.controller;

import desafioapitodo.domain.Todo;
import desafioapitodo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    Todo create(@Valid Todo todo) {
        return (Todo) todoService.save(todo);
    }

    @GetMapping("/read")
    List<Todo> read() {
        return todoService.list();
    }

    @PutMapping("/update")
    List<Todo> update(Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/delete/{id}")
    List<Todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}
