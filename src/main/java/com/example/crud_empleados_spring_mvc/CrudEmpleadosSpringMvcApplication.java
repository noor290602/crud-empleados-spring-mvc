package com.example.crud_empleados_spring_mvc;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.crud_empleados_spring_mvc.entities.Correo;
import com.example.crud_empleados_spring_mvc.entities.Departamento;
import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.entities.Telefono;
import com.example.crud_empleados_spring_mvc.models.Genero;
import com.example.crud_empleados_spring_mvc.services.CorreoService;
import com.example.crud_empleados_spring_mvc.services.DepartamentoService;
import com.example.crud_empleados_spring_mvc.services.EmpleadoService;
import com.example.crud_empleados_spring_mvc.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEmpleadosSpringMvcApplication implements CommandLineRunner {

	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;
	private final TelefonoService telefonoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEmpleadosSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/* DEPARTAMENTOS */

		// Vamos a crear departamentos y empleados
		Departamento dpto1 = Departamento.builder()
				.nombre("RRHH")
				.build();

		Departamento dpto2 = Departamento.builder()
				.nombre("INFORMATICA")
				.build();

		Departamento dpto3 = Departamento.builder()
				.nombre("FINANZAS")
				.build();

		// Persistir (guardar) los dptos
		departamentoService.saveDepartamento(dpto1);
		departamentoService.saveDepartamento(dpto2);
		departamentoService.saveDepartamento(dpto3);

		/* EMPLEADOS */
		Empleado emp1 = Empleado.builder()
				.nombre("Noor")
				.primerApellido("Aloune")
				.segundoApellido("Sagouma")
				.genero(Genero.MUJER)
				.fechaInicio(LocalDate.of(2002, Month.JUNE, 29))
				.salario(2000)
				.departamento(dpto1)
				.build();

		Empleado emp2 = Empleado.builder()
				.nombre("Víctor")
				.primerApellido("Machado")
				.segundoApellido("Arteaga")
				.genero(Genero.HOMBRE)
				.fechaInicio(LocalDate.of(1965, Month.FEBRUARY, 4))
				.salario(4000)
				.departamento(dpto2)
				.build();

		Empleado emp3 = Empleado.builder()
				.nombre("Laura")
				.primerApellido("González")
				.segundoApellido("Ruiz")
				.genero(Genero.MUJER)
				.fechaInicio(LocalDate.of(1980, Month.JUNE, 15))
				.salario(3500)
				.departamento(dpto2)
				.build();

		Empleado emp4 = Empleado.builder()
				.nombre("Andrés")
				.primerApellido("Serrano")
				.segundoApellido("López")
				.genero(Genero.HOMBRE)
				.fechaInicio(LocalDate.of(1990, Month.SEPTEMBER, 22))
				.salario(4200)
				.departamento(dpto2)
				.build();

				
		// Persistir (guardar) los EMPLEADOS
		empleadoService.saveEmpleado(emp1);
		empleadoService.saveEmpleado(emp2);
		empleadoService.saveEmpleado(emp3);
		empleadoService.saveEmpleado(emp4);

		/* TELEFONOS */
		Telefono t1 = Telefono.builder().numero("+34123456789").empleado(emp1).build();
		Telefono t2 = Telefono.builder().numero("+34987654321").empleado(emp1).build();
		Telefono t3 = Telefono.builder().numero("+34111222333").empleado(emp2).build();
		Telefono t4 = Telefono.builder().numero("+34999888777").empleado(emp2).build();
		Telefono t5 = Telefono.builder().numero("+34222333444").empleado(emp3).build();
		Telefono t6 = Telefono.builder().numero("+34988777666").empleado(emp3).build();
		Telefono t7 = Telefono.builder().numero("+34333444555").empleado(emp4).build();
		Telefono t8 = Telefono.builder().numero("+34977666555").empleado(emp4).build();

		// Persistir (guardar) los TELEFONOS
		telefonoService.saveTelefono(t1);
		telefonoService.saveTelefono(t2);
		telefonoService.saveTelefono(t3);
		telefonoService.saveTelefono(t4);
		telefonoService.saveTelefono(t5);
		telefonoService.saveTelefono(t6);
		telefonoService.saveTelefono(t7);
		telefonoService.saveTelefono(t8);

		/* CORREOS */
		Correo ea1 = Correo.builder().email("nalosag@gmail.com").empleado(emp1).build();
		Correo ea2 = Correo.builder().email("nalosag2@gmail.com").empleado(emp1).build();
		Correo ea3 = Correo.builder().email("vmachar@gmail.com").empleado(emp2).build();
		Correo ea4 = Correo.builder().email("vmachar2@gmail.com").empleado(emp2).build();
		Correo ea5 = Correo.builder().email("lagoru@gmail.com").empleado(emp3).build();
		Correo ea6 = Correo.builder().email("lagoru2@gmail.com").empleado(emp3).build();
		Correo ea7 = Correo.builder().email("aselo@gmail.com").empleado(emp4).build();
		Correo ea8 = Correo.builder().email("aselo2@gmail.com").empleado(emp4).build();

		correoService.saveCorreo(ea1);
		correoService.saveCorreo(ea2);
		correoService.saveCorreo(ea3);
		correoService.saveCorreo(ea4);
		correoService.saveCorreo(ea5);
		correoService.saveCorreo(ea6);
		correoService.saveCorreo(ea7);
		correoService.saveCorreo(ea8);

	}

}
