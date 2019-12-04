package com.iesnervion.practicafragmentvm.Clases;

public class Correo {
    private String descripcion;
    private String asunto;

    //Constructores
    public Correo(){
        descripcion  = "";
        asunto = "";
    }

    public Correo(String asunto, String descripcion){
        this.asunto = asunto;
        this.descripcion = descripcion;
    }

    //Getter and setter
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

}
