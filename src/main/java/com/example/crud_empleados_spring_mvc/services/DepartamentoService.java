package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Departamento;

public interface DepartamentoService {
    Departamento saveDepartamento(Departamento departamento);

    List<Departamento> getAllDepartamentos();
}
