package dao;

import model.DADOS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class DADOSDAO {

private Connection conn;
private PreparedStatement stmt;
private Statement st;
private ResultSet rs;
private ArrayList <DADOS> lista = new ArrayList<>();
	
	public DADOSDAO() {
		conn = new ConnectionFactory().getConexao();
	}
	
	public void inserir(DADOS dados) {
		String sql = "INSERT INTO DADOS (uf_endereco, cidade_endereco, bairro_endereco, rua_endereco) VALUES (?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dados.getUf_endereco());
			stmt.setString(2,dados.getCidade_endereco());
			stmt.setString(3, dados.getBairro_endereco());
			stmt.setString(4, dados.getRua_endereco());
			stmt.execute();
			stmt.close();
			
			
		}catch(Exception erro) {
			throw new RuntimeException("Erro 2: " + erro);
		}
	}
	
	public void alterar(DADOS dados) {
		String sql = "UPDATE DADOS SET uf_endereco = ?, cidade_endereco = ?, bairro_endereco = ?, rua_endereco = ? WHERE codigo_endereco = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dados.getUf_endereco());
			stmt.setString(2,dados.getCidade_endereco());
			stmt.setString(3, dados.getBairro_endereco());
			stmt.setString(4, dados.getRua_endereco());
			stmt.execute();
			stmt.close();
			
			
		}catch(Exception erro) {
			throw new RuntimeException("Erro 3: " + erro);
		}
	}
	
	public void excluir(int valor) {
		String sql = "DELETE FROM DADOS WHERE codigo_endereco = " + valor;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
			
			
		}catch(Exception erro) {
			throw new RuntimeException("Erro 4: " + erro);
		}
	}
	
	public ArrayList<DADOS> listarTodos(){
		String sql = "SELECT * FROM DADOS";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				DADOS dados = new DADOS();
				dados.setCodigo_endereco(rs.getInt("codigo_endereco"));
				dados.setUf_endereco(rs.getString("uf_endereco"));
				dados.setCidade_endereco(rs.getString("cidade_endereco"));
				dados.setBairro_endereco(rs.getString("bairro_endereco"));
				dados.setRua_endereco(rs.getString("rua_endereco"));
				
				lista.add(dados);
			}
		}catch(Exception erro) {
			throw new RuntimeException("Erro 5: " + erro);
		}
		return lista;
	}
	
	
}
