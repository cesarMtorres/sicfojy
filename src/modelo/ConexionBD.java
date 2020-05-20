package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	
	protected String clave;
	protected String usuario;
	protected String driver;
        private Statement stmt;
        private Connection conexion;
        private ResultSet rs;
	protected String driverUrl;
	protected final String database = "sicfojy";
        protected final String ip = "127.0.0.1";
        protected final String puerto = "5434";
	protected Connection con = null;

	public ConexionBD() 
	{
		Ini1();
	}
	public void Ini1()
	{
		this.driver = "org.postgresql.Driver";
		this.driverUrl = "jdbc:postgresql://localhost:5434/sicfojy";
		this.usuario = "postgres";
		this.clave = "123456";
	}
	public void Ini2()
	{
		this.driver="com.mysql.jdbc.Driver";
		this.driverUrl="jdbc:mysql://localhost/sicfojy";
		this.usuario="postgres";
		this.clave="123456";
	}

	public boolean conectar(){
	Boolean con_status = true;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(driverUrl, usuario, clave);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			con_status = false;
		} catch (SQLException e) {
			e.printStackTrace();
			con_status = false;
		}
		return con_status;
	}
	public int ejecutarActualizacion(String sql) {
		Statement sen;
		int a;
		try {
			sen = con.createStatement();
			a = sen.executeUpdate(sql);
			return a;
		} 
		catch (SQLException e) 
		{ 
			e.printStackTrace();
		return 0;
		}
	};
	public ResultSet ejecutarConsulta(String sql) {
		Statement sen;
		ResultSet rs;
		try { 
		    sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = sen.executeQuery(sql);
			if (rs.next()) {
				rs.beforeFirst();
				return rs;
			} else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void cerrar() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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
        
	public Connection getCon() {
		return con;
	}
	public void setConnectString(String driverUrl) {
		this.driverUrl = driverUrl;
	}
      protected ResultSet consultarDatos(Connection con, String sql) {

	try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            } catch (SQLException e) {

		e.printStackTrace();

            }

        return rs;

    }
      protected final Connection crearConexion() {

        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://"+ ip  +":" + puerto + "/" + database , usuario, clave);

            conexion.setAutoCommit(false);

            System.out.println("Conectado...");

        } catch (SQLException e) {

            e.printStackTrace();

            System.err.println("No conectado...");

        }
            return conexion;
    }
       protected final void cerrarConexion(Connection con) {

        if (con != null) {

            try {
                con.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }
}