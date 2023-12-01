package classes;

import java.sql.Date;

public class Profesor {

    private int cod_prof;
    private String nombre_ape;
    private String especialidad;
    private int jefe_dep;
    private Date fecha_nac;
    private String sexo;
    private int cod_centro;

    public Profesor() {

    }

    public int getCod_prof() {
        return cod_prof;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }

    public String getNombre_ape() {
        return nombre_ape;
    }

    public void setNombre_ape(String nombre_ape) {
        this.nombre_ape = nombre_ape;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getJefe_dep() {
        return jefe_dep;
    }

    public void setJefe_dep(int jefe_dep) {
        this.jefe_dep = jefe_dep;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCod_centro() {
        return cod_centro;
    }

    public void setCod_centro(int cod_centro) {
        this.cod_centro = cod_centro;
    }
}
