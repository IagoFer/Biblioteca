package biblioteste.model;
public class LivroElaborado {
    private String Titulo,NomeAutor,FNomeAutor,NomeEditora,URLEditora;
    private double Custo;
    
    public LivroElaborado(){}
    public LivroElaborado(String Titulo,String NomeAutor,String FNomeAutor,String NomeEditora,String URLEditora,double Custo){
        this.setTitulo(Titulo);
        this.setNomeAutor(NomeAutor);
        this.setFNomeAutor(FNomeAutor);
        this.setNomeEditora(NomeEditora);
        this.setURLEditora(URLEditora);
        this.setCusto(Custo);
    }
    
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getNomeAutor() {
        return NomeAutor;
    }

    public void setNomeAutor(String NomeAutor) {
        this.NomeAutor = NomeAutor;
    }

    public String getFNomeAutor() {
        return FNomeAutor;
    }

    public void setFNomeAutor(String FNomeAutor) {
        this.FNomeAutor = FNomeAutor;
    }

    public String getNomeEditora() {
        return NomeEditora;
    }

    public void setNomeEditora(String NomeEditora) {
        this.NomeEditora = NomeEditora;
    }

    public String getURLEditora() {
        return URLEditora;
    }

    public void setURLEditora(String URLEditora) {
        this.URLEditora = URLEditora;
    }

    public double getCusto() {
        return Custo;
    }

    public void setCusto(double Custo) {
        this.Custo = Custo;
    }
    
}
