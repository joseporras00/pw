package ejercicio1;

public class Anuncio {
	
	public enum Tags
	{
		Actualidad,
		Alimentacion,
		Deportes,
		Diversion,
		Moda,
		Musica,
		Tecnologias;
	};
	
	public enum Tipo {
		general,
		tematico,
		individualizado,
		flash,
	};

	public enum Fase {
		editado,
		en_espera,
		publicado,
		archivado,
	};

	private int id;
	private String titulo;
	private String propietario;	
	private String destinatario;
	private String cuerpo;
	private Tipo tipo;
	private Fase fase;
	private Tags tag;
	private String fecha_comienzo;
	private String fecha_fin;

	public Anuncio() {
		destinatario= null;
		tag= null;
		fecha_comienzo= null;
		fecha_fin= null;	
	}
	
	public void AnuncioGeneral(int id)
	{
		this.setId(id);
		this.setFase(Fase.publicado.name());
		this.setTipo(Tipo.general);
	}
	
	public void AnuncioTematico(int id)
	{
		this.setId(id);
		this.setFase(Fase.publicado.name());
		this.setTipo(Tipo.tematico);
	}
	
	public void AnuncioIndividualizado(int id, String destinatario)
	{
		this.setId(id);
		this.setFase(Fase.publicado.name());
		this.setDestinatario(destinatario);
		this.setTipo(Tipo.individualizado);
	}
	
	public void AnuncioFlash(int id)
	{
		this.setId(id);
		this.setFase(Fase.editado.name());
		this.setTipo(Tipo.flash);
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getPropietario() {
		return this.propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	
	public String getDestinatario() {
		return this.destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getCuerpo() {
		return this.cuerpo;
	}
	
	 public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	 public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String getFase() {
		return this.fase.toString();
	}
	
	 public void setFase(String aux) {
		for(Fase f : Fase.values())
		{
			if(f.name().equals(aux))
				this.fase=f;
		}
	}
	
	public String getTag() {
		return this.tag.toString();
	}
	
	 public void setTag(String aux) {
		 for(Tags t : Tags.values())
			{
				if(t.name().equals(aux))
					this.tag=t;
			}
	}
	
	public String getFechaComienzo() {
		return this.fecha_comienzo;
	}
	
	 public void setFechaComienzo(String fecha) {
		this.fecha_comienzo = fecha;
	}
	
	public String getFechaFin() {
		return this.fecha_fin;
	}
	
	public void setFechaFin(String fecha) {
		this.fecha_fin = fecha;
	}
	 
	public boolean comprobarTags(String tema) {
		for(Tags t : Tags.values())
		{
			if(t.name().equals(tema))
				return true;
		}
		return false;
	}

	public String toString() {
		
		String info = "Id: " + this.id + " Titulo: " + this.titulo + " Propietario: " + this.propietario;
		
		if(this.destinatario != null)
			info += " Destinatarios: " + this.destinatario;
		
		if(this.tag != null)
			info += " Tematica: " + this.tag;
		
		if(this.fecha_comienzo != null)
			info += " Fecha comienzo: " + this.fecha_comienzo + " Fecha fin: " + this.fecha_fin;
		
		info += " Cuerpo:\n" + this.cuerpo + "\n";
		
		return info;
	}
	
}
