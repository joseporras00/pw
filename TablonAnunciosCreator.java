package ejercicio1;

import java.util.Scanner;

import ejercicio1.Anuncio.*;

import java.util.ArrayList;

public class TablonAnunciosCreator {
	
	private Scanner s = new Scanner(System.in);
	private Scanner si = new Scanner(System.in);
	public int n;
	private ArrayList<Anuncio> lista = new ArrayList<Anuncio>();
	private GestorContactos myGestor=GestorContactos.getGestorContactos();

	public TablonAnunciosCreator() {
		n = 0;
	}
	
	public void crearAnuncio() 
	{
		String tip;
		Anuncio anuncio_aux = new Anuncio();
		boolean error = false;

		System.out.print("Introduce el titulo del anuncio: ");
		anuncio_aux.setTitulo(s.nextLine());
		System.out.print("Introduce el propietario del anuncio(email): ");
		anuncio_aux.setPropietario(s.nextLine());
		
		if(!myGestor.comprobarUsuario(anuncio_aux.getPropietario()))
			error = true;
		
		System.out.print("Introduce el cuerpo del anuncio: ");
		anuncio_aux.setCuerpo(s.nextLine());
		
		System.out.print("Tipos reconocidos en el sistema:\n");
		for(Tipo t : Tipo.values())
			System.out.print(t.name() + "\t");
		System.out.print("Introduce el tipo de anuncio: ");
		tip = s.nextLine();
		
		if(tip.equals("general"))
		{
			anuncio_aux.AnuncioGeneral(n);
		}
		
		else if(tip.equals("tematico"))
		{
			System.out.print("Tags reconocidos en el sistema:\n");
			for(Tags t : Tags.values())
				System.out.print(t.name() + "\t");
			System.out.print("Introduce el tag del anuncio: ");
			String tema = s.nextLine();
			
			if(!anuncio_aux.comprobarTags(tema))
				error = true;
			
			anuncio_aux.setTag(tema);
			
			anuncio_aux.AnuncioTematico(n);
		}
		
		else if(tip.equals("individualizado"))
		{
			System.out.print("Introduce el email del destinatario: ");
			String aux = s.nextLine();
			
			if(myGestor.comprobarUsuario(aux))
					anuncio_aux.AnuncioIndividualizado(n, aux);
		}
		else if(tip.equals("flash"))
		{
			
			System.out.print("Introduce la fecha y hora de comienzo(dd/mm/aaaa-hh:mm): ");
			anuncio_aux.setFechaComienzo(s.nextLine());
			//this.comprobarFecha(comienzo);
			System.out.print("Introduce la fecha y hora de fin(dd/mm/aaaa-hh:mm): ");
			anuncio_aux.setFechaFin(s.nextLine());
			//this.comprobarFecha(fin);
			anuncio_aux.AnuncioFlash(n);
		}
		else
		{
			System.out.print("\nError al introducir el tipo de anuncio");
			error=true;
		}
		
		if(error==false)
		{
			lista.add(anuncio_aux);
			n++;
		}
	}
	
	
	public void editarAnuncio() {
		String tip;
		int err=0,id;
		System.out.print("Indica Id del anuncio a editar: ");
		id=si.nextInt();
		for(int i=0;i<lista.size();i++) {
			if((lista.get(i).getId())==id) {
				System.out.print("Encontrado\n");
				System.out.print("Introduce el titulo del anuncio: ");
				lista.get(i).setTitulo(s.nextLine());
				System.out.print("Introduce el propietario del anuncio(email): ");
				lista.get(i).setPropietario(s.nextLine());
				
				if(!myGestor.comprobarUsuario(lista.get(i).getPropietario()))
					err = 1;
				
				System.out.print("Introduce el cuerpo del anuncio: ");
				lista.get(i).setCuerpo(s.nextLine());
				
				System.out.print("Tipos reconocidos en el sistema:\n");
				for(Tipo t : Tipo.values())
					System.out.print(t.name() + "\t");
				System.out.print("Introduce el tipo de anuncio: ");
				tip = s.nextLine();
				
				if(tip.equals("general"))
				{					
					lista.get(i).AnuncioGeneral(id);
				}
				
				else if(tip.equals("tematico"))
				{
					System.out.print("Tags reconocidos en el sistema:\n");
					for(Tags t : Tags.values())
						System.out.print(t.name() + "\t");
					System.out.print("Introduce el tag del anuncio: ");
					String tema = s.nextLine();
					
					if(!lista.get(i).comprobarTags(tema))
						err = 1;
					
					lista.get(i).setTag(tema);
					
					lista.get(i).AnuncioTematico(id);
				}
				
				else if(tip.equals("individualizado"))
				{
					System.out.print("Introduce el email del destinatario " + i+1 + ": ");
					String aux = s.nextLine();
						
					if(myGestor.comprobarUsuario(aux))
							lista.get(i).AnuncioIndividualizado(n ,aux);
				}
				else if(tip.equals("flash"))
				{
					
					System.out.print("Introduce la fecha y hora de comienzo(dd/mm/aaaa-hh:mm): ");
					lista.get(i).setFechaComienzo(s.nextLine());
					//this.comprobarFecha(comienzo);
					System.out.print("Introduce la fecha y hora de comienzo(dd/mm/aaaa-hh:mm): ");
					lista.get(i).setFechaFin(s.nextLine());
					//this.comprobarFecha(fin);
				}
				else
				{
					System.out.print("Error al introducir el tipo de anuncio");
					err=1;
				}
				
			}
			}
		if(err==1) {
			System.out.print("Fallo al editar el anuncio\n");
		}
		
	}
	
	public void publicarAnuncio() {
		int encontrado=0;
		System.out.print("Indica id de anuncio a publicar: ");
		int id = si.nextInt();
		for(Anuncio a: lista) {
			if((a.getId())==(id)) {
				System.out.print("Indica fecha de comienzo: ");
				a.setFechaComienzo(s.nextLine());
				System.out.print("Indica fecha de finalizacion: ");
				a.setFechaFin(s.nextLine());
				encontrado = 1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo existe ningun anuncio con ese id\n");
	}
	
	public void archivarAnuncio() {
		int encontrado = 0;
		System.out.print("Indica id de anuncio a archivar: ");
		int id = si.nextInt();
		for(Anuncio a: lista) {
			if((a.getId())==(id)) {
				a.setFase(Fase.archivado.name());
				System.out.print("\nAnuncio archivado con exito\n");
				encontrado = 1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo existe ningun anuncio con ese id\n");
	}

	public void buscarFecha() {
		
		int encontrado = 0;
		System.out.print("Indica fecha de finalizacion de anuncio a buscar: ");
		String fecha = s.nextLine();
		for(Anuncio a: lista) {
			if((a.getFechaFin()).equals(fecha)) {
				System.out.print(a.toString());
				encontrado = 1;
			}
		}
		
		if(encontrado == 0)
			System.out.print("\nNo hay ningun anuncio de la fecha introducida\n");
	}
	
	public void buscarTag() {
		String tema;
		Tags buscado=null;
		int encontrado = 0;
		
		System.out.print("Tags reconocidos en el sistema:\n");
		for(Tags t : Tags.values())
			System.out.print(t.name() + "\t");
		System.out.print("Indica Tag de anuncio a buscar: ");
		tema=s.nextLine();
		
		for(Tags t : Tags.values())
		{
			if(t.name().equals(tema))
				buscado=t;
		}
		
		for(Anuncio a: lista) {
			if(a.getTag()==buscado.name()) {
				System.out.print(a.toString());
				encontrado = 1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo hay ningun anuncio del tema introducido\n");
	}

	public void buscarPropietario() {

		System.out.print("Indica el propietario de anuncios a buscar(email): ");
		String email = s.nextLine();
		int encontrado = 0;
		
		for(Anuncio a: lista) {
			if((a.getPropietario().equals(email))) {
				System.out.print(a.toString());
				encontrado = 1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo hay ningun anuncio del usuario introducido\n");

	}

	public void buscarDestinatario() {

		int encontrado = 0;

		System.out.print("Indica el destinatario del anuncio a buscar(email): ");
		String dest=s.nextLine();
		
		for(Anuncio a: lista) 
		{
			
			String aux=a.getDestinatario();

			if(aux.equals(dest))
			{
				System.out.print(a.toString());
				encontrado = 1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo hay ningun anuncio para el usuario introducido\n");
	}

	public void buscarDestinatario(String email) {
		
		int encontrado = 0;
		
		for(Anuncio a: lista)
		{
			String aux=a.getDestinatario();

			if(aux.equals(email)) 
			{
				System.out.print(a.toString());
				encontrado=1;
			}
		}
		if(encontrado == 0)
			System.out.print("\nNo hay ningun anuncio para ti\n");
	}
}
