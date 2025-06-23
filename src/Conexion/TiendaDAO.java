package Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Mundo.Tienda;

public class TiendaDAO {
  private Connection conn;

  public TiendaDAO() {
    conn = new Conexiondb().conectar();
  }

  public void insertarTienda(Tienda tienda) {
    String sql = "INSERT INTO tienda(nombre, direccion) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, tienda.getNombre());
      stmt.setString(2, tienda.getDireccion());
      stmt.executeUpdate();
      System.out.println("Tienda insertada correctamente.");
    } catch (SQLException e) {
      System.err.println("Error al insertar tienda: " + e.getMessage());
    }
  }

  public List<Tienda> obtenerTiendas() {
    List<Tienda> lista = new ArrayList<>();
    String sql = "SELECT * FROM tienda";
    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Tienda tienda = new Tienda();
        tienda.setId(rs.getInt("id"));
        tienda.setNombre(rs.getString("nombre"));
        tienda.setDireccion(rs.getString("direccion"));
        lista.add(tienda);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener tiendas: " + e.getMessage());
    }
    return lista;
  }

  // Cierra la conexión
  public void cerrarConexion() {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
        System.out.println("Conexión cerrada.");
      }
    } catch (SQLException e) {
      System.err.println("Error al cerrar la conexión: " + e.getMessage());
    }
  }
}
