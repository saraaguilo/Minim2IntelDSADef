package edu.upc.dsa.models;

import edu.upc.dsa.exceptions.InsufficientMoneyException;
import edu.upc.dsa.CRUD.util.RandomUtils;

public class User {
    String idUser;
    String name;
    String surname;
    String email;
    String password;
    int money;
    public User() {}
    public User(String idUser, String name, String surname, String email, String password){
        this();
        this.idUser = RandomUtils.getId();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.money=200;
    }
    public String getIdUser(){return idUser;}
    public void setIdUser(String idUser){this.idUser = idUser;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getSurname(){return surname;}
    public void setSurname(String surname){this.surname = surname;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public double getMoney() {return money;}
    public void setMoney(int money) {this.money = money;}

    public void purchaseItem(Item item) throws InsufficientMoneyException {
        if(item.getPrice()>this.money){
            throw new InsufficientMoneyException();
        }
        this.money = this.money - item.getPrice();
    }
    public void update(String name, String surname, String email, String password){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

}

