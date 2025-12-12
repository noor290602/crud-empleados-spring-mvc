package com.example.crud_empleados_spring_mvc.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crud_empleados_spring_mvc.entities.Correo;
import com.example.crud_empleados_spring_mvc.entities.Departamento;
import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.entities.Telefono;
import com.example.crud_empleados_spring_mvc.services.CorreoService;
import com.example.crud_empleados_spring_mvc.services.DepartamentoService;
import com.example.crud_empleados_spring_mvc.services.EmpleadoService;
import com.example.crud_empleados_spring_mvc.services.TelefonoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;
    private final TelefonoService telefonoService;
    private final CorreoService correoService;

    private static Logger LOGGER = LoggerFactory.getLogger(EmpleadoController.class);

    @GetMapping("/view/{idEmpleado}")
    public String verEmpleado(@PathVariable("idEmpleado") int idEmpleado, Model model) {
        
        Empleado empleado = empleadoService.getEmpleado(idEmpleado);
        model.addAttribute("empleado", empleado);

        List<Telefono> listaTelefonos = telefonoService.findByEmpleado(empleado);
        String telefonosEmpleado = "";
        for (Telefono telefono : listaTelefonos) {
            telefonosEmpleado += telefono.getNumero() + "; ";
        };

        model.addAttribute("telefonos", telefonosEmpleado);

        List<Correo> listaCorreos = correoService.findByEmpleado(empleado);
        String correosEmpleado = "";
        for (Correo correo : listaCorreos) {
            correosEmpleado += correo.getEmail() + "; ";
        };
        
        model.addAttribute("emails", correosEmpleado);

        return "empleadoVista";
    }

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
    @GetMapping("/add")
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

    /* Método que recibe un empleado, en el cuerpo del protocolo HTTP,
        mediante el metodo POST y lo persiste (guarda) en la tabla de empleados correspondiente
    */
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado, 
        @RequestParam(name = "telefonosEmpleado", required = false) String telefonosEmpleado, 
        @RequestParam(name = "emailsEmpleado", required = false) String emailsEmpleado) {

        /* Comprobar si el objeto empleado lo hemos recibido */
        LOGGER.info("Empleado recibido: " + empleado);

        Empleado empGuardado = empleadoService.saveEmpleado(empleado);

        if (telefonosEmpleado != "" || telefonosEmpleado != null) {

            List<String> listaTlfnosEmpleado = Arrays.stream(telefonosEmpleado.split(";"))
                        .map(String::trim) 
                        .toList();

             /* Antes de  crear/modificar los correos y tlfns, hay que eliminar los asociados de ese empleado*/
            if (telefonoService.existsByEmpleado(empleado)) {
                telefonoService.deleteByEmpleado(empleado);
            }

            listaTlfnosEmpleado.stream().forEach( tel -> {
                Telefono telefono = Telefono.builder()
                    .numero(tel)
                    .empleado(empGuardado)
                    .build();
                    
                telefonoService.saveTelefono(telefono);
            });
        }

        if (emailsEmpleado != "" || emailsEmpleado != null) {

            List<String> listaCorreosEmpleado = Arrays.stream(emailsEmpleado.split(";"))
            .map(String::trim) 
            .toList();
            
            /* Antes de  crear/modificar los correos y tlfns, hay que eliminar los asociados de ese empleado*/
            if (correoService.existsByEmpleado(empleado)) {
                correoService.deleteByEmpleado(empleado);
            }

            listaCorreosEmpleado.forEach(email -> {
                Correo correo = Correo.builder()
                    .email(email)
                    .empleado(empGuardado)
                    .build();
                correoService.saveCorreo(correo);
            });
        }

        return "redirect:/empleados/listar";
    }

    /* Método que actualiza/modifica un Empleado cuyo id se recibe como parámetro */
    @GetMapping("/update/{idEmpleado}")
    public String modificarEmpleado(@PathVariable("idEmpleado") int idEmpleado, Model model) {

        Empleado empleado = empleadoService.getEmpleado(idEmpleado);

        List<Departamento> listaDepartamentos = departamentoService.getAllDepartamentos();
        model.addAttribute("departamentos", listaDepartamentos);

        /* Recuperar correos y tlfns del empleado */

        List<Telefono> listaTelefonos = telefonoService.getAllTelefonos().stream()
            .filter(t -> t.getEmpleado().equals(empleado))
            .toList();

        List<Correo> listaCorreos = correoService.getAllCorreos().stream()
            .filter(c -> c.getEmpleado().equals(empleado))
            .toList();

        if (!listaTelefonos.isEmpty() && !listaCorreos.isEmpty()) {
            
            String dirsCorreo = listaCorreos.stream()
                .map(Correo::getEmail)
                .collect(Collectors.joining(";"));

            String numsTelefono = listaTelefonos.stream()
                .map(Telefono::getNumero)
                .collect(Collectors.joining(";"));
            
            model.addAttribute("dirsCorreo", dirsCorreo);
            model.addAttribute("numsTelefono", numsTelefono);
        }

        

        empleadoService.updateEmpleado(idEmpleado);

        model.addAttribute("empleado", empleado);

        return "formsAltaModificacion";
    }

    @GetMapping("/delete/{idEmpleado}")
    public String eliminarEmpleado(@PathVariable("idEmpleado") int idEmpleado) {

        empleadoService.deleteEmpleado(empleadoService.getEmpleado(idEmpleado));

        return "redirect:/empleados/listar";
    }
}
