package requerimiento1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		ArrayList<Thread> numbersThreads = new ArrayList<Thread>();
		
		num = 0;
		Scanner number = new Scanner(System.in);
		for(int i = 0; i < 4; i++)
		{
			System.out.println("Put number " + i + ": ");
			num = number.nextInt();
			PrimeCalculator numRunnable = new PrimeCalculator(num);
			Thread t1 = new Thread(numRunnable);
			numbersThreads.add(t1);
		}
		
		for(int i = 0; i < numbersThreads.size(); i++)
			numbersThreads.get(i).start();
		number.close();
	}

}
