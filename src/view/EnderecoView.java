package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DADOSDAO;
import model.DADOS;
import table.EnderecoTableModel;
import table.PesquisaCep;
import org.json.*;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class EnderecoView extends JFrame {
	private JTable table;
	private JTextField tfCodigo;
	private JTextField tfBairro;
	private JTextField tfRua;
	private JTextField tfPesquisarPorCep;
	private JTextField tfUf;
	private JTextField tfCidade;
	private JTable tbEndereco;


	public EnderecoView() {

		DADOS dados = new DADOS();
		DADOSDAO dadosDao = new DADOSDAO();

		setAutoRequestFocus(false);

		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 199, 593, 301);
		getContentPane().add(scrollPane);

		tbEndereco = new JTable();
		scrollPane.setViewportView(tbEndereco);

		JLabel lblNewLabel = new JLabel("Código");
		lblNewLabel.setBounds(12, 7, 69, 15);
		getContentPane().add(lblNewLabel);

		tfCodigo = new JTextField();
		tfCodigo.setBackground(Color.PINK);
		tfCodigo.setEnabled(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(86, 0, 542, 19);
		getContentPane().add(tfCodigo);

		tbEndereco.setModel(new EnderecoTableModel(new DADOSDAO().listarTodos()));

	

		JButton btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tbEndereco.setModel(new EnderecoTableModel(new DADOSDAO().listarTodos()));
				tfCodigo.setText("");
				tfBairro.setText("");
				tfRua.setText("");
				tfPesquisarPorCep.setText("");
			}
		});
		btLimpar.setBounds(236, 151, 117, 25);
		getContentPane().add(btLimpar);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Produto - Excluir",
						JOptionPane.YES_NO_OPTION);
				if (escolha == 0) {
					int codigo = Integer.parseInt(tfCodigo.getText());
					dadosDao.excluir(codigo);

					tbEndereco.setModel(new EnderecoTableModel(new DADOSDAO().listarTodos()));
					tfCodigo.setText("");
					tfBairro.setText("");
					tfRua.setText("");
					tfPesquisarPorCep.setText("");
					btExcluir.setEnabled(false);
				}

			}
		});
		
		
		btExcluir.setBounds(389, 151, 117, 25);
		getContentPane().add(btExcluir);

		tbEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfCodigo.setText(tbEndereco.getValueAt(tbEndereco.getSelectedRow(), EnderecoTableModel.COL_CODIGO_PRODUTO)
						.toString());
				tfUf.setText(
						tbEndereco.getValueAt(tbEndereco.getSelectedRow(), EnderecoTableModel.COL_UF_ENDERECO).toString());
				tfCidade.setText(tbEndereco.getValueAt(tbEndereco.getSelectedRow(), EnderecoTableModel.COL_CIDADE_ENDERECO)
						.toString());

				tfBairro.setText(tbEndereco.getValueAt(tbEndereco.getSelectedRow(), EnderecoTableModel.COL_BAIRRO_ENDERECO)
						.toString());

				tfRua.setText(tbEndereco.getValueAt(tbEndereco.getSelectedRow(), EnderecoTableModel.COL_RUA_ENDERECO)
						.toString());

				btExcluir.setEnabled(true);
			}
		});
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tfBairro.getText().equals("") || tfRua.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Há campos em branco!", "Aviso", JOptionPane.WARNING_MESSAGE);
				} else {
					if (tfCodigo.getText().equals("")) {
						dados.setUf_endereco(tfUf.getText());
						dados.setCidade_endereco(tfCidade.getText());
						dados.setBairro_endereco(tfBairro.getText());
						dados.setRua_endereco(tfRua.getText());
						dadosDao.inserir(dados);
					} else {
						dados.setUf_endereco(tfUf.getText());
						dados.setCidade_endereco(tfCidade.getText());
						dados.setBairro_endereco(tfBairro.getText());
						dados.setRua_endereco(tfRua.getText());
						dadosDao.alterar(dados);
					}
				}

				tbEndereco.setModel(new EnderecoTableModel(new DADOSDAO().listarTodos()));
				tfCodigo.setText("");
				tfBairro.setText("");
				tfRua.setText("");
				tfPesquisarPorCep.setText("");
			}

		});

		JLabel lblNewLabel_1 = new JLabel("Bairro");
		lblNewLabel_1.setBounds(12, 100, 69, 15);
		getContentPane().add(lblNewLabel_1);

		tfBairro = new JTextField();
		tfBairro.setColumns(10);
		tfBairro.setBounds(86, 98, 542, 19);
		getContentPane().add(tfBairro);

		JLabel lblNewLabel_2 = new JLabel("Rua");
		lblNewLabel_2.setBounds(12, 124, 69, 15);
		getContentPane().add(lblNewLabel_2);

		tfRua = new JTextField();
		tfRua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		tfRua.setColumns(10);
		tfRua.setBounds(86, 122, 542, 19);
		getContentPane().add(tfRua);

		btSalvar.setBounds(511, 151, 117, 25);
		getContentPane().add(btSalvar);

		JLabel lblPesquisar = new JLabel("Pesquisar por CEP");
		lblPesquisar.setBounds(12, 512, 168, 15);
		getContentPane().add(lblPesquisar);

		tfPesquisarPorCep = new JTextField();
		tfPesquisarPorCep.setBounds(35, 532, 209, 19);
		getContentPane().add(tfPesquisarPorCep);
		tfPesquisarPorCep.setColumns(10);
		btExcluir.setEnabled(false);

		JLabel lblNewLabel_2_1 = new JLabel("UF");
		lblNewLabel_2_1.setBounds(12, 46, 69, 15);
		getContentPane().add(lblNewLabel_2_1);

		tfUf = new JTextField();
		tfUf.setColumns(10);
		tfUf.setBounds(86, 44, 542, 19);
		getContentPane().add(tfUf);

		JLabel lblNewLabel_2_1_1 = new JLabel("Cidade");
		lblNewLabel_2_1_1.setBounds(12, 75, 69, 15);
		getContentPane().add(lblNewLabel_2_1_1);

		tfCidade = new JTextField();
		tfCidade.setColumns(10);
		tfCidade.setBounds(86, 73, 542, 19);
		getContentPane().add(tfCidade);
		
		JButton btPesquisar = new JButton("PESQUISAR");
		btPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String cepDigitado = tfPesquisarPorCep.getText();
				
				PesquisaCep pesquisarCep = new PesquisaCep(cepDigitado);
				try {
					JSONObject obj = (JSONObject) pesquisarCep.buscar(cepDigitado);

				    dados.setUf_endereco(obj.get("uf").toString());
				    dados.setCidade_endereco(obj.get("localidade").toString());
				    dados.setBairro_endereco(obj.get("bairro").toString());
					dados.setRua_endereco(obj.get("logradouro").toString());
					dadosDao.inserir(dados);
					
					tbEndereco.setModel(new EnderecoTableModel(new DADOSDAO().listarTodos()));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btPesquisar.setBackground(UIManager.getColor("OptionPane.errorDialog.titlePane.shadow"));
		btPesquisar.setBounds(272, 529, 356, 25);
		getContentPane().add(btPesquisar);

	}
}
