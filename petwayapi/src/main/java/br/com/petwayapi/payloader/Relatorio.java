package br.com.petwayapi.payloader;


import java.util.List;

public class Relatorio {

    private List<Object> arrayDados;
    private String formato;

    public Relatorio(List<Object> arrayDados, String formato) {
        this.arrayDados = arrayDados;
        this.formato = formato;
    }

    public List<Object> getArrayDados() {
        return arrayDados;
    }

    public void setArrayDados(List<Object> arrayDados) {
        this.arrayDados = arrayDados;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}
