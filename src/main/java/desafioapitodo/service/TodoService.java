package desafioapitodo.service;


import desafioapitodo.domain.Todo;
import desafioapitodo.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> list() {
        // Consulta com ordenação por prioridade e id
        Sort sort = Sort.by(Sort.Direction.ASC, "prioridade").and(Sort.by(Sort.Direction.ASC, "id"));

        return todoRepository.findAll(sort);
    }

    // a Lógica é sempre retornar a lista de todos após as operações, pensando na atualização da lista na tela

    public Todo save(Todo todo) {
        todoRepository.save(todo);
        return todo;
    }

    public List<Todo> update(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.deleteById(id);
        return list();
    }





}
