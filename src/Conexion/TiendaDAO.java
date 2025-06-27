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

  public int insertarTienda(Tienda tienda) {
    String sql = "INSERT INTO tiendas(nombre, contacto, direccion, ruta_imagen) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, tienda.getNombre());
      stmt.setString(2, tienda.getContacto());
      stmt.setString(3, tienda.getDireccion());
      stmt.setString(4, tienda.getRutaImagen());
      stmt.executeUpdate();
      
      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          int idTienda = generatedKeys.getInt(1);
          System.out.println("Tienda insertada correctamente con ID: " + idTienda);
          return idTienda;
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al insertar tienda: " + e.getMessage());
    }
    return -1;
  }

  public void insertarCategoriaTienda(int idTienda, int idCategoria) {
    String sql = "INSERT INTO tienda_categorias(id_tienda, id_categoria) VALUES (?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, idTienda);
      stmt.setInt(2, idCategoria);
      stmt.executeUpdate();
      System.out.println("Relación tienda-categoría insertada correctamente.");
    } catch (SQLException e) {
      System.err.println("Error al insertar relación tienda-categoría: " + e.getMessage());
    }
  }

  public List<Tienda> obtenerTiendas() {
    List<Tienda> lista = new ArrayList<>();
    String sql = "SELECT t.*, GROUP_CONCAT(tc.id_categoria) AS categorias " +
                 "FROM tiendas t LEFT JOIN tienda_categorias tc ON t.id = tc.id_tienda " +
                 "GROUP BY t.id";
    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Tienda tienda = new Tienda(
            rs.getInt("id"), 
            rs.getString("nombre"),
            "Sin categoría",
            rs.getString("contacto"),
            rs.getString("direccion"),
            rs.getString("ruta_imagen"));
        lista.add(tienda);
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener tiendas: " + e.getMessage());
    }
    return lista;
  }

  public Tienda obtenerTiendaPorNombre(String nombre) {
    String sql = "SELECT t.*, " +
                 "(SELECT GROUP_CONCAT(c.nombre SEPARATOR ', ') " +
                 " FROM tienda_categorias tc " +
                 " JOIN categorias c ON tc.id_categoria = c.id " +
                 " WHERE tc.id_tienda = t.id) AS nombre_categoria " +
                 "FROM tiendas t " +
                 "WHERE t.nombre = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, nombre);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          String nombreCategoria = rs.getString("nombre_categoria");
          return new Tienda(
              rs.getInt("id"), 
              rs.getString("nombre"),
              nombreCategoria != null ? nombreCategoria : "Sin categoría",
              rs.getString("contacto"),
              rs.getString("direccion"),
              rs.getString("ruta_imagen"));
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al obtener tienda por nombre: " + e.getMessage());
    }
    return null;
  }

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

  public boolean eliminarTiendaPorNombre(String nombre) {
    String sqlSelect = "SELECT id FROM tiendas WHERE nombre = ?";
    String sqlDeleteRelaciones = "DELETE FROM tienda_categorias WHERE id_tienda = ?";
    String sqlDeleteTienda = "DELETE FROM tiendas WHERE id = ?";
    try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
      stmtSelect.setString(1, nombre);
      try (ResultSet rs = stmtSelect.executeQuery()) {
        if (rs.next()) {
          int idTienda = rs.getInt("id");
          try (PreparedStatement stmtRel = conn.prepareStatement(sqlDeleteRelaciones)) {
            stmtRel.setInt(1, idTienda);
            stmtRel.executeUpdate();
          }
          try (PreparedStatement stmtTienda = conn.prepareStatement(sqlDeleteTienda)) {
            stmtTienda.setInt(1, idTienda);
            int filas = stmtTienda.executeUpdate();
            return filas > 0;
          }
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al eliminar tienda: " + e.getMessage());
    }
    return false;
  }

  public boolean actualizarTiendaPorNombre(String nombreAntiguo, String nuevoNombre, String nuevaCategoria, String nuevoContacto, String nuevaDireccion, int idNuevaCategoria) {
    String sqlSelect = "SELECT id FROM tiendas WHERE nombre = ?";
    String sqlUpdate = "UPDATE tiendas SET nombre = ?, contacto = ?, direccion = ? WHERE nombre = ?";
    String sqlDeleteRel = "DELETE FROM tienda_categorias WHERE id_tienda = ?";
    String sqlInsertRel = "INSERT INTO tienda_categorias(id_tienda, id_categoria) VALUES (?, ?)";
    try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
      stmtSelect.setString(1, nombreAntiguo);
      try (ResultSet rs = stmtSelect.executeQuery()) {
        if (rs.next()) {
          int idTienda = rs.getInt("id");
          // Actualizar datos principales
          try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoContacto);
            stmt.setString(3, nuevaDireccion);
            stmt.setString(4, nombreAntiguo);
            stmt.executeUpdate();
          }
          // Eliminar relación anterior
          try (PreparedStatement stmtDel = conn.prepareStatement(sqlDeleteRel)) {
            stmtDel.setInt(1, idTienda);
            stmtDel.executeUpdate();
          }
          // Insertar nueva relación
          try (PreparedStatement stmtIns = conn.prepareStatement(sqlInsertRel)) {
            stmtIns.setInt(1, idTienda);
            stmtIns.setInt(2, idNuevaCategoria);
            stmtIns.executeUpdate();
          }
          return true;
        }
      }
    } catch (SQLException e) {
      System.err.println("Error al actualizar tienda: " + e.getMessage());
    }
    return false;
  }
}
