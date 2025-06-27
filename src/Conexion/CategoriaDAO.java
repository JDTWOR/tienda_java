package Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Mundo.Categoria;

public class CategoriaDAO {
    private Connection conn;

    public CategoriaDAO() {
        conn = new Conexiondb().conectar();
    }

    public void insertarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias(nombre) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categoria.getNombre());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Categoría insertada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar categoría: " + e.getMessage());
        }
    }

    public List<Categoria> obtenerCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("id"),
                    rs.getString("nombre"));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener categorías: " + e.getMessage());
        }
        return lista;
    }

    public List<Categoria> obtenerCategoriasPorTienda(int idTienda) {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT c.* FROM categorias c " +
                     "JOIN tienda_categorias tc ON c.id = tc.id_categoria " +
                     "WHERE tc.id_tienda = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTienda);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nombre"));
                    lista.add(categoria);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener categorías por tienda: " + e.getMessage());
        }
        return lista;
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
}
