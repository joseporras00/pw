package ejercicio1;

import java.util.Scanner;

public class GestorContactos {
	
	private static GestorContactos instancia = null;
	
	public int x;

	Scanner s = new Scanner(System.in);
	
	
	public GestorContactos() {
		x=UserDAO.count();
	}
		
	public static GestorContactos getGestorContactos() {
		if(instancia == null) {
			instancia = new GestorContactos();
		}
		return instancia;
	}
		
	// Operaciones públicas de la clase GestorContactos
	public boolean comprobarUsuario(String email)
	{		
		Contacto aux = UserDAO.queryByEmail(email);

		if(aux!=null)
			return true;
		else
			return false;
	}
	
	public String imprimeNombre(String email) {
		
		Contacto aux = UserDAO.queryByEmail(email);
		
		return aux.getNombre() + aux.getApellidos();
	}
	
}
