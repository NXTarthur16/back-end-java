package org.example.Domain;

import org.json.JSONObject;

import java.util.List;

public class Usuario {
    public int id = 0;
    public static String name = "";
    public static String lastName = "";
    public static int age = 0;
    public static String address = "";
    public static String email = "";
    public static String password = "";
    public static String cpf = "";

    public Usuario(){
    }

    public Usuario(int id){
        this.id=id;
    }

    public Usuario(String name, String lastName, int age, String address,
                   String email, String password, String cpf) {

        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public Usuario(int id, String name, String lastName, int age, String address,
                   String email, String password, String cpf) {

        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("lastName", lastName);
        json.put("age", age);
        json.put("address", address);
        json.put("email", email);
        json.put("password", password);
        json.put("cpf", cpf);

        return json;
    }

    public JSONObject arrayToJson(List<Usuario> usuarioList) {
        JSONObject json = new JSONObject();

        if (!usuarioList.isEmpty()) {
            int contJson = 0;

            for (Usuario usuario : usuarioList) {
                JSONObject jsonFor = new JSONObject();
                jsonFor.put("id", usuario.getId());
                jsonFor.put("name", usuario.getName());
                jsonFor.put("lastName", usuario.getLastName());
                jsonFor.put("age", usuario.getAge());
                jsonFor.put("address", usuario.getAddress());
                jsonFor.put("email", usuario.getEmail());
                jsonFor.put("password", usuario.getPassword());
                jsonFor.put("cpf", usuario.getCpf());

                json.put(String.valueOf(contJson), jsonFor);

                contJson++;
            }
            return json;
        }

        else{
            return null;
        }
    }

    public static Usuario getUsuario(int index, List<Usuario> usuarioList){
        if(index >= 0 && index < usuarioList.size()) {

            return usuarioList.get(index);
        }

        else{
            return null;
        }
    }

    public static List<Usuario> getAllUsers(List<Usuario> usuarioList){
        return usuarioList;
    }
}