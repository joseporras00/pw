package ejercicio1;

import ejercicio1.Anuncio.Tags;

public class Contacto {
	
	private String nombre;
	private String apellidos;
	private String email;
	private Tags tag;
	private String fechaNac;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTag() {
		return tag.toString();
	}
	public void setTag(String aux) {
		
		for(Tags t : Tags.values())
		{
			if(t.name().equals(aux))
				this.tag=t;
		}
	}
	
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
}
