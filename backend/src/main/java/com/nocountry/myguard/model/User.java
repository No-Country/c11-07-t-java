package com.nocountry.myguard.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String email;
    private long id;
    private String password;
    private String role;


    public User(String username, String email, long id, String password, String role) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Bienvenido");
            return true;
        } else {
            System.out.println("El usuario o contraseña es incorrecto");
            return false;
        }

    }
    public void logout() {
        System.out.println("Ha cerrado sesión con éxito!");
    }

    public void editProfile(String username, String email) {
        this.username = username;
        this.email = email;
        System.out.println("Perfil modificado");
    }

    public void displayProfile() {
        System.out.println("Nombre de usuario: " + username);
        System.out.println("Email: " + email);
    }


    /*public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User("john", "password123", "John Doe", "john@example.com");

        if (user.login(username, password)) {
            user.displayProfile();

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            user.editProfile(newName, newEmail);
            user.displayProfile();
        }

        user.logout()
    * */
}
