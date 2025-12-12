package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Correo;
import com.example.crud_empleados_spring_mvc.entities.Empleado;

public interface CorreoService {
    List<Correo> getAllCorreos();
    Correo saveCorreo(Correo correo);
    boolean existsByEmpleado(Empleado empleado);
    void deleteByEmpleado(Empleado empleado);
    List<Correo> findByEmpleado(Empleado empleado);
}
