package requerimiento2y3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneradorEmails { //Generamos los campos del email aleatoriamente
	
	public Email generarEmail()
	{
		
		Email email = new Email(generarId(), generarDest(), generarRemit(), generarAsunto(), generarMensaje());
		
		return email;
	}
	
	private String generarId()
	{
		List<String> listaId = new ArrayList<String>();
		listaId.add("1234-A");
		listaId.add("1234-B");
		listaId.add("1234-C");
		listaId.add("1234-D");
		listaId.add("1234-E");
		listaId.add("1234-F");
		listaId.add("1234-G");
		listaId.add("1234-H");
		listaId.add("1234-I");
		listaId.add("1234-J");
		listaId.add("1234-K");
		listaId.add("1234-L");
		listaId.add("1234-M");
		listaId.add("1234-N");
		listaId.add("1234-O");
		listaId.add("1234-P");
		listaId.add("1234-Q");
		listaId.add("1234-R");
		listaId.add("1234-S");
		listaId.add("1234-T");
		listaId.add("1234-U");
		listaId.add("1234-V");
		listaId.add("1234-W");
		listaId.add("1234-X");
		listaId.add("1234-Y");
		listaId.add("1234-Z");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 26);
		
		return listaId.get(numero);
	}
	
	private String generarDest()
	{
		List<String> listaDest = new ArrayList<String>();
		//Se ha incluido varias veces el email de pikachu
		//para que haya mas probabilidad de ver cómo se descarta
		listaDest.add("destino_A@gmail.com");
		listaDest.add("destino_B@gmail.com");
		listaDest.add("destino_Cgmail.com");
		listaDest.add("destino_D@gmail.com");
		listaDest.add("pikachu@gmail.com");
		listaDest.add("destino_E@gmail.com");
		listaDest.add("destino_F@gmail.com");
		listaDest.add("destino_G@gmail.com");
		listaDest.add("destino_H@gmail.com");
		listaDest.add("pikachu@gmail.com");
		listaDest.add("destino_I@gmail.com");
		listaDest.add("destino_J@gmail.com");
		listaDest.add("destino_K@gmail.com");
		listaDest.add("destino_L@gmail.com");
		listaDest.add("destino_M@gmail.com");
		listaDest.add("pikachu@gmail.com");
		listaDest.add("destino_N@gmail.com");
		listaDest.add("destino_O@gmail.com");
		listaDest.add("destino_P@gmail.com");
		listaDest.add("destino_Q@gmail.com");
		listaDest.add("pikachu@gmail.com");
		listaDest.add("destino_R@gmail.com");
		listaDest.add("destino_S@gmail.com");
		listaDest.add("destino_T@gmail.com");
		listaDest.add("destino_U@gmail.com");
		listaDest.add("destino_V@gmail.com");
		listaDest.add("pikachu@gmail.com");
		listaDest.add("destino_W@gmail.com");
		listaDest.add("destino_X@gmail.com");
		listaDest.add("destino_Y@gmail.com");
		listaDest.add("destino_Z@gmail.com");
		listaDest.add("pikachu@gmail.com");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 32);
		
		return listaDest.get(numero);
	}
	
	private String generarRemit()
	{
		List<String> listaRemit = new ArrayList<String>();
		listaRemit.add("remitente_A@gmail.com");
		listaRemit.add("remitente_B@gmail.com");
		listaRemit.add("remitente_C@gmail.com");
		listaRemit.add("remitente_D@gmail.com");
		listaRemit.add("remitente_E@gmail.com");
		listaRemit.add("remitente_F@gmail.com");
		listaRemit.add("remitente_G@gmail.com");
		listaRemit.add("remitente_H@gmail.com");
		listaRemit.add("remitente_I@gmail.com");
		listaRemit.add("remitente_J@gmail.com");
		listaRemit.add("remitente_K@gmail.com");
		listaRemit.add("remitente_L@gmail.com");
		listaRemit.add("remitente_M@gmail.com");
		listaRemit.add("remitente_N@gmail.com");
		listaRemit.add("remitente_O@gmail.com");
		listaRemit.add("remitente_P@gmail.com");
		listaRemit.add("remitente_Q@gmail.com");
		listaRemit.add("remitente_R@gmail.com");
		listaRemit.add("remitente_S@gmail.com");
		listaRemit.add("remitente_T@gmail.com");
		listaRemit.add("remitente_U@gmail.com");
		listaRemit.add("remitente_V@gmail.com");
		listaRemit.add("remitente_W@gmail.com");
		listaRemit.add("remitente_X@gmail.com");
		listaRemit.add("remitente_Y@gmail.com");
		listaRemit.add("remitente_Z@gmail.com");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 26);
		
		return listaRemit.get(numero);
	}
	
	private String generarAsunto()
	{
		List<String> listaAsunto = new ArrayList<String>();
		listaAsunto.add("asunto_1");
		listaAsunto.add("asunto_2");
		listaAsunto.add("asunto_3");
		listaAsunto.add("asunto_4");
		listaAsunto.add("asunto_5");
		listaAsunto.add("asunto_6");
		listaAsunto.add("asunto_7");
		listaAsunto.add("asunto_8");
		listaAsunto.add("asunto_9");
		listaAsunto.add("asunto_10");
		listaAsunto.add("asunto_11");
		listaAsunto.add("asunto_12");
		listaAsunto.add("asunto_13");
		listaAsunto.add("asunto_14");
		listaAsunto.add("asunto_15");
		listaAsunto.add("asunto_16");
		listaAsunto.add("asunto_17");
		listaAsunto.add("asunto_18");
		listaAsunto.add("asunto_19");
		listaAsunto.add("asunto_20");
		listaAsunto.add("asunto_21");
		listaAsunto.add("asunto_22");
		listaAsunto.add("asunto_23");
		listaAsunto.add("asunto_24");
		listaAsunto.add("asunto_25");
		listaAsunto.add("asunto_26");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 26);
		
		return listaAsunto.get(numero);
	}
	
	private String generarMensaje()
	{
		List<String> listaMensaje = new ArrayList<String>();
		listaMensaje.add("Mensaje de email numero 1");
		listaMensaje.add("Mensaje de email numero 2");
		listaMensaje.add("Mensaje de email numero 3");
		listaMensaje.add("Mensaje de email numero 4");
		listaMensaje.add("Mensaje de email numero 5");
		listaMensaje.add("Mensaje de email numero 6");
		listaMensaje.add("Mensaje de email numero 7");
		listaMensaje.add("Mensaje de email numero 8");
		listaMensaje.add("Mensaje de email numero 9");
		listaMensaje.add("Mensaje de email numero 10");
		listaMensaje.add("Mensaje de email numero 11");
		listaMensaje.add("Mensaje de email numero 12");
		listaMensaje.add("Mensaje de email numero 13");
		listaMensaje.add("Mensaje de email numero 14");
		listaMensaje.add("Mensaje de email numero 15");
		listaMensaje.add("Mensaje de email numero 16");
		listaMensaje.add("Mensaje de email numero 17");
		listaMensaje.add("Mensaje de email numero 18");
		listaMensaje.add("Mensaje de email numero 19");
		listaMensaje.add("Mensaje de email numero 20");
		listaMensaje.add("Mensaje de email numero 21");
		listaMensaje.add("Mensaje de email numero 22");
		listaMensaje.add("Mensaje de email numero 23");
		listaMensaje.add("Mensaje de email numero 24");
		listaMensaje.add("Mensaje de email numero 25");
		listaMensaje.add("Mensaje de email numero 26");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 26);
		
		return listaMensaje.get(numero);

	}
}
