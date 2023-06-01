package edu.upc.dsa.models;

public class Inventory {
    public String idItem;
    public String idUser;
    //public int quantity;
    public Inventory() {}
    public Inventory(String idUser, String idItem)
    {
        this.idItem = idItem;
        this.idUser = idUser;
    }


    public String getItem()
    {
        return idItem;
    }
    public String getUser()
    {
        return idUser;
    }
    /**public int getQuantity(){
        return quantity;
    }**/
    public void setItem(String idItem){
        this.idItem = idItem;
    }
    public void setUser(String idUser)
    {
        this.idUser = idUser;
    }
    /**public void setQuantity(int quantity){ this.quantity=quantity;}**/
}
