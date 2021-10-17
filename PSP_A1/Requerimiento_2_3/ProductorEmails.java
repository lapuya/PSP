package requerimiento2y3;

public class ProductorEmails implements Runnable {
	/**
	 * Clase que genera emails y los manda a la cola
	 */
	
	private String nombre;
	private Buffer buffer;
	
	public ProductorEmails(String nombre, Buffer buffer)
	{
		this.nombre = nombre;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		GeneradorEmails generador = new GeneradorEmails();
		for(int i = 0; i <= 10; i++)
		{
			Email email = generador.generarEmail();
			System.out.println(this.nombre + " ha generado un email");
			buffer.addEmail(email);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	

}
