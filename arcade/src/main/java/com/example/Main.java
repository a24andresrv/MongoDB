package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class Main {
        public static void main(String[] args) {
                MongoProvider provider = new MongoProvider();
                Scanner sc = new Scanner(System.in);
                Integer opcion = null;
                while (true) {
                        System.out.println("1. Puntuación total por xogador");
                        System.out.println("2. Mellor partida de cada xogador");
                        System.out.println("3. Partida máis curta por xogo");
                        System.out.println("4. Ranking de xogadores");
                        System.out.println("5. Listaxe simplificada de partidas");
                        System.out.println("6. Xogos máis puntuables");
                        System.out.println("7. Inserta valores de exemplo");
                        System.out.println("0. Salir");
                        System.out.print("Escolle unha opción: ");

                        opcion = sc.nextInt();

                        MongoCollection<Document> col = provider.getCollection("partidas");
                        GestorPartidas gestor = new GestorPartidas();

                        switch (opcion) {
                                case 1:
                                        gestor.puntuacionTotalPorXogador(col);
                                        break;
                                case 2:
                                        gestor.mellorPartidaPorXogador(col);
                                        break;
                                case 3:
                                        gestor.partidaMaisCurtaPorXogo(col);
                                        break;
                                case 4:
                                        gestor.rankingDeXogadores(col);
                                        break;
                                case 5:
                                        gestor.listaxeSimplificada(col);
                                        break;
                                case 6:
                                        gestor.xogosMaisPuntuables(col);
                                        break;
                                case 7:
                                        gestor.insertarDocumentos(col);
                                        break;

                                case 0:
                                        sc.close();
                                        return;
                                default:
                                        System.out.println("Opción non válida");
                        }
                }

        }



}