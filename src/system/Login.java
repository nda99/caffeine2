package system;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Login {
    private HashMap<String, String[]> userMap;
    private String userFile;

    /**
     * Create a "Login" object, it reads a given csv file, parses it and build a hashmap of users with the content
     * @param csvFile csv file to be read
     * @throws InvalidUsersFileException print the line where something is wrong
     */
    public Login(String csvFile) throws InvalidUsersFileException{
        userFile = csvFile;
        userMap = new HashMap<>();
        String line;
        String[] userTemp = new String[2];
        int lineCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                lineCounter ++;
                String[] user = line.split(",");
                if(user.length != 3){
                    throw new InvalidUsersFileException(lineCounter);
                } else{
                    userTemp[0] = user[1];
                    userTemp[1] = user[2];
                    userMap.put(user[0], userTemp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Register a new staff member (staff or manager)
     * @param userName username
     * @param password password only a SHA 256 of the password is stored, not the password itself
     * @param position staff or manager
     * @throws UserNameAlreadyTakenException
     */
    public void register(String userName, String password, String position) throws UserNameAlreadyTakenException {
        String[] value;
        value = userMap.get(userName);
        if (value != null){
            throw new UserNameAlreadyTakenException(userName);
        }else{
            value = new String[2];
            value[0] = hashPassword(password);
            value[1] = position;
            userMap.put(userName, value);
            try {
                FileWriter userStream = new FileWriter(userFile, true);
                String newline = System.getProperty("line.separator");
                userStream.append(userName + "," + value[0] + "," + position + newline);
                userStream.flush();
                userStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
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
            System.out.println("login successful !");
            return true;
        }
    }
}
