package ejercicio1;

import java.util.Scanner;

public class GestorContactos {
	
	private static GestorContactos instancia = null;
	
	public int x;
	private String matriz[][];
	
	private Ficheros fichero = new Ficheros();
	Scanner s = new Scanner(System.in);
	
	
	public GestorContactos() {
		UserDAO uDAO=new UserDAO;
		//matriz = new String[25][5]; //El valor de filas de esta matriz es el numero máx de contactos que admite el programa
		//x = fichero.leerFichero(matriz); //Con este se inicializa la x y la matriz
	}
		
	public static GestorContactos getGestorContactos() {
		if(instancia == null) {
			instancia = new GestorContactos();
		}
		return instancia;
	}
		
	// Operaciones públicas de la clase GestorContactos
	public void comprobarUsuario(String email)
	{
		Contacto aux=null;
		aux=uDAO.queryById(email);
		
		
	}
	
	public String imprimeNombre(String email) {
		
		Contacto aux=null
		String output = null;
		
		aux=uDAO.queryByEmail(email); esto imprime todo el usuario
		
		
		output = aux.getNombre();
		
		return output;
	}
	
}
