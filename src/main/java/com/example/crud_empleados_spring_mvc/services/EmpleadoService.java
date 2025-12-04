package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Empleado;

public interface EmpleadoService {

    List<Empleado> getAllEmpleados();

    Empleado getEmpleado(int empleadoId);

    Empleado saveEmpleado(Empleado empleado);

    void deleteEmpleado(Empleado empleado);

    Empleado updateEmpleado(int empleadoId);

    List<Empleado> getEmpleadoByName(String nombre);

    boolean existsEmpleadoBySalario(double salario);

}
