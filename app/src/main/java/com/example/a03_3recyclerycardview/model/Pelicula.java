package com.example.a03_3recyclerycardview.model;

public class Pelicula {
    private String nombre;
    private int poster;

    public Pelicula(){
    }

    public Pelicula(String nombre, int poster) {
        this.nombre = nombre;
        this.poster = poster;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
