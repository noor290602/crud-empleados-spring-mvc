package com.example.crud_empleados_spring_mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud_empleados_spring_mvc.entities.Departamento;
import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.services.DepartamentoService;
import com.example.crud_empleados_spring_mvc.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;

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
    
    // Metodo que recibe la peticion (request) de alta de un empleado
    @GetMapping("/add/{idEmpleado}")
    public String altaEmpleado(Model model) {

        /* ¿CÓMO GESTIONA SPRING FRAMEWORK EL FORMULARIO?

        Envia un objeto Empleado vacio, para el caso de un alta nueva, y vincula los campos 
        o propiedades de dicho objeto con los controles del formulario, es decir, de los 
        elementos input del formulario, de tal manera que cuando se recogen los valores del 
        los controles del formulario, en respuesta al evento submit, no hay que hacer 
        absolutamente nada para recuperar el valor de los controles del formulario, solamente
        recuperar el objeto Empleado completo, nada de request.getParameter() como teniamos 
        que hacer en el proyecto CRUD de Empleados sin Spring 
        
        */

         /* ¿CÓMO SE ENVIA EL OBJETO EMPLEADO VACIO AL FORMULARIO?
            Se añade al modelo un objeto Empleado vacio, con new Empleado(), y Spring Framework
            se encarga de vincular los campos del objeto con los controles del formulario
         */

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        List<Departamento> listaDepartamentos = departamentoService.getAllDepartamentos();
        model.addAttribute("departamentos", listaDepartamentos);

        return "formsAltaModificacion";
    }

    /*
    @GetMapping("add/{idEmpleado}")
    public String altaEmpleado(@RequestParam("idEmpleado") int idEmpleado, Model model) {
        Empleado empleado = empleadoService.getEmpleado(idEmpleado);
        
        model.addAttribute("empleado", empleado);
        return "redirect:/empleados/listar";
    }
    */
}
