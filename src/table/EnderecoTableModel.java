package table;

import javax.swing.table.AbstractTableModel;

import model.DADOS;
import java.util.ArrayList;


public class EnderecoTableModel extends AbstractTableModel{

	public static final int COL_CODIGO_PRODUTO = 0;
	public static final int COL_UF_ENDERECO = 1;
	public static final int COL_CIDADE_ENDERECO = 2;
	public static final int COL_BAIRRO_ENDERECO = 3;
	public static final int COL_RUA_ENDERECO = 4;
	public ArrayList<DADOS> lista;
	
	public EnderecoTableModel(ArrayList<DADOS>l) {
		lista = new ArrayList<DADOS> (l);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@Override
	public Object getValueAt(int linhas, int colunas) {
		DADOS dados = lista.get(linhas);
		if(colunas == COL_CODIGO_PRODUTO) return dados.getCodigo_endereco();
		if(colunas == COL_UF_ENDERECO) return dados.getUf_endereco();
		if(colunas == COL_CIDADE_ENDERECO) return dados.getCidade_endereco();
		if(colunas == COL_BAIRRO_ENDERECO) return dados.getBairro_endereco();
		if(colunas == COL_RUA_ENDERECO) return dados.getRua_endereco();
		
		return "";
		
	}
	
	@Override
	public String getColumnName(int colunas) {
		
		if(colunas == COL_CODIGO_PRODUTO) return "Código";
		if(colunas == COL_UF_ENDERECO) return "UF";
		if(colunas == COL_CIDADE_ENDERECO) return "Cidade";
		if(colunas == COL_BAIRRO_ENDERECO) return "Bairro";
		if(colunas == COL_RUA_ENDERECO) return "Rua";
		return "";
		
	}
}
