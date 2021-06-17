package entidades;

import java.time.LocalDate;
import java.util.Date;

public class libro {
	
	private long id;
	private String nombre;
	private String autor;
	private String genero;
	private int paginas;
	private Date fecha;
	
	public libro(long id, String nombre, String autor, String genero, int paginas, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.genero = genero;
		this.paginas = paginas;
		this.fecha = fecha;
	}

	public libro(String nombre, String autor, String genero, int paginas, Date fecha) {
		super();
		this.nombre = nombre;
		this.autor = autor;
		this.genero = genero;
		this.paginas = paginas;
		this.fecha = fecha;	}

        public libro(){
            
           
        }

    public libro(long id, String nombre, String autor, String genero, int paginas, java.sql.Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

       public Date getFecha() {
        return fecha;
        }

        public void setFecha(Date fecha) {
        this.fecha = fecha;
        }

    @Override
    public String toString() {
        return "libro{" + "id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", genero=" + genero + ", paginas=" + paginas + ", fecha=" + fecha + '}';
    }

	
	 
}