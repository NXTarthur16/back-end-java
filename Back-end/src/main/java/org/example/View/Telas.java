package org.example.View;

import java.util.Scanner;
import org.example.Services.Roteamento;

public class Telas {
    static Scanner sc = new Scanner(System.in);

    public static void telaInicial() {
        int escolhaInput = 0;

        System.out.println("Bem vindo ao nosso código!!");
        System.out.println(" ");

        System.out.println("1. inicializar servidor web");
        System.out.println(" ");
        escolhaInput = Integer.parseInt(sc.nextLine());

        Roteamento.rotas(escolhaInput);
    }
}