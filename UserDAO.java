// En la pr�ctica, la clase deber�a ir en <es.uco.pw.data.dao>
package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// Ser�a recomendable tener una clase DAO que guardara los m�todos comunes (p.ej. getConnection()) y 
// de la que heredase esta clase y el resto de DAOs
public class UserDAO {
	
	private static String Usave, Uupdate, Uselect, Udelete;
	UserDAO()
	{
		Properties sql = new Properties();
		
		try {
			FileInputStream is = new FileInputStream("C:\\Users\\juan1\\eclipse-workspace\\practica1\\src\\ejercicio2\\sql.properties.txt");
			sql.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		Usave = sql.getProperty("sql.Usave");
		Uupdate = sql.getProperty("sql.Uupdate");
		Uselect = sql.getProperty("sql.Uselect");
		Udelete = sql.getProperty("sql.Udelete");
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
  public static int save(Contacto usuario){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament ser� m�s r�pido (si es uso recurrente) y permite invocaci�n a par�metros
		// Lo habitual es que las consultas y sentencias SQL est�n en un fichero de propiedades aparte, no en c�digo
		PreparedStatement ps=con.prepareStatement(Usave);
		// El orden de los par�metros debe coincidir con las '?' del c�digo SQL
		ps.setString(1,usuario.getNombre());
		ps.setString(2,usuario.getApellidos());
		ps.setString(3,usuario.getEmail());
		ps.setString(4,usuario.getFechaNac());
		ps.setString(5,usuario.getTag());
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene m�s opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre deber�a tener informaci�n del resultado de la sentencia SQL
	return status;
}
  
// M�todo para actualizar un usuario
public static int update(Contacto usuario){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(Uupdate);
		ps.setString(1,usuario.getNombre());
		ps.setString(2,usuario.getApellidos());
		ps.setString(3,usuario.getFechaNac());
		ps.setString(4,usuario.getTag());
		// En este caso, 'id' va despu�s, conforme al orden de la sentencia SQL
		ps.setString(5,usuario.getEmail());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}

// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
public static Contacto queryByEmail (String email) {
	Statement stmt = null; 
	Contacto aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(Uselect);
	    while (rs.next()) {
	    	String nombre = rs.getString("nombre");
	        String apellidos = rs.getString("apellidos");
	        String fecha = rs.getString("fecha");
	        String tags = rs.getString("tags");
	        aux = new Contacto();
	        aux.setNombre(nombre);
	        aux.setApellidos(apellidos);
			aux.setEmail(email);
	        aux.setFechaNac(fecha);
	        aux.setTag(tags);
	        System.out.println(nombre + "\t" + apellidos +
	                               "\t" + email + "\t" + fecha + "\t" + tags);
	    }
	    // Se debe tener precauci�n con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 

public static int delete(String email){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(Udelete);
		ps.setString(1,email);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}
}