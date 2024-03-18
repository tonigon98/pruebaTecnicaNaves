package com.capitolConsulting.gestionNaves.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Validated
@Entity
@Table(name = "naves_espaciales")
public class NaveEspacial implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "modelo")
  private String modelo;


}
