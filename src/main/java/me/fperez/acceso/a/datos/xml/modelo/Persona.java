/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.fperez.acceso.a.datos.xml.modelo;

/**
 *
 * @author fperez
 */
public class Persona {

    private String nombre;
    private int anyoNacimiento;
    private String ciudad;

    public Persona(String Nombre, int AnyoNacimiento, String Ciudad) {
        this.nombre = Nombre;
        this.anyoNacimiento = AnyoNacimiento;
        this.ciudad = Ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setAnyoNacimiento(int AnyoNacimiento) {
        this.anyoNacimiento = AnyoNacimiento;
    }

    public void setCiudad(String Ciudad) {
        this.ciudad = Ciudad;
    }

    @Override
    public String toString() {
        return String.format("Persona{nombre='%s', anyoNacimiento=%d, ciudad='%s'}", nombre, anyoNacimiento, ciudad);
    }
}
