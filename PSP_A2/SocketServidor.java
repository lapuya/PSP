import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServidor {

	public static final int PUERTO = 2017;

	public static void main(String[] args) {

		// Carga de Libros preestablecidos
		// -----------------------------------------------------------------------------------
		ArrayList<Libro> libros = new ArrayList<Libro>();
		libros = cargarLibros();

		// -----------------------------------------------------------------------------------

		System.out.println("          APLICACIÓN SERVIDOR          ");
		System.out.println("---------------------------------------");

		// Inicialización de todas las clases necesarias para el funcionamiento del
		// socket
		// -----------------------------------------------------------------------------------
		int peticion = 0;
		// -----------------------------------------------------------------------------------

		try (ServerSocket serverSocket = new ServerSocket()) {
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);

			// Para escuchar peticiones desde el puerto
			serverSocket.bind(direccion);

			System.out.println("SERVIDOR BIBLIOTECA: esperando peticion por el puerto " + PUERTO);

			while (true) {

				Socket socketAlCliente = serverSocket.accept();
				System.out.println("SERVIDOR BIBLIOTECA: peticion numero " + ++peticion + " recibida");

				new HiloBiblioteca(socketAlCliente, libros);
			}
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error -> " + e);
			e.printStackTrace();
		}
	}

	private static ArrayList<Libro> cargarLibros() {

		ArrayList<Libro> libros = new ArrayList<Libro>();

		Libro l1 = new Libro("1234", "La sombra del viento", "Carlos Ruiz Zafon", 10.95);
		Libro l2 = new Libro("5678", "El juego del Ángel", "Carlos Ruiz Zafon", 19.90);
		Libro l3 = new Libro("4321", "El Codigo da Vinci", "Dan Brown", 16.95);
		Libro l4 = new Libro("8765", "El diario de Ana Frank", "Anna Frank", 12.00);
		Libro l5 = new Libro("1111", "Fundacion", "Isaac Asimov", 14.20);
		// añadir libros
		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
		libros.add(l5);

		return libros;
	}

}