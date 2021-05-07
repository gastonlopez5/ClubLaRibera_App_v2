package com.example.clublaribera_app_v2.modelos;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String dni;
    private String email;
    private String fotoPerfil;
    private String clave;
    private Boolean estado;
    private int rolId;
    private int grupoId;
    private Grupo grupo;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String telefono, String dni, String email, String fotoPerfil, String clave, Boolean estado, int rolId, int grupoId, Grupo grupo, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
        this.clave = clave;
        this.estado = estado;
        this.rolId = rolId;
        this.grupoId = grupoId;
        this.grupo = grupo;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
