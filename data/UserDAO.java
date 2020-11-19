// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 
 * @author Jose Manuel Porras, Juan Valverde
 * clase UserDao
 *
 */
public class UserDAO {
	
	private static String Usave, Uupdate, Uselect, Udelete;
	UserDAO()
	{
		Properties sql = new Properties();
		
		try {
			FileInputStream is = new FileInputStream("C:\\Users\\usuario\\Desktop\\recuperado\\pw-juanvs00-patch-1\\config.properties.txt\");
			sql.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		Usave = sql.getProperty("sql.Usave");
		Uupdate = sql.getProperty("sql.Uupdate");
		Uselect = sql.getProperty("sql.Uselect");
		Udelete = sql.getProperty("sql.Udelete");
	}
	
	
 
	// En primer lugar, obtenemos una instancia del driver de MySQL
	Connection con=null;
	try {
	  Class.forName("com.mysql.jdbc.Driver");
	  // Introducir correctamente el nombre y datos de conexión - Idealmente, estos datos se 
	  // indican en un fichero de propiedades
	  con= DriverManager.getConnection("jdbc:mysql://" + Main.server + ":" + Main.puerto + "/" + Main.usuario,Main.usuario,Main.usuario,Main.pass);
	// Importante capturar 
	} catch(Exception e) {
	  System.out.println(e);
	}
	return con;
  }
  
  // Método para insertar una fila
  public static int save(Contacto usuario){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement(Usave);
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setString(1,usuario.getNombre());
		ps.setString(2,usuario.getApellidos());
		ps.setString(3,usuario.getEmail());
		ps.setString(4,usuario.getFechaNac());
		ps.setString(5,usuario.getTag());
		status = ps.executeUpdate();
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}
  
// Método para actualizar un usuario
public static int update(Contacto usuario){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement(Uupdate);
		ps.setString(1,usuario.getNombre());
		ps.setString(2,usuario.getApellidos());
		ps.setString(3,usuario.getFechaNac());
		ps.setString(4,usuario.getTag());
		// En este caso, 'id' va después, conforme al orden de la sentencia SQL
		ps.setString(5,usuario.getEmail());
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}


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
	    
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 

//borrar una fila
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
