/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class User {

    private String name;
    private Date dateOfBirth;
    private String userName;
    private String phoneNumber;
    private String password;
    private String bgcolor = "#ffffff";

    private boolean isValidUser = false;

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsValidUser() {
        return isValidUser;
    }

    public void setIsValidUser(boolean isValidUser) {
        this.isValidUser = isValidUser;
    }

    /**
     * Creates a new instance of User
     */
    public User() {
    }

    public String register() {

        Controller.initDB("testDB");
        String userCQL = "INSERT INTO users (username, name, password, phoneNumber, background,birthdate)"
                + "  VALUES('" + userName + "', '" + name + "', '" + password + "', '" + phoneNumber + "', '" + bgcolor + "','" + dateOfBirth + "')";
        // System.out.println("\n\n\n\n\n\n"+userCQL+"\n\n\n\n\n\n\n\n");
        //Controller.colseConnection();
        String cql = "select username from users where username='" + userName + "'";
        if (Controller.isValidUsername(cql)) {
            Controller.insertUsers(userCQL);
            return "index.xhtml?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "username "+userName+" has been taken ... please choose another username", null);
                FacesContext.getCurrentInstance().addMessage("reg:username", msg);
            return "";
        }

    }

    public void signIn() {
        Controller.initDB("testDB");
        String cql = "select username,password from users where username='" + userName + "'";
        isValidUser = Controller.signIn(cql, password);
        if(!isValidUser)
        {
           FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password", null);
                FacesContext.getCurrentInstance().addMessage("form:msg", msg); 
        }
        Controller.colseConnection();

    }
    public String signOut()
    {
        
        System.out.println("\n\n\n\n\n\naa " + userName + "\n\n\n\n\n\n\n\n");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
    
    public void retreivePassword()
    {
        Controller.initDB("testDB");
        String cql = "select username,password from users where username='" + userName + "'";
        String pass = Controller.retreivePassword(cql);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Your password: "+ pass, null);
                FacesContext.getCurrentInstance().addMessage("form:msg", msg); 
    }

}
