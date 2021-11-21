package es.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.cliente.entidad.Videojuego;
import es.cliente.servicio.ServicioProxyVideojuego;

@SpringBootApplication
public class PspA3RestClienteApplication implements CommandLineRunner {

	// Inyectamos los objetos que necesitamos para acceder a ServicioProxyVideojuego

	@Autowired
	private ServicioProxyVideojuego spv;

	// Inyectamos el propio contexto de Spring, ya que es una app web
	// que se lanzará en un tomcat y necesitaremos poder pararla.

	@Autowired
	private ApplicationContext context;

	// Realiza las comunicaciones http a nuestro servicio REST
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Método main que lanza la aplicación

	public static void main(String[] args) {
		SpringApplication.run(PspA3RestClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		int op;

		op = menu();
		while (op != 6) {
			if (op == 1)
				altaVideojuego();
			else if (op == 2)
				bajaVideojuego();
			else if (op == 3)
				modificarVideojuego();
			else if (op == 4)
				obtenerVideojuegoId();
			else if (op == 5)
				listarVideojuegos();
			op = menu();

		}

		System.out.println("******************** CERRANDO APLICACION ********************");

		// Paramos nuestra aplicación Spring Boot
		pararAplicacion();
	}

	// Método para obtener un videojuego por ID

	public void obtenerVideojuegoId() {
		int id;

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la id del videojuego a buscar ");
		id = sc.nextInt();
		System.out.println(spv.obtener(id));
	}

	// Método para modificar un videojuego por ID

	private void modificarVideojuego() {
		Scanner sc = new Scanner(System.in);
		int id;
		String nombre;
		String company;
		Double nota;

		Videojuego vModificar = new Videojuego();

		System.out.println("Introduzca el ID del videojuego a modificar: ");
		id = sc.nextInt();
		vModificar.setId(id);

		sc.nextLine();

		System.out.println("Introduce el nuevo nombre: ");
		nombre = sc.nextLine();
		vModificar.setNombre(nombre);

		System.out.println("Introduce la empresa: ");
		company = sc.nextLine();
		vModificar.setCompany(company);

		System.out.println("Introduce la nota: ");
		nota = sc.nextDouble();
		vModificar.setNota(nota);

		boolean modificada = spv.modificar(vModificar);
		System.out.println("run -> videojuego modificado? " + modificada);

	}

	// Método para dar de baja un videojuego por ID

	public void bajaVideojuego() {
		Scanner sc = new Scanner(System.in);
		int id;

		System.out.println("Introduzca el id del videojuego a borrar");
		id = sc.nextInt();

		boolean borrada = spv.borrar(id);
		System.out.println("run -> Persona con id " + id + " borrada? " + borrada);

	}

	// Mediante el método exit de la clase SpringApplication paramos la aplicación.
	// lo simplificamos con esta función lambda.

	public void pararAplicacion() {
		SpringApplication.exit(context, () -> 0);
	}

	// Método para obtener una lista de todos los videojuegos

	public void listarVideojuegos() {
		List<Videojuego> lista = spv.listar();
		for (int i = 0; i < lista.size(); i++)
			System.out.println(lista.get(i));

	}

	// Método para dar de alta un videojuego

	public void altaVideojuego() {

		String nombre;
		String company;
		Double nota;
		int id;

		Scanner sc = new Scanner(System.in);
		Videojuego v = new Videojuego();

		System.out.println("Introduzca el nombre del videojuego: ");
		nombre = sc.nextLine();

		System.out.println("Introduzca la empresa del videojuego: ");
		company = sc.nextLine();

		System.out.println("Introduzca el id del videojuego: ");
		id = sc.nextInt();

		System.out.println("Introduzca la nota del videojuego: ");
		nota = sc.nextDouble();

		v.setId(id);
		v.setNombre(nombre);
		v.setCompany(company);
		v.setNota(nota);

		spv.alta(v);
		System.out.println("Videojuego dado de alta");
	}

	// Creamos el menú y le pasamos los datos por consola.

	public int menu() {
		Scanner sc = new Scanner(System.in);
		int op;

		System.out.println("***************** MENU *****************");
		System.out.println("Elija una opcion");
		System.out.println("1. Dar de alta un videojuego");
		System.out.println("2. Dar de baja un videojuego por ID");
		System.out.println("3. Modificar un videojuego por ID");
		System.out.println("4. Obtener un videojuego por ID");
		System.out.println("5. Listar todos los videojuegos");
		System.out.println("6. Salir");

		op = sc.nextInt();
		while (op < 1 || op > 6) {
			System.out.println("Error en la eleccion. Escoja una de las siguientes opciones");
			System.out.println("1. Dar de alta un videojuego");
			System.out.println("2. Dar de baja un videojuego por ID");
			System.out.println("3. Modificar un videojuego por ID");
			System.out.println("4. Obtener un videojuego por ID");
			System.out.println("5. Listar todos los videojuegos");
			System.out.println("6. Salir");
			op = sc.nextInt();
		}

		return op;

	}

}

