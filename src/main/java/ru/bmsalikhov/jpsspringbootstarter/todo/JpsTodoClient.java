package ru.bmsalikhov.jpsspringbootstarter.todo;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class JpsTodoClient {
    private final RestClient restClient;

    public JpsTodoClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> findAll() {
        return restClient.get()
                .uri("/todos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public Todo findById(Integer id) {
        return restClient.get()
                .uri("/todos/{id}", id)
                .retrieve()
                .body(Todo.class);
    }
}
