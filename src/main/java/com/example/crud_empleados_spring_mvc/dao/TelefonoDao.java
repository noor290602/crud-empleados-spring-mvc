package com.example.crud_empleados_spring_mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud_empleados_spring_mvc.entities.Telefono;
import com.example.crud_empleados_spring_mvc.entities.Empleado;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer> {
    boolean existsByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
    List<Telefono> findByEmpleado(Empleado empleado);
}
