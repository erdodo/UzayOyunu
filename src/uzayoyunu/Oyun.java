package uzayoyunu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

  


}
/**
 *KEY LİSTENER - TUŞ TAKİBİ YAPAR
 * ACTİON LİSTENER - TİMER ÇALIŞTIĞINDA HAREKET SAĞLAR
 */
public class Oyun extends JPanel implements KeyListener,ActionListener {

    Timer timer = new Timer(10,this);//10MS SANİYEDE BİR ÇALIŞ
    private int gecen_sure=0; //SURE HESABI TUTAN NESNE
    private int harcanan_ates=0;//ATEŞ SAYISINI TUTAN NESNE
    
    private BufferedImage image;//RESMİMİZİ TUTAN NESNE
    
    private ArrayList<Ates> atesler=new ArrayList<Ates>();//EKRANDA BİRDEN FAZLA ATEŞ OLMASI İÇİN ARRAYLİST
    private int atesdirY=1;//ATEŞ HAREKET SAYISI
    
    private int topX=0;//TOPUN X KONUMU NESNESİ
    private int topdirX=2;//TOPUN HAREKET SAYISI
    
    private int uzayGemisiX=0;//UZAY GEMİSİ KONUMU
    private int dirUzayX=20;//UZAY GEMİSİ HAREKET SAYISI

    public boolean kontrolEt(){
        for(Ates ates:atesler){
         
           return new Rectangle(ates.getX(),ates.getY(),5,10).intersects(new Rectangle(topX,10,20,20)); 
        
        }
       return false;
    }
    public Oyun(){
        try {
             image = ImageIO.read(new FileImageInputStream(new File("ugemi.png")));//RESMİ NESNEYE ATAMA KODU
        } catch (IOException e) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE,null,e);
        }
        setBackground(Color.GRAY);
        
        timer.start();//OYUN BAŞLADI ACTİONPERFORM ÇALIŞIYOR
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure+=5;
        g.setColor(Color.cyan);//TOP RENGİ
        g.fillOval(topX, 10, 20, 20);//baslangıcX-baslangıcY-buyuklukX-buyuklukY
        g.drawImage(image, uzayGemisiX, 490, image.getWidth()/10,image.getHeight()/10,this);
        //resim nesnesi - baslangıcX - baslangıcY - genişlik - yükseklik
        for(Ates ates: atesler){
            if(ates.getY()<0){//0DAN KÜÇÜKSE YANİ EKRANDAN ÇIKINCA
                atesler.remove(ates);//ATEŞLER EKRANDAN ÇIKINCA ARRAYLİSTTEN SİL
            }
        }
        g.setColor(Color.red);//ATEŞ RENGİ
        for(Ates ates: atesler){
            g.fillRect(ates.getX(), ates.getY(), 5, 10);//HER ATEŞ İÇİN KARE ÜRET
        }
        
        if (kontrolEt()){
            timer.stop();
            String message="Kazandınız...\n"+"Harcanan ateş : "+ harcanan_ates+"\nGeçen Süre : "+(gecen_sure/1000.0);
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        if(c==KeyEvent.VK_LEFT){
            if(uzayGemisiX<=0){
                uzayGemisiX=0;
            }else{
                uzayGemisiX-=dirUzayX;
            }
            
        }
        else if(c==KeyEvent.VK_RIGHT){
            if(uzayGemisiX>=750){
                uzayGemisiX=750;
            }else{
                uzayGemisiX += dirUzayX;
            }
        }
        else if(c==KeyEvent.VK_CONTROL){
         
              atesler.add(new Ates(uzayGemisiX+18,475));//Oyun(uzayGemisiX+18, 475)
              
              harcanan_ates++;
         
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (Ates ates:atesler){
            ates.setY(ates.getY()-atesdirY);//ATEŞİN HAREKET ETMESİNİ SAĞLAYAN KOD
        }
        
        
        topX+=topdirX;//HER DONGUDE TOPDİRX DEĞİŞKENİMİZ KADAR HAREKET ET
        if(topX>=770 || topX<=0){//TOPUN SINIRLAR İÇİNDE KALMASINI SAĞLAYAN KOD
            topdirX=-topdirX;
        }
        repaint();//PAİNT METOTUNU ÇAĞIRIYORUZ BU SAYEDE HER DONGUDE YENİDEN ÇİZİLİYOR
    }

    
    
}
