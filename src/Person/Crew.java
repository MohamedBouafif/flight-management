/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import static Person.Role.Crew;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author MARIEM
 */
public final class Crew extends Person{
    
    public Crew(int id, String nom, String address, String mail, int phoneNumber,Account account) {
        super(id, nom, address, mail, phoneNumber,account);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }
    public boolean VerifLogin(int id, String password) {//est ce que id w password sont valides
        return this.account.VerifLogin(id,password,Crew);
    }
    
    public void setAccount() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter identify number: ");
            int Id = scanner.nextInt();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            PersonStatus status=PersonStatus.ACTIVE;
            Role role=Role.Crew;
            Account a=new Account(Id,password,status,role);
            this.account=a;
            System.out.println("Account created successfully with ID: " + Id);
        }catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a valid number for the ID.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
