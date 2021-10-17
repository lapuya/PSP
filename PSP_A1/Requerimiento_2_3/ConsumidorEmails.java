package requerimiento2y3;

public class ConsumidorEmails implements Runnable {
	
	private String nombre;
	private Buffer buffer;
	
	public ConsumidorEmails(String nombre, Buffer buffer)
	{
		this.nombre = nombre;
		this.buffer = buffer;
	}
	
	@Override
	public void run() { //Método que consume los emails
		// TODO Auto-generated method stub
		for (int i = 0; i <= 15; i++)
		{
			System.out.println("Nombre del hilo consumidor: " + this.nombre + "\n" + buffer.consumirEmail());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
