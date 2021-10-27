import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServidor {

	public static final int PUERTO = 2017;

	public static void main(String[] args) {

		//Carga de Libros preestablecidos
		//-----------------------------------------------------------------------------------
		ArrayList<Libro> libros = new ArrayList<Libro>();
		Libro l1 = new Libro("1234", "La sombra del viento", "Carlos Ruiz Zafon", 10.95);
		Libro l2 = new Libro("5678", "The Lord of the Rings", "J.R.R. Tolkien", 19.90);
		Libro l3 = new Libro("4321", "El Codigo da Vinci", "Dan Brown", 16.95);
		Libro l4 = new Libro("8765", "El diario de Ana Frank", "Anna Frank", 12.00);
		Libro l5 = new Libro("1111", "Fundacion", "Isaac Asimov", 14.20);
		//añadir libros
		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
		libros.add(l5);
		//-----------------------------------------------------------------------------------

		System.out.println("          APLICACIÓN SERVIDOR          ");
		System.out.println("---------------------------------------");

		//Inicialización de todas las clases necesarias para el funcionamiento del socket
		//-----------------------------------------------------------------------------------
		InputStreamReader entrada = null;
		PrintStream salida = null;
		Socket socketAlCliente = null;
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		//-----------------------------------------------------------------------------------

		try (ServerSocket serverSocket = new ServerSocket()) {

			//Escuchar peticiones desde el puerto
			serverSocket.bind(direccion);

			int peticion = 0;

			while(true) {
				System.out.println("SERVIDOR BIBLIOTECA: esperando peticion por el puerto " + PUERTO);

				socketAlCliente = serverSocket.accept();
				System.out.println("SERVIDOR BIBLIOTECA: peticion numero " + ++peticion + " recibida");

				entrada = new InputStreamReader(socketAlCliente.getInputStream());
				BufferedReader bf = new BufferedReader(entrada); //lee la linea de entrada

				String stringRecibido = bf.readLine(); //esperamos hasta que el socket cliente me haga un println

				System.out.println("SERVIDOR BIBLIOTECA: peticion recibida: " + stringRecibido);

				//Parseo de la información
				//-----------------------------------------------------------------------------------
				String[] info = stringRecibido.split("-");
				String formato = info[0];
				String tipo = info[1];
				//-----------------------------------------------------------------------------------

				//Tratamiento de la informacion: búsqueda por ISBN o Título
				//-----------------------------------------------------------------------------------
				int i = 0;
				boolean found = false;
				if (tipo.contains("1")) //busqueda por isbn
				{
					while (!found && i < libros.size())
					{
						if (libros.get(i).getIsbn().equals(formato))
						{
							System.out.println("SERVIDOR BIBLIOTECA: Libro encontrado por ISBN");
							salida = new PrintStream(socketAlCliente.getOutputStream());
							salida.println(libros.get(i).toString());
							found = true;
						}
						i++;
					}

				} else if (tipo.contains("2")) //Busqueda por titulo
				{
					while (!found && i < libros.size())
					{
						if (libros.get(i).getTitulo().equals(formato))
						{
							System.out.println("SERVIDOR BIBLIOTECA: Libro encontrado por titulo");
							salida = new PrintStream(socketAlCliente.getOutputStream());
							salida.println(libros.get(i).toString());
							found = true;
						}
						i++;
					}
				}
				//-----------------------------------------------------------------------------------


				//Caso de que el libro no esté en la biblioteca
				//-----------------------------------------------------------------------------------
				if (i == libros.size()) {
					System.out.println("SERVIDOR BIBLIOTECA: Libro no disponible");
					salida = new PrintStream(socketAlCliente.getOutputStream());
					salida.println("El libro no está disponible o no se encuentra en la biblioteca");
				}
				//-----------------------------------------------------------------------------------
				socketAlCliente.close();
			}
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error -> " + e);
			e.printStackTrace();
		}
	}

}
