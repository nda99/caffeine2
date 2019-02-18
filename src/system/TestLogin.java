package system;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*;

public class TestLogin {

    @Test
    public void testRegister() {
        String dummyFile = "dummy.csv";
        boolean result = false;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            Login login = new Login(dummyFile);
            result = login.register("theo25", "pswd3", "Manager", "Theo", "theo@cafe.com");
        }
        catch (InvalidUsersFileException e){
            e.printStackTrace();
        }

        assertEquals("Failed to register", true, result);

        new File(dummyFile).delete();

    }

    @Test
    /**
     * This test does not use the register method in case it does not work properly
     */
    public void testLoginSucess(){
        String dummyFile = "dummy.csv";
        boolean result = false;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
            String newline = System.getProperty("line.separator");
            writer.write("theo25,K/vJbnkGFFDrBy0T/mO92PL7By6KI5H9FpN+p4ILn/k=,Manager,Theo,theo@cafe.com"+newline);
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            Login login = new Login(dummyFile);
            result = login.login("theo25", "pswd3");
        }
        catch (InvalidUsersFileException e){
            e.printStackTrace();
        }


        assertEquals("Failed to login", true, result);

        new File(dummyFile).delete();
    }

    @Test
    /**
     * This test does not use the register method in case it does not work properly
     */
    public void testLoginFailWrongPass(){
        String dummyFile = "dummy.csv";
        boolean result = true;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
            String newline = System.getProperty("line.separator");
            writer.write("theo25,K/vJbnkGFFDrBy0T/mO92PL7By6KI5H9FpN+p4ILn/k=,Manager,Theo,theo@cafe.com"+newline);
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            Login login = new Login(dummyFile);
            result = login.login("theo25", "wrongpassword");
        }
        catch (InvalidUsersFileException e){
            e.printStackTrace();
        }


        assertEquals("Login should have failed", false, result);

        new File(dummyFile).delete();
    }

    @Test
    /**
     * This test does not use the register method in case it does not work properly
     */
    public void testLoginFailMissingUser(){
        String dummyFile = "dummy.csv";
        boolean result = true;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
            String newline = System.getProperty("line.separator");
            writer.write("theo25,K/vJbnkGFFDrBy0T/mO92PL7By6KI5H9FpN+p4ILn/k=,Manager,Theo,theo@cafe.com"+newline);
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            Login login = new Login(dummyFile);
            result = login.login("Inexistantuser", "pswd3");
        }
        catch (InvalidUsersFileException e){
            e.printStackTrace();
        }


        assertEquals("login should have failed", false, result);

        new File(dummyFile).delete();
    }
}
