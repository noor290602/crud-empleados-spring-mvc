package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import com.example.crud_empleados_spring_mvc.entities.Correo;

public interface CorreoService {
    List<Correo> getAllCorreos();
    Correo saveCorreo(Correo correo);
}
