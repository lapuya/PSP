package es.videojuego.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.videojuego.modelo.entidad.*;

@Component
public class DaoVideojuego {
	
	public List<Videojuego> listaVideojuegos;
	public int contador;
	
	public DaoVideojuego() {
		System.out.println("CREAMOS DaoVideojuegos");
		listaVideojuegos = new ArrayList<Videojuego>();
		
		Videojuego v1 = new Videojuego(contador++, "League of Legends", "Riot Games", 6.0);
		Videojuego v2 = new Videojuego(contador++, "Counter Strike", "Valve", 4.9);
		Videojuego v3 = new Videojuego(contador++, "Bioshock", "Irrational Games", 9.3);
		Videojuego v4 = new Videojuego(contador++, "Red Dead Redemption", "Rockstar", 10.0);
		Videojuego v5 = new Videojuego(contador++, "Valorant", "Riot Games", 8.6);
		
		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
		listaVideojuegos.add(v3);
		listaVideojuegos.add(v4);
		listaVideojuegos.add(v5);

	}
	
	//Requerimiento 2 -> no se pueden repetir id o nombre
	public boolean add (Videojuego v) {
		
		int i = 0;
		
		while (i < listaVideojuegos.size()) {
			if (listaVideojuegos.get(i).getId() != v.getId() && !listaVideojuegos.get(i).getNombre().contains(v.getNombre()))
					i++;
			else
				break;
		}
		
		if (i >= listaVideojuegos.size()) {
			listaVideojuegos.add(v);
			return true;
		}
		return false;
	}
	
	public Videojuego delete(int id)
	{
		int i = 0;
		
		while (i < listaVideojuegos.size()) {
			if (listaVideojuegos.get(i).getId() == id)
				return listaVideojuegos.remove(i);
			i++;
				
		}
		System.out.println("No esta el videojuego");
		return null;
	}
	
	//actualizar un videojuego
	public Videojuego update(Videojuego v) {
		try {
			int i = 0;
			
			while (i < listaVideojuegos.size())
			{
				if (listaVideojuegos.get(i).getId() == v.getId())
					break;
				i++;
			}
			Videojuego vAux = listaVideojuegos.get(i);
			vAux.setNombre(v.getNombre());
			vAux.setCompany(v.getCompany());
			vAux.setNota(v.getNota());
			
			return vAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> videojuego fuera de rango");
			return null;
		}
	}
	
	public Videojuego get(int id) {
		int i = 0;
		
		while (i < listaVideojuegos.size()) {
			if (listaVideojuegos.get(i).getId() == id)
				return listaVideojuegos.get(i);
			i++;
		}
		System.out.println("Videojuego no encontrado");
		return null;
	}
	
	//listar todos los videojuegos
	public List<Videojuego> list() {
		return listaVideojuegos;
	}
}
