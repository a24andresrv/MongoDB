package com.example;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class Main {
    public static void main(String[] args) {
        MongoProvider provider = new MongoProvider();

    }

    public void insertarDocumentos(MongoCollection<Document> col) {
        Document doc1 = new Document("xogador", "Mario").append("xogo", "spaceInvaders").append("puntuacion", 1200)
                .append("duracion", 15).append("nivel", 3);
        Document doc2 = new Document("xogador", "Luigi").append("xogo", "marioKart").append("puntuacion", 950)
                .append("duracion", 10).append("nivel", 2);
        Document doc3 = new Document("xogador", "Peach").append("xogo", "zelda").append("puntuacion", 1400)
                .append("duracion", 20).append("nivel", 4);
        Document doc4 = new Document("xogador", "Bowser").append("xogo", "donkeyKong").append("puntuacion", 1100)
                .append("duracion", 18).append("nivel", 3);

        ArrayList<Document> listaPartidas = new ArrayList<>();
        listaPartidas.add(doc1);
        listaPartidas.add(doc2);
        listaPartidas.add(doc3);
        listaPartidas.add(doc4);
        col.insertMany(listaPartidas);
    }

}