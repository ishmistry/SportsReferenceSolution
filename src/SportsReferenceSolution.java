import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class SportsReferenceSolution {

    LinkedList<LinkedList<String>> wl;

    public static void main(String[] args) {
        JSONParser parser = new JSONParser(); // creating parser to read thru json file
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("winloss.json")); // reading json file

            for (Object o : array) {
                JSONObject team = (JSONObject) o;

            }

        } catch (FileNotFoundException e) { // exceptions in the case invalid file is provided
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
