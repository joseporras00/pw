package ejercicio1;

import java.util.Scanner;

import ejercicio1.AnuncioDAO.*;
import ejercicio1.Anuncio.*;

public class TablonAnunciosCreator {
	
	private Scanner s = new Scanner(System.in);
	private Scanner si = new Scanner(System.in);
	public int n;
	private GestorContactos myGestor=GestorContactos.getGestorContactos();
	

	public TablonAnunciosCreator() {
		n = AnuncioDAO.count();
		
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
			
			if(!myGestor.comprobarUsuario(aux))
				error=true;
			
			anuncio_aux.AnuncioIndividualizado(n, aux);
		}
		else if(tip.equals("flash"))
		{
			
			System.out.print("Introduce la fecha y hora de comienzo(dd/mm/aaaa): ");
			anuncio_aux.setFechaComienzo(s.nextLine());
			//this.comprobarFecha(anuncio_aux.getFechaComienzo());
			System.out.print("Introduce la fecha y hora de fin(dd/mm/aaaa): ");
			anuncio_aux.setFechaFin(s.nextLine());
			//this.comprobarFecha(anuncio_aux.getFechaFin());
			anuncio_aux.AnuncioFlash(n);
		}
		else
		{
			System.out.print("\nError al introducir el tipo de anuncio");
			error=true;
		}
		
		if(error==false)
		{
			AnuncioDAO.save(anuncio_aux);
			n++;
		}
	}
	
	
	public void editarAnuncio() 
	{
		String tip;
		boolean error = false;
		int id;
		
		System.out.print("Indica Id del anuncio a editar: ");
		id=si.nextInt();
		
		Anuncio aux = AnuncioDAO.queryById(id);
		tip = aux.getTipo().toString();
		
		if(aux != null)
		{
					System.out.print("\nEncontrado");
					
					System.out.print("\nIntroduce el titulo del anuncio: ");
					aux.setTitulo(s.nextLine());
					
					System.out.print("Introduce el propietario del anuncio: ");
					aux.setPropietario(s.nextLine());
					if(!myGestor.comprobarUsuario(aux.getPropietario()))
						error = true;
					
					System.out.print("Introduce el cuerpo del anuncio: ");
					aux.setCuerpo(s.nextLine());
					
					if(tip.equals("general"))
					{
						aux.AnuncioGeneral(aux.getId());
					}
					else if(tip.equals("tematico"))
					{
						System.out.print("Tags reconocidos en el sistema:\n");
						for(Tags t : Tags.values())
							System.out.print(t.name() + "\t");
						System.out.print("Introduce el tag del anuncio: ");
						String tema = s.nextLine();
						
						if(!aux.comprobarTags(tema))
							error = true;
						
						aux.setTag(tema);
						
						aux.AnuncioTematico(aux.getId());
					}
					else if(tip.equals("individualizado"))
					{
						System.out.print("Introduce el email del destinatario: ");
						String dest = s.nextLine();
						
						if(!myGestor.comprobarUsuario(dest))
							error=true;
						
						aux.AnuncioIndividualizado(aux.getId(), dest);
					}
					else if(tip.equals("flash"))
					{
						System.out.print("Introduce la fecha y hora de comienzo(dd/mm/aaaa-hh:mm): ");
						aux.setFechaComienzo(s.nextLine());
						//this.comprobarFecha(anuncio_aux.getFechaComienzo());
						System.out.print("Introduce la fecha y hora de fin(dd/mm/aaaa-hh:mm): ");
						aux.setFechaFin(s.nextLine());
						//this.comprobarFecha(anuncio_aux.getFechaFin());
						aux.AnuncioFlash(aux.getId());
					}
					else
					{
						System.out.print("\nError al introducir el tipo de anuncio");
						error=true;
					}
					
					if(error==false)
					{
						AnuncioDAO.update(aux);
						n++;
					}	
		}
	}
	
	public void publicarAnuncio()
	{	
		System.out.print("Indica Id del anuncio a publicar: ");
		int id = si.nextInt();
		
		Anuncio aux = AnuncioDAO.queryById(id);
		
		if(aux!=null)
		{
			aux.setFase("publicado");
			AnuncioDAO.update(aux);
		}
		else
			System.out.print("\nNo existe ningun anuncio con ese id\n");
	}
	
	public void archivarAnuncio() 
	{
		System.out.print("Indica Id del anuncio a archivar: ");
		int id = si.nextInt();
		
		Anuncio aux = AnuncioDAO.queryById(id);
		
		if(aux!=null)
		{
			aux.setFase("archivado");
			AnuncioDAO.update(aux);
		}
		else
			System.out.print("\nNo existe ningun anuncio con ese id\n");
	}

	public void buscarFecha() 
	{
		Anuncio aux = null;
		boolean encontrado = false;
		
		System.out.print("Introduce la fecha a buscar(dd/mm/aaaa): ");
		String fecha = s.nextLine();
		
		for(int i=0;i<n;i++)
		{
			aux = AnuncioDAO.queryById(i);
			
			if(aux.getFechaFin().equals(fecha))
			{
				System.out.print(aux);
				encontrado = true;
			}
		}
		
		if(encontrado == false)
			System.out.print("\nNo hay ningun anuncio en esa fecha");
	}
	
	public void buscarTag()
	{
		String tema;
		boolean encontrado = false;
		Anuncio aux = null;
		
		System.out.print("Tags reconocidos en el sistema:\n");
		for(Tags t : Tags.values())
			System.out.print(t.name() + "\t");
		System.out.print("Indica Tag de anuncio a buscar: ");
		tema=s.nextLine();
		
		for(int i=0;i<n;i++)
		{
			aux = AnuncioDAO.queryById(i);
			
			if(aux.getFase().equals(tema))
			{
				System.out.print(aux);
				encontrado = true;
			}
		}
		
		if(encontrado == false)
			System.out.print("\nNo hay ningun anuncio del tema introducido\n");
	}

	public void buscarPropietario()
	{

		System.out.print("Indica el propietario de anuncios a buscar(email): ");
		String email = s.nextLine();
		boolean encontrado = false;
		Anuncio aux = null;
		
		for(int i=0;i<n;i++)
		{
			aux = AnuncioDAO.queryById(i);
			
			if(aux.getPropietario().equals(email))
			{
				System.out.print(aux);
				encontrado = true;
			}
		}
		
		if(encontrado == false)
			System.out.print("\nNo hay ningun anuncio del usuario introducido\n");

	}

	public void buscarDestinatario()
	{
		boolean encontrado = false;
		Anuncio aux = null;

		System.out.print("Indica el destinatario del anuncio a buscar(email): ");
		String dest=s.nextLine();
		
		for(int i=0;i<n;i++)
		{
			aux = AnuncioDAO.queryById(i);
			
			if(aux.getDestinatario().equals(dest))
			{
				System.out.print(aux);
				encontrado = true;
			}
		}
		
		if(encontrado == false)
			System.out.print("\nNo hay ningun anuncio para el usuario introducido\n");
	}

	public void buscarDestinatario(String email) {
		
		boolean encontrado = false;
		Anuncio aux = null;
		
		for(int i=0;i<n;i++)
		{
			aux = AnuncioDAO.queryById(i);
			
			if(aux.getDestinatario().equals(email))
			{
				System.out.print(aux);
				encontrado = true;
			}
		}
		
		if(encontrado == false)
			System.out.print("\nNo hay ningun anuncio para ti\n");
	}
}
