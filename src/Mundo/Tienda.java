package Mundo;

public class Tienda {
  private long id;
  private String nombre;
  private long idCategoria;
  private String contacto;
  private String direccion;
  private String rutaImagen;

  public Tienda(String pnombre, long pidCategoria, String pcontacto, String pdireccion, String prutaImagen) {
    this.nombre = pnombre;
    this.idCategoria = pidCategoria;
    this.contacto = pcontacto;
    this.direccion = pdireccion;
    this.rutaImagen = prutaImagen;
  }

  public Tienda(long pid, String pnombre, long pidCategoria, String pcontacto, String pdireccion, String prutaImagen) {
    this.id = pid;
    this.nombre = pnombre;
    this.idCategoria = pidCategoria;
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

  public long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(long pidCategoria) {
    this.idCategoria = pidCategoria;
  }

  public String getRutaImagen() {
    return rutaImagen;
  }

  public void setRutaImagen(String prutaImagen) {
    this.rutaImagen = prutaImagen;
  }
}
