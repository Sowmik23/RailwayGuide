package com.example.sowmik.offline3;

public class Train {

    String id;
    String name;
    String no;
    String offday;
    String departure;
    String dep_time;
    String arrival;
    String arri_time;
    String name_no;
    String dep_arri;

    String sub_station;
    String price;

    public Train(){

    }

    public Train(String id, String name, String no, String offday, String departure, String dep_time, String arrival, String arri_time, String name_no, String dep_arri, String sub_station) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.offday = offday;
        this.departure = departure;
        this.dep_time = dep_time;
        this.arrival = arrival;
        this.arri_time = arri_time;
        this.name_no = name_no;
        this.dep_arri = dep_arri;
        this.sub_station = sub_station;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public String getOffday() {
        return offday;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDep_time() {
        return dep_time;
    }

    public String getArrival() {
        return arrival;
    }

    public String getArri_time() {
        return arri_time;
    }

    public String getName_no() {
        return name_no;
    }

    public String getDep_arri() {
        return dep_arri;
    }

    public String getSub_station(){
        return  sub_station;
    }

    public String getPrice(){ return price; }
}

