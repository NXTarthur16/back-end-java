package org.example.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.example.DAL.AnuncioDal;
import org.example.Domain.Anuncio;
import org.json.JSONObject;

import org.example.Services.ResponseEndPoints;

import java.io.IOException;
import java.io.InputStream;


import java.util.ArrayList;
import java.util.List;

public class AnuncioController {
    public static String response = "";
    static ResponseEndPoints res = new ResponseEndPoints();
    private static List<Anuncio> anuncioList = new ArrayList<>();
    public static class AnuncioHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            if ("GET".equals(exchange.getRequestMethod())){
                doGet(exchange);
            }
            else if ("POST".equals(exchange.getRequestMethod())){
                doPost(exchange);
            }
            else if ("DELETE".equals(exchange.getRequestMethod())) {
                doDelete(exchange);
            }
            else if ("PUT".equals(exchange.getRequestMethod())){
                doPut(exchange);
            }
            else if ("OPTIONS".equals(exchange.getRequestMethod())){
                doOptions(exchange);
            }
            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response, 405);
            }
        }
    }

    public static void doGet(HttpExchange exchange) throws IOException{
        AnuncioDal anuncioDal = new AnuncioDal();
        Anuncio anuncio = new Anuncio();
        List<Anuncio> anuncioArray;
        JSONObject json;

        try{
            anuncioArray = anuncioDal.listarAnuncio();
            json = anuncio.arrayToJson(anuncioArray);

            System.out.println("Estou no try do doGet: " + json);
            res.enviarResponseJson(exchange, json, 200);

        }catch(Exception e){
            System.out.println("O erro foi: " + e);
            response = "Ocorreu um erro ao buscar os dados";
            res.enviarResponse(exchange, response, 500);
        }
    }

    public static void doPost(HttpExchange exchange) throws IOException{
        AnuncioDal anuncioDal = new AnuncioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            Anuncio anuncio = new Anuncio(
                    json.getString("numeroContato"),
                    json.getString("valor1"),
                    json.getString("valor2"),
                    json.getString("comodos"),
                    json.getString("bairro"),
                    json.getString("descricao"),
                    json.getString("titulo"),
                    json.getString("imagem")
            );

            anuncioList.add(anuncio);

            resp = anuncioDal.inserirAnuncio(anuncio.numeroContato, anuncio.valor1, anuncio.valor2, anuncio.comodos, anuncio.bairro,
                    anuncio.descricao, anuncio.titulo, anuncio.imagem);

            if(resp == 0){
                response = "Houve um problema ao criar o anúncio";
            }else{
                response = "Anúncio criado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

            res.enviarResponse(exchange, response, 200);
        }
    }

    public static void doDelete(HttpExchange exchange) throws IOException{
        AnuncioDal anuncioDal = new AnuncioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            resp = anuncioDal.excluirAnuncio(id);

            if(resp == 0){
                response = "Houve um problema ao deletar anúncio";
            }else{
                response = "Anúncio deletado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

        }

        response = "Essa e a rota de anuncio - DELETE";
        res.enviarResponse(exchange, response, 200);
    }

    public static void doPut(HttpExchange exchange) throws IOException{
        AnuncioDal anuncioDal = new AnuncioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            Anuncio anuncio = new Anuncio(
                    json.getString("numeroContato"),
                    json.getString("valor1"),
                    json.getString("valor2"),
                    json.getString("comodos"),
                    json.getString("bairro"),
                    json.getString("descricao"),
                    json.getString("titulo"),
                    json.getString("imagem")
            );

            anuncioList.add(anuncio);

            resp = anuncioDal.atualizarAnuncio(anuncio.numeroContato, anuncio.valor1, anuncio.valor2, anuncio.comodos, anuncio.bairro,
                    anuncio.descricao, anuncio.titulo, anuncio.imagem, id);

            if(resp == 0){
                response = "Houve um problema ao atualizar o anúncio";
            }else{
                response = "Anúncio atualizado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

            res.enviarResponse(exchange, response, 200);
        }
    }

    public static void doOptions(HttpExchange exchange) throws IOException{
        exchange.sendResponseHeaders(204,-1);
        exchange.close();
        return;
    }
}