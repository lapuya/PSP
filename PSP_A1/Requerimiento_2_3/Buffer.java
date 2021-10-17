package requerimiento2y3;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	
	private final static int MAX_CORREOS = 5; //Solo permitimos cinco correos en el buffer
	private Queue<Email> cola = new LinkedList<Email>();
	
	public synchronized void addEmail(Email email)
	{
		while (cola.size() == MAX_CORREOS)//Mientras que el buffer est· lleno, esperamos.
		{
			try 
			{
				System.out.println("El buffer est√° lleno. Esperando...");
				wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		if (!email.getDestinatario().equals("pikachu@gmail.com"))//No permitimos que se envÌen correos a Pikachu
		{
			cola.offer(email); //Si no es Pikachu, lo aÒadimos al buffer
			System.out.println("Email a√±adido al buffer. Tama√±o del buffer: " + cola.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else//Si no, descartamos
		{
			System.out.println("--------------------------------- EMAIL DESCARTADO ------------------------------------------------");
		}
		notify();
	}
	
	public synchronized String consumirEmail() //Si no hay correos en el buffer, se espera
	{
		while (cola.size() == 0)
		{
			try {
				wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		Email email = cola.poll();//Eliminamos de la cola el email y lo guardamos en el objeto que retornaremos como toString()
		System.out.println("Email consumido. Tama√±o del buffer: " + cola.size());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email.toString();
	}
}
