package io.jaziu.todoapi.repository;

import io.jaziu.todoapi.model.Todo;
import io.jaziu.todoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAllByUser(Optional<User> user);
}
