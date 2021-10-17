package Primos;
public class PrimeCalculator implements Runnable { // Implementa la interfaz Runnable para poder usar run()

	long num; // Declaración de la variable num con su constructor

	public PrimeCalculator(long num) {
		this.num = num;
	}

	@Override
	/**Método sobreescrito. Imprime el nombre el hilo que va a hacer la operación ejecutando isPrime()
	 * Declaración de la variable startTime, que corresponde al tiempo de inicio de en ms
	 * Calculamos el tiempo de ejecución restando el tiempo de inicio al tiempo de finalización
	 */
	public void run() { 
		long startTime = System.currentTimeMillis();
		System.out.println("Arrancando hilo: " + Thread.currentThread().getName());
		if (isPrime(this.num))
			System.out.println("Processed number: " + this.num + ". It's a prime number");
		else
			System.out.println("Processed number: " + this.num + ". It is NOT a prime number");
		System.out.println(Thread.currentThread().getName() + " has finished");
		long endTime = System.currentTimeMillis();
		System.out.println("Execution time: " + (endTime - startTime));

	}

	public boolean isPrime(long num) { // Método para determinar si el número es primo
		int n = 2;
		boolean primo = true;
		while ((primo) && (n != num)) {
			if (num % n == 0)
				primo = false;
			n++;
		}
		return primo;
	}
}
