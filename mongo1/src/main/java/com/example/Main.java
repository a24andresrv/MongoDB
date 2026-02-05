package com.example;

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class Main {
    public static void main(String[] args) {
        try (MongoProvider provider = new MongoProvider();)
        {
            System.out.println();

            MongoCollection<Document> colection= provider.alumnado();
            System.out.println("Creacion de alumno");

            Document alumno= new Document("nombre", "Anxo")
                    .append("edad", 21)
                    .append("ciclo", "DAM");
            provider.alumnado().insertOne(alumno);
            Document alumno2= new Document("nombre", "Anxo2")
                    .append("edad", 21)
                    .append("ciclo", "DAM");
            provider.alumnado().insertOne(alumno2);
            ArrayList<Document> out = new ArrayList<Document>();
            provider.alumnado().find().into(out);
            System.out.println();
            System.out.println("Todos los alumnos: ");
            System.out.println(out);
            System.out.println();
            System.out.println("Elminar un elemento");
            colection.deleteOne(Filters.eq("nombre","Anxo")).getDeletedCount();
            out=new ArrayList<Document>();
            provider.alumnado().find().into(out);
            System.out.println(out);
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }
}