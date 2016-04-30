package com.smartcity.posgrado.Eventos;

/**
 * Created by giovanny on 30/04/16.
 */
public class NEvento {
    int imagen;
    String titulo;
    String FeHora;
    String Lugar;

    public NEvento(int imagen, String titulo, String feHora, String lugar) {
        this.imagen = imagen;
        this.titulo = titulo;
        FeHora = feHora;
        Lugar = lugar;
    }

    public int getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFeHora() {
        return FeHora;
    }

    public String getLugar() {
        return Lugar;
    }
}
