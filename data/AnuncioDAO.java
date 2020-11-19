// En la práctica, la clase debería ir en <es.uco.pw.data.dao>
package ejercicio1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 
 * @author Jose Manuel Porras, Juan Valverde
 * clase AnuncioDao
 *
 */
public class AnuncioDAO {
	
	
	private static String Asave, Aupdate, AselectI,AselectF,AselectT,AselectP,AselectD, Adelete;
	AnuncioDAO()
	{
		Properties sql = new Properties();
		
		try {
			FileInputStream is = new FileInputStream("C:\\Users\\usuario\\Desktop\\recuperado\\pw-juanvs00-patch-1\\config.properties.txt");
			sql.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		Asave = sql.getProperty("sql.Usave");
		Aupdate = sql.getProperty("sql.Uupdate");
		AselectI = sql.getProperty("sql.UselectI");
		AselectF = sql.getProperty("sql.UselectF");
		AselectT = sql.getProperty("sql.UselectT");
		AselectP = sql.getProperty("sql.UselectP");
		AselectD = sql.getProperty("sql.UselectD");
		Adelete = sql.getProperty("sql.Udelete");
	}
	
	
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
	  con= DriverManager.getConnection("jdbc:mysql://" + Main.server + ":" + Main.puerto + "/" + Main.usuario,Main.usuario,Main.pass);
	// Importante capturar 
	} catch(Exception e) {
	  System.out.println(e);
	}
	return con;
  }
  
  // Método para insertar una fila
  // En ningún caso es recomendable el paso por parámetro de los valores individuales
  // Se recomienda utilizar el UserBean o una clase envoltorio User que tenga estas propiedades
  public static int save(Anuncio anuncio){
	int status=0;
	try{
		Connection con=getConnection();
		// PreparedStament será más rápido (si es uso recurrente) y permite invocación a parámetros
		// Lo habitual es que las consultas y sentencias SQL estén en un fichero de propiedades aparte, no en código
		PreparedStatement ps=con.prepareStatement(Asave);
		// El orden de los parámetros debe coincidir con las '?' del código SQL
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
	// Importante capturar las excepciones. Si nuestra aplicaciones tiene más opciones de fallo,
	// podemos capturar directamente SQLException
	}catch(Exception e){System.out.println(e);}
	// El invocante siempre debería tener información del resultado de la sentencia SQL
	return status;
}
  
// Método para actualizar un usuario
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
		// En este caso, 'id' va después, conforme al orden de la sentencia SQL
		
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
	    ResultSet rs = stmt.executeQuery(AselectI);
	    while (rs.next()) {
	    	String titulo = rs.getString("Titulo");
	        String propietario = rs.getString("Propietario");
	        String destinatario = rs.getString("Destinatarios");
         	String cuerpo = rs.getString("Cuerpo");
         	String fase = rs.getString("Fase");
         	String tag = rs.getString("Tags");
         	String fcom = rs.getString("F. Comienzo");
         	String ffin = rs.getString("F. Fin");

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
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	System.out.println("No se ha encontrado ning�n anuncio");
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
}

public static Anuncio queryByFecha (String ffin) {
	Statement stmt = null; 
	Anuncio aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(AselectF);
	    while (rs.next()) {
	    	int id= rs.getInt("id")
	    	String titulo = rs.getString("titulo");
	        String propietario = rs.getString("propietario");
	        String destinatario = rs.getString("destinatario");
         	String cuerpo = rs.getString("cuerpo");
         	String fase = rs.getString("fase");
         	String tag = rs.getString("tag");
         	String fcom = rs.getString("fcom");
         	
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
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	System.out.println("No se ha encontrado ning�n anuncio");
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 

public static Anuncio queryByTag (int tag) {
	Statement stmt = null; 
	Anuncio aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(AselectT);
	    while (rs.next()) {
	    	int id= rs.getInt("id")
	    	String titulo = rs.getString("titulo");
	        String propietario = rs.getString("propietario");
	        String destinatario = rs.getString("destinatario");
         	String cuerpo = rs.getString("cuerpo");
         	String fase = rs.getString("fase");
         	String fcom = rs.getString("fcom");
         	enum tags= rs.getEnum("tags");
         	
	        aux = new Anuncio();
	        aux.setId(id);
	        aux.setTitulo(titulo);
	        aux.setPropietario(propietario);
	        aux.setDestinatario(destinatario);
	        aux.setCuerpo(cuerpo);
	        aux.setFase(fase);
	        aux.setTag(tags);
	        aux.setFechaComienzo(fcom);
	        aux.setFechaFin(ffin);
          	
	        System.out.println(id + "\n" + titulo +
	                               "\n" + propietario + "\n" + destinatario + "\n" + cuerpo + "\n" + fase + "\n" + tag + "\n" + fcom + "\n" + ffin + "\n\n");
	    }
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	System.out.println("No se ha encontrado ning�n anuncio");
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 


public static Anuncio queryByPropietario (String propietario) {
	Statement stmt = null; 
	Anuncio aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(AselectP);
	    while (rs.next()) {
	    	int id= rs.getInt("id")
	    	String titulo = rs.getString("titulo");
	        String destinatario = rs.getString("destinatarios");
         	String cuerpo = rs.getString("cuerpo");
         	String fase = rs.getString("fase");
         	String tag = rs.getString("tag");
         	String fcom = rs.getString("fcom");
         	String fcom = rs.getString("ffin");
         	
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
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	System.out.println("No se ha encontrado ning�n anuncio");
	    	stmt.close(); 
	} catch (Exception e) {
		System.out.println(e);
	} 
	return aux;
} 

public static Anuncio queryByDestinatario (String destinatario) {
	Statement stmt = null; 
	Anuncio aux = null;
	try {
		Connection con=getConnection();
		// En consultas, se hace uso de un Statement 
		stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(AselectD);
	    while (rs.next()) {
	    	int id= rs.getInt("id")
	    	String titulo = rs.getString("titulo");
	        String propietario = rs.getString("propietario");
	        String destinatario = rs.getString("destinatarios");
         	String cuerpo = rs.getString("cuerpo");
         	String fase = rs.getString("fase");
         	enum tags = rs.getEnum("tags");
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
	    // Se debe tener precaución con cerrar las conexiones, uso de auto-commit, etc.
	    if (stmt != null) 
	    	System.out.println("No se ha encontrado ning�n anuncio");
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
