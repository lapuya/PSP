package requerimiento2y3;

import java.util.ArrayList;

public class Main { //Creación del buffer, productores,consumidores e hilos

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Buffer buffer = new Buffer();
		
		ProductorEmails pe1 = new ProductorEmails("Productor 1", buffer);
		ProductorEmails pe2 = new ProductorEmails("Productor 2", buffer);
		ProductorEmails pe3 = new ProductorEmails("Productor 3", buffer);
		
		ConsumidorEmails ce1 = new ConsumidorEmails("Consumidor 1", buffer);
		ConsumidorEmails ce2 = new ConsumidorEmails("Consumidor 2", buffer);
		Thread t1 = new Thread(pe1);
		Thread t2 = new Thread(pe2);
		Thread t3 = new Thread(pe3);
		Thread t4 = new Thread(ce1);
		Thread t5 = new Thread(ce2);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();


	}

}
