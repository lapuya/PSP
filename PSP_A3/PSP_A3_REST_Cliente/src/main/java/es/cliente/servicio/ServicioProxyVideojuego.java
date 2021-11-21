package es.cliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.cliente.entidad.Videojuego;

// Damos de alta un objeto tipo ServicioProxyVideojuego en contexto Spring

@Service
public class ServicioProxyVideojuego {

	// URL del servicio REST de videojuegos
	public static final String URL = "http://localhost:8080/videojuegos/";

	// Inyectamos un objeto tipo RestTemplate para ayudarnos
	// a hacer peticiones HTTP al servicio REST
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Método para dar de alta un videojuego en el servicio REST
	 * 
	 * @param v videojuego que damos de alta
	 * @return videojuego dado de alta en servicio REST. Null si no se ha podido dar
	 *         de alta
	 */

	public Videojuego alta(Videojuego v) {
		try {

			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, v, Videojuego.class);
			System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("alta -> El videojuego NO se ha dado de alta, id: " + v);
			System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	/**
	 * Método que borra un videojuego en el servicio REST
	 * 
	 * @param id videojuego que queremos borrar
	 * @return true si lo hemos podido borrar. False en caso contrario.
	 */

	public boolean borrar(int id) {
		try {

			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("borrar -> El videojuego NO se ha podido borrar, id: " + id);
			System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	/**
	 * Método que modifica un videojuego en el servicio REST
	 * 
	 * @param v videojuego que vamos a modificar, se hará por ID
	 * @return true si lo hemos podido modificar. False en caso contrario
	 */

	public boolean modificar(Videojuego v) {
		try {

			restTemplate.put(URL + v.getId(), v, Videojuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("modificar -> La persona NO se ha modificado, id: " + v.getId());
			System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	/**
	 * Método para obtener un videojuego del servicio REST por ID lanza una
	 * excepción si no existe
	 * 
	 * @param id que queremos obtener
	 * @return videojuego que buscamos, null si no se encuentra en el server o
	 *         cualquier otro error
	 */

	public Videojuego obtener(int id) {
		try {
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs = re.getStatusCode();
			if (hs == HttpStatus.OK) {
				return re.getBody();
			} else {
				System.out.println("No admitida");
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("obtener -> El videojuego NO se ha encontrado, id: " + id);
			System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	/**
	 * Método que devuelve una lista de todos los videojuegos
	 * 
	 * @return listado de videojuegos
	 */

	public List<Videojuego> listar() {

		try {
			ResponseEntity<Videojuego[]> response = restTemplate.getForEntity(URL, Videojuego[].class);
			Videojuego[] arrayPersonas = response.getBody();
			return Arrays.asList(arrayPersonas);// convertimos el array en un arraylist
		} catch (HttpClientErrorException e) {
			System.out.println("listar -> Error al obtener la lista de videojuegos");
			System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

}
