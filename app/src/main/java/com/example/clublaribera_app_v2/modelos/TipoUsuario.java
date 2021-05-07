package com.example.clublaribera_app_v2.modelos;

import java.io.Serializable;

public class TipoUsuario implements Serializable {
    private int id;
    private String rol;

    public TipoUsuario() {
    }

    public TipoUsuario(int id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return rol;
    }
}
