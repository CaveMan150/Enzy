
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Row;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Controller {

    final static String userTable
            = "CREATE TABLE IF NOT EXISTS users (username text, name text, password text, phoneNumber text, background text, birthdate text, PRIMARY KEY (username))";

    final static String msgTable
            = "CREATE TABLE IF NOT EXISTS messages (messageID int, username text, content text, hashtag text, posteddate timestamp, "
            + " PRIMARY KEY (messageID))";

    final static String comTable
            = "CREATE TABLE IF NOT EXISTS comment (commentID int, messageID int, username text, content text, posteddate timestamp, "
            + " PRIMARY KEY (commentID))";

    static Cluster cluster = null;
    static Session session = null;

    public static boolean initDB(String dbName) {
        // Connect to the cassandra database"
        try {
            cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
            session = cluster.connect();
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + dbName + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");
            session.execute("use " + dbName);
            session.execute(userTable);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if(cluster!=null)
                cluster.close();
            return false;
        }
        
    }

    public static void insertUsers(String sql) {
        try
        {
                        session.execute(sql);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void colseConnection()
    {
        session.close();
        cluster.close();
    }

   public static boolean signIn(String cql, String password) 
    {
        ResultSet res =session.execute(cql);
        Row r = res.one();
        if(r==null)
            return false;
        else
        {
            String pass = r.getString("password");
            if(pass.equals(password))
                return true;
            else
                return false;
        }
    }
   
   public static boolean isValidUsername(String cql) 
    {
        ResultSet res =session.execute(cql);
        Row r = res.one();
        if(r==null)
            return true;
        else
                return false;
    }

    public static String retreivePassword(String cql) {
        ResultSet res =session.execute(cql);
        Row r = res.one();
        if(r==null)
            return "username does not exist";
        else
        {
            String pass = r.getString("password");
             return pass;
        }
    }
}
