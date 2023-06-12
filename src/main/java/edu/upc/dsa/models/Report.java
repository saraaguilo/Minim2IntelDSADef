package edu.upc.dsa.models;

public class Report {
    public String date;
    public String informer;
    public String message;
    public Report(){};
    public Report(String date, String informer, String message){
        this.date = date;
        this.informer = informer;
        this.message = message;
    }

    public String getDate() {
        return date;
    }
    public String getInformer() {
        return informer;
    }
    public String getMessage() {
        return message;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setInformer(String informer) {
        this.informer = informer;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
