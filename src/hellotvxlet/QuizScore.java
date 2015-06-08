/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import org.havi.ui.HScene;
import org.havi.ui.HStaticText;

/**
 *
 * @author student
 */
public class QuizScore {
    private static HStaticText Message;
    private static double total = 0;
    private static double score = 0;
    public ImageComponent zw, go, kn;     
    
    public static void RaiseScore () {
        score ++;
    }
    
    public static void RaiseTotal () {       
        total ++;
    }    
    
    public static void SetScore (HScene scene, int time) {                
        ImageComponent zw = new ImageComponent("zwak.png", 315,70);
        ImageComponent go = new ImageComponent("goed.png", 310,70);
        ImageComponent kn = new ImageComponent("knap.png", 320,70);   
        
        String TimeText = "";
        String Text = "";
        int percentage = (int) Math.round((score/30) * 100);
        
        if (time < 30) {
            TimeText = "\n in " + Integer.toString(time) + " seconden";            
        }   
               
        if (score > 25) {
            scene.add(kn);
            scene.popToFront(kn);               
        } else if (score > 15)  {
            scene.add(go);
            scene.popToFront(go);                     
        } else {
            scene.add(zw);
            scene.popToFront(zw);             
        }                 
                 
        Message = new HStaticText(Text + "Je score bedraagt " + Integer.toString(percentage) + " percent" +
                " \n Je beantwoorde " + Math.round(total) + " vragen " + TimeText + 
                " \n waarvan " + Math.round(score) + " juist",320,200,300,200);
        
        scene.add(Message);
        scene.popToFront(Message);          
        scene.repaint();        

    }     
}
