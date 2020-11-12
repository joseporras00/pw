// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package bdd;

import java.sql.*;
import java.util.Hashtable;

// Sería recomendable tener una clase DAO que guardara los métodos comunes (p.ej. getConnection()) y 
// de la que heredase esta clase y el resto de DAOs
public class AnuncioDAO {
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
		PreparedStatement ps=con.prepareStatement("insert into Anuncios (id,titulo,propietario,destinatarios,cuerpo,fase,tag,fcom,ffin) values(?,?,?,?,?,?,?,?,?)");
		// El orden de los parámetros debe coincidir con las '?' del código SQL
		ps.setInt(1,id);
		ps.setString(2,titulo);
		ps.setString(3,propietario);
		ps.setString(4,destinatarios);
		ps.setString(5,cuerpo);
    ps.setString(6,fase);
    ps.setString(7,tag);
    ps.setString(8,fcom);
    ps.setString(9,ffin);
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
		PreparedStatement ps=con.prepareStatement("update Anuncios set titulo=?,propietario=?,destinatarios=?,cuerpo=?,fase=?,tag=?,fcom=?,ffin=? where id=?");
		ps.setString(1,titulo);
		ps.setString(2,propietario);
		ps.setString(3,destinatarios);
    ps.setString(4,cuerpo);
    ps.setString(5,fase);
    ps.setString(6,tag);
    ps.setString(7,fcom);
    ps.setString(8,ffin);
		ps.setInt(9,id);
		// En este caso, 'id' va después, conforme al orden de la sentencia SQL
		
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}
	return status;
}

// Para la consulta, se ha tomado una estructura Hash (columna-tabla, valor)
public static Hashtable<String,String> queryById (int id) {
	Statement stmt = null; 
	Hashtable<String,String> resul = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("select titulo, propietario, destinatarios, cuerpo, fase, tag, fcom, ffin from Anuncios where id = " + id);
	    while (rs.next()) {
	    	  String titulo = rs.getString("titulo");
	        String propietario = rs.getString("propietario");
		      String destinatarios = rs.getString("destinatarios");
          String cuerpo = rs.getString("cuerpo");
          String fase = rs.getString("fase");
          String tag = rs.getString("tag");
          String fcom = rs.getString("fcom");
          String ffin = rs.getString("ffin");
	        int id = rs.getInt("id");
	        resul = new Hashtable<String,String>();
	        resul.put("Id", Integer.toString(id));
	        resul.put("Titulo", titulo);
		      resul.put("Propietario", propietario);
	        resul.put("Destinatarios", destinatarios);
          resul.put("Cuerpo", cuerpo);
          resul.put("Fase", fase);
          resul.put("Tag", tag);
          resul.put("F. Comienzo", fcom);
          resul.put("F. Fin", ffin);    
	        System.out.println(id + "\n" + titulo +
	                               "\n" + propietario + "\n" + destinatarios + "\n" + cuerpo + "\n" + fase + "\n" + tag + "\n" + fcom + "\n" + ffin + "\n\n");
	    }
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return resul;
} 

public static int delete(int id){
	int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from Anuncios where id=?");
		ps.setId(1,id);
		status=ps.executeUpdate();
	}catch(Exception e){System.out.println(e);}

	return status;
}
}
