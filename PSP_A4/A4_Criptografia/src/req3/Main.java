package req3;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int op;
		String frase = "";
		String fraseCifrado = "";
		String fraseDesencriptado = "";
		Cipher cifrador;
		KeyPair claves;
		KeyPairGenerator generador;
		byte[] bytesMensajeCifrado = frase.getBytes();
		
		
		try {
			generador = KeyPairGenerator.getInstance("RSA");
			claves = generador.generateKeyPair();
			cifrador = Cipher.getInstance("RSA");
			op = menu();
			while (op != 6) {
				if (op == 1) {
					//encriptamos con la clave privada
					System.out.println("Introduzca la frase a encriptar");
					frase = sc.nextLine();
					cifrador.init(Cipher.ENCRYPT_MODE, claves.getPrivate());
					byte[] bytesFrase = frase.getBytes();
					bytesMensajeCifrado = cifrador.doFinal(bytesFrase);
					fraseCifrado = new String(bytesMensajeCifrado);
				} else if (op == 2) {
					//Mostramos la frase encriptada
					System.out.println("Frase encriptada: " + fraseCifrado);
				} else if (op == 3) {
					//desencriptamos frase con clave publica
					cifrador.init(Cipher.DECRYPT_MODE, claves.getPublic());
					byte[] bytesMensajeDescifrado = cifrador.doFinal(bytesMensajeCifrado);
					System.out.println("La frase es: " + new String(bytesMensajeDescifrado));
				} else if (op == 4) {
					//Encriptamos un coche con clave privada
					String matricula, marca, modelo;
					int precio;
					System.out.println("Introduzca la matricula del coche: ");
					matricula = sc.nextLine();
					System.out.println("Introduzca la marca del coche: ");
					marca = sc.nextLine();
					System.out.println("Introduzca el modelo del coche: ");
					modelo = sc.nextLine();
					System.out.println("Introduzca el precio del coche: ");
					precio = sc.nextInt();
					
					Coche c1 = new Coche (matricula, marca, modelo, precio);
					cifrador.init(Cipher.ENCRYPT_MODE, claves.getPrivate());
					SealedObject so = new SealedObject(c1, cifrador);
					System.out.println("Coche cifrado:" + so);
				}
				op = menu();
			}
		} catch (Exception gse) {
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
