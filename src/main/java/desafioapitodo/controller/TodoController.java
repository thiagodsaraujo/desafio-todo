package desafioapitodo.controller;

import desafioapitodo.domain.Todo;
import desafioapitodo.service.TodoService;
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
    List<Todo> create(Todo todo) {
        return todoService.save(todo);
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
