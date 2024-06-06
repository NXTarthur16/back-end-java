package org.example.DAL;

import org.example.Domain.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDal {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=Imobiliaria;trustServerCertificate=true";
            String usuario = "Arthur";
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

    public int inserirUsuario(String name, String lastName, int age, String address,
                              String email, String password, String cpf) throws SQLException{
        String sql = "INSERT INTO Usuario (name, lastName, age, address, email, password, cpf) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setString(4, address);
            statement.setString(5, email);
            statement.setString(6, password);
            statement.setString(7, cpf);

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

    public List listarUsuario() throws SQLException{
        String sql = "SELECT * FROM Usuario";
        ResultSet result = null;

        List<Usuario> usuarioArray = new ArrayList<>();

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos usuários: ");

            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                int age = result.getInt("age");
                String address = result.getString("address");
                String email = result.getString("email");
                String password = result.getString("password");
                String cpf = result.getString("cpf");

                Usuario currentUsuario = new Usuario(id, name, lastName, age, address,
                        email, password, cpf);

                usuarioArray.add(currentUsuario);

                System.out.println("Usuário adicionado com sucesso");
            }

            result.close();

            return  usuarioArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }
        return usuarioArray;
    }

    public int atualizarUsuario(int id, String name, String lastName, int age, String address,
                                String email, String password, String cpf) throws SQLException{
        String sql = "UPDATE Usuario SET name = ?, lastName = ?, age = ?, " +
                "address = ?, email = ?, password = ?, cpf = ?< WHERE id = ?";
        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setString(4, address);
            statement.setString(5, email);
            statement.setString(6, password);
            statement.setString(7, cpf);
            statement.setInt(8, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;

        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirUsuario(int id) throws SQLException{
        String sql = "DELETE FROM Usuario WHERE id = ?";
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