package es.cliente.entidad;

// Creamos la clase videojuego con sus variables y respectivos getters and setters, constructores y método toString

public class Videojuego {

	private int id;
	private String nombre;
	private String company;
	private Double nota;

	public Videojuego() {
		super();
	}

	public Videojuego(int id, String nombre, String company, Double nota) {
		this.id = id;
		this.nombre = nombre;
		this.company = company;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", company=" + company + ", nota=" + nota + "]";
	}

}
