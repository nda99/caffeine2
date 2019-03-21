package model;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.*;
import exceptions.*;

public class Login {
    private HashMap<String, String[]> userMap;
    private String staffFile;
    private ActivityLog log = ActivityLog.getInstance();

    /**
     * Create a "Login" object, it reads a given csv file, parses it and build a hashmap of users with the content
     * @param csvFile csv file to be read
     * @throws InvalidUsersFileException print the line where something is wrong
     */
    public Login(String csvFile) throws InvalidUsersFileException{
    	
        staffFile = csvFile;
        userMap = new HashMap<>();
        String line;
        String[] userTemp = new String[4];
        int lineCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                lineCounter ++;
                String[] user = line.split(",");
                if(user.length != 5){
                    throw new InvalidUsersFileException(lineCounter, user.length);
                } else{
                    userTemp[0] = user[1];
                    userTemp[1] = user[2];
                    userTemp[2] = user[3];
                    userTemp[3] = user[4];
                    userMap.put(user[0], userTemp.clone());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a staff member exists or not with username
     * @param userName
     * @return true if user exist, false else
     */
    public boolean staffExist(String userName){
        if(userMap.get(userName) == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Register a new staff member (staff or manager)
     * @param userName username
     * @param password password only a SHA 256 of the password is stored, not the password itself
     * @param position staff or manager
     * @throws InvalidRegistrationException
     * @return true if successful, false if not
     */
    public boolean register(String userName, String password, String position, String fullName, String emailAddress) throws InvalidRegistrationException{
        checkEmail(emailAddress);
        checkPosition(position);
        checkUserName(userName);
        String[] value;
        value = new String[4];
        value[0] = hashPassword(password);
        value[1] = position;
        value[2] = fullName;
        value[3] = emailAddress;
        userMap.put(userName, value);
        try {
            FileWriter userStream = new FileWriter(staffFile, true);
            String newline = System.getProperty("line.separator");
            userStream.append(userName + "," + value[0] + "," + value[1] + "," + value[2] + "," + value[3] + newline);
            userStream.flush();
            userStream.close();
        }catch (IOException e){
        	log.logWarning("Registration of  " + fullName + " as " + position + " could not be made");
            return false;
        }
        log.logInfo("Successful registration of  " + fullName + " as " + position);
        return true;
    }

    /**
     * create the SHA-256 hash of a password
     * @param password
     * @return the String corresponding to the hash of the password
     */
    private String hashPassword(String password){
        String passwordHash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            passwordHash = Base64.getEncoder().encodeToString(hash);
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return passwordHash;
    }

    /**
     * Login a user
     * @param userName user name
     * @param password password
     * @return true if the login is successful, false if not
     */
    public boolean login(String userName, String password){
        String[] valueTemp = userMap.get(userName);
        String passwordH = hashPassword(password);
        if(valueTemp == null){
            System.out.println("This username does not exist");
            return false;
        }
        else if(!(passwordH.equals(valueTemp[0]))){
            System.out.println("Invalid password ");
            return false;
        }
        else{
            //System.out.println("login successful !");
            log.logInfo(userName + " logged in successfully.");
            return true;
        }
    }

    /**
     * get full name, email address and position from userMap
     * @param userName
     * @return a String array with full name, email address and position in this order
     */
    public String[] getDetails(String userName)throws StaffNonExistantException {
        String[] result = new String[3];
        if(!staffExist(userName)){
            throw new StaffNonExistantException(userName);
        }else{
            String[] valueTemp = userMap.get(userName);
            result[0] = valueTemp[2];
            result[1] = valueTemp[3];
            result[2] = valueTemp[1];
        }
        return result;
    }

    /**
     * Check the username, it should just not be null or already existing
     * @param un username
     * @throws InvalidRegistrationException
     */
    private void checkUserName(String un) throws InvalidRegistrationException{
        if(un == null) {
            throw new InvalidRegistrationException("Invalid userName");
        }
        if(userMap.get(un)!=null) {
            throw new InvalidRegistrationException("The user name " + un + " is already taken");
        }
    }

    /**
     * Check position, it should either be Staff or Manager
     * @param pos position
     * @throws InvalidRegistrationException
     */
    private void checkPosition(String pos) throws InvalidRegistrationException{
    	System.out.println("pos ="+pos);
        if (!(pos.equals("Staff") || pos.equals("Manager"))){
            throw new InvalidRegistrationException("Invalid position, should be Staff or Manager");
        }

    }

    /**
     * Check the email address just by making sur it contains "@"
     * @param email email address
     * @throws InvalidRegistrationException
     */
    private void checkEmail(String email) throws InvalidRegistrationException{
        if (!email.contains("@")){
            throw new InvalidRegistrationException("Invalid email");
        }
    }
}
