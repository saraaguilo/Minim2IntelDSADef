package edu.upc.dsa.models;

public class Item {
    String name;
    String description;
    double price;
    //int ability;

    public Item() {};

    public Item(String name, String description, double price){
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        //this.ability = ability;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}
    /**public int getAbility(){return ability;}
    public void setAbility(int ability){this.ability= ability;}**/
}
