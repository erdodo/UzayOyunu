package uzayoyunu;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;

public class OyunEkrani extends JFrame{

    public OyunEkrani(String title) throws HeadlessException {
        super(title);//OYUN EKRANI KALITILDIĞI YER
    }
    
    public static void main(String[] args) throws IOException {
     OyunEkrani ekran = new   OyunEkrani("Uzay Oyunu");//OYUN BAŞLIĞI
     ekran.setResizable(false);//YENİDEN BOYUTLANAMAZ
     ekran.setFocusable(false);//JFRAME ODAKLANMA JPANELE ODAKLAN
     ekran.setSize(800,600);//EKRAN BOYUTLARI
     ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
     
     Oyun oyun =new Oyun();
     oyun.requestFocus();//KLAVYEDEN GİRİLENLERİ ANLAMAK İÇİN BANA ODAĞI VER DEMEK
     oyun.addKeyListener(oyun);//OYUN SINIFINDAN GELEN TUŞLARI OYUN DA KULLAN
     oyun.setFocusable(true);//ODAK JPANELDE
     oyun.setFocusTraversalKeysEnabled(false);//KLAVYE İŞLEMLERİ DİREK GERÇEKLEŞMESİ İÇİN GEREKLİ KOD
     
     ekran.add(oyun);//JPANEL JFRAME E EKLENDİ
     ekran.setVisible(true);//JPANEL DİREK OLUŞSUN
     
     
     
     
     
     
     
     
    }
}
