/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbasetest;
import java.util.ArrayList;

public class MessagesList 
{
    ArrayList<Message> messagesList;
    
    public MessagesList()
    {
        messagesList = new ArrayList<>();
    }
    
    public void add(Message message)
    {
        messagesList.add(message);
    }
}
