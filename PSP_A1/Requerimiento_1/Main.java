package Primos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long num;
		ArrayList<Thread> numbersThreads = new ArrayList<Thread>();  //Creacion de la colecci�n que albergar� los hilos
		num = 0;
		Scanner number = new Scanner(System.in); //Creaci�n del objeto number
		for(int i = 0; i < 4; i++)  // Para recorrer  hasta cuatro n�meros
		{
			System.out.println("Put number " + i + ": ");
			num = number.nextLong(); // para  longroducir el siguiente n�mero
			PrimeCalculator numRunnable = new PrimeCalculator(num);
			Thread t1 = new Thread(numRunnable); //Creaci�n de un hilo
			numbersThreads.add(t1); //se a�ade el hilo a la colecci�n
		}
		
		for(int i = 0; i < numbersThreads.size(); i++) //Recorre la colecci�n para arrancar cada hilo
			numbersThreads.get(i).start();
		number.close(); //Cierre del m�todo Scanner
	}

}
