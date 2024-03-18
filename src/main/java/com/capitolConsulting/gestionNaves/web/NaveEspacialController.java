package com.capitolConsulting.gestionNaves.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capitolConsulting.gestionNaves.entity.NaveEspacial;
import com.capitolConsulting.gestionNaves.service.NaveEspacialService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/naves")
public class NaveEspacialController {

  private final NaveEspacialService naveEspacialService;


  @GetMapping
  public ResponseEntity<Page<NaveEspacial>> obtenerTodasLasNaves(Pageable pageable) {
    Page<NaveEspacial> naves = naveEspacialService.obtenerTodasLasNaves(pageable);
    return ResponseEntity.ok(naves);
  }

  @PostMapping
  public ResponseEntity<NaveEspacial> crearNuevaNave(@Valid @RequestBody NaveEspacial naveEspacial) {
    NaveEspacial nuevaNave = naveEspacialService.guardarNuevaNave(naveEspacial);
    return new ResponseEntity<>(nuevaNave, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<NaveEspacial> obtenerNavePorId(@PathVariable Long id) {
    return naveEspacialService.obtenerNavePorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<NaveEspacial> actualizarNave(@PathVariable Long id,
      @RequestBody NaveEspacial naveEspacial) {
// Asegúrate de verificar que la nave con este ID existe antes de actualizar
    return naveEspacialService.obtenerNavePorId(id)
        .map(naveExistente -> {
          naveEspacial.setId(
              id); // Asegúrate de asignar el ID para actualizar en lugar de crear una nueva
          NaveEspacial naveActualizada = naveEspacialService.actualizarNave(naveEspacial);
          return ResponseEntity.ok(naveActualizada);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarNave(@PathVariable Long id) {
    // Asegúrate de verificar que la nave con este ID existe antes de eliminar
    if (naveEspacialService.obtenerNavePorId(id).isPresent()) {
      naveEspacialService.eliminarNave(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<NaveEspacial>> buscarNavesPorNombre(
      @RequestParam String nombre) {
    List<NaveEspacial> naves = naveEspacialService.buscarPorNombre(nombre);
    return ResponseEntity.ok(naves);
  }
}
