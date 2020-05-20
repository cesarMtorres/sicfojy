package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ModeloBaseDatos {
	
	public Connection conexion;
	private Statement stmt;
	private ResultSet rs;
	private final String driver = "org.postgresql.Driver"; 
	private final String usuario = "postgres";
	private final String clave = "123456";
	private final String url = "jdbc:postgresql://localhost:5434/sicfojy";
	
	//Constructor de la clase
	public ModeloBaseDatos() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {//Excepcion generada por la clase Class
		}		
	}
	
	//Metodo para crear la conexion con la base de datos
	protected final Connection crearConexion() {
		try {
			conexion = DriverManager.getConnection(url, usuario, clave);
			conexion.setAutoCommit(false);
                        System.out.println("Conectado...");
		} catch (SQLException e) {                        
                    System.out.println("No conectado...");
		}		
		return conexion;
	}
	
	//Metodo para cerrar la conexion con la base de datos
	protected final void cerrarConexion(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	//Método para realizar consultas (select...)
	protected final ResultSet consultarDatos(Connection con, String sql) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
		}		
		return rs;
	}
	
	//Método para actualizar los datos (insert, update, delete...)
	protected boolean actualizarDatos(Connection con, String sql) {
		boolean sw = false;
		try {
			stmt = con.createStatement();
			/* Si al ejecutar executeUpdate retorna un valor distinto de cero
			 * indica que se realiza una modificacion en la base de datos
			 * exitosamente */
			if (stmt.executeUpdate(sql) > 0)
				sw = true;
		} catch (SQLException e) {
			deshacerCambios(con);
		}
		return sw;
	}
        
        
	
	//Metodo para activar el commit
	protected final void realizarCambios(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
		}
	}
	
	//Metodo para ejecutar un rollback y deshacer los cambios
	protected final void deshacerCambios(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
		}
	}
        
        //Agregar el getter para la variable con

    public Connection getConexion() {
        return conexion;
    }
        
}
