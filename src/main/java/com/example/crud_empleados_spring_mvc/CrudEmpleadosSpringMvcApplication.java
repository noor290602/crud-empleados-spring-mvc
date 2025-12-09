package com.example.crud_empleados_spring_mvc;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.crud_empleados_spring_mvc.entities.Departamento;
import com.example.crud_empleados_spring_mvc.entities.Empleado;
import com.example.crud_empleados_spring_mvc.models.Genero;
import com.example.crud_empleados_spring_mvc.services.DepartamentoService;
import com.example.crud_empleados_spring_mvc.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEmpleadosSpringMvcApplication implements CommandLineRunner {

	private final EmpleadoService empleadoService;
	private final DepartamentoService departamentoService;

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
	}

}
