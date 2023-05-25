package edu.upc.dsa.models;

public class Inventory {
    public String item; // name of the item
    public String user; //username of the player
    public int quantity;
    public Inventory() {}
    public Inventory(String item, String user)
    {
        this.item = item;
        this.user = user;
    }


    public String getItem()
    {
        return item;
    }

    public String getUser()
    {
        return user;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setItem(String item){
        this.item = item;
    }
    public void setUser(String user)
    {
        this.user = user;
    }
    public void setQuantity(int quantity){ this.quantity=quantity;}
}
