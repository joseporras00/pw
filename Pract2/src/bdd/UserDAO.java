// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package bdd;

import java.sql.*;
import java.util.Hashtable;

// Sería recomendable tener una clase DAO que guardara los métodos comunes (p.ej. getConnection()) y 
// de la que heredase esta clase y el resto de DAOs
public class UserDAO {
  // Método que establece la conexión con la base de datos
  // NOTA: Los métodos estáticos no son obligatorios (ni siquiera los más apropiados):
  // Se ha escrito de esta manera únicamente para facilitar la ejecución
  public static Connection getConnection(){
	// En primer lugar, obtenemos una instancia del driver de MySQL
	Connection con=null;
	try {
	  Class.forName("com.mysql.jdbc.Driver");
	  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
	  // indican en un fichero de propiedades
	  con= DriverManager.getConnection("jdbc:mysql://oraclepr.uco.es:3306/basedatos","usuario","password");
	// Importante capturar 
	} catch(Exception e) {
	  System.out.println(e);
	}
	return con;
  }
  
  // Método para insertar una fila
  // En ningún caso es recomendable el paso por parámetro de los valores individuales
  // Se recomienda utilizar el UserBean o una clase envoltorio User que tenga estas propiedades
  public static int save(String nombre, String apellidos,String email,String fecha, int tags){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement("insert into Usuarios (nombre,apellidos,email,fecha,tags) values(?,?,?,?,?)");
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setString(1,nombre);
		ps.setString(2,apellidos);
		ps.setString(3,email);
		ps.setString(4,fecha);
		ps.setInt(5,tags);
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}
  
// Método para actualizar un usuario
public static int update(String nombre, String apellidos,String email,String fecha, int tags){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("update Usuarios set nombre=?,apellidos=?,fecha=?,tags=? where email=?");
		ps.setString(1,nombre);
		ps.setString(2,apellidos);
		ps.setString(3,fecha);
		ps.setInt(4,tags);
		// En este caso, 'id' va después, conforme al orden de la sentencia SQL
		ps.setString(5,email);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}

// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
public static Hashtable<String,String> queryByEmail (String email) {
	Statement stmt = null; 
	Hashtable<String,String> resul = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("select nombre, apellidos, fecha, tags from Usuarios where email = " + email);
	    while (rs.next()) {
	    	String nombre = rs.getString("nombre");
	        String apellidos = rs.getString("apellidos");
		String fecha = rs.getString("fecha");
	        int tags = rs.getInt("tags");
	        resul = new Hashtable<String,String>();
	        resul.put("Nombre", nombre);
	        resul.put("Apellidos", apellidos);
		resul.put("Email", email);
	        resul.put("F. Nacimiento", fecha);
	        resul.put("tags", Integer.toString(tags));        
	        System.out.println(nombre + "\t" + apellidos +
	                               "\t" + email + "\t" + fecha + "\t" + tags);
	    }
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return resul;
} 

public static int delete(String email){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from Usuarios where email=?");
		ps.setString(1,email);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}
}
