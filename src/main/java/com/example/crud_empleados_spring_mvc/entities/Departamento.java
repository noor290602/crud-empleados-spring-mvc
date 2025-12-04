package com.example.crud_empleados_spring_mvc.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // crea las tablas en la BBDD
@Table(name = "departamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

// Serializar -> convertir de Java a BBDD
// Deserializar -> de BBDD a Java
public class Departamento implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id // PK, NOT NULL
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private int id;
    private String nombre;
    // cascade -> cuando se propage a empleado se elimina
    // mappedBy -> campo que manda (campo que está en el padre)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "departamento")
    private List<Empleado> listaEmpleados;

}
