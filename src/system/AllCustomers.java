package system;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AllCustomers {
    private HashMap<String, LoyalCustomer> customers;
    private String customerFile;

    /**
     * Constructor, reads the input csv file and build the HashMap based on the data found
     * @param customerFile
     * @throws InvalidCustomerFileException
     */
    public AllCustomers(String customerFile) throws InvalidCustomerFileException{
        this.customerFile = customerFile;
        this.customers = new HashMap<>();
        String line;
        int lineCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(customerFile))) {
            while ((line = br.readLine()) != null) {
                lineCounter ++;
                String[] customer = line.split(",");
                if(customer.length != 2){
                    throw new InvalidCustomerFileException(lineCounter, customer.length);
                } else{
                    LoyalCustomer custTemp = new LoyalCustomer(customer[0], Integer.parseInt(customer[1]));
                    customers.put(customer[0], custTemp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Add a loyal customer to the file and to the map
     * @param membershipNo membership number
     * @return true if the customer was added, false if it failed
     */
    public boolean addLoyalCustomer(String membershipNo) throws UserNameAlreadyTakenException {
        String msHash = hashMembership(membershipNo);
        LoyalCustomer customer = customers.get(msHash);
        if(customer != null){
            throw new UserNameAlreadyTakenException(membershipNo);
        }else{
            customer = new LoyalCustomer(membershipNo);
            customers.put(msHash, customer.copy());
            try {
                FileWriter customerStream = new FileWriter(customerFile, true);
                String newline = System.getProperty("line.separator");
                customerStream.append(msHash + "," + "0" + newline);
                customerStream.flush();
                customerStream.close();
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * create the SHA-256 hash of a membership
     * @param membership
     * @return the String corresponding to the hash of the membership
     */
    private String hashMembership(String membership){
        String membershipHash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(membership.getBytes(StandardCharsets.UTF_8));
            membershipHash = Base64.getEncoder().encodeToString(hash);
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return membershipHash;
    }

    /**
     * Return the loyal customer with the desired membership number, return the actual LoyalCustomer and not a copy
     * @param membership membership of the loyal customer to get
     * @return the loyal customer corresponding to the membership if found
     * @throws CustomerNonExistantException
     */
    public LoyalCustomer getLoyalCustomer(String membership) throws CustomerNonExistantException{
        String msHash = hashMembership(membership);
        LoyalCustomer res = customers.get(msHash);
        if(res == null){
            throw new CustomerNonExistantException(membership);
        }
        else{
            return res;
        }
    }

    /**
     * Remove a loyal customer based on its membership number
     * @param membership membership number
     * @return
     */
    public String removeLoyalCustomer(String membership){
        String msHash = hashMembership(membership);
        customers.remove(membership);
        return "Loyal customer " + membership + "has been removed";
    }

    /**
     * Remove a loyal customer based on the hash of its membership number
     * @param membershipHash hash of the membership
     */
    public void removeCustomerHash(String membershipHash){
        customers.remove(membershipHash);
    }

    /**
     * Simple copy function used in the updateFile method to create a backup file
     * @param source
     * @param dest
     */
    private void copyFiles(String source, String dest){
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update the file to record the number of points, lazy version, erases the previous file and write it again.
     * This method should always be used before finishing the program to save the changes.
     * @return true if it succeeded, false else
     */
    public boolean updateFile(){
        String backUpFile = "CustomersTempBackup.csv";
        // Copying previous customer file before erasing it
        copyFiles(this.customerFile, backUpFile);
        //deleting old customer file
        new File(this.customerFile).delete();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(this.customerFile), "utf-8"))) {
            String newline = System.getProperty("line.separator");

            //fill new file with current data in the Hashmap
            for (HashMap.Entry<String, LoyalCustomer> custTemp : customers.entrySet()) {
                LoyalCustomer lcTemp = custTemp.getValue();
                writer.write(lcTemp.getMembershipNo() + "," +  lcTemp.getPoints() + newline);
            }

            writer.flush();
            writer.close();
        }catch (IOException e){
            copyFiles(backUpFile, this.customerFile);
            new File(backUpFile).delete();
            e.printStackTrace();
            return false;
        }

        //delete backup if successful
        new File(backUpFile).delete();
        return true;
    }

}
