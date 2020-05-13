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
    private String firstName, lastName, email, password,childFirstName, childLastName;
    private int childID;

    private static ParentUser parentUser;
    
    private ParentUser() {
    }
    
    public static ParentUser Instance(String firstName, String lastName, String email, String password, String childFirstName, String childLastName, int childID) {
        if (parentUser == null) {
            //por mientras tiene valores seteados en lo que hacemos el login
            parentUser = new ParentUser(firstName,lastName,email,password,childFirstName,childLastName,childID);
        }
        
        return parentUser;
    }
    public ParentUser(String firstName, String lastName, String email, String password, String childFirstName, String childLastName, int childID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.childFirstName = childFirstName;
        this.childLastName = childLastName;
        this.childID = childID;
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

    public String getChildFirstName() {
        return childFirstName;
    }

    public void setChildFirstName(String childFirstName) {
        this.childFirstName = childFirstName;
    }

    public String getChildLastName() {
        return childLastName;
    }

    public void setChildLastName(String childLastName) {
        this.childLastName = childLastName;
    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }
    
    
}
