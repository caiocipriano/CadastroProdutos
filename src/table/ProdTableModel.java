package table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Produto;

public class ProdTableModel extends AbstractTableModel{
	
	public static final int COL_ID_PRODUTO = 0;
	public static final int COL_DESCRICAO = 1;
	public static final int COL_PRECO = 2;
	public ArrayList<Produto> lista;
	
	public ProdTableModel(ArrayList<Produto>l){
		lista = new ArrayList<Produto>(l);
	}
	
	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int linhas, int colunas){
		Produto produto = lista.get(linhas);
		if(colunas == COL_ID_PRODUTO) {
			return produto.getId_produto();
		}
		if(colunas == COL_DESCRICAO) {
			return produto.getDescricao();
		}
		if(colunas == COL_PRECO){
			return produto.getPreco();
		}
		
		return "";
	}
	
	@Override
	public String getColumnName(int colunas) {
		if(colunas == COL_ID_PRODUTO) {
			return "Código";
		}
		if(colunas == COL_DESCRICAO) {
			return "Descrição";
		}
		if(colunas == COL_PRECO){
			return "Preço";
		}
		return "";
	}
	
}
