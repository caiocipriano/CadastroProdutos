package connect;

import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoConnect {
//AQUI FAÇO MEU CRUD
	
	private Connection conn;
	private PreparedStatement stmt;
	private Statement st;
	private ResultSet rs;
	private ArrayList<Produto> lista = new ArrayList<Produto>();
	
	
	public ProdutoConnect(){
		conn = new ConnectionFactory().getConexao();
	}
	
	public void inserir(Produto produto){
		String sql ="INSERT INTO tb_produto(descricao, preco) VALUES(?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setDouble(2, produto.getPreco());
			stmt.execute();
			stmt.close();
		}catch(Exception erro){
			throw new RuntimeException("Erro 2" + erro);	
		}
	}
	
	public void alterar(Produto produto){
		String sql = "UPDATE tb_produto SET descricao = ?, preco = ? WHERE id_produto = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,produto.getDescricao());
			stmt.setDouble(2,produto.getPreco());
			stmt.setInt(3, produto.getId_produto());
			stmt.execute();
			stmt.close();
		}catch(Exception erro){
			throw new RuntimeException("Erro 3" + erro);	
		}
	}
	
	
	public void excluir(int valor){
		String sql ="DELETE FROM tb_produto WHERE id_produto =" + valor;
		try {
			st = conn.createStatement();
			st.execute(sql);
			st.close();
		}catch(Exception erro){
			throw new RuntimeException("Erro 4" + erro);	
		}
	}
	
	public ArrayList <Produto> listarTodos(){
		String sql = "SELECT * FROM tb_produto";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Produto produto = new Produto();
				produto.setId_produto(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				lista.add(produto);
			}
			
		}catch(Exception erro){
			throw new RuntimeException("Erro 5" + erro);	
		}
		return lista;
	}
	
	public ArrayList<Produto> listarTodosDescricao(String valor){
		String sql = "SELECT * FROM tb_produto WHERE descricao LIKE '%" + valor + "%'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Produto produto = new Produto();
				produto.setId_produto(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				lista.add(produto);
			}
			
		}catch(Exception erro){
			throw new RuntimeException("Erro 6" + erro);	
		}
		return lista;
	}
	
	
	
	
	
}
