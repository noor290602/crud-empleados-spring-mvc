package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_empleados_spring_mvc.dao.CorreoDao;
import com.example.crud_empleados_spring_mvc.entities.Correo;
import com.example.crud_empleados_spring_mvc.entities.Empleado;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorreoServiceImpl implements CorreoService {
    private final CorreoDao correoDao;

    @Override
    public List<Correo> getAllCorreos() {
        return correoDao.findAll();
    }

    @Override
    public Correo saveCorreo(Correo correo) {
        return correoDao.save(correo);
    }

    @Override
    public boolean existsByEmpleado(Empleado empleado) {
        return correoDao.existsByEmpleado(empleado);
    }

    @Override
    @Transactional
    public void deleteByEmpleado(Empleado empleado) {
        correoDao.deleteByEmpleado(empleado);
    }

    @Override
    public List<Correo> findByEmpleado(Empleado empleado) {
        return correoDao.findByEmpleado(empleado);
    }
}
