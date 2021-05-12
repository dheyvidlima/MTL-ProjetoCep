package br.com.martinello.viacep.Control;

import br.com.martinello.viacep.Domain.ErrosDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.Reader;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

public class CepControl {

    private String leitor(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String buscarCep(String cep) throws SQLException, IOException {

        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            CepControl l = new CepControl();

            String Txt = l.leitor(br);

            JSONObject jsonObj = new JSONObject(Txt);

            CepGS.setCEP(jsonObj.getString("cep"));
            CepGS.setLOGRADOURO(jsonObj.getString("logradouro"));
            CepGS.setCOMPLEMENTO(jsonObj.getString("complemento"));
            CepGS.setBAIRRO(jsonObj.getString("bairro"));
            CepGS.setLOCALIDADE(jsonObj.getString("localidade"));
            CepGS.setUF(jsonObj.getString("uf"));
            CepGS.setIBGE(jsonObj.getString("ibge"));

            ErrosDB erro = new ErrosDB();

            erro.verificacao();// Checar integridade do arquivo

        } catch (IOException | JSONException e) {
            ErrosDB erro = new ErrosDB();
            erro.erroCep();
            throw new RuntimeException(e);

        }
        return null;
    }

}
