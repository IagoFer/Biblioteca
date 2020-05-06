package Generico;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Ferramenta {
    public static void Erro(Exception e){
        System.out.println("Erro : (" + e.getMessage() + ")");
        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), e.getClass().getName().toString().replace("java.", "").replace("lang.", ""), 0);
        e.printStackTrace();

    }
    public static void ErroSeguro(Exception e,java.sql.Connection conn) throws SQLException{
        System.out.println("Erro Seguro : (" + e.getMessage() + ")");
        JOptionPane.showMessageDialog(null, "Erro Seguro : " + e.getMessage()+"\nRevertendo Banco Para Estado Anterior", e.getClass().getName().toString().replace("java.", "").replace("lang.", ""), 0);
        conn.rollback();
        e.printStackTrace();
    }
}
