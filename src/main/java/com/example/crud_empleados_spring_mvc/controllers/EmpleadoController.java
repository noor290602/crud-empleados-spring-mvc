package com.example.crud_empleados_spring_mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    // El metodo siguiente recibira peticiones (request), a la url:
    // localhost:8080/empleados/listar
    @GetMapping("/listar")
    public String listarEmpleados(Model model) {

        //Recuperar a través del servicio de empleado, la lista de 
        //empleados de la tabla empleados

        List<Empleado> listaEmpleados = empleadoService.getAllEmpleados();

        //Agregar la lista de empleados al modelo
        model.addAttribute("empleados", listaEmpleados);


        return "listadoEmpleados";
    }
    
   
}
