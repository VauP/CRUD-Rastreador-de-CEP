package table;

import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.net.*;
import java.io.*;

public class PesquisaCep {

	String cepAtual;

	public PesquisaCep(String cepDigitado) {
		this.cepAtual = cepDigitado;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public final String getHttpGET(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();

            URL url = new URL(urlToRead);
            HttpURLConnection conn;

			conn = (HttpURLConnection) url.openConnection();
		
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
           
        
        return result.toString();
    }


	public Object buscar(String cep) throws Exception {
        // define o cep atual
        cepAtual = cep;

        // define a url
        String url = "http://viacep.com.br/ws/" + cep + "/json/";

        // define os dados
        JSONObject obj = new JSONObject(getHttpGET(url));
        
        return obj;
    }

}
