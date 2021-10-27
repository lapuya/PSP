import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class HiloBiblioteca implements Runnable {

	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;
	private ArrayList<Libro> libros = new ArrayList<Libro>();

	public HiloBiblioteca (Socket socketAlCliente, ArrayList <Libro> libros) {
		numCliente++;
		this.libros = libros;
		hilo = new Thread(this, "Cliente_" + numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();

	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		boolean continuar = true;

		try {
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			BufferedReader bf = new BufferedReader(entrada); //lee la linea de entrada
			while(continuar) {
				String stringRecibido = bf.readLine();
				System.out.println("SERVIDOR BIBLIOTECA: formato recibido: " + stringRecibido);
				if(stringRecibido.equals("4"))
				{
					System.out.println(hilo.getName() + " ha cerrado la comunicacion.");
					continuar = false;
					salida.println("OK");
				} else {
					String[] info = stringRecibido.split("-");
					String formato = info[0];
					String tipo = info[1];
					analizarYBuscar(tipo, formato, libros, salida, socketAlCliente);
				}
			}
			socketAlCliente.close();
		} catch (IOException e) {
			System.err.println("Hilo Biblioteca: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Hilo Biblioteca: Error");
			e.printStackTrace();
		}
	}

	private void analizarYBuscar(String tipo, String formato, ArrayList<Libro> libros2, PrintStream salida, Socket socketAlCliente) throws IOException {
		int i = 0;
		boolean found = false;
		if (tipo.contains("1")) { //busqueda por isbn
			while (!found && i < libros.size()){
				if (libros.get(i).getIsbn().equals(formato)){
					System.out.println("SERVIDOR BIBLIOTECA: Libro encontrado por ISBN");
					salida = new PrintStream(socketAlCliente.getOutputStream());
					salida.println(libros.get(i).toString());
					found = true;
				} else
					i++;
			}
		} else if (tipo.contains("2")){ //Busqueda por titulo
			while (!found && i < libros.size()) {
				if (libros.get(i).getTitulo().equals(formato)) {
					System.out.println("SERVIDOR BIBLIOTECA: Libro encontrado por titulo");
					salida = new PrintStream(socketAlCliente.getOutputStream());
					salida.println(libros.get(i).toString());
					found = true;
				} else
					i++;
			}
		} else if (tipo.contains("3")) { //Busqueda por autor
			buscarPorAutor(formato, libros, salida, socketAlCliente);
		}
		if (i == libros.size()) {
			System.out.println("SERVIDOR BIBLIOTECA: Libro no disponible");
			salida = new PrintStream(socketAlCliente.getOutputStream());
			salida.println("El libro no está disponible o no se encuentra en la biblioteca");
		}
	}

	private void buscarPorAutor(String autor, ArrayList<Libro> libros, PrintStream salida, Socket socketAlCliente) throws IOException {
		StringBuilder sb = new StringBuilder();

		for(Libro l : libros) {
			if (l.getAutor().equals(autor)) {
				sb.append("//**//");
				sb.append(l.toString());
				sb.append("//**//");
			}
		}

		if (sb.length() == 0) {
			System.out.println("SERVIDOR BIBLIOTECA: No se han encontrado libros de este autor");
			salida = new PrintStream(socketAlCliente.getOutputStream());
			salida.println("No hay libros de este autor.");
		} else {
			System.out.println("SERVIDOR BIBLIOTECA: Libro/s encontrados.");
			salida = new PrintStream(socketAlCliente.getOutputStream());
			salida.println(sb.toString());
		}

	}
}
