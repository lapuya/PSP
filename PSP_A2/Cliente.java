import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) throws IOException {

		System.out.println("----------------------------------");
		System.out.println("--------BIBLIOTECA VIRTUAL--------");
		System.out.println("----------------------------------");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

		try (Scanner sc = new Scanner(System.in); Socket socketServidor = new Socket();) {

			boolean continuar = true;

			// Bucle do-while para poder hacer tantas peticiones de libros como se quiera
			// hasta que se introduzca un 3.

			do {
				System.out.println("");
				System.out.println("Escriba el número de la acción deseada:");
				System.out.println("");
				System.out.println("1-Consultar libro por ISBN.");
				System.out.println("2-Consultar libro por título.");
				System.out.println("3-Salir de la aplicación;");

				// Se pide número por pantalla para utilizar el menú
				String n = sc.nextLine();

				switch (n) {

				// Busqueda de libro por ISBN.
				case "1":

					System.out.println("Introduzca el ISBN del libro deseado: ");

					// Le añado el 'tipo' de dato que le estoy mandando (ISBN o título) separado por
					// un '-'.
					String busqueda = sc.nextLine() + "-1";

					// Conectando el Socket con el servidor.
					System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
					socketServidor.connect(direccionServidor);
					System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);

					// Lo que envía el Socket.
					PrintStream salida = new PrintStream(socketServidor.getOutputStream());
					salida.println(busqueda);

					// Lo que trae de vuelta el Socket.
					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					InputStreamReader entrada = new InputStreamReader(socketServidor.getInputStream());
					BufferedReader bf = new BufferedReader(entrada);
					String resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la búsqueda es: " + resultado.toString());

					break;

				// Repito lo anterior buscando por título.
				case "2":
					System.out.println("Introduzca el título del libro deseado: ");

					// Le añado el 'tipo' de dato que le estoy mandando (ISBN o título)
					busqueda = sc.nextLine() + "-2";

					System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
					socketServidor.connect(direccionServidor);
					System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);

					salida = new PrintStream(socketServidor.getOutputStream());
					salida.println(busqueda);

					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					entrada = new InputStreamReader(socketServidor.getInputStream());
					bf = new BufferedReader(entrada);
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la búsqueda es: " + resultado.toString());

					break;

				case "3":
					continuar = false;
					break;

				default:
					break;
				}

			} while (continuar);
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		System.out.println("CLIENTE: Fin del programa");
	}
}