package es.videojuego.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.videojuego.modelo.entidad.Videojuego;
import es.videojuego.modelo.persistencia.DaoVideojuego;

@RestController
public class ControladorVideojuegos {
	
	@Autowired
	private DaoVideojuego daoVideojuego;
	
	
	//GET -> obtener un videojuego por id
	@GetMapping(path="videojuegos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> getVideojuego(@PathVariable("id") int id) {
        System.out.println("Buscando videojuego con id: " + id);
        Videojuego v = daoVideojuego.get(id);
        if(v != null) 
            return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
        else 
            return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
        
    }
	
	//Post -> dar de alta un videojuego
	@PostMapping(path="videojuegos",consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Videojuego> altaVideojuego(@RequestBody Videojuego v) {
        System.out.println("altaVideojuego: objeto videojuego: " + v);
        if (daoVideojuego.add(v))
        	return new ResponseEntity<Videojuego>(v,HttpStatus.CREATED);//201 CREATED
        else
        	return new ResponseEntity<Videojuego>(v,HttpStatus.BAD_REQUEST); //Es una peticion mala porque no puede haber videojuegos con mismo id y nombre
    }
	
	//Listamos todos los videojuegos
	@GetMapping(path="videojuegos",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Videojuego>> listarVideojuego() {
        List<Videojuego> listaVideojuegos = null;
        
        listaVideojuegos = daoVideojuego.list();
        System.out.println(listaVideojuegos);
        return new ResponseEntity<List<Videojuego>>(listaVideojuegos,HttpStatus.OK);
    }
	
	//Put -> Modificacion por Id
	@PutMapping(path="videojuegos/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Videojuego> modificarVideojuego(
            @PathVariable("id") int id, 
            @RequestBody Videojuego v) {
        System.out.println("ID a modificar: " + id);
        System.out.println("Datos a modificar: " + v);
        v.setId(id);
        Videojuego vUpdate = daoVideojuego.update(v);
        if(vUpdate != null) {
            return new ResponseEntity<Videojuego>(HttpStatus.OK);//200 OK
        }else {
            return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
        }
    }
	
	//DELETE -> borrar un videojuego por id
	@DeleteMapping(path="videojuegos/{id}")
    public ResponseEntity<Videojuego> borrarVideojuego(@PathVariable("id") int id) {
        System.out.println("ID a borrar: " + id);
        Videojuego v = daoVideojuego.delete(id);
        if(v != null) {
            return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
        }else {
            return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
        }
    }
	
	
}
