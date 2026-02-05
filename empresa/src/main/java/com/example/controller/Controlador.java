package com.example.controller;

//Este import permite no tener que poner Filters.eq pones simplemetne eq o and
import static com.mongodb.client.model.Filters.*;
//Este hace lo mismo pero para las actualizaciones
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Aggregates.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.GestorEmpleado;
import com.example.MongoProvider;
import com.example.view.Vista;
import com.mongodb.client.MongoCursor;

public class Controlador {
    Vista vista = new Vista();
    GestorEmpleado gestorEmpleado = new GestorEmpleado();

    public void ejecutar() {
        try (MongoProvider mongoProvider = new MongoProvider()) {
            Integer opcion;
            do {
                opcion = vista.mostrarMenu();
                menu(mongoProvider, opcion);
            } while (opcion != 9);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

    }

    private void menu(MongoProvider mongoProvider, Integer opcion) {
        MongoCursor<Document> cursor;
        switch (opcion) {
            case 1://Crear la acolección
                String nombreColeccion = vista.crearColeccion();
                mongoProvider.createCollection(nombreColeccion);
                break;
            case 2://Insertar empleados
                gestorEmpleado.insertarEmpleados(mongoProvider);
                break;
            case 3://Empleados del departamento 10
                cursor = gestorEmpleado.empleadosDep10(mongoProvider);
                vista.imprimirConsulta(cursor);
                break;
            case 4://Empleados de los departamentos 10 y 20
                cursor = gestorEmpleado.empleadosDep1020(mongoProvider);
                vista.imprimirConsulta(cursor);
                break;
            case 5://Empleados con salario > 1300 y oficio profesora
                cursor = gestorEmpleado.profesoraMas1300(mongoProvider);
                vista.imprimirConsulta(cursor);
                break;
            case 6://Incrementar en 100 el salario de los analistas
                cursor = gestorEmpleado.incrementarAnalistas(mongoProvider);
                vista.imprimirConsulta(cursor);
                break;
            case 7://Decrementar en 20 las comisiones
                cursor = gestorEmpleado.decrementarComisiones(mongoProvider);
                vista.imprimirConsulta(cursor);
                break;
            case 8: // Muestra la media de salario
                break;
            case 9: // Muestra por departamento: número de empleados, salario medio y máximo salario
                break;
            case 10: // Muestra el nombre del empleado con el salario máximo
                break;
            case 11: // Sale de la aplicación
                break;
            default:

                break;
        }
    }

}
