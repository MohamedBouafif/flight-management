/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import java.util.Scanner;
public class Account implements VerifLogin{
    public int id;
    public String password;
    private PersonStatus status;
    private Role role;
    public Account(int id, String password, PersonStatus status, Role role) {
        this.id = id;
        this.password = password;
        this.status = status;
        this.role=role;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(PersonStatus status) throws Exception{
        this.status = status;
    }
    @Override
    public boolean VerifLogin(int Id, String pass,Role r) {
        
        return pass.equals(password) && Id==id && r==role;
    }
   public void resetPassword() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            if (newPassword == null || newPassword.isEmpty()) {
                throw new Exception("Password cannot be empty.");
            }
            this.password = newPassword;
            System.out.println("Password has been successfully reset.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    } 
}
