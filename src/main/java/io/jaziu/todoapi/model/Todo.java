package io.jaziu.todoapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String text;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Todo(String text,  User user) {
        this.text = text;
        this.active = true;
        this.user = user;
    }

}
