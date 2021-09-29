package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connect.ProdutoConnect;
import model.Produto;
import table.ProdTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProdutoView extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfPreco;
	private JTable tbProduto;
	private JTextField tfPesquisar;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoView frame = new ProdutoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ProdutoView() {
		
		Produto pro = new Produto();
		ProdutoConnect prd = new ProdutoConnect();
		
		//setLocationRelativeTo(null);
		//tbProduto.setModel(new ProdTableModel(new ProdutoConnect().listarTodos()));
		
		setResizable(false);
		setTitle("Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 629, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(23, 28, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1.setBounds(23, 62, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pre\u00E7o:");
		lblNewLabel_2.setBounds(23, 99, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfCodigo = new JTextField();
		tfCodigo.setBackground(Color.PINK);
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(100, 25, 454, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDescricao = new JTextField();
		tfDescricao.setBounds(100, 59, 454, 20);
		contentPane.add(tfDescricao);
		tfDescricao.setColumns(10);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(100, 96, 454, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfDescricao.getText().equals("") || tfPreco.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Há campos em Branco!","Produto - Aviso",JOptionPane.WARNING_MESSAGE);
				}else{
					
				if(tfCodigo.getText().equals("")){
					pro.setDescricao(tfDescricao.getText());
					pro.setPreco(Double.parseDouble(tfPreco.getText()));
					prd.inserir(pro);
				}else{
					pro.setDescricao(tfDescricao.getText());
					pro.setPreco(Double.parseDouble(tfPreco.getText()));
					pro.setId_produto(Integer.parseInt(tfCodigo.getText()));
					prd.alterar(pro);
				}
				
			}
			tbProduto.setModel(new ProdTableModel(new ProdutoConnect().listarTodos()));
			tfCodigo.setText("");
			tfDescricao.setText("");
			tfPreco.setText("");
			tfPesquisar.setText("");
		}
		});

		btSalvar.setBounds(465, 136, 89, 23);
		contentPane.add(btSalvar);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.setEnabled(false);
		btExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int escolha = JOptionPane.showConfirmDialog(null,"Deseja Excluir?","Produto - Excluir",JOptionPane.YES_NO_OPTION);
				
				if(escolha == 0){
					int codigo = Integer.parseInt(tfCodigo.getText());
					new ProdutoConnect().excluir(codigo);
					tbProduto.setModel(new ProdTableModel(new ProdutoConnect().listarTodos()));
					tfCodigo.setText("");
					tfDescricao.setText("");
					tfPreco.setText("");
					tfPesquisar.setText("");
					btExcluir.setEnabled(false);
				}
			}
		});
		btExcluir.setBounds(352, 136, 89, 23);
		contentPane.add(btExcluir);
		
		JButton btLimpar = new JButton("Limpar");
		btLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbProduto.setModel(new ProdTableModel(new ProdutoConnect().listarTodos()));
				tfCodigo.setText("");
				tfDescricao.setText("");
				tfPreco.setText("");
				tfPesquisar.setText("");
			}
		});
		btLimpar.setBounds(241, 136, 89, 23);
		contentPane.add(btLimpar);
		
		tbProduto = new JTable();
		tbProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfCodigo.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdTableModel.COL_ID_PRODUTO).toString());
				tfDescricao.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdTableModel.COL_DESCRICAO).toString());
				tfPreco.setText(tbProduto.getValueAt(tbProduto.getSelectedRow(),ProdTableModel.COL_PRECO).toString());
				btExcluir.setEnabled(true);
			}
		});
		tbProduto.setBounds(60, 170, 472, 101);
		contentPane.add(tbProduto);
		
		JLabel lblNewLabel_3 = new JLabel("Pesquisar(Descri\u00E7\u00E3o):");
		lblNewLabel_3.setBounds(23, 282, 157, 14);
		contentPane.add(lblNewLabel_3);
		
		tfPesquisar = new JTextField();
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String pesquisa = tfPesquisar.getText();
				tbProduto.setModel(new ProdTableModel(new ProdutoConnect().listarTodosDescricao(pesquisa)));
			}
		});
		tfPesquisar.setBounds(23, 303, 531, 20);
		contentPane.add(tfPesquisar);
		tfPesquisar.setColumns(10);
	}
}
