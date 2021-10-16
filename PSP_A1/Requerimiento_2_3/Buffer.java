package requerimiento2y3;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	
	private final static int MAX_CORREOS = 5;
	private Queue<Email> cola = new LinkedList<Email>();
	
	public synchronized void addEmail(Email email)
	{
		while (cola.size() == MAX_CORREOS)
		{
			try 
			{
				System.out.println("El buffer está lleno. Esperando...");
				wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		if (!email.getDestinatario().equals("pikachu@gmail.com"))
		{
			cola.offer(email);
			System.out.println("Email añadido al buffer. Tamaño del buffer: " + cola.size());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			System.out.println("--------------------------------- EMAIL DESCARTADO ------------------------------------------------");
		}
		notify();
	}
	
	public synchronized String consumirEmail()
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
		Email email = cola.poll();
		System.out.println("Email consumido. Tamaño del buffer: " + cola.size());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email.toString();
	}
}
