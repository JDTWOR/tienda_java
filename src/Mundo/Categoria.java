package Mundo;

public class Categoria {
  private long id;
  private String nombre;

  public Categoria(String pnombre) {
    this.nombre = pnombre;
  }

  public Categoria(long pid, String pnombre) {
    this.id = pid;
    this.nombre = pnombre;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long pid) {
    this.id = pid;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String pnombre) {
    this.nombre = pnombre;
  }
}
