package it.itispaleocapa.sonzognig;
import javax.swing.*;

public class TrafficLight extends Thread
{
    Amico crossroads;
    public TrafficLight(Amico crossroads){
        this.crossroads = crossroads;
    }
    
    public void run(){
        while(true){
            try
            {
                
                crossroads.northSouth = false;
                crossroads.eastWest = true;
                crossroads.semaforoNord.setIcon(new ImageIcon("immagini/Semafori/SemaforiNord/SemaforoGialloNord.png"));
                crossroads.semaforoSud.setIcon(new ImageIcon("immagini/Semafori/SemaforiSud/SemaforoGialloSud.png"));
                System.out.println("giallo N-S");
                sleep(2500);
                crossroads.semaforoNord.setIcon(new ImageIcon("immagini/Semafori/SemaforiNord/SemaforoRossoNord.png"));
                crossroads.semaforoSud.setIcon(new ImageIcon("immagini/Semafori/SemaforiSud/SemaforoRossoSud.png"));
                System.out.println("rosso N-S");
                crossroads.semaforoEst.setIcon(new ImageIcon("immagini/Semafori/SemaforiEst/SemaforoVerdeEst.png"));
                crossroads.semaforoOvest.setIcon(new ImageIcon("immagini/Semafori/SemaforiOvest/SemaforoVerdeOvest.png"));
                System.out.println("verde W-E");
                crossroads.westSemaphore.release();
                crossroads.eastSemaphore.release();
                sleep(5000);
                
                crossroads.northSouth = true;
                crossroads.eastWest = false;
                crossroads.semaforoEst.setIcon(new ImageIcon("immagini/Semafori/SemaforiEst/SemaforoGialloEst.png"));
                crossroads.semaforoOvest.setIcon(new ImageIcon("immagini/Semafori/SemaforiOvest/SemaforoGialloOvest.png"));
                System.out.println("giallo E-O");
                sleep(2500);
                crossroads.semaforoEst.setIcon(new ImageIcon("immagini/Semafori/SemaforiEst/SemaforoRossoEst.png"));
                crossroads.semaforoOvest.setIcon(new ImageIcon("immagini/Semafori/SemaforiOvest/SemaforoRossoOvest.png"));
                System.out.println("rosso E-O");
                crossroads.semaforoNord.setIcon(new ImageIcon("immagini/Semafori/SemaforiNord/SemaforoVerdeNord.png"));
                crossroads.semaforoSud.setIcon(new ImageIcon("immagini/Semafori/SemaforiSud/SemaforoVerdeSud.png"));
                System.out.println("verde N-S");
                crossroads.northSemaphore.release();
                crossroads.southSemaphore.release();
                sleep(5000);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }    
        }
    }
}
