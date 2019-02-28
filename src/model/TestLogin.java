package model;

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
            System.out.println(e.getMessage());
        }
        catch (InvalidRegistration e){
            System.out.println(e.getMessage());
        }

        new File(dummyFile).delete();

        assertEquals("Failed to register", true, result);

    }

    @Test
    public void testRegisterFail() {
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
            result = login.register("theo25", "pswd3", "Manager", "Theo", "theo@cafe.com");
        }
        catch (InvalidUsersFileException e){
            System.out.println(e.getMessage());
        }
        catch (InvalidRegistration e){
            System.out.println(e.getMessage());
            result = false;
        }

        new File(dummyFile).delete();

        assertEquals("register should have failed", false, result);

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

        new File(dummyFile).delete();

        assertEquals("Failed to login", true, result);

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

        new File(dummyFile).delete();

        assertEquals("Login should have failed", false, result);
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

        new File(dummyFile).delete();

        assertEquals("login should have failed", false, result);
    }
}
