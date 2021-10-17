package Primos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long num;
		ArrayList<Thread> numbersThreads = new ArrayList<Thread>();  //Creacion de la colección que albergará los hilos
		num = 0;
		Scanner number = new Scanner(System.in); //Creación del objeto number
		for(int i = 0; i < 4; i++)  // Para recorrer  hasta cuatro números
		{
			System.out.println("Put number " + i + ": ");
			num = number.nextLong(); // para  longroducir el siguiente número
			PrimeCalculator numRunnable = new PrimeCalculator(num);
			Thread t1 = new Thread(numRunnable); //Creación de un hilo
			numbersThreads.add(t1); //se añade el hilo a la colección
		}
		
		for(int i = 0; i < numbersThreads.size(); i++) //Recorre la colección para arrancar cada hilo
			numbersThreads.get(i).start();
		number.close(); //Cierre del método Scanner
	}

}
