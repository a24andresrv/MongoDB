package com.example;

import static com.mongodb.client.model.Filters.*;

import static com.mongodb.client.model.Updates.*;

import static com.mongodb.client.model.Aggregates.*;

import static com.mongodb.client.model.Accumulators.*;

import static com.mongodb.client.model.Sorts.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

public class GestorEmpleado {

    public void insertarEmpleados(MongoProvider mongoProvider) {
        MongoCollection<Document> col = mongoProvider.getCollection("empleados");
        ArrayList<Document> empleados = new ArrayList<>();
        Document emp1 = new Document("emp_no", 1)
                .append("nombre", "Juan")
                .append("dep", 10)
                .append("salario", 1000)
                .append("fechaalta", "10/10/1999");
        empleados.add(emp1);
        Document emp2 = new Document("emp_no", 2)
                .append("nombre", "Alicia")
                .append("dep", 10)
                .append("salario", 1400)
                .append("fechaalta", "07/08/2000")
                .append("oficio", "Profesora");
        empleados.add(emp2);
        Document emp3 = new Document("emp_no", 3)
                .append("nombre", "María Jesús")
                .append("dep", 20)
                .append("salario", 1500)
                .append("fechaalta", "05/01/2005")
                .append("oficio", "Analista")
                .append("comisión", 100);
        empleados.add(emp3);
        Document emp4 = new Document("emp_no", 4)
                .append("nombre", "Alberto")
                .append("dep", 20)
                .append("salario", 1100)
                .append("fechaalta", "15/11/2001");
        empleados.add(emp4);
        Document emp5 = new Document("emp_no", 5)
                .append("nombre", "Fernando")
                .append("dep", 30)
                .append("salario", 1400)
                .append("fechaalta", "20/11/1999")
                .append("oficio", "Analista")
                .append("comisión", 200);
        empleados.add(emp5);

        col.insertMany(empleados);
        System.out.println("Colección empleados creada con datos iniciales.");
    }

    private MongoCursor<Document> empleadosFind(MongoProvider mongoProvider, Bson filter) {
        MongoCollection<Document> col = mongoProvider.getCollection("empleados");
        FindIterable<Document> empleados = col.find(filter);
        return empleados.iterator();
    }

    public AggregateIterable<Document> empleadosAgregate(MongoProvider mongoProvider,List<Bson> pipeline) {
        MongoCollection<Document> col = mongoProvider.getCollection("empleados");
        AggregateIterable<Document> salario = col.aggregate(pipeline);
        return salario;
    }

    public AggregateIterable<Document> getSalarioDept(MongoProvider mongoProvider){
        List<Document> lista = new ArrayList<>();
        List<Bson> pipeline = List.of(
            group("dep",
            sum("numEmpleado",1),
            avg("salarioMedio","$salario"),
            Accumulators.max("salarioMaximo","$salario")
            ),
            sort(ascending("dep"))
        );
        return empleadosAgregate(mongoProvider, pipeline);
    }

    public MongoCursor<Document> empleadosUpdate(MongoProvider mongoProvider, Bson filter, Bson update) {
        MongoCollection<Document> col = mongoProvider.getCollection("empleados");
        MongoCursor<Document> cursorActualizacion = null;
        try {
            UpdateResult nEmpleadosUpdated = col.updateMany(filter, update);
            cursorActualizacion = empleadosFind(mongoProvider, filter);
            return cursorActualizacion;
        } catch (Exception e) {
            System.out.println("Error actualizando: " + e.getMessage());
            return cursorActualizacion;
        }
    }

    public MongoCursor<Document> empleadosDep10(MongoProvider mongoProvider) {
        Bson filter = eq("dep", 10);
        return empleadosFind(mongoProvider, filter);
    }

    public MongoCursor<Document> empleadosDep1020(MongoProvider mongoProvider) {
        Bson filter = or(eq("dep", 10), eq("dep", 20));
        return empleadosFind(mongoProvider, filter);
    }

    public MongoCursor<Document> profesoraMas1300(MongoProvider mongoProvider) {
        Bson filter = and(eq("oficio", "Profesora"), gt("salario", 1300));
        return empleadosFind(mongoProvider, filter);
    }

    public MongoCursor<Document> incrementarAnalistas(MongoProvider mongoProvider) {
        Bson filter = eq("oficio", "Analista");
        Bson update = inc("salario", 100);
        return empleadosUpdate(mongoProvider, filter, update);
    }

    public MongoCursor<Document> decrementarComisiones(MongoProvider mongoProvider) {
        // Filtro que comprueba si tiene el atributo comisión, si no se pone los que
        // tiene el atributo se lo crea con -20
        Bson filter = exists("comisión", true);
        Bson update = inc("comisión", -20);
        return empleadosUpdate(mongoProvider, filter, update);
    }
}
