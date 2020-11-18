
package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

 class Main {
	 	public static Scanner si = new Scanner(System.in);
	 	public static String usuario, pass;
	public static void main(String[] args)
	{
		if(args.length!=1)
		{
			//System.out.print("El programa funciona asi: \n\tjava -jar ejercicio1.jar (ubicacion fichero properties)");
			//return;
		}
		
		Properties prop = new Properties();
		
		try {
			FileInputStream is = new FileInputStream("C:\\Users\\juan1\\eclipse-workspace\\practica1\\src\\ejercicio2\\config.properties.txt");
			prop.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
		usuario = prop.getProperty("conf.usuario");
		pass = prop.getProperty("conf.pass");
		TablonAnunciosCreator t = new TablonAnunciosCreator();
		Scanner s = new Scanner(System.in);
		
		int opcion, opcionBuscar;
		GestorContactos Gestor=GestorContactos.getGestorContactos();
		String email;
		int error ;
		
		
		do {
			error = 0;
			System.out.print("\nIdentificate mediante email:");
			email=s.nextLine();
			if(!Gestor.comprobarUsuario(email)){
				System.out.print("\nUsuario no encontrado en la base de datos\n");
				error = 1;
			}
			
			if(error == 0)
			{
				System.out.print("________________________________________________________________________________________\n\n");
				System.out.print("Logueado como "+ Gestor.imprimeNombre(email) + ", puede ver su tablon en la opcion 6\n");
			}
			
			System.out.print("________________________________________________________________________________________\n\n");
			System.out.print("1. Crear anuncio\n2. Editar anuncio\n3. Publicar anuncio\n4. Archivar anuncio\n5. Buscar anuncio\n6. Mostrar mi tablon\n7. Salir\n\nElige una opcion: ");
			opcion = si.nextInt();
			
		switch(opcion)
		{
		case 1:
			t.crearAnuncio();
			break;
		case 2:
			t.editarAnuncio();
			break;
			
		case 3:
			t.publicarAnuncio();
			break;
			
		case 4:
			t.archivarAnuncio();
			break;
			
		case 5:
			System.out.print("________________________________________________________________________________________\n\n");
			System.out.print("1. Buscar por fecha\n2. Buscar por tag\n3. Buscar por propietario\n4. Buscar por destinatario\n\nElige una opcion: ");
			opcionBuscar = si.nextInt();
			
			switch(opcionBuscar)
			{
				case 1:
					t.buscarFecha();
					break;
				case 2:
					t.buscarTag();
					break;
				case 3:
					t.buscarPropietario();
					break;
				case 4:
					t.buscarDestinatario();
					break;
				default:
					System.out.print("\nError, debe elegir un número entre 1 y 4\n");
					break;
			}
			
			break;
		
		case 6:
			if(Gestor.comprobarUsuario(email))
				t.buscarDestinatario(email);
			else
				System.out.print("\nNo estás actualmente logueado, inicia sesion\n");
			break;
			
		case 7:
			System.out.print("\nPrograma finalizado con éxito");
			break;
		
		default:
			System.out.print("\nError, debe elegir un número entre 1 y 6\n");
			break;
		}
	}while(opcion != 7); 

		s.close();
}
}
