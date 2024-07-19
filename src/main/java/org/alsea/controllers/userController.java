package org.alsea.controllers;

import org.alsea.models.user;
import org.alsea.models.userUpdate;
import org.alsea.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class userController {
@Autowired
    private userService userService;

@GetMapping
//http://localhost:8080/users (GET)
    public List<user> getAllUsers(){
    return userService.getAllUsers();

}

    @PostMapping
    public Map createUser(@RequestBody user usuario) {
        Map resultado = userService.createUser(usuario);
        return resultado;
    }

    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable Long id) {
        Optional<user> usuario = userService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<user> updateUser(@PathVariable Long id, @RequestBody userUpdate usuario) {
        user usuarioActualizado = userService.updateUser(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
