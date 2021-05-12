package br.com.martinello.viacep.Domain;

import br.com.martinello.viacep.Control.CepGS;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CepDAO {

    static String url = "jdbc:mysql://127.0.0.1:3306/INTEGRACAO";
    static String usr = "vitor";
    static String psw = "etrev7529";
    static Connection conn;
    static String sql;

    public String Conexao() throws SQLException, IOException {

        try {

            conn = DriverManager.getConnection(url, usr, psw);
            System.out.println("Conectado e aplicando alterações.");
            sql = ("USE INTEGRACAO;");

            String query = "INSERT INTO Ceps (cep, uf, localidade, bairro, logradouro, complemento, codigo_ibge) VALUES (?, ?, ?, ?, ?, ? ,?)";
            PreparedStatement pstm = CepDAO.conn.prepareStatement(query);

            pstm.setString(1, CepGS.getCEP());
            pstm.setString(2, CepGS.getUF());
            pstm.setString(3, CepGS.getLOCALIDADE());
            pstm.setString(4, CepGS.getBAIRRO());
            pstm.setString(5, CepGS.getLOGRADOURO());
            pstm.setString(6, CepGS.getCOMPLEMENTO());
            pstm.setString(7, CepGS.getIBGE());

            pstm.execute();
            
            ErrosDB select = new ErrosDB();
            
            select.retorno();
            

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (conn != null) {
                    conn.close();

                }

            } catch (RuntimeException ex) {
                ex.printStackTrace();

            }
        }

        return null;
    }

}
