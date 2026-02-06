package com.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

public class GestorPartidas {
    public void insertarDocumentos(MongoCollection<Document> col) {
        Document doc1 = new Document("xogador", "Xogador1").append("xogo", "xogoA")
                .append("puntuacion", 500)
                .append("duracion", 12).append("nivel", 1);
        Document doc2 = new Document("xogador", "Xogador1").append("xogo", "xogoB").append("puntuacion", 300)
                .append("duracion", 8).append("nivel", 2);
        Document doc3 = new Document("xogador", "Xogador2").append("xogo", "xogoA").append("puntuacion", 700)
                .append("duracion", 15).append("nivel", 3);
        Document doc4 = new Document("xogador", "Xogador2").append("xogo", "xogoC")
                .append("puntuacion", 400)
                .append("duracion", 10).append("nivel", 2);
        Document doc5 = new Document("xogador", "Xogador3").append("xogo", "xogoB")
                .append("puntuacion", 600)
                .append("duracion", 20).append("nivel", 4);
        Document doc6 = new Document("xogador", "Xogador3").append("xogo", "xogoC")
                .append("puntuacion", 800)
                .append("duracion", 25).append("nivel", 5);

        ArrayList<Document> listaPartidas = new ArrayList<>();
        listaPartidas.add(doc1);
        listaPartidas.add(doc2);
        listaPartidas.add(doc3);
        listaPartidas.add(doc4);
        listaPartidas.add(doc5);
        listaPartidas.add(doc6);
        col.insertMany(listaPartidas);
        System.out.println("Valores de exemplo insertados correctamente.");
    }

    public void puntuacionTotalPorXogador(MongoCollection<Document> col) {
        ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.group("$xogador", Accumulators.sum("total puntuacion", "$puntuacion")));
        col.aggregate(pipeline).into(documentos);
        for (Document documento : documentos) {
            System.out.println(documento);
        }
    }

    public void mellorPartidaPorXogador(MongoCollection<Document> col) {
        ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.group("$xogador", Accumulators.max("mejor puntuación", "$puntuacion")));
        col.aggregate(pipeline).into(documentos);
        for (Document documento : documentos) {
            System.out.println(documento);
        }
    }

    public void partidaMaisCurtaPorXogo(MongoCollection<Document> col) {
        ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.group("$xogado", Accumulators.min("partida mais curta", "$duracion")));
        col.aggregate(pipeline).into(documentos);
        for (Document documento : documentos) {
            System.out.println(documento);
        }
    }

    public void rankingDeXogadores(MongoCollection<Document> col) {
        ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.group("$xogador",
                Accumulators.sum("puntuacion_total", "$puntuacion")));
        pipeline.add(Aggregates.sort(Sorts.descending("puntuacion_total")));
        col.aggregate(pipeline).into(documentos);
        for (Document documento : documentos) {
            System.out.println(documento);
        }
    }


    public void listaxeSimplificada(MongoCollection<Document> col){
        ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.project(Projections.fields(excludeId(),include())));
    }
    /*public void xogosMaisPuntuables(MongoCollection<Document> col){
                ArrayList<Document> documentos = new ArrayList<Document>();
        ArrayList<Bson> pipeline = new ArrayList<>();
        pipeline.add(Aggregates.group("$xogador",
                Accumulators.sum("puntuacion_total", "$puntuacion")));
        pipeline.add(Aggregates.sort(Sorts.descending("puntuacion_total")));
        col.aggregate(pipeline).into(documentos);
        for (Document documento : documentos) {
            System.out.println(documento);
        }
    }*/
}
