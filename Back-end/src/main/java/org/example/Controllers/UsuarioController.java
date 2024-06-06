package org.example.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.example.Domain.Usuario;
import org.example.DAL.UsuarioDal;
import org.json.JSONObject;

import org.example.Services.ResponseEndPoints;

import java.io.IOException;
import java.io.InputStream;


import java.util.ArrayList;
import java.util.List;
public class UsuarioController {
    public static String response = "";
    static ResponseEndPoints res = new ResponseEndPoints();
    private static List<Usuario> usuarioList = new ArrayList<>();
    public static class UsuarioHandler implements HttpHandler {
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
        UsuarioDal usuarioDal = new UsuarioDal();
        Usuario usuario = new Usuario();
        List<Usuario> usuarioArray;
        JSONObject json;

            try{
                usuarioArray = usuarioDal.listarUsuario();
                json = usuario.arrayToJson(usuarioArray);

                res.enviarResponseJson(exchange, json, 200);
            }catch(Exception e){
                System.out.println("O erro foi: " + e);
                response = "Ocorreu um erro ao buscar os dados";
                res.enviarResponse(exchange, response, 500);
            }
    }

    public static void doPost(HttpExchange exchange) throws IOException{
        UsuarioDal userDal = new UsuarioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            Usuario usuario = new Usuario(
                    json.getString("name"),
                    json.getString("lastName"),
                    json.getInt("age"),
                    json.getString("address"),
                    json.getString("email"),
                    json.getString("password"),
                    json.getString("cpf")
            );

            usuarioList.add(usuario);

            resp = userDal.inserirUsuario(usuario.name, usuario.lastName, usuario.age,
                    usuario.address, usuario.email, usuario.password, usuario.cpf);

            if(resp == 0){
                response = "Houve um problema ao criar usuário";
            }else{
                response = "Usuário criado com sucesso";
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
        UsuarioDal userDal = new UsuarioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            resp = userDal.excluirUsuario(id);

            if(resp == 0){
                response = "Houve um problema ao deletar usuário";
            }else{
                response = "Usuário deletado com sucesso";
            }

            res.enviarResponse(exchange, response, 200);
        }catch(Exception e){
            response = e.toString();

            System.out.println(response);
            System.out.println("---------");

        }

        response = "Essa e a rota de user - DELETE";
        res.enviarResponse(exchange, response, 200);
    }

    public static void doPut(HttpExchange exchange) throws IOException{
        UsuarioDal userDal = new UsuarioDal();

        try(InputStream requestBody = exchange.getRequestBody()){
            JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));
            int resp = 0;

            int id = Integer.parseInt(json.getString("id"));

            Usuario usuario = new Usuario(
                    json.getString("name"),
                    json.getString("lastName"),
                    json.getInt("age"),
                    json.getString("address"),
                    json.getString("email"),
                    json.getString("password"),
                    json.getString("cpf")
            );

            usuarioList.add(usuario);

            resp = userDal.atualizarUsuario(id, usuario.name, usuario.lastName, usuario.age,
                    usuario.address, usuario.email, usuario.password, usuario.cpf);

            if(resp == 0){
                response = "Houve um problema ao atualizar usuário";
            }else{
                response = "Usuário atualizado com sucesso";
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