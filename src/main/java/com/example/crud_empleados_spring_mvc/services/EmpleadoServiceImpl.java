package com.example.crud_empleados_spring_mvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_empleados_spring_mvc.dao.EmpleadoDao;
import com.example.crud_empleados_spring_mvc.entities.Empleado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    // Se necesita conectarse a la capa Dao
    // Anteriormente esto se hacía usando una anotación @Autowired, que quiere decir
    // que
    // cuando se crease un objeto de tipo EmpleadoService tb se crease un obj de la
    // capa dao
    // Esta anotación está obsoleta y es ineficiente, porque actualmente es +
    // eficiente
    // inyectar el objeto de la capa dao mediante el constructor de la clase
    // EmployeeServideImpl,
    // lo cual ha sido mejorado con una anotación de lombok

    // TRADICIONALMENTE: inyeccion de dependencia por constructor para inyectar el
    // DAO
    // @Autowired
    // private EmpleadoDao empleadoDao;

    // o

    // public EmpleadoServiceImpl(EmpleadoDao empleadoDao) {
    // this.empleadoDao = empleadoDao;
    // }

    private final EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado getEmpleado(int empleadoId) {
        return empleadoDao.findById(empleadoId).get();
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public void deleteEmpleado(Empleado empleado) {
        empleadoDao.delete(empleado);
    }

    @Override
    public Empleado updateEmpleado(int empleadoId) {
        return empleadoDao.save(empleadoDao.findById(empleadoId).get());
    }

     @Override
    public List<Empleado> getEmpleadoByName(String name) {
        return empleadoDao.findByNombre(name);
    }
 
    @Override
    public boolean existsEmpleadoBySalario(double salary) {
       return empleadoDao.existsBySalario(salary);
    }
 
 

}
