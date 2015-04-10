import java.awt.Frame;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;

public class UserInterFace extends Frame{
   Panel window;
   Button buttons;
   public UserInterFace(Panel w, Button b){
       Panel window = w;
      Button buttons = b;
     }
   public void panel(){
       window = new Panel();
      }
   public void button (){
      buttons = new Button("Register");
    }
   public void add (){
      window.add(buttons);
      
    }
   }
    
      