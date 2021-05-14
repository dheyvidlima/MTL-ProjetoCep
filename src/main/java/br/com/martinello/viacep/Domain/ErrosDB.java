package br.com.martinello.viacep.Domain;

import br.com.martinello.viacep.Control.CepGS;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vitor.trevisan
 */
public class ErrosDB {

    static String url = "jdbc:mysql://127.0.0.1:3306/INTEGRACAO";
    static String usr = "vitor";
    static String psw = "etrev7529";
    static Connection conn;

    public void erroCep() throws SQLException {
        conn = DriverManager.getConnection(url, usr, psw);
        Statement stm = conn.createStatement();
        String sql = ("INSERT INTO Log (id_log, msg_erro) VALUES (id_log = id_log + 1, 'ERRO: inserção de CEP inválido');");
        stm.execute(sql);
        JOptionPane.showMessageDialog(null, "Cep inválido inserido");

    }

    public void verificacao() throws SQLException, IOException {

        conn = DriverManager.getConnection(url, usr, psw);
        Statement stm = conn.createStatement();
        String sql_res = "SELECT * FROM Ceps WHERE cep = '" + CepGS.getCEP() + "'";
        ResultSet rs = stm.executeQuery(sql_res);

        if (rs.next()) {
            String cep = rs.getString("cep");
            System.out.println("Cep " + cep + " já cadastrado, atualizando...");
            ErrosDB erro = new ErrosDB();
            erro.cepDuplicado();

        } else {
            System.out.println("Efetuando cadastro no banco de dados...");
            CepDAO conn = new CepDAO();
            conn.Conexao();

        }

    }

    public void cepDuplicado() throws SQLException {
        conn = DriverManager.getConnection(url, usr, psw);
        String query = "UPDATE Ceps SET cep=?, uf=?, localidade=?, bairro=?, logradouro=?, complemento=?, codigo_ibge=? WHERE cep ='" + CepGS.getCEP() + "';";

        PreparedStatement pstm = conn.prepareStatement(query);

        pstm.setString(1, CepGS.getCEP());
        pstm.setString(2, CepGS.getUF());
        pstm.setString(3, CepGS.getLOCALIDADE());
        pstm.setString(4, CepGS.getBAIRRO());
        pstm.setString(5, CepGS.getLOGRADOURO());
        pstm.setString(6, CepGS.getCOMPLEMENTO());
        pstm.setString(7, CepGS.getIBGE());

        pstm.executeUpdate();

        System.out.println("Colunas atualizadas.");

        retorno();

    }

    public void retorno() throws SQLException {

        conn = DriverManager.getConnection(url, usr, psw);
        String query = "Select * from Ceps WHERE cep = " + CepGS.getCEP();
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.execute();

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            System.out.println("Cep = " + rs.getString("cep"));
            System.out.println("UF = " + rs.getString("uf"));
            System.out.println("Logradouro = " + rs.getString("logradouro"));
            System.out.println("Localidade = " + rs.getString("localidade"));
            System.out.println("Bairro = " + rs.getString("bairro"));
            System.out.println("Complemento = " + rs.getString("complemento"));
            System.out.println("IBGE = " + rs.getString("codigo_ibge"));

        }
        
            

        conn.close();
    }
}
