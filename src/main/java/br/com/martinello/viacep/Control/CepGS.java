package br.com.martinello.viacep.Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vitor.trevisan
 */
public class CepGS {

    public static String getCEP() {
        return CEP;
    }

    public static void setCEP(String CEP) {
        CepGS.CEP = CEP;
    }

    public static String getLOGRADOURO() {
        return LOGRADOURO;
    }

    public static void setLOGRADOURO(String LOGRADOURO) {
        CepGS.LOGRADOURO = LOGRADOURO;
    }

    public static String getCOMPLEMENTO() {
        return COMPLEMENTO;
    }

    public static void setCOMPLEMENTO(String COMPLEMENTO) {
        CepGS.COMPLEMENTO = COMPLEMENTO;
    }

    public static String getBAIRRO() {
        return BAIRRO;
    }

    public static void setBAIRRO(String BAIRRO) {
        CepGS.BAIRRO = BAIRRO;
    }

    public static String getLOCALIDADE() {
        return LOCALIDADE;
    }

    public static void setLOCALIDADE(String LOCALIDADE) {
        CepGS.LOCALIDADE = LOCALIDADE;
    }

    public static String getUF() {
        return UF;
    }

    public static void setUF(String UF) {
        CepGS.UF = UF;
    }

    public static String getIBGE() {
        return IBGE;
    }

    public static void setIBGE(String IBGE) {
        CepGS.IBGE = IBGE;
    }

    private static String CEP;
    private static String LOGRADOURO;
    private static String COMPLEMENTO;
    private static String BAIRRO;
    private static String LOCALIDADE;
    private static String UF;
    private static String IBGE;
    
    
}
