package requerimiento1;

public class PrimeCalculator implements Runnable {
	
	int num;
	
	public PrimeCalculator(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		int n = 2;
		long startTime = System.currentTimeMillis();
		System.out.println("Arrancando hilo: " + Thread.currentThread().getName());
		if(isPrime(this.num, n))
			System.out.println("Processed number: " + this.num + ". It's a prime number");
		else
			System.out.println("Processed number: " + this.num + ". It is NOT a prime number");
		System.out.println(Thread.currentThread().getName() + " has finished");
		long endTime = System.currentTimeMillis();
		System.out.println("Execution time: " + (endTime - startTime));
			
		
	}

	private boolean isPrime(int x, int n) {
		if (x == 0 || x == 1)
			return false;
		if (n == x)
			return true;
		if (x % n == 0)
			return false;
		n++;
		return isPrime(x, n);
		
	}

}
