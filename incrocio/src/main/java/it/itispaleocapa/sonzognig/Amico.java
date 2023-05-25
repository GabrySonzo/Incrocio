package it.itispaleocapa.sonzognig;
    
import java.util.concurrent.Semaphore;
import javax.swing.*;
import javax.swing.JLabel;

public class Amico {
    public JFrame finestra = new JFrame();
    public Semaphore eastSemaphore = new Semaphore(1);
    public Semaphore westSemaphore = new Semaphore(1);
    public Semaphore northSemaphore = new Semaphore(1);
    public Semaphore southSemaphore = new Semaphore(1);
    public JLabel semaforoNord, semaforoSud, semaforoOvest, semaforoEst;
    public Auto[] northQueue = new Auto[3];
    public Auto[] southQueue = new Auto[3];
    public Auto[] eastQueue = new Auto[3];
    public Auto[] westQueue = new Auto[3];
    public int northAuto = 0;
    public int southAuto = 0;
    public int eastAuto = 0;
    public int westAuto = 0;
    public boolean northSouth = true;
    public boolean eastWest = false;
    public Amico() {
        try
        {
            northSemaphore.acquire();
            southSemaphore.acquire();
            westSemaphore.acquire();
            eastSemaphore.acquire();
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
    
    public void init(){
        finestra.setSize(800,800);
        finestra.setResizable(false);
        finestra.setVisible(true);
        JLabel sfondo = new JLabel(new ImageIcon("immagini/incrocio.jpg"));
        finestra.setContentPane(sfondo);
        semaforoNord = new JLabel(new ImageIcon("immagini/Semafori/SemaforiNord/SemaforoRossoNord.png"));
        finestra.add(semaforoNord);
        semaforoNord.setSize(140,310);
        semaforoNord.setLocation(410,360);
        semaforoSud = new JLabel(new ImageIcon("immagini/Semafori/SemaforiSud/SemaforoRossoSud.png"));
        finestra.add(semaforoSud);
        semaforoSud.setSize(140,310);
        semaforoSud.setLocation(230,100);
        semaforoEst = new JLabel(new ImageIcon("immagini/Semafori/SemaforiEst/SemaforoVerdeEst.png"));
        finestra.add(semaforoEst);
        semaforoEst.setSize(290,140);
        semaforoEst.setLocation(120,405);
        semaforoOvest = new JLabel(new ImageIcon("immagini/Semafori/SemaforiOvest/SemaforoVerdeOvest.png"));
        finestra.add(semaforoOvest);
        semaforoOvest.setSize(310,140);
        semaforoOvest.setLocation(365,210);
    }
    // Metodo per far passare un'auto proveniente da est
    public void carFromEast(int carId, JLabel immagine, int x, int y) {
        boolean passato = true;
        try {
            while(passato){
                eastSemaphore.acquire(); // Acquisisce il semaforo per la direzione est
                if(eastWest){
                    passato = false;
                    System.out.println("Auto " + carId + " proveniente da est in transito...");
                    immagine.setLocation((x-130),y);
                    if(eastQueue[1]!=null)
                        eastQueue[1].immagine.setLocation(360, y);
                    if(eastQueue[2]!=null)
                        eastQueue[2].immagine.setLocation(490, y);
                    Thread.sleep(1000); // Simulazione del tempo di attraversamento dell'incrocio
                    immagine.setLocation((x-260),y);
                    System.out.println("Auto " + carId + " proveniente da est ha lasciato l'incrocio");
                    eastQueue[0] = eastQueue[1];
                    eastQueue[1] = eastQueue[2];
                    eastQueue[2] = null;
                    eastAuto--;
                    eastSemaphore.release(); // Rilascia il semaforo per la direzione est 
                    Thread.sleep(1000);
                    immagine.setLocation((x-390),y);
                    Thread.sleep(1000);
                    immagine.setIcon(null);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per far passare un'auto proveniente da ovest
    public void carFromWest(int carId, JLabel immagine, int x, int y) {
        boolean passato = true;
        try {
            while(passato){
                westSemaphore.acquire(); // Acquisisce il semaforo per la direzione est
                if(eastWest){
                    passato = false;
                    System.out.println("Auto " + carId + " proveniente da ovest in transito...");
                    immagine.setLocation((x+130), y);
                    if(westQueue[1]!=null)
                        westQueue[1].immagine.setLocation(100, y);
                    if(westQueue[2]!=null)
                        westQueue[2].immagine.setLocation(-30, y);
                    Thread.sleep(1000); // Simulazione del tempo di attraversamento dell'incrocio
                    immagine.setLocation((x+260),y);
                    System.out.println("Auto " + carId + " proveniente da ovest ha lasciato l'incrocio");
                    westQueue[0] = westQueue[1];
                    westQueue[1] = westQueue[2];
                    westQueue[2] = null;
                    westAuto--;
                    westSemaphore.release(); // Rilascia il semaforo per la direzione est   
                    Thread.sleep(1000);
                    immagine.setLocation((x+390), y);
                    Thread.sleep(1000);
                    immagine.setIcon(null);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per far passare un'auto proveniente da nord
    public void carFromNorth(int carId, JLabel immagine, int x, int y) {
        boolean passato = true;
        try {
            while(passato){
                northSemaphore.acquire(); // Acquisisce il semaforo per la direzione est
                if(northSouth){
                    passato = false;
                    System.out.println("Auto " + carId + " proveniente da nord in transito...");
                    immagine.setLocation((x),(y+130));
                    if(northQueue[1]!=null)
                        northQueue[1].immagine.setLocation(x, 100);
                    if(northQueue[2]!=null)
                        northQueue[2].immagine.setLocation(x, -30);
                    Thread.sleep(1000); // Simulazione del tempo di attraversamento dell'incrocio
                    immagine.setLocation((x),(y+260));
                    System.out.println("Auto " + carId + " proveniente da nord ha lasciato l'incrocio");
                    northQueue[0] = northQueue[1];
                    northQueue[1] = northQueue[2];
                    northQueue[2] = null;
                    northAuto--;
                    northSemaphore.release(); // Rilascia il semaforo per la direzione est   
                    Thread.sleep(1000);
                    immagine.setLocation((x),(y+390));
                    Thread.sleep(1000);
                    immagine.setIcon(null);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per far passare un'auto proveniente da sud
    public void carFromSouth(int carId, JLabel immagine, int x, int y) {
        boolean passato = true;
        try {
            while(passato){
                southSemaphore.acquire(); // Acquisisce il semaforo per la direzione est
                if(northSouth){
                    passato = false;
                    System.out.println("Auto " + carId + " proveniente da sud in transito...");
                    immagine.setLocation((x),(y-130));
                    if(southQueue[1]!=null)
                        southQueue[1].immagine.setLocation(x, 360);
                    if(southQueue[2]!=null)
                        southQueue[2].immagine.setLocation(x, 490);
                    Thread.sleep(1000); // Simulazione del tempo di attraversamento dell'incrocio
                    immagine.setLocation((x),(y-260));
                    System.out.println("Auto " + carId + " proveniente da sud ha lasciato l'incrocio");
                    southQueue[0] = southQueue[1];
                    southQueue[1] = southQueue[2];
                    southQueue[2] = null;
                    southAuto--;
                    southSemaphore.release(); // Rilascia il semaforo per la direzione est 
                    Thread.sleep(1000);
                    immagine.setLocation((x),(y-390));
                    Thread.sleep(1000);
                    immagine.setIcon(null);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}






    
