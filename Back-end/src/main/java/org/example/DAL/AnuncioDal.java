package org.example.DAL;

import org.example.Domain.Anuncio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnuncioDal {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=Imobiliaria;trustServerCertificate=true";
            String usuario = "arthur";
            String senha = "arthur07012007";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                return conexao;
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);
        }

        return conexao;
    }

    public int inserirAnuncio(String numeroContato, String valor1, String valor2, String comodos, String bairro, String descricao,
                              String titulo, String imagem) throws SQLException{
        String sql = "INSERT INTO Anuncio (numeroContato, valor1, valor2, comodos, bairro, descricao, titulo, imagem) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, numeroContato);
            statement.setString(2, valor1);
            statement.setString(3, valor2);
            statement.setString(4, comodos);
            statement.setString(5, bairro);
            statement.setString(6, descricao);
            statement.setString(7, titulo);
            statement.setString(8, imagem);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            conexao.close();
            return linhasAfetadas;
        }catch(SQLException e){
            System.out.println("O Erro na Inserção de dados foi: " + e);
            conexao.close();
        }
        conexao.close();
        return linhasAfetadas;
    }

    public List listarAnuncio() throws SQLException{
        String sql = "SELECT * FROM Anuncio";
        ResultSet result = null;

        List<Anuncio> AnucioArray = new ArrayList<>();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos anúncios: ");

            while (result.next()){
                int id = result.getInt("id");
                String numeroContato = result.getString("numeroContato");
                String valor1 = result.getString("valor1");
                String valor2 = result.getString("valor2");
                String comodos = result.getString("comodos");
                String bairro = result.getString("bairro");
                String descricao = result.getString("descricao");
                String titulo = result.getString("titulo");
                String imagem = result.getString("imagem");

                Anuncio currentAnuncio = new Anuncio(id, numeroContato, valor1, valor2, comodos, bairro,
                        descricao, titulo, imagem);

                AnucioArray.add(currentAnuncio);

                System.out.println("Anuncio adicionado com sucesso " + currentAnuncio);
            }
            result.close();

            return AnucioArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }
        return AnucioArray;
    }

    public int atualizarAnuncio(String numeroContato, String valor1, String valor2, String comodos, String bairro,
                                String descricao, String titulo, String imagem, int id) throws SQLException{
        String sql = "UPDATE Anuncio SET numeroContato = ?, valor1 = ?, valor2 = ?, comodos = ?, age = ?, bairro = ?, descricao = ?, titulo = ?, imagem = ?< WHERE id = ?";
        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, numeroContato);
            statement.setString(2, valor1);
            statement.setString(3, valor2);
            statement.setString(4, comodos);
            statement.setString(5, bairro);
            statement.setString(6, descricao);
            statement.setString(7, titulo);
            statement.setString(8, imagem);
            statement.setInt(9, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;

        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirAnuncio(int id) throws SQLException{
        String sql = "DELETE FROM Anuncio WHERE id = ?";
        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setInt(1, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;
        }catch(SQLException e){
            System.out.println("O Erro na inserção de dados foi: " + e);
        }
        return linhasAfetadas;
    }
}