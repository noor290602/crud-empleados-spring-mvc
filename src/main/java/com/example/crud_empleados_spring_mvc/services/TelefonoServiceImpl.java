package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_empleados_spring_mvc.dao.TelefonoDao;
import com.example.crud_empleados_spring_mvc.entities.Telefono;

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

}
