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


    public String getidItem()
    {
        return idItem;
    }
    public String getIdUser()
    {
        return idUser;
    }
    public void setidItem(String idItem){
        this.idItem = idItem;
    }
    public void setidUser(String idUser)
    {
        this.idUser = idUser;
    }
    /**public void setQuantity(int quantity){ this.quantity=quantity;}**/
}
