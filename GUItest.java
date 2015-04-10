import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
public class GUItest
{

    
    public static void main(String[] args) 
    {
      Panel RegisterWindow = new Panel();
      Button RegisterButton = new Button();
      UserInterFace interfaces = new UserInterFace(RegisterWindow, RegisterButton);
      interfaces.panel();
      interfaces.button();
      interfaces.add();
    }
    
    }
