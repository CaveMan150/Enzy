/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbasetest;
import java.util.ArrayList;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

 Cluster cluster;
 Session session;
public class UsersList 
{
    ArrayList<User> usersList;
    
    public UsersList()
    {
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("UserDatabase");
        usersList = new ArrayList<>();
    }
    
    public void addUser(User newUser)
    {
        usersList.add(newUser);
    }
    
    public boolean checkIfTaken(String desiredUsername)
    {
        boolean isTaken = false;
        int numOfUsers =  usersList.size();

        for(int i = 0; i < numOfUsers; i++)
        {
            if(desiredUsername.equals(usersList.get(i).userName))
            {
                isTaken = true;
                return isTaken;
                session.excute("INSERT INTO users (UserName)values (desiredUsername)");
            }
        }
        return isTaken;
    }
    
    public boolean checkIfUser(String userName, String password)
    {
        boolean isAValidUser = false;
        int numOfUsers = usersList.size();
        
        for(int i = 0; i < numOfUsers; i++)
        {
            if(userName.equals(usersList.get(i).userName))
            {
                if(password.equals(usersList.get(i).userName))
                {
                    isAValidUser = true;
                    return isAValidUser;
                }
            }
        }
        return isAValidUser;
    }
}
