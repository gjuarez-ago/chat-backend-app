package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "app_user")  // Cambia el nombre de la tabla
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    private String password;  // Para simplificar el ejemplo, guardamos la contraseña directamente, pero en un proyecto real deberías cifrarla.
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
