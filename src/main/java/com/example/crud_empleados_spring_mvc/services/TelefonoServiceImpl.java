package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_empleados_spring_mvc.dao.TelefonoDao;
import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.entities.Telefono;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {

    private final TelefonoDao telefonoDao;

    @Override
    public List<Telefono> getAllTelefonos() {
        return telefonoDao.findAll();
    }

    @Override
    public Telefono saveTelefono(Telefono telefono) {
        return telefonoDao.save(telefono);
    }

    @Override
    public boolean existsByEmpleado(Empleado empleado) {
        return telefonoDao.existsByEmpleado(empleado);
    }

    @Override
    @Transactional
    public void deleteByEmpleado(Empleado empleado) {
        telefonoDao.deleteByEmpleado(empleado);
    }

}
