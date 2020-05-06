package biblioteste.model;
public class AutorLivro {
    private String ISBN;
    private int idautor,seqno;
    
    public AutorLivro(){}
    public AutorLivro(String ISBN,int idautor,int seqno){
        this.setISBN(ISBN);
        this.setIdautor(idautor);
        this.setSeqno(seqno);
    }
    
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getIdautor() {
        return idautor;
    }
    public void setIdautor(int idautor) {
        this.idautor = idautor;
    }

    public int getSeqno() {
        return seqno;
    }
    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    @Override
    public String toString() {
        return this.getISBN();
    }
    
    
}
