package com.example.view;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

public class Vista {
    private Scanner sc = new Scanner(System.in);

    public Integer mostrarMenu() {
        System.out.println("1. Crear coleccion");
        System.out.println("2. Insertar documentos");
        System.out.println("3. Visualizar empleados del departamento 10");
        System.out.println("4. Visualizar empleados de los departamentos 10 y 20");
        System.out.println("5. Obtener empleados con salario > 1300 y oficio \"Profesora\"");
        System.out.println("6. Subir el salario de los analistas en 100€");
        System.out.println("7. Decrementar la comisión existente en 20€");
        System.out.println("8. Visualizar la media de salario");
        System.out.println("9. Visualizar por departamento: número de empleados, salario medio y máximo salario");
        System.out.println("10. Visualizar el nombre del empleado que tiene el máximo salario");
        System.out.println("11. Salir");
        System.out.print("Seleccione una opción: ");
        return leerEntero();
    }
        public String crearColeccion() {
        System.out.print("Introduce el nombre de la colección a crear: ");
        return sc.nextLine();
    }
    public void imprimirConsulta(MongoCursor<Document> cursor){
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    public Integer leerEntero() {
        Integer entero = null;
        while (entero == null) {
            try {
                entero = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Introduce un entero: " + e.getMessage());
            }
        }
        return entero;
    }

    public String leerLinea() {
        System.out.print("Introduce una línea de texto: ");
        return sc.nextLine();
    }

}
