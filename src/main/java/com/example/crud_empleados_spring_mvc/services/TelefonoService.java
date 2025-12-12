package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.entities.Telefono;

public interface TelefonoService {
    List<Telefono> getAllTelefonos();
    Telefono saveTelefono(Telefono telefono);
    boolean existsByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
    List<Telefono> findByEmpleado(Empleado empleado);
}
