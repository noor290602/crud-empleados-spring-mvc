package com.example.crud_empleados_spring_mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud_empleados_spring_mvc.entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

}
