package org.example.Domain;

import org.json.JSONObject;

import java.util.List;

public class Anuncio {

    public int id = 0;
    public static String numeroContato;
    public static String valor1;
    public static String valor2;
    public static String comodos;
    public static String bairro;
    public static String descricao;
    public static String titulo;
    public static String imagem;

    public Anuncio(String numeroContato, String valor1, String valor2, String comodos,
                          String bairro, String descricao, String titulo, String imagem) {
        this.numeroContato = numeroContato;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.comodos = comodos;
        this.bairro = bairro;
        this.descricao = descricao;
        this.titulo = titulo;
        this.imagem = imagem;
    }

    public Anuncio(int id,String numeroContato, String valor1, String valor2, String comodos,
                   String bairro, String descricao, String titulo, String imagem){

        this.id = id;
        this.numeroContato = numeroContato;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.comodos = comodos;
        this.bairro = bairro;
        this.descricao = descricao;
        this.titulo = titulo;
        this.imagem = imagem;
    }

    public Anuncio() {

    }

    public Anuncio(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public static String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getComodos() {
        return comodos;
    }

    public void setComodos(String comodos) {
        this.comodos = comodos;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("numeroContato", numeroContato);
        json.put("valor1", valor1);
        json.put("valor2", valor2);
        json.put("comodos", comodos);
        json.put("bairro", bairro);
        json.put("descricao", descricao);
        json.put("titulo", titulo);
        json.put("imagem", imagem);

        return json;
    }

    public JSONObject arrayToJson(List<Anuncio> anuncioList) {
        JSONObject json = new JSONObject();

        if (!anuncioList.isEmpty()) {
            var contJson = 0;

            for (Anuncio anuncio : anuncioList) {
                JSONObject jsonFor = new JSONObject();
                jsonFor.put("id", anuncio.getId());
                jsonFor.put("numeroContato", anuncio.getNumeroContato());
                jsonFor.put("valor1", anuncio.getValor1());
                jsonFor.put("valor2", anuncio.getValor2());
                jsonFor.put("comodos", anuncio.getComodos());
                jsonFor.put("bairro", anuncio.getBairro());
                jsonFor.put("descricao", anuncio.getDescricao());
                jsonFor.put("titulo", anuncio.getTitulo());
                jsonFor.put("imagem", anuncio.getImagem());

                json.put(String.valueOf(contJson), jsonFor);

                contJson++;
            }
            return json;
        }

        else{
            return null;
        }
    }

    public static Anuncio getAnuncio(int index, List<Anuncio> anuncioList){
        if(index >= 0 && index < anuncioList.size()) {

            return anuncioList.get(index);
        }

        else{
            return null;
        }
    }

    public static List<Anuncio> getAllAnuncio(List<Anuncio> anuncioList){
        return anuncioList;
    }
}