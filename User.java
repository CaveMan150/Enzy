/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbasetest;


public class User 
{
    protected String name;
    protected String dateOfBirth;
    protected String userName;
    
    MessagesList userMessageList = new MessagesList();
    UsersList followers = new UsersList();
    UsersList iFollow = new UsersList();
    
    public User(String realName, String dob, String uName)
    {
        name = realName;
        dateOfBirth = dob;
        userName = uName;
    }
    
    public void postMessage(Message message)
    {
        //post it and add it to the list of messages
        //maybe just add it and then display updated messages
        userMessageList.add(message);
    }

}
