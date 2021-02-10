package com.example.sms;

public class Position {
    int id;
    String numero;
    String altitude;
    String longitude;


    public Position(int id, String numero, String altitude, String longitude) {
        this.id = id;
        this.numero = numero;
        this.altitude = altitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", altitude='" + altitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

}



