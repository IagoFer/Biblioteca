package biblioteste.model;
public class Livro {
    private String titulo,ISBN;
    private int ideditora;
    private double custo;
    
    public Livro(){}
    public Livro(String titulo,String ISBN,int ideditora,double custo){
        this.setTitulo(titulo);
        this.setISBN(ISBN);
        this.setIdeditora(ideditora);
        this.setCusto(custo);
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getCusto() {
        return custo;
    }
    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getIdeditora() {
        return ideditora;
    }
    public void setIdeditora(int ideditora) {
        this.ideditora = ideditora;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return this.getTitulo();
    }
}
