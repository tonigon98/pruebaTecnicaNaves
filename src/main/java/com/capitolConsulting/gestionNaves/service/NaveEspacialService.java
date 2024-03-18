package com.capitolConsulting.gestionNaves.service;

import com.capitolConsulting.gestionNaves.entity.NaveEspacial;
import com.capitolConsulting.gestionNaves.repository.NaveEspacialRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NaveEspacialService {

  private final NaveEspacialRepository naveEspacialRepository;

  public Page<NaveEspacial> obtenerTodasLasNaves(Pageable pageable) {
    return naveEspacialRepository.findAll(pageable);

  }

  public NaveEspacial guardarNuevaNave(NaveEspacial naveEspacial) {
    return naveEspacialRepository.save(naveEspacial);
  }

  @Cacheable("naves")
  public Optional<NaveEspacial> obtenerNavePorId(Long id) {
    return naveEspacialRepository.findById(id);
  }

  public NaveEspacial actualizarNave(NaveEspacial naveEspacial) {
    // Aquí puedes agregar lógica adicional si es necesario antes de guardar
    return naveEspacialRepository.save(naveEspacial);
  }

  public void eliminarNave(Long id) {
    naveEspacialRepository.deleteById(id);
  }

  public List<NaveEspacial> buscarPorNombre(String nombre) {
    return naveEspacialRepository.findByNombreContaining(nombre);
  }

}
