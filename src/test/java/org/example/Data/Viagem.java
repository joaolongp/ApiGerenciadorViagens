package org.example.Data;

import org.json.JSONObject;

import java.util.*;

public class Viagem {
    public String acompanhante;
    public String localDeDestino;
    public String dataPartida;
    public String dataRetorno;
    public String regiao;

    public Viagem(){
        Random rnd = new Random();
        String[] listNames = {"joao", "maria", "pedro", "camila", "jonas"};
        String[] listCountries = {"Italia", "EUA", "Franca", "Canada", "Brasil"};
        String[] listRegion = {"Sul", "Norte", "Leste", "Oeste"};
        acompanhante = listNames[rnd.nextInt(listNames.length)];
        localDeDestino = listCountries[rnd.nextInt(listCountries.length)];
        dataPartida = "2022-01-01";
        dataRetorno = "2022-01-02";
        regiao = listRegion[rnd.nextInt(listRegion.length)];
    }

    public String toJsonString(){
        Map<String, String> elements = new HashMap<>();
        elements.put("acompanhante", acompanhante);
        elements.put("localDeDestino", localDeDestino);
        elements.put("dataPartida", dataPartida);
        elements.put("dataRetorno", dataRetorno);
        elements.put("regiao", regiao);

        return new JSONObject(elements).toString();
    }
}
