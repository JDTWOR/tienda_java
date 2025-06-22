package Mundo;

public class Tienda {
  private int id;
  private String nombre;
  private String direccion;

  private String contacto;

  public Tienda(String pnombre, String pdireccion, String pcontacto) {
    this.nombre = pnombre;
    this.direccion = pdireccion;
    this.contacto = pcontacto;
  }

  public void setId(int pid) {
    this.id = pid;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String pnombre) {
    this.nombre = pnombre;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public void setDireccion(String pdireccion) {
    this.direccion = pdireccion;
  }

  public String getContacto() {
    return this.contacto;
  }

  public void setContacto(String pcontacto) {
    this.contacto = pcontacto;
  }

}
