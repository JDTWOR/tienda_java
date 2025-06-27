package Mundo;

public class Categoria {
  private int id;
  private String nombre;

  public Categoria(String pnombre) {
    this.nombre = pnombre;
  }

  public Categoria(int pid, String pnombre) {
    this.id = pid;
    this.nombre = pnombre;
  }

  public int getId() {
    return this.id;
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
}
