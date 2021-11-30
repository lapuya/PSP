package req1_2;

import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int op;
		String frase = "";
		String fraseCifrado = "";
		Cipher cifrador;
		SecretKey paloEspartano;
		KeyGenerator generador;
		byte[] bytesMensajeCifrado = frase.getBytes();
		
		
		try {
			generador = KeyGenerator.getInstance("AES");
			paloEspartano = generador.generateKey();
			cifrador = Cipher.getInstance("AES");
			op = menu();
			while (op != 6) {
				if (op == 1) {
					//encriptamos la frase introducida por el usuario
					System.out.println("Introduzca la frase a encriptar");
					frase = sc.nextLine();
					cifrador.init(Cipher.ENCRYPT_MODE, paloEspartano);
					byte[] bytesFrase = frase.getBytes();
					bytesMensajeCifrado = cifrador.doFinal(bytesFrase);
					fraseCifrado = new String(bytesMensajeCifrado);
				} else if (op == 2) {
					//Mostramos la frase encriptada
					System.out.println("Frase encriptada: " + fraseCifrado);
				} else if (op == 3) {
					//desencriptamos la frase y mostramos por pantalla
					cifrador.init(Cipher.DECRYPT_MODE, paloEspartano);
					byte[] bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
					System.out.println("La frase es: " + new String(bytesMensajeDescifrado));
				} else if (op == 4) {
					//Encriptamos un coche
					String matricula, marca, modelo;
					int precio;
					
					//Pedimos los datos al usuario
					System.out.println("Introduzca la matricula del coche: ");
					matricula = sc.nextLine();
					System.out.println("Introduzca la marca del coche: ");
					marca = sc.nextLine();
					System.out.println("Introduzca el modelo del coche: ");
					modelo = sc.nextLine();
					System.out.println("Introduzca el precio del coche: ");
					precio = sc.nextInt();
					
					//encriptamos coche y mostramos por pantalla que se ha encriptado
					Coche c1 = new Coche (matricula, marca, modelo, precio);
					cifrador.init(Cipher.ENCRYPT_MODE, paloEspartano);
					SealedObject so = new SealedObject(c1, cifrador);
					System.out.println("Coche cifrado:" + so);
				}
				op = menu();
			}
		} catch (Exception gse) {
			// TODO Auto-generated catch block
			gse.printStackTrace();
		}
	}


	public static int menu() {
		
		Scanner sc = new Scanner(System.in);
		int op;
		
		System.out.println("----------------------------------------------");
		System.out.println("1. Encriptar frase");
		System.out.println("2. Mostar frase encriptada");
		System.out.println("3. Desencriptar frase");
		System.out.println("4. Encriptar coche y mostrar encriptacion");
		System.out.println("5. Salir del programa");
		System.out.println("----------------------------------------------");

		op = sc.nextInt();
		
		while (op < 1 || op > 5) {
			System.out.println("----------------------------------------------");
			System.out.println("Error, elija una de las siguientes opciones");
			System.out.println("1. Encriptar frase");
			System.out.println("2. Mostar frase encriptada");
			System.out.println("3. Desencriptar frase");
			System.out.println("4. Encriptar coche y mostrar encriptacion");
			System.out.println("5. Salir del programa");
			System.out.println("----------------------------------------------");

			op = sc.nextInt();
		}
		return op;
	}

}
