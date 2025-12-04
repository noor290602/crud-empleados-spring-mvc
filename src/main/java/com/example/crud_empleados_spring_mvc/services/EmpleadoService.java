package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Empleado;

public interface EmpleadoService {
    List<Empleado> getAllEmpleados();

    Empleado saveEmpleado();

    Empleado getEmpleadoById();

    void delete(Empleado empleado);

    Empleado updateEmpleado(int empleadoId);

    List<Empleado> getEmpleadoByName(String name);
}
