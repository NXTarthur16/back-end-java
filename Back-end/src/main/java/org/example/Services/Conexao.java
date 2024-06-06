package org.example.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=Imobiliaria;trustServerCertificate=true";
            String usuario = "arthur";
            String senha = "arthur07012007";

            System.out.println("Conectando...");

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                System.out.println("conexão com o banco feita com sucesso");
                conexao.close();
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);

        }finally{
            try {
                if (conexao != null && !conexao.isClosed()){
                    conexao.close();
                }
            }catch(SQLException e){
                System.out.println("O erro no finaly foi: " + e);
            }
        }
        return conexao;
    }
}