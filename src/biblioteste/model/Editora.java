package biblioteste.model;
public class Editora {
    private int id;
    private String nome,url;
    
    public Editora(){}
    public Editora(int id,String nome,String url){
        this.setId(id);
        this.setNome(nome);
        this.setUrl(url);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
