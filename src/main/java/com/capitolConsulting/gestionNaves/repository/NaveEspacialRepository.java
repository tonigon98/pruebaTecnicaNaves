package com.capitolConsulting.gestionNaves.repository;

import com.capitolConsulting.gestionNaves.entity.NaveEspacial;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NaveEspacialRepository extends JpaRepository<NaveEspacial, Long> {

  List<NaveEspacial> findByNombreContaining(String nombre);


}
