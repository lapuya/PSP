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

		String resultado;
		StringBuilder sb = new StringBuilder();
		System.out.println("----------------------------------");
		System.out.println("--------BIBLIOTECA VIRTUAL--------");
		System.out.println("----------------------------------");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);

		try (Scanner sc = new Scanner(System.in)) {

			boolean continuar = true;
			// Conectando el Socket con el servidor.
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexi�n");
			Socket socketServidor = new Socket();
			socketServidor.connect(direccionServidor);
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + " por el puerto " + PUERTO);
			InputStreamReader entrada = new InputStreamReader(socketServidor.getInputStream());
			BufferedReader bf = new BufferedReader(entrada);

			// Lo que env�a el Socket.
			PrintStream salida = new PrintStream(socketServidor.getOutputStream());

			// Bucle do-while para poder hacer tantas peticiones de libros como se quiera
			// hasta que se introduzca un 3.

			do {
				System.out.println("");
				System.out.println("Escriba el n�mero de la acci�n deseada:");
				System.out.println("");
				System.out.println("1-Consultar libro por ISBN.");
				System.out.println("2-Consultar libro por t�tulo.");
				System.out.println("3-Consultar libro por autor.");
				System.out.println("4-A�adir libro.");
				System.out.println("5-Salir de la aplicaci�n;");

				// Se pide n�mero por pantalla para utilizar el men�
				String n = sc.nextLine();

				switch (n) {

				// Busqueda de libro por ISBN.
				case "1":

					System.out.println("Introduzca el ISBN del libro deseado: ");

					// Le a�ado el 'tipo' de dato que le estoy mandando (ISBN o t�tulo) separado por
					// un '-'.

					String busqueda = sc.nextLine() + "-1";
					salida.println(busqueda);

					// Lo que trae de vuelta el Socket.
					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la b�squeda es: " + resultado.toString());
					break;

				// Repito lo anterior buscando por t�tulo.
				case "2":
					System.out.println("Introduzca el t�tulo del libro deseado: ");

					// Le a�ado el 'tipo' de dato que le estoy mandando (ISBN o t�tulo)

					busqueda = sc.nextLine() + "-2";
					salida.println(busqueda);
					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la b�squeda es: " + resultado);
					break;

				// B�squeda de libro por autor.

				case "3":
					System.out.println("Introduzca el autor: ");
					busqueda = sc.nextLine() + "-3";
					salida.println(busqueda);
					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la b�squeda es: " + resultado);

					break;

				// Este caso le creamos para el requerimiento 3. Para a�adir un libro a la base
				// de datos.

				case "4":
					System.out.println("Introduzca los datos del libro a a�adir.");
					System.out.println("ISBN: ");
					sb.append(sc.nextLine());
					sb.append("-");
					System.out.println("Titulo: ");
					sb.append(sc.nextLine());
					sb.append("-");
					System.out.println("Autor: ");
					sb.append(sc.nextLine());
					sb.append("-");
					System.out.println("Precio: ");
					sb.append(sc.nextLine());

					String libro = sb.toString();
					System.out.println(libro);
					salida.println(libro);
					System.out.println("CLIENTE: Esperando al resultado del servidor...");
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado de la operaci�n es: " + resultado);
					break;

				// Con esto salimos de la aplicaci�n.

				case "5":
					busqueda = "5";
					salida.println(busqueda);
					resultado = bf.readLine();
					System.out.println("CLIENTE: El resultado del servidor es " + resultado);
					continuar = false;
					break;
				default:
					break;
				}
			} while (continuar);
			socketServidor.close();
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la direcci�n" + IP_SERVER);
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