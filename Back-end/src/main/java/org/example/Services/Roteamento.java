package org.example.Services;

import org.example.View.Telas;

import java.util.Scanner;

import static java.lang.System.exit;

public class Roteamento {
    static Telas telas = new Telas();
    //    static Telas telas = new Telas();
    static Scanner sc = new Scanner(System.in);

    public static void rotas(int escolha) {

        switch (escolha) {
            case 2:
                exit(0);
                break;
            case 1:
                System.out.println("Inicializando servidor");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                telas.telaInicial();
                break;
        }
    }
}