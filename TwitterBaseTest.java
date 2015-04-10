/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterbasetest;
import java.util.Scanner;

public class TwitterBaseTest 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /////////////////////////////////////
        ///Text menu based
        /////////////////////////////////////
        String answer;
        String desiredUser;
        String password;
        String dob;
        String userName;
        boolean login = false;
        boolean isTaken;
        User curUser = null;
        String post;

        UsersList memberList = new UsersList();
        
        Scanner sc = new Scanner(System.in);
        
        ////////////////////////////////////////////////
        ///Need to store on another file or in database
        ////////////////////////////////////////////////
        
        ////////////////////////////////////////////////
        ///Read from files
        ////////////////////////////////////////////////
        
        do
        {
            System.out.println("Select an option: \n (R)egister \n" + 
                    "(L)ogin \n" + "L(O)gout \n" + "(P)ost a message \n" 
                    + "(V)iew messages of people you are subscribed to \n" + 
                    "(E)xit");
            
            answer = sc.next();
            
            if(answer.equals("R"))
            {
                //Register
                if(!login)
                {
                    do
                    {
                        System.out.println("Enter your desired username: ");
                        desiredUser = sc.next();
                        //Call check to see if desired username is already a username
                        isTaken = memberList.checkIfTaken(desiredUser);

                        if(isTaken)
                        {
                            System.out.println("Name is taken");
                        }
                    }while(isTaken);
                    
                    System.out.println("Enter your desired password: ");
                    password = sc.next();
                
                    System.out.println("Enter your date of birth ex.(January 01, 2000)");
                    dob = sc.next();
                
                    User newUser = new User(desiredUser, dob, password);
                    memberList.addUser(newUser);
                }
                else
                {
                    System.out.println("Logout to register another user");
                }
            }
            else if(answer.equals("L"))
            {
                //Login
                //Get the user object of the cuurrent user
                System.out.println("Username: ");
                userName = sc.next();
                System.out.println("Password: ");
                password = sc.next();
                
                login = memberList.checkIfUser(userName, password);
                
                if(login)
                {
                    curUser.userName = userName;
                }
                //Maybe that means it returns the current object
            }
            else if(answer.equals("O") && login)
            {
                //Logout
                login = false;
                curUser.userName = null;
            }
            else if(answer.equals("P") && login)
            {
                //Post a message
                System.out.println("What's on your mind? ");
                post = sc.next();
                Message message = new Message(post);
                
                curUser.postMessage(message);
                //Make sure it is being stored in the right user
                //Make sure a user is logged in
                
                
            }
            else if(answer.equals("V") && login)
            {
                //View messages of people
                        
            }
            else if(answer.equals("E"))
            {
                //Write to every file that needs to be done
                //exits
                return;
            }
            /*else if(answer.equals())
            {
                
            }*/
            else if(!answer.equals("R") && !answer.equals("L") && !answer.equals("O") 
                    && !answer.equals("P") && !answer.equals("V") 
                    /*&& !answer.equals() && !answer.equals()*/)
            {
                System.out.println();
            }
            
        }while(!answer.equals("E"));
    }
}
