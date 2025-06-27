# Tienda Java

Este proyecto es una aplicación de escritorio en Java para la gestión de tiendas y sus categorías, utilizando una base de datos SQLite. Permite agregar, editar y visualizar tiendas, así como gestionar categorías asociadas.

## Estructura del Proyecto

- **src/Interfaz/**: Contiene las clases de la interfaz gráfica (Swing), como diálogos y paneles para la gestión de tiendas y categorías.
- **src/Mundo/**: Incluye las clases del modelo de dominio (`Tienda`, `Categoria`).
- **src/Conexion/**: Incluye las clases de acceso a datos (DAO) y la conexión a la base de datos (`TiendaDAO`, `CategoriaDAO`, `Conexiondb`).
- **images/**: Carpeta sugerida para almacenar imágenes asociadas a las tiendas.
- **tiendas.db**: Archivo de base de datos SQLite.

## Requisitos

- Java 8 o superior
- [SQLite JDBC Driver](https://github.com/xerial/sqlite-jdbc) (debe estar en la carpeta `lib/`)

## Instalación y Ejecución

1. **Clona el repositorio o descarga el código fuente.**
2. **Asegúrate de tener el driver JDBC de SQLite en la carpeta `lib/`.**
3. **Compila el proyecto:**
   - Si usas Eclipse, importa el proyecto como un proyecto Java existente.
   - Si usas línea de comandos:
     ```sh
     javac -cp "lib/*" -d bin src/Conexion/*.java src/Mundo/*.java src/Interfaz/*.java
     ```
4. **Ejecuta la aplicación:**
   - Desde Eclipse, ejecuta la clase principal (por ejemplo, `PanelPrincipal`).
   - Desde línea de comandos:
     ```sh
     java -cp "bin:lib/*" Interfaz.PanelPrincipal
     ```

## Modelo de Base de Datos

El proyecto utiliza tres tablas principales:
- `tiendas`: Información de cada tienda.
- `categorias`: Lista de categorías disponibles.
- `tienda_categorias`: Relación N:M entre tiendas y categorías.

## Notas
- Las imágenes seleccionadas para las tiendas deben estar en la carpeta `images/` o en una ruta accesible.
- El archivo de base de datos `tiendas.db` se crea automáticamente si no existe.

## Autor
- [Tu Nombre]

---

¡Contribuciones y sugerencias son bienvenidas! 