package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionMySQL {
    
	public String database = "libreria";
	public String url = "jdbc:mysql://localhost/" + database;
	public String user = "root";
	public String password = "";
	
	public Connection conectar() {
		Connection link = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			link = DriverManager.getConnection(this.url, this.user, this.password);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Error al conectar:" + ex + "\n Asegurese de que el servidor este encendido.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return link;
	}
	

}