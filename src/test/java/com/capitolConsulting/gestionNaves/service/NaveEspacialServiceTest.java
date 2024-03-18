package com.capitolConsulting.gestionNaves.service;

import com.capitolConsulting.gestionNaves.entity.NaveEspacial;
import com.capitolConsulting.gestionNaves.repository.NaveEspacialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NaveEspacialServiceTest {

  @Mock
  private NaveEspacialRepository naveEspacialRepository;

  @InjectMocks
  private NaveEspacialService naveEspacialService;

  private NaveEspacial naveEspacial;
  private Page<NaveEspacial> naveEspacialPage;

  @BeforeEach
  void setUp() {
    naveEspacial = new NaveEspacial(); // Configura aquí las propiedades de la nave
    naveEspacialPage = new PageImpl<>(Collections.singletonList(naveEspacial));
  }

  @Test
  void obtenerTodasLasNaves() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    when(naveEspacialRepository.findAll(pageRequest)).thenReturn(naveEspacialPage);

    Page<NaveEspacial> resultado = naveEspacialService.obtenerTodasLasNaves(pageRequest);

    assertThat(resultado).isEqualTo(naveEspacialPage);
    verify(naveEspacialRepository).findAll(pageRequest);
  }

  @Test
  void guardarNuevaNave() {
    when(naveEspacialRepository.save(naveEspacial)).thenReturn(naveEspacial);

    NaveEspacial resultado = naveEspacialService.guardarNuevaNave(naveEspacial);

    assertThat(resultado).isEqualTo(naveEspacial);
    verify(naveEspacialRepository).save(naveEspacial);
  }

  @Test
  void obtenerNavePorId() {
    Long id = 1L;
    when(naveEspacialRepository.findById(id)).thenReturn(Optional.of(naveEspacial));

    Optional<NaveEspacial> resultado = naveEspacialService.obtenerNavePorId(id);

    assertThat(resultado).contains(naveEspacial);
    verify(naveEspacialRepository).findById(id);
  }

}