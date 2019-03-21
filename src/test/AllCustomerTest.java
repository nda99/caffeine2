package test;

import static org.junit.Assert.*;

import model.AllCustomers;
import exceptions.InvalidCustomerFileException;
import exceptions.UserNameAlreadyTakenException;
import org.junit.Test;

import java.io.*;

public class AllCustomerTest {

    @Test
    public void testAddLoyalCustomerTrue(){
        String dummyFile = "dummy.csv";
        boolean result = false;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            AllCustomers customers = new AllCustomers(dummyFile);
            result = customers.addLoyalCustomer("AZERTY");
        }
        catch (InvalidCustomerFileException e){
            e.getMessage();
        }
        catch (UserNameAlreadyTakenException e){
            e.getMessage();
        }

        assertEquals("Failed to register", true, result);

        new File(dummyFile).delete();

    }

    @Test
    public void testAddLoyalCustomerFalse(){
        String dummyFile = "dummy.csv";
        boolean result = true;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dummyFile), "utf-8"))) {
        }catch(IOException e){
            e.printStackTrace();
        }

        try {
            AllCustomers customers = new AllCustomers(dummyFile);
            customers.addLoyalCustomer("AZERTY");
            result = customers.addLoyalCustomer("AZERTY");
        }
        catch (InvalidCustomerFileException e){
            System.out.println(e.getMessage());
        }
        catch (UserNameAlreadyTakenException e){
            System.out.println(e.getMessage());
            result = false;
        }

        assertEquals("adding an already existing customer should fail", false, result);

        new File(dummyFile).delete();

    }
}
