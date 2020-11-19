// En la pr�ctica, la clase deber�a ir en <es.uco.pw.data.dao>
package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// Ser�a recomendable tener una clase DAO que guardara los m�todos comunes (p.ej. getConnection()) y 
// de la que heredase esta clase y el resto de DAOs
public class AnuncioDAO {
	
	
	private static String Asave, Aupdate, Aselect, Adelete;
	AnuncioDAO()
	{
		Properties sql = new Properties();
		
		try {
			FileInputStream is = new FileInputStream("C:\\Users\\juan1\\eclipse-workspace\\practica1\\src\\ejercicio2\\sql.properties.txt");
			sql.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		Asave = sql.getProperty("sql.Usave");
		Aupdate = sql.getProperty("sql.Uupdate");
		Aselect = sql.getProperty("sql.Uselect");
		Adelete = sql.getProperty("sql.Udelete");
	}
	
	
  // M�todo que establece la conexi�n con la base de datos
  // NOTA: Los m�todos est�ticos no son obligatorios (ni siquiera los m�s apropiados):
  // Se ha escrito de esta manera �nicamente para facilitar la ejecuci�n
  public static Connection getConnection(){
	// En primer lugar, obtenemos una instancia del driver de MySQL
	Connection con=null;
	try {
	  Class.forName("com.mysql.jdbc.Driver");
	  // Introducir correctamente el nombre y datos de conexi�n - Idealmente, estos datos se 
	  // indican en un fichero de propiedades
	  con= DriverManager.getConnection("jdbc:mysql://oraclepr.uco.es:3306/basedatos",Main.usuario,Main.pass);
	// Importante capturar 
	} catch(Exception e) {
	  System.out.println(e);
	}
	return con;
  }
  
  // M�todo para insertar una fila
  // En ning�n caso es recomendable el paso por par�metro de los valores individuales
  // Se recomienda utilizar el UserBean o una clase envoltorio User que tenga estas propiedades
  public static int save(Anuncio anuncio){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament ser� m�s r�pido (si es uso recurrente) y permite invocaci�n a par�metros
		// Lo habitual es que las consultas y sentencias SQL est�n en un fichero de propiedades aparte, no en c�digo
		PreparedStatement ps=con.prepareStatement(Asave);
		// El orden de los par�metros debe coincidir con las '?' del c�digo SQL
		ps.setInt(1,anuncio.getId());
		ps.setString(2,anuncio.getTitulo());
		ps.setString(3,anuncio.getPropietario());
		ps.setString(4,anuncio.getDestinatario());
		ps.setString(5,anuncio.getCuerpo());
    	ps.setString(6,anuncio.getFase());
    	ps.setString(7,anuncio.getTag());
    	ps.setString(8,anuncio.getFechaComienzo());
    	ps.setString(9,anuncio.getFechaFin());
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene m�s opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre deber�a tener informaci�n del resultado de la sentencia SQL
	return status;
}
  
// M�todo para actualizar un usuario
public static int update(Anuncio anuncio){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(Aupdate);
		ps.setString(1,anuncio.getTitulo());
		ps.setString(2,anuncio.getPropietario());
		ps.setString(3,anuncio.getDestinatario());
    	ps.setString(4,anuncio.getCuerpo());
    	ps.setString(5,anuncio.getFase());
    	ps.setString(6,anuncio.getTag());
    	ps.setString(7,anuncio.getFechaComienzo());
    	ps.setString(8,anuncio.getFechaFin());
		ps.setInt(9,anuncio.getId());
		// En este caso, 'id' va despu�s, conforme al orden de la sentencia SQL
		
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}

// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
public static Anuncio queryById (int id) {
	Statement stmt = null; 
	Anuncio aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(Aselect);
	    while (rs.next()) {
	    	String titulo = rs.getString("titulo");
	        String propietario = rs.getString("propietario");
	        String destinatario = rs.getString("destinatario");
         	String cuerpo = rs.getString("cuerpo");
         	String fase = rs.getString("fase");
         	String tag = rs.getString("tag");
         	String fcom = rs.getString("fcom");
         	String ffin = rs.getString("ffin");

	        aux = new Anuncio();
	        aux.setId(id);
	        aux.setTitulo(titulo);
	        aux.setPropietario(propietario);
	        aux.setDestinatario(destinatario);
	        aux.setCuerpo(cuerpo);
	        aux.setFase(fase);
	        aux.setTag(tag);
	        aux.setFechaComienzo(fcom);
	        aux.setFechaFin(ffin);
          	
	        System.out.println(id + "\n" + titulo +
	                               "\n" + propietario + "\n" + destinatario + "\n" + cuerpo + "\n" + fase + "\n" + tag + "\n" + fcom + "\n" + ffin + "\n\n");
	    }
	    // Se debe tener precauci�n con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 

public static int delete(int id){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(Adelete);
		ps.setInt(1,id);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}
}