/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;
import org.havi.ui.*;

/**
 *
 * @author student
 */
public class BackgroundTimerTask extends TimerTask {
    
    private ImageComponent mc;     
    private HScene scene;
    private int teller = 0;
    private float timer = 0;
    
    public void run() {
        teller++;
        timer++;
        
        if (timer == 3000) { // 3000 == 30 seconden  
            QuizQuestion.RemoveQuestion(scene);
            QuizScore.SetScore(scene, 30);
        }
        
        if (timer < 3000) {
            if (teller%100 < 80) return; //teller%100 0..99 
        
            mc.setBounds(mc.getX(), mc.getY() - 10, mc.getWidth(), mc.getHeight());
        }
    }
    
    public void setComponent(ImageComponent mc, HScene scene) {
        this.mc = mc;
        this.scene = scene;
    }
    
    public int getTime() {
        return Math.round(timer/102);
    }    
    
}

