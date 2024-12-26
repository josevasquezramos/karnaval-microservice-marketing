package com.sistemas.karnaval.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistemas.karnaval.entidad.Oferta;
import com.sistemas.karnaval.servicio.OfertaService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;

	@PostMapping
	public ResponseEntity<Oferta> crearOferta(@RequestBody Oferta oferta) {
		return ResponseEntity.ok(ofertaService.crear(oferta));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Oferta> actualizarOferta(@PathVariable Long id, @RequestBody Oferta oferta) {
		return ResponseEntity.ok(ofertaService.actualizar(id, oferta));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
		ofertaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oferta> buscarOferta(@PathVariable Long id) {
		return ResponseEntity.ok(ofertaService.buscarPorId(id));
	}

	@GetMapping
	public ResponseEntity<List<Oferta>> listarOfertas() {
		return ResponseEntity.ok(ofertaService.listarTodos());
	}

	@GetMapping("/activas")
	public ResponseEntity<List<Oferta>> listarOfertasActivas() {
		return ResponseEntity.ok(ofertaService.listarActivas());
	}
}
