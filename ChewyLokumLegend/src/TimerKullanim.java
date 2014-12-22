

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


 //Bu class kald›r›lacak, TimerKullanim i?indeki methodlar implement edilecek.

public class TimerKullanim {
 
    private static int time;

    public static void main(String args[]) {


  ActionListener sayici = new ActionListener() {

 

public void actionPerformed(ActionEvent event) {

    time += 1;

    // ﬂuan print ettiriyorum, s›rf says›n diye normalde bu sat›r› kald›raca€›z.
    System.out.println("Counter = "+time);

    /*
    * 
    * Onun yerine implement ederken bu tarz bir ﬂey yapaca€›z san›r›m. Tam emin de€ilim, sallad›m.
    *  
    *  
    * if (time < TimedLevel.getTimeLimit())
    * {
    * 
    * }
    * 
    * else
    * {
    * LokumGame.getInstance().gameOver(false);
    * }
    *
    */
    
}


  };

 
 Timer timer = new Timer(1000, sayici);


  timer.start();

    }
}
