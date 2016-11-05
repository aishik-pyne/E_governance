/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Codes;

import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID=1L;//important line
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver Loaded");
        } catch (Exception ex) {
            System.out.println("SQL Driver not Found");
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("username");
        String password=request.getParameter("passwords");
        try
        {
            password = hash(password);
        }
        catch (NoSuchAlgorithmException ex) {
       Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        boolean val=isPresent(password,name);
        if(val){
            try {
                out.print("<html><body>");
                out.print("welcome "+name);
                out.print("<br />");
                out.print("password - "+password);
                out.print("</body></html>");
            }catch(Exception e) {
                out.close();
             }
        }
        else{
            try {
                out.print("<html><body>");
                out.print("Incorrect credentials "+name);
                out.print("<br />");
                out.print("</body></html>");
            }catch(Exception e) {
                out.close();
            }
        }
    }
    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    public boolean isPresent(String passwrord,String name){
        boolean flag=false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
				//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","papan2202");
				//Statement stmt = conn.createStatement();

            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sis_db","root","abcdefgh");
            //here sonoo is database name, root is username and password
            System.out.println("Connection done");
            java.sql.Statement stmt=myConn.createStatement();
            System.out.println("query being done");
            ResultSet rs=stmt.executeQuery("select id from sis_db.student_main where roll_no like "+name);
            System.out.println("queried");
            myConn.close();
            System.out.println("Connection closed");
            if(rs!=null) {
                flag= true;
            }
            else{
                flag=false;
            }
        }catch(Exception e){

            System.out.println(e);}
        return flag;
            }
}
