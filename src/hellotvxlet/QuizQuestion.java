/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import org.havi.ui.*;
import org.dvb.ui.DVBColor;

/**
 *
 * @author student
 */
public class QuizQuestion {
    public String Question;
    public int answer;  
    public static HTextButton answer_0, answer_1, answer_2; 
    public static HStaticText question;
    public String[] Answers;    

    public QuizQuestion (String Question, int answer, String[] Answers) {
        this.Question = Question;
        this.answer = answer; 
        this.Answers = Answers; 
    }  
    
    public void SetScore (int q_index, int choice) {        
        QuizScore.RaiseTotal();
        
        if (choice == this.answer) {
            QuizScore.RaiseScore();
        } 
    } 

    public static void RemoveQuestion (HScene scene) {        
        scene.remove(question);   
        scene.remove(answer_0);
        scene.remove(answer_1);
        scene.remove(answer_2); 
        
        scene.repaint();
    } 
        
    
    public static void Show(HScene scene, int nr) {
        scene.repaint();
        
        String Q = QuestionCollection.getQuestion(nr);
        String[] A = QuestionCollection.getAnswer(nr);
        
        question = new HStaticText(Q,320,000,300,200);
        
        scene.add(question);
        scene.popToFront(question);        
        
        answer_0 = new HTextButton(A[0],320,200,300,40);
        answer_0.setBackground(new DVBColor(255,255,0,200));                
        answer_0.setBackgroundMode(HVisible.BACKGROUND_FILL);
        answer_0.setForeground(new DVBColor(255,0,0,255)); 
        answer_0.setBordersEnabled(false);
        
        scene.add(answer_0);
        scene.popToFront(answer_0);
            
        answer_0.requestFocus();

        answer_1 = new HTextButton(A[1],320,300,300,40);
        answer_1.setBackground(new DVBColor(255,0,0,200));
        answer_1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        answer_1.setForeground(new DVBColor(255,255,255,255));          
        answer_1.setBordersEnabled(false);
        
        scene.add(answer_1);
        scene.popToFront(answer_1);       
        
        answer_2 = new HTextButton(A[2],320,400,300,40);
        answer_2.setBackground(new DVBColor(255,0,0,200));
        answer_2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        answer_2.setForeground(new DVBColor(255,255,255,255)); 
        answer_2.setBordersEnabled(false);
        
        scene.add(answer_2);
        scene.popToFront(answer_2);                       
    }
    
    public static void SetFocus(int nr) {  
        answer_0.setBackground(new DVBColor(255,0,0,200));
        answer_0.setForeground(new DVBColor(255,255,255,255));        

        answer_1.setBackground(new DVBColor(255,0,0,200));
        answer_1.setForeground(new DVBColor(255,255,255,255));          
        
        answer_2.setBackground(new DVBColor(255,0,0,200));
        answer_2.setForeground(new DVBColor(255,255,255,255));         
        
        switch(nr) {
            case 0:
                answer_0.requestFocus();
                answer_0.setBackground(new DVBColor(255,255,0,200));
                answer_0.setForeground(new DVBColor(255,0,0,255)); 
                break;
            case 1:
                answer_1.requestFocus();
                answer_1.setBackground(new DVBColor(255,255,0,200));
                answer_1.setForeground(new DVBColor(255,0,0,255)); 
                break;  
            case 2:
                answer_2.requestFocus();
                answer_2.setBackground(new DVBColor(255,255,0,200));
                answer_2.setForeground(new DVBColor(255,0,0,255)); 
                break;                
        }   
    }  

}
