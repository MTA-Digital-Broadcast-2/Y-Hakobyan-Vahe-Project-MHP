package hellotvxlet;

import javax.tv.xlet.*;
import org.dvb.event.*;
import java.util.Timer;
import org.havi.ui.*;
import org.havi.ui.event.*;
import org.davic.resources.*;
import java.awt.event.*;
import org.dvb.ui.DVBColor;
import java.awt.Color;

public class HelloTVXlet implements Xlet, ResourceClient, UserEventListener, HActionListener {    
    private HScene scene;
    public HScreen screen;
    private XletContext actueleXletContext;
    public ImageComponent mc, fc, tt; 
    private HTextButton startButton;    
    private Timer timer;
    public int index = 0;
    public int question = 0;
    public boolean start = false;
    BackgroundTimerTask objMijnTimerTask; 
        
    public void notifyRelease(ResourceProxy proxy) {}
    public void release(ResourceProxy proxy) {}
    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        return false;
    }
    
    public void initXlet(XletContext context) {
        this.actueleXletContext = context;        
        screen = HScreen.getDefaultHScreen();
        HSceneTemplate sceneTemplate = new HSceneTemplate();
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
                        
        mc = new ImageComponent("background.png", 0,0);
        fc = new ImageComponent("number-field.png", 0,0);
        tt = new ImageComponent("title.png", 245,160);        
        
        startButton = new HTextButton("START DE QUIZ");
        startButton.setLocation(320, 450);
        startButton.setSize(300, 76);
        startButton.setBackground(new DVBColor(0, 0, 0, 179));
        startButton.setBackgroundMode(HVisible.BACKGROUND_FILL);
                
        scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);        
        scene.setBackground(Color.white);
        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        scene.add(startButton);

        startButton.setActionCommand("start");
        startButton.addHActionListener(this);
        startButton.requestFocus();
        
        objMijnTimerTask = new BackgroundTimerTask();
        objMijnTimerTask.setComponent(this.mc, scene);
        
        timer = new Timer();
        
        scene.add(fc);
        scene.add(mc);
        scene.add(tt);
        scene.popToFront(tt);        
                
    }

    public void startXlet() {
        System.out.println("Start Xlet");

        scene.validate();
        scene.setVisible(true);
        
        EventManager manager = EventManager.getInstance();
        UserEventRepository repository = new UserEventRepository("Voorbeeld");
        repository.addKey(HRcEvent.VK_UP);
        repository.addKey(HRcEvent.VK_DOWN);
        repository.addKey(HRcEvent.VK_ENTER);
        
        manager.addUserEventListener(this, repository);        
    }

    public void pauseXlet() {
        System.out.println("Pause Xlet");
    }

    public void destroyXlet(boolean unconditional) {
        System.out.println("Destroy Xlet");
    }
    
    public void userEventReceived(UserEvent e) {
        if(e.getType() == KeyEvent.KEY_PRESSED) {
            switch(e.getCode()) {
                case HRcEvent.VK_UP:   
                    if (index != 0)  {
                        index -= 1;
                        QuizQuestion.SetFocus(index);                         
                    }     
                     
                    break;
                case HRcEvent.VK_DOWN:
                    if (index != 2)  {
                        index += 1;
                        QuizQuestion.SetFocus(index);                        
                    } 
                        
                    break;
                case HRcEvent.VK_ENTER:  
                    if (start){
                        QuestionCollection.getQuizQuestion(question).SetScore(question, index);
                        QuizQuestion.RemoveQuestion(scene);
                        
                        if (question == 29){                            
                            QuizScore.SetScore(scene, objMijnTimerTask.getTime());
                            timer.cancel();
                        } else {   
                            question += 1;
                            index = 0;  
                            
                            QuizQuestion.Show(scene, question);                                                       
                        }     
                    }                       
                    break;                    
            }
        }
        
    }
    
    public void setScoreBackground (int score){
        
        
    }
    
    public void actionPerformed (ActionEvent e){
        
        if (e.getActionCommand() == "start") {
            
           startButton.removeHActionListener(this);
           scene.remove(startButton);
           scene.remove(tt);
           timer.scheduleAtFixedRate(objMijnTimerTask, 0, 10); 
           
           QuizQuestion.Show(scene, 0);

           start = true;
        }               
    }       
}
