import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class SportsReferenceSolution {

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Input file name:");
            String filename = scan.nextLine(); // scan filename from user input
            LinkedList<Team> scores = scoreParse(filename);
            scores = sort(scores);
            System.out.print("Team ");
            for (int i = 0; i < scores.size(); i++) {
                System.out.print(" " + scores.get(i).getTeam() + " ");
            }
            for (int i = 0; i < scores.size(); i++) { // printing teams and # of wins against each opposing team
                System.out.print("\n" + scores.get(i).getTeam() + "  ");
                for (int j = 0; j < scores.get(i).getOpponents().size(); j++) {
                    if (i == j) { // if same team row and column
                        System.out.print(" --- ");
                    }
                    if ((scores.get(i).getOpponents().get(j).getWins() / 10) == 0) {
                        System.out.print("  " + scores.get(i).getOpponents().get(j).getWins() + "  ");
                    } else if (10 > scores.get(i).getOpponents().get(j).getWins() / 10 &&
                            scores.get(i).getOpponents().get(j).getWins() / 10 > 0) {
                        System.out.print(" " + scores.get(i).getOpponents().get(j).getWins() + "  ");
                    } else {
                        System.out.print(" " + scores.get(i).getOpponents().get(j).getWins() + " ");
                    }
                }
            }
            System.out.print(" --- ");
        } catch (NoSuchFileException e) {
            System.out.println("Error: File not found!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static LinkedList<Team> scoreParse(String filename) throws IOException { // function for parsing json file

        String file = new String(Files.readAllBytes(Paths.get(filename)));
        file = file.replace("\n", "");

        JSONObject jsonObject = new JSONObject(file);
        Iterator teamKeys = jsonObject.keys(); // getting team keys as an iterator
        LinkedList<Team> teams = new LinkedList<>();

        int teamIdx = 0;
        while(teamKeys.hasNext()) {

            String curTeam = (String)teamKeys.next(); // getting next team key
            teams.add(new Team(curTeam)); // adding team to linked list
            JSONObject teamJSON = jsonObject.getJSONObject(curTeam);
            Iterator opponentKeys = teamJSON.keys(); // getting opponent keys as an iterator

            while(opponentKeys.hasNext()) {
                String curOpponent = (String)opponentKeys.next(); // getting next opponent key
                JSONObject oppJSON = teamJSON.getJSONObject(curOpponent);
                int wins = oppJSON.getInt("W"); // getting # of wins from given opponent
                teams.get(teamIdx).getOpponents().add(new Opponent(curOpponent, wins)); // adding opponent to respective team object
            }
            teamIdx++;
        }

        return teams;
    }

    public static LinkedList<Team> sort(LinkedList<Team> scores) { // sorting linked list using bubble sort
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 0; j < scores.get(i).getOpponents().size() - 1; j++) {
                for (int k = 0; k < scores.get(i).getOpponents().size() - j - 1; k++) {
                    if (scores.get(i).getOpponents().get(k).getOpponent().compareTo(scores.get(i).getOpponents().get(k+1).getOpponent()) > 0) {
                        Opponent op = scores.get(i).getOpponents().remove(k);
                        scores.get(i).getOpponents().add(k + 1, op);
                    }
                }
            }
            for (int l = 0; l < scores.size() - i - 1; l++) {
                if (scores.get(l).getTeam().compareTo(scores.get(l+1).getTeam()) > 0) {
                    Team t = scores.remove(l);
                    scores.add(l + 1, t);
                }
            }
        }
        return scores;
    }
}
