package ru.bmsalikhov.jpsspringbootstarter.todo;

public record Todo(Integer userId, Integer id, String title, Boolean completed) {
}
