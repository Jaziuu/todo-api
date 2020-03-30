package io.jaziu.todoapi;

import io.jaziu.todoapi.model.Role;
import io.jaziu.todoapi.model.RoleName;
import io.jaziu.todoapi.model.Todo;
import io.jaziu.todoapi.model.User;
import io.jaziu.todoapi.repository.RoleRepository;
import io.jaziu.todoapi.repository.TodoRepository;
import io.jaziu.todoapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DbInit implements CommandLineRunner {

    private UserRepository userRepository;
    private TodoRepository todoRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public DbInit(UserRepository userRepository, TodoRepository todoRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

//
//
//        roleRepository.save(new Role(RoleName.ROLE_USER));
//        roleRepository.save(new Role(RoleName.ROLE_ADMIN));
//
//        User admin = new User("admin",encoder.encode("admin123"),"adminemail@gmail.com");
//        User user = new User("user",encoder.encode("user123"),"useremail@gmail.com");
//        Todo todo1 = new Todo("Finish this app",admin);
//        Todo todo2 = new Todo("Create UI",admin);
//        Todo todo3 = new Todo("Make coffe",user);
//        Todo todo4 = new Todo("Find Job",user);
//
//        userRepository.save(admin);
//        userRepository.save(user);
//
//        todoRepository.save(todo1);
//        todoRepository.save(todo2);
//        todoRepository.save(todo3);
//        todoRepository.save(todo4);
//
//        Set<Todo> adminTodos= new HashSet<>();
//        adminTodos.add(todo1);
//        adminTodos.add(todo2);
//        admin.setTodos(adminTodos);
//
//        Set<Todo> userTodos= new HashSet<>();
//        userTodos.add(todo3);
//        userTodos.add(todo4);
//        user.setTodos(userTodos);
//
//        Set<Role> adminRoles = new HashSet<>();
//        Set<Role> userRoles = new HashSet<>();
//
//        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//
//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//
//        adminRoles.add(userRole);
//        adminRoles.add(adminRole);
//        userRoles.add(userRole);
//
//        admin.setRoles(adminRoles);
//        userRepository.save(admin);
//
//        user.setRoles(userRoles);
//        userRepository.save(user);

    }
}
