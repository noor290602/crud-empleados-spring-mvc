package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_empleados_spring_mvc.dao.DepartamentoDao;
import com.example.crud_empleados_spring_mvc.entities.Departamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService{

    private final DepartamentoDao departamentoDao;
   
    @Override
    public List<Departamento> getAllDepartamentos() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento saveDepartamento(Departamento departamento) {
        return departamentoDao.save(departamento);
    }


}
