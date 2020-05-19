package objectosNegocio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author crisb
 */
public class ParentUser {
    private String firstName, lastName, email, password, token;

    private static ParentUser parentUser;
    
    private ParentUser() {
    }
    
    public static ParentUser crear(String firstName, String lastName, String email, String password, String token) {
        parentUser = new ParentUser(firstName,lastName,email,password, token);
        
        return parentUser;
    }
    
    public static ParentUser Instance() {
        return parentUser;
    }
    
    public ParentUser(String firstName, String lastName, String email, String password, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}
