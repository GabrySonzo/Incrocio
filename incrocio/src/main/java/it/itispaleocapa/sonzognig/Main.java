package it.itispaleocapa.sonzognig;
import javax.swing.JLabel;
import javax.swing.*;

public class Main extends Thread
{
    public static void main (String[] args) throws InterruptedException {
        /**
         * @author Sonzogni & Oberti
         * @version 1.0
         */
        Amico a = new Amico();
        TrafficLight t = new TrafficLight(a);
        String[] direzioni = {"North", "South", "West", "East"};
        String[] veicoli = {"Auto1", "Auto2", "Auto3","Auto4","Camion"};
        int n = 0;
        a.init();
        t.start();
        sleep(1000);
        while(true){
            String direzione = direzioni[(int)(Math.random()*4)];
            String veicolo = veicoli[(int)(Math.random()*5)];
            switch(direzione){
                case "North":
                    if(a.northAuto < 3){
                        JLabel immagine = new JLabel(new ImageIcon("immagini/" + veicolo + "/" + veicolo + "Nord.png"));
                        a.finestra.add(immagine);
                        immagine.setSize(140,310);
                        immagine.setLocation(295,(130*(1-a.northAuto)-30));
                        a.northQueue[a.northAuto] = new Auto(n, direzione, a, immagine, 295, 100);
                        sleep(1000);
                        a.northQueue[a.northAuto].start();
                        a.northAuto++;
                    }
                break;
                case "South":
                    if(a.southAuto < 3){
                        JLabel immagine = new JLabel(new ImageIcon("immagini/" + veicolo + "/" + veicolo + "Sud.png"));
                        a.finestra.add(immagine);
                        immagine.setSize(140,310);
                        immagine.setLocation(355,(360+(a.southAuto*130)));
                        a.southQueue[a.southAuto] = new Auto(n, direzione, a, immagine, 355, 360);
                        sleep(1000);
                        a.southQueue[a.southAuto].start();
                        a.southAuto++;
                    }
                break;
                case "East":
                    if(a.eastAuto < 3){
                        JLabel immagine = new JLabel(new ImageIcon("immagini/" + veicolo + "/" + veicolo + "Est.png"));
                        a.finestra.add(immagine);
                        immagine.setSize(310,140);
                        immagine.setLocation((360+(a.eastAuto*130)), 280);
                        a.eastQueue[a.eastAuto] = new Auto(n, direzione, a, immagine, 360, 280);
                        sleep(1000);
                        a.eastQueue[a.eastAuto].start();
                        a.eastAuto++;
                    }
                break;
                case "West":
                    if(a.westAuto < 3){
                        JLabel immagine = new JLabel(new ImageIcon("immagini/" + veicolo + "/" + veicolo + "Ovest.png"));
                        a.finestra.add(immagine);
                        immagine.setSize(310,140);
                        immagine.setLocation((130*(1-a.westAuto)-30), 340);
                        a.westQueue[a.westAuto] = new Auto(n, direzione, a, immagine, 100, 340);
                        sleep(1000);
                        a.westQueue[a.westAuto].start();
                        a.westAuto++;
                    }
                break;
            }
            n++;
            if(n>12){
                n=0;
            }
            sleep((int)(Math.random()*500));
        }
    }
}


