package Mundo;

public class Tienda {
	private String nombre;
	private String direccion;
	  
	private String contacto;

	  
	public Tienda(String pnombre, String pdireccion, String pcontacto) {
		nombre = pnombre;
	    direccion = pdireccion;
	    contacto = pcontacto;
	}

	  
	public String getNombre() {
	    return this.nombre;
	}

	  
	public String getDireccion() {
	    return this.direccion;
	}

	  
	public String getContacto() {
	    return this.contacto;
	}

}
