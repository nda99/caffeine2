package system;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    private HashMap<String, String[]> userMap;


    public Login(String csvFile){
        String line;
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] user = line.split(cvsSplitBy);

                System.out.println("user name = " + user[0] + ", pasword = " + user[1] + ", position = " + user[2]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password){
        return password;
    }
}
