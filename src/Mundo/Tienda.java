package Mundo;

public class Tienda {
  private long id;
  private String nombre;
  private String categoria;
  private String contacto;
  private String direccion;
  private String rutaImagen;

  public Tienda(String pnombre, String pcategoria, String pcontacto, String pdireccion, String prutaImagen) {
    this.nombre = pnombre;
    this.categoria = pcategoria;
    this.contacto = pcontacto;
    this.direccion = pdireccion;
    this.rutaImagen = prutaImagen;
  }

  public Tienda(long pid, String pnombre, String pcategoria, String pcontacto, String pdireccion, String prutaImagen) {
    this.id = pid;
    this.nombre = pnombre;
    this.categoria = pcategoria;
    this.contacto = pcontacto;
    this.direccion = pdireccion;
    this.rutaImagen = prutaImagen;
  }

  public void setId(long pid) {
    this.id = pid;
  }

  public long getId() {
    return this.id;
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

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String pcategoria) {
    this.categoria = pcategoria;
  }

  public String getRutaImagen() {
    return rutaImagen;
  }

  public void setRutaImagen(String prutaImagen) {
    this.rutaImagen = prutaImagen;
  }
}
