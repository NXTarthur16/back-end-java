package org.example.Services;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.example.Controllers.UsuarioController;
import org.example.Controllers.AnuncioController;

public class WebServer {
    public void apiServer() throws IOException{

        HttpServer server = HttpServer.create(new InetSocketAddress(8090),
                0);

        HttpHandler usuarioHandler = new UsuarioController.UsuarioHandler();
        HttpHandler anunciarHandler = new AnuncioController.AnuncioHandler();

        server.createContext("/api/login",  exchange -> {
            configureCors(exchange);
            usuarioHandler.handle(exchange);
        });
        server.createContext("/api/anunciar",  exchange -> {
            configureCors(exchange);
            anunciarHandler.handle(exchange);
        });

        server.setExecutor(null);
        System.out.println("Servidor Iniciado");
        server.start();
    }

    private void configureCors(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        String requestOrigin = exchange.getResponseHeaders().getFirst("Origin");
        if (requestOrigin != null) {
            headers.set("Access-Control-Allow-Origin", requestOrigin);
        }
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET,OPTIONS,POST,PUT,DELETE");
        headers.set("Access-Control-Allow-Headers", "Content-Type, Authorization");
        headers.set("Access-Control-Allow-Credentials", "true");
        headers.set("Access-Control-Max-Age", "3600");
    }
}