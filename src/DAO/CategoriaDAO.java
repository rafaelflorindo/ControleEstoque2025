package DAO;

import Modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public boolean inserir(Categoria categoria){
        String sql = "INSERT INTO categorias (descricao) VALUES (?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, categoria.getDescricao());
            stmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Categoria> listarTodos(){
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias order by id DESC";

        try(
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ){
            while(rs.next()){
                Categoria p = new Categoria();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                categorias.add(p);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categorias;
    }

    public boolean atualizar(Categoria produto){
        String sql = "UPDATE categorias SET descricao = ? WHERE id = ?";
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getId());
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id){
        String sql = "DELETE FROM categorias WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}