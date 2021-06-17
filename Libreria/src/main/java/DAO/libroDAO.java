package DAO;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import db.jdbcHelper;
import entidades.libro;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Fecha;

public class libroDAO extends libro{


    public libroDAO(String nombre, String autor, String genero, int paginas, Date fecha) {
        super(nombre, autor, genero, paginas, fecha);
    }
    
    public libroDAO(){
        
    }

	public boolean insertar(libro libro) {
		String query = "INSERT INTO libro (nombre, autor, genero, paginas, fecha)" + "VALUES ('" + this.getNombre()
				+ "','" + this.getAutor() + "','" + this.getGenero() + "','" + this.getPaginas() + "','"
				+ this.getFecha() + "','";
		jdbcHelper jdbc = new jdbcHelper();
		boolean exito = jdbc.ejecutarQuery(query);
		return exito;
	}

	public boolean modificar(libro libro) {
		String query = "UPDATE libro SET " + "nombre = '" + this.getNombre() + "'," + "autor = '" + this.getAutor()
				+ "'," + "genero = '" + this.getGenero() + "'," + "paginas = '" + this.getPaginas() + "',"
				+ "fecha = '" + this.getFecha() + "'," + "' WHERE id = " + this.getId();
		jdbcHelper jdbc = new jdbcHelper();
		boolean exito = jdbc.ejecutarQuery(query);
		return exito;
	}

	public boolean eliminar(long id) {
                String query = "DELETE FROM libro WHERE id = " + id;
		jdbcHelper jdbc = new jdbcHelper();
		boolean exito = jdbc.ejecutarQuery(query);
		return exito;
	}

	public boolean validar(libro libro) {
		StringBuilder sb = new StringBuilder();
		boolean esValido = true;
		if (libro == null) {
			esValido = false;
			sb.append("*No existe el libro\n");
		}
		if (getNombre().equals("")) {
			esValido = false;
			sb.append("*Nombre requerido\n");
		}
		if (getNombre().length() > 100) {
			esValido = false;
			sb.append("*Nombre debe ser menor a 100 caracteres\n");
		}
		if (getAutor().equals("")) {
			esValido = false;
			sb.append("*Autor requerido\n");
		}
		if (getAutor().length() > 100) {
			esValido = false;
			sb.append("*Autor debe ser menor a 100 caracteres\n");
		}
		if (getGenero().equals("")) {
			esValido = false;
			sb.append("*Genero requerido\n");
		}
		if (getPaginas() <= 0) {
			esValido = false;
			sb.append("*Paginas debe ser mayor a 0\n");
		}
		 else {
                }
		if(!esValido) {
			JOptionPane.showMessageDialog(null,"Se encontraron los siguientes errores: " + sb.toString(), "Error de validacion", JOptionPane.WARNING_MESSAGE);
		}
		return esValido;
	}
	
	public boolean guardar(libro libro){
		if(validar(libro) == false) {
			return false;
		}
		boolean exito;
		if(libro.getId() == 0) {
			exito = insertar(libro);
		}else {
			exito = modificar(libro);
		}
		return exito;
	}
	
	public libro buscarLibro(long idBusqueda) {
		String query ="SELECT * FROM libro where id = " + idBusqueda;
		jdbcHelper jdbc = new jdbcHelper();
		ResultSet rs = jdbc.realizarConsulta(query);
		libro libro = null;
		try {
			if(rs.next()) {
				long id = idBusqueda;
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				int paginas = rs.getInt("paginas");
				Date fecha = rs.getDate("fecha");
				libro = new libro(id, nombre, autor, genero, paginas, fecha);
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al buscar libro: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return libro;
	}
	
	public ObservableList<libro> buscarTodos(){
		String query = "SELECT * FROM libro";
		jdbcHelper jdbc = new jdbcHelper();
		ResultSet rs = jdbc.realizarConsulta(query);
		
		ObservableList<libro> libros = FXCollections.observableArrayList();
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				int paginas = rs.getInt("paginas");
				Date fecha = rs.getDate("fecha");
				libros.add(new libro(id, nombre, autor, genero, paginas, fecha));
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al buscar libro: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return libros;
	}
	
	public ObservableList<libro> buscarPorNombre(String nombreB){
		String query = "SELECT * FROM libro WHERE nombre LIKE '%" + nombreB + "%'";
		jdbcHelper jdbc = new jdbcHelper();
		ResultSet rs = jdbc.realizarConsulta(query);
		
		ObservableList<libro> libros = FXCollections.observableArrayList();
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				int paginas = rs.getInt("paginas");
				Date fecha = rs.getDate("fecha");
				libros.add(new libro(id, nombre, autor, genero, paginas, fecha));
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al buscar libro por nombre: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return libros;
	} 
	
	public ObservableList<libro> buscarPorAutor(String autorB){
		String query = "SELECT * FROM libro WHERE autor LIKE '%" + autorB + "%'";
		jdbcHelper jdbc = new jdbcHelper();
		ResultSet rs = jdbc.realizarConsulta(query);
		
		ObservableList<libro> libros = FXCollections.observableArrayList();
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				int paginas = rs.getInt("paginas");
				Date fecha = rs.getDate("fecha");
				libros.add(new libro(id, nombre, autor, genero, paginas, fecha));
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al buscar libro por autor: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return libros;
	} 
	
	public ObservableList<libro> buscarPorGenero(String generoB){
		String query = "SELECT * FROM libro WHERE genero LIKE '%" + generoB + "%'";
		jdbcHelper jdbc = new jdbcHelper();
		ResultSet rs = jdbc.realizarConsulta(query);
		
		ObservableList<libro> libros = FXCollections.observableArrayList();
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				String genero = rs.getString("genero");
				int paginas = rs.getInt("paginas");
				Date fecha = rs.getDate("fecha");
				libros.add(new libro(id, nombre, autor, genero, paginas, fecha));
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al buscar libro por genero.: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return libros;
	} 
}