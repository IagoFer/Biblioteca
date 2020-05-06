package biblioteste.model;
public class Autor {
    private int id;
    private String nome,Fnome;
    
    public Autor(){}
    public Autor(int id,String nome,String Fnome){
        this.setId(id);
        this.setNome(nome);
        this.setFnome(Fnome);
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
    
    public String getFnome() {
        return Fnome;
    }
    public void setFnome(String Fnome) {
        this.Fnome = Fnome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}
