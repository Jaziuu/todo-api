package io.jaziu.todoapi.api.viewmodel;

import io.jaziu.todoapi.model.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoViewModel {

    private Long id;
    private String text;
    private boolean active;
    private Long userId;

    public TodoViewModel(Todo todo) {

        this.id = todo.getId();
        this.text = todo.getText();
        this.userId = todo.getUser().getId();
        this.active = todo.isActive();
    }
}
