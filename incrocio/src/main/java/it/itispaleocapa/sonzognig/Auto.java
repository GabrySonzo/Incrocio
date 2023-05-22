package incrocio.src.main.java.it.itispaleocapa.sonzognig;
import javax.swing.JLabel;

public class Auto extends Thread{
    int codex;
    String direction;
    Amico crossroads;
    JLabel immagine;
    public int x, y;
    public Auto(int codex, String direction, Amico crossroads, JLabel immagine,int x,int y){
        this.immagine = immagine;
        this.codex = codex;
        this.direction = direction;
        this.crossroads = crossroads;
        this.x = x;
        this.y = y;
    }
    
    public void run(){
        switch (direction) {
            case "North":
                crossroads.carFromNorth(codex,immagine,x,y);
                break;
            case "South":
                crossroads.carFromSouth(codex,immagine,x,y);
                break;
            case "East":
                crossroads.carFromEast(codex,immagine,x,y);
                break;
            case "West":
                crossroads.carFromWest(codex,immagine,x,y);
                break;
        }
    }
}