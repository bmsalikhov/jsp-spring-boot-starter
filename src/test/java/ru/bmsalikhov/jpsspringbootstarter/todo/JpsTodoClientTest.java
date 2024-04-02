package ru.bmsalikhov.jpsspringbootstarter.todo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ru.bmsalikhov.jpsspringbootstarter.JsonPlaceholderServiceConfiguration;

import static org.junit.jupiter.api.Assertions.*;

class JpsTodoClientTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JsonPlaceholderServiceConfiguration.class, RestClientAutoConfiguration.class));

    @Test
    void shouldContainTodoRestClientBean() {
        contextRunner.run(context -> {
            assertTrue(context.containsBean("jsonPlaceholderRestClient"));
            assertTrue(context.containsBean("jpsTodoClient"));
        });
    }

    @Test
    void shouldFindAllTodos() {
        contextRunner
                .run((context) -> {
                    JpsTodoClient todoClient = context.getBean(JpsTodoClient.class);
                    assertEquals(200, todoClient.findAll().size());
                });
    }

    @Test
    void shouldFindTodoById() {
        contextRunner
                .run((context) -> {
                    JpsTodoClient todoClient = context.getBean(JpsTodoClient.class);
                    Todo todo = todoClient.findById(1);
                    assertEquals(1, todo.userId());
                    assertEquals(1, todo.id());
                    assertEquals("delectus aut autem", todo.title());
                    assertEquals(false, todo.completed());
                });
    }
}