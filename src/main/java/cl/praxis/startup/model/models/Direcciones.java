package cl.praxis.startup.model;

import lombok.Setter;

public class Direcciones {

    @Setter
    private String calle;
    private String numeracion;
    private Direcciones direcciones;

    public Direcciones(){}
    public String getCalle(){return calle; }
    public void setCalle(String calle){this.calle = calle; }
    public String getNumeracion(){return numeracion;}
    public void setNumeracion(String numeracion) {this.numeracion = numeracion; }

    public Direcciones getDirecciones(){
        return direcciones;
    }
    public void setDirecciones(Direcciones direcciones) {this.direcciones = direcciones;}
}
