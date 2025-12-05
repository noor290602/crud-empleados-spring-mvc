package com.example.crud_empleados_spring_mvc.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.crud_empleados_spring_mvc.models.Genero;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Empleado {

    // Es lo que permite recuperar la tabla de la BBDD
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    // Ésta anotación es para que el género se guarde en la tabla como String,
    // el nombre del género, y no como ordinal
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    private double salario;

    // Le indicamos a Hibernate que cuando recuperemos un empleado no me traiga el
    // dpto (LAZY)
    // Las relaciones entre las entidades, en JPA, son BIDIRECCIONALES, a diferencia
    // de en el sistema
    // de gestión de bbdd relacionales, que son UNIDIRECCIONALES (el padre no sabe
    // nada acerca de los
    // hijos, pero los hijos sí saben quien es el padre, pq la FK se crea en el lado
    // de muchos, de los hijos).
    // En JPA (bidireccional), tanto padre como hijos saben del otro
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;
}
