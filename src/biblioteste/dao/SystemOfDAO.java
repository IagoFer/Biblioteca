package biblioteste.dao;

import Generico.Ferramenta;
import biblioteste.model.Autor;
import biblioteste.model.AutorLivro;
import biblioteste.model.Editora;
import biblioteste.model.Livro;
import biblioteste.model.LivroElaborado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SystemOfDAO {
    public static final String CFN = "org.apache.derby.jdbc.ClientDriver";
    public static final String URL = "jdbc:derby://localhost:1527/Trabalho";
    public static final String USUARIO = "Trabalho";
    public static final String SENHA = "t";
    
    private Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(CFN);
        return DriverManager.getConnection(URL,USUARIO,SENHA);
    }
    private void closeConnection(Connection c) throws SQLException{
        try{
            c.close();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e,c);
        }
    }
    
    public ArrayList<Livro> ListarLivros() throws SQLException{
        ArrayList<Livro> s = new ArrayList<Livro>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from BOOKS");
            while(rs.next()){
                s.add(new Livro(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return s;
    }
    public ArrayList<LivroElaborado> ListarLivrosElaborados() throws SQLException{
        ArrayList<LivroElaborado> s = new ArrayList<LivroElaborado>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select b.TITLE,a.\"NAME\",a.FNAME,p.\"NAME\",p.URL,b.PRICE from BOOKS as b,AUTHORS as a,BOOKSAUTHORS as ab,PUBLISHERS as p where a.AUTHOR_ID = ab.AUTHOR_ID and b.ISBN = ab.ISBN and b.PUBLISHER_ID = p.PUBLISHER_ID");
            while(rs.next()){
                s.add(new LivroElaborado(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return s;
    }
    public ArrayList<Autor> ListarAutores() throws SQLException{
        ArrayList<Autor> s = new ArrayList<Autor>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from AUTHORS");
            while(rs.next()){
                s.add(new Autor(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return s;
    }
    public ArrayList<AutorLivro> ListarAutoresLivros() throws SQLException{
        ArrayList<AutorLivro> s = new ArrayList<AutorLivro>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from BOOKSAUTHORS");
            while(rs.next()){
                s.add(new AutorLivro(rs.getString(1),rs.getInt(2),rs.getInt(3)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return s;
    }
    public ArrayList<Editora> ListarEditoras() throws SQLException{
        ArrayList<Editora> s = new ArrayList<Editora>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from PUBLISHERS");
            while(rs.next()){
                s.add(new Editora(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return s;
    }
    
    public void IncluirLivro(Livro l) throws SQLException {
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("insert into BOOKS values (?,?,?,?)");
            comando.setString(1, l.getTitulo());
            comando.setString(2, l.getISBN());
            comando.setInt(3, l.getIdeditora());
            comando.setDouble(4, l.getCusto());
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void IncluirAutor(Autor a) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("insert into AUTHORS values (?,?,?)");
            comando.setInt(1, a.getId());
            comando.setString(2, a.getNome());
            comando.setString(3, a.getFnome());
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            closeConnection(c);
        }
    }
    public void IncluirAutorLivro(AutorLivro al) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("insert into BOOKSAUTHORS values (?,?,?)");
            comando.setString(1, al.getISBN());
            comando.setInt(2, al.getIdautor());
            comando.setInt(3, al.getSeqno());
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            closeConnection(c);
        }
    }
    public void IncluirEditora(Editora e) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("insert into PUBLISHERS values (?,?,?)");
            comando.setInt(1, e.getId());
            comando.setString(2, e.getNome());
            comando.setString(3, e.getUrl());
            comando.executeUpdate();
        }
        catch(Exception ee){
            Ferramenta.ErroSeguro(ee, c);
        }
        finally{
            closeConnection(c);
        }
    }
    
    public void ModificarLivro(String titulovelho,Livro novo) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("update BOOKS set TITLE = ?,ISBN = ?,PUBLISHER_ID = ?,PRICE = ? where TITLE = ?");
            comando.setString(1, novo.getTitulo());
            comando.setString(2, novo.getISBN());
            comando.setInt(3, novo.getIdeditora());
            comando.setDouble(4, novo.getCusto());
            comando.setString(5, titulovelho);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ModificarAutor(String nomevelho,Autor novo) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("update AUTHORS set AUTHOR_ID = ?,NAME = ?,FNAME = ? where NAME = ?");
            comando.setInt(1, novo.getId());
            comando.setString(2, novo.getNome());
            comando.setString(3, novo.getFnome());
            comando.setString(4, nomevelho);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ModificarAutorLivro(String ISBNVelho,int IDVelho,AutorLivro novo) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("update BOOKSAUTHORS set ISBN = ?,AUTHOR_ID = ?,SEQ_NO = ? where ISBN = ? and AUTHOR_ID = ?");
            comando.setString(1, novo.getISBN());
            comando.setInt(2, novo.getIdautor());
            comando.setInt(3, novo.getSeqno());
            comando.setString(4, ISBNVelho);
            comando.setInt(5, IDVelho);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ModificarEditora(String nomevelho,Editora novo) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("update PUBLISHERS set PUBLISHER_ID = ?,NAME = ?,URL = ? where NAME = ?");
            comando.setInt(1, novo.getId());
            comando.setString(2, novo.getNome());
            comando.setString(3, novo.getUrl());
            comando.setString(4, nomevelho);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    
    public void ExcluirLivro(String titulo) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("delete from BOOKS where TITLE = ?");
            comando.setString(1, titulo);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ExcluirAutor(int id) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("delete from AUTHORS where AUTHOR_ID = ?");
            comando.setInt(1, id);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ExcluirAutorLivro(String isbn,int id) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("delete from BOOKSAUTHORS where ISBN = ? and AUTHOR_ID = ?");
            comando.setString(1, isbn);
            comando.setInt(2, id);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    public void ExcluirEditora(int id) throws SQLException{
        Connection c = null;
        try{
            c = this.getConnection();
            PreparedStatement comando = c.prepareStatement("delete from PUBLISHERS where PUBLISHER_ID = ?");
            comando.setInt(1, id);
            comando.executeUpdate();
        }
        catch(Exception e){
            Ferramenta.ErroSeguro(e, c);
        }
        finally{
            this.closeConnection(c);
        }
    }
    
    public ArrayList<Livro> PesquisarLivro(String palavra) throws SQLException{
        ArrayList<Livro> l = new ArrayList<Livro>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from BOOKS where TITLE LIKE '%"+palavra+"%'");
            while(rs.next()){
                l.add(new Livro(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return l;
    }
    public ArrayList<LivroElaborado> PesquisarLivroElaborado(String palavra) throws SQLException{
        ArrayList<LivroElaborado> l = new ArrayList<LivroElaborado>();
        Connection c = null;
        try{
            c = this.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select b.TITLE,a.\"NAME\",a.FNAME,p.\"NAME\",p.URL,b.PRICE from BOOKS as b,AUTHORS as a,BOOKSAUTHORS as ab,PUBLISHERS as p where a.AUTHOR_ID = ab.AUTHOR_ID and b.ISBN = ab.ISBN and b.PUBLISHER_ID = p.PUBLISHER_ID and TITLE LIKE '%"+palavra+"%'");
            while(rs.next()){
                l.add(new LivroElaborado(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
        }
        catch(Exception e){
            Ferramenta.Erro(e);
        }
        finally{
            this.closeConnection(c);
        }
        return l;
    }
}
