/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirCraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author moham
 */
public class AirCrafts {
    List <AirCraft> airCrafts;
    public  AirCrafts(){
        airCrafts = new ArrayList<>();
    }
    //Fonction qui ajoute des aéorenef en prenant en compte les exception derreur de saisie et la duplication
    public void addAirCraft(AirCraft airCraft)throws DuplicateElementException{
        if(airCraft.getName() == null || airCraft.getName().isEmpty())
            throw new IllegalArgumentException("Aircraft Name is required");
        if(airCraft.getModel() == null || airCraft.getModel().isEmpty())
            throw new IllegalArgumentException("Aircraft Model is required");
        if(airCraft.getStatus() == null)
            throw new IllegalArgumentException("Aircraft Status is required");
        if(airCraft.getDateFabrication() == null)
            throw new IllegalArgumentException("Aircraft Date is required");
        if(airCrafts.contains(airCraft))
            throw new DuplicateElementException("Aircraft already Exists");
        airCrafts.add(airCraft);
    }
    //Une fonction qui filtre les données et a chaque donnée filtré elle execute une action precise
    public void airCraftFilterAndPerform(AirCraftFilter airCraftFilter, AirCraftAction action){
        //J utilise le filtre puis jexecute laction en utilisant un stream au lieu de list et parcour normal
        airCrafts.stream()
             .filter(airCraftFilter::filter)
             .forEach(action::execute);
    }
    
    //Fonction qui fait le tri en s'apuyant sur un caractere donne
    public void sortAirCrafts(Comparator <AirCraft> comparator){
        //c'est pas la pein ici que je cree un autre interface fonctionnel car comparator l'est et en plus il a la fonction de tri que je lai besoin
        airCrafts.sort(comparator);
    }
    
    //Je vais lutiliser pour le trie en utilisant 2 caracteres simultanément
    public static AirCraftFilter and(AirCraftFilter acf1 , AirCraftFilter acf2){
        return airCraft -> acf1.filter(airCraft)&&acf2.filter(airCraft);
    }
    
     /*Utilité :  on veux afficher les avion en panne par ordre croissant de datte de fabrication
        AirCraftFilter brokenAirCraft = airCraft -> airCraft.getstatus == BROKEN;
        TaskFilter oldAirCraft = airCraft -> airCraft.getDateFabrication().isBefore(LocalDate.now().plusDays(20));   
        Aircrafts.airCraftFilterAndPerform(AirCraftFilter.and(brokenAirCraft, oldAirCraft), task -> System.out.println(task));
    */
}