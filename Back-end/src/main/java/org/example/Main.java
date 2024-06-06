package org.example;

import org.example.Services.WebServer;
import org.example.View.Telas;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Telas telas = new Telas();
        WebServer server = new WebServer();

        telas.telaInicial();
        server.apiServer();
    }
}