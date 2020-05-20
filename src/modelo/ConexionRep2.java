/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abe
 */
public class ConexionRep2 {
    
    private Connection conn;
    private Statement stm;
    private ResultSet rst;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public ResultSet getRst() {
        return rst;
    }

    public void setRst(ResultSet rst) {
        this.rst = rst;
    }

    public void conectar() throws ClassNotFoundException, SQLException {

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5434/sicfojy", "postgres", "123456");

        } finally {

        }
    }

    public void desconectar() throws SQLException {

        conn.close();

    }

    public ResultSet selectAutomovilesDisponibles() throws ClassNotFoundException, SQLException {

        this.conectar();

        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT A.Placa, M.Modelo, Anno, Precio FROM Modelos M, Automoviles A"
                + " WHERE (M.IdModelo = A.Modelo) and (A.Estado = 'Disponible');");

        return rslt;

    }

    public ResultSet selectAutomovilesComprados() throws ClassNotFoundException, SQLException {

        this.conectar();

        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT C.Nombre, C.Cedula, C.Numero_Tarjeta, M.Modelo FROM Clientes C, Factura, Modelos M, Automoviles A, Autos_Comprados AC"
                + " WHERE (M.idModelo = A.Modelo) AND (AC.Placa = A.Placa);");

        return rslt;

    }

    public ResultSet selectMarcasDisponibles() throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT DISTINCT M.Nombre FROM Marcas M, Modelos MO, Automoviles A"
                + " WHERE (MO.Marca = M.idMarca) AND (MO.idModelo = A.Modelo) AND (A.Estado = \"Disponible\");");
        return rslt;
    }

    public ResultSet selectModelosDisponibles() throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT DISTINCT M.Modelo FROM Modelos M, Automoviles A"
                + " WHERE (A.Estado = \"Disponible\") AND (M.IdModelo = A.Modelo);");
        return rslt;
    }

    public ResultSet selectModelosXMarca(String marca) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT DISTINCT MO.Modelo FROM Marcas MA, Modelos MO, Automoviles A"
                + " WHERE (MA.idMarca = MO.Marca) AND (MA.Nombre = \"" + marca + "\") AND (A.Estado = \"Disponible\")"
                + "  AND (MO.IdModelo = A.Modelo);");
        return rslt;

    }

    public ResultSet selectAñosXModelo(String marca, String modelo) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT DISTINCT MO.Anno FROM Marcas MA, Modelos MO, Automoviles A"
                + " WHERE (MA.idMarca = MO.Marca) AND (MA.Nombre = \"" + marca + "\") AND (A.Estado = \"Disponible\") AND (MO.Modelo = \"" + modelo + "\")"
                + "  AND (MO.IdModelo = A.Modelo);");
        return rslt;

    }

    public ResultSet selectPrecioPorModelo(String marca, String modelo, Object anno) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT MO.Precio FROM Marcas MA, Modelos MO, Automoviles A"
                + " WHERE (MA.idMarca = MO.Marca) AND (MA.Nombre = \"" + marca + "\") AND (A.Estado = \"Disponible\") AND (MO.Modelo = \"" + modelo + "\")"
                + "  AND (MO.IdModelo = A.Modelo) AND (MO.Anno = " + anno + ");");
        return rslt;

    }

    public ResultSet selectColoresPorModelo(String marca, String modelo, Object anno) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT DISTINCT A.Color FROM Marcas MA, Modelos MO, Automoviles A"
                + " WHERE (MA.idMarca = MO.Marca) AND (MA.Nombre = \"" + marca + "\") AND (A.Estado = \"Disponible\") AND (MO.Modelo = \"" + modelo + "\")"
                + "  AND (MO.IdModelo = A.Modelo) AND (MO.Anno = " + anno + ");");
        return rslt;

    }

    public ResultSet selectPlaca(String marca, String modelo, Integer anno, String color) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rslt = stmt.executeQuery("SELECT A.Placa FROM Marcas MA, Modelos MO, Automoviles A"
                + " WHERE (MA.idMarca = MO.Marca) AND (MA.Nombre = \"" + marca + "\") AND (A.Estado = \"Disponible\") AND (MO.Modelo = \"" + modelo + "\")"
                + "  AND (MO.IdModelo = A.Modelo) AND (MO.Anno = " + anno + ") AND (A.Color = \"" + color + "\");");
        return rslt;

    }

    public void cambiarEstadoComprado(int placa) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE Automoviles SET Estado = \"Comprado\" WHERE (Placa = " + placa + ");");

    }

    public void cambiarEstadoDisponible(int placa) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE Automoviles SET Estado = \"Disponible\" WHERE (Placa = " + placa + ");");

    }

    public void agregarCliente(String nombre, int cedula, int tarjeta) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO CLIENTES (NOMBRE, CEDULA, NUMERO_TARJETA)"
                + " VALUES (\"" + nombre + "\"," + cedula + "," + tarjeta + ")");

    }

    public int generarFactura(int cedula) throws ClassNotFoundException, SQLException {

        this.conectar();
        int id = 0;

        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery("SELECT IDCLIENTE FROM CLIENTES WHERE (CEDULA = " + cedula + ")");

        while (rst.next()) {

            id = (Integer) rst.getObject("IDCLIENTE");

        }

        stmt.execute("INSERT INTO FACTURA (CLIENTE, FECHA) VALUES (" + id + ", '2014-06-14 00:00:00')");
        return id;
    }

    public void comprarAuto(int id, int placa) throws ClassNotFoundException, SQLException {

        this.conectar();
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Autos_Comprados (PLACA, CLIENTE, FECHA) VALUES (" + placa + ", " + id + ", '2014-06-14 00:00:00')");

    }
    
}
