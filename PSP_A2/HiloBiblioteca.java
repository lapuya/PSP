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
	private ArrayList<Libro> libros;
	
	public HiloBiblioteca (Socket socketAlCliente, ArrayList <Libro> libros) {
		numCliente++;
		this.libros = new ArrayList<Libro>();
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
		BufferedReader entradaBuffer = null;
		boolean continuar = true;
		
		try {
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			BufferedReader bf = new BufferedReader(entrada); //lee la linea de entrada
			while(continuar)
			{
				String stringRecibido = bf.readLine(); 
				System.out.println(stringRecibido);
				if(stringRecibido.equals("3"))
				{
					System.out.println(hilo.getName() + " ha cerrado la comunicacion.");
					continuar = false;
					salida.println("Salir");
				} else {
					String[] info = stringRecibido.split("-");
					String formato = info[0];
					String tipo = info[1];
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
							} else
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
							} else
								i++;
						}
					}
					if (i == libros.size()) {
						System.out.println("SERVIDOR BIBLIOTECA: Libro no disponible");
						salida = new PrintStream(socketAlCliente.getOutputStream());
						salida.println("El libro no está disponible o no se encuentra en la biblioteca");
					}
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
	

}
