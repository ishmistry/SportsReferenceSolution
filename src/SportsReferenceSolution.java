import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;


public class SportsReferenceSolution {

    public static void main(String[] args) {
        try {
            LinkedList<Team> scores = scoreParse("winloss.json");
            scores = sort(scores);
            for (int i = 0; i < scores.size(); i++) {
                System.out.println(scores.get(i).getTeam());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static LinkedList<Team> scoreParse(String filename) throws IOException {

        String file = new String(Files.readAllBytes(Paths.get(filename)));
        file = file.replace("\n", "");

        JSONObject jsonObject = new JSONObject(file);
        Iterator teamKeys = jsonObject.keys();
        LinkedList<Team> teams = new LinkedList<>();

        int teamIdx = 0;
        while(teamKeys.hasNext()) {

            String curTeam = (String)teamKeys.next();
            teams.add(new Team(curTeam));
            JSONObject teamJSON = jsonObject.getJSONObject(curTeam);
            Iterator opponentKeys = teamJSON.keys();

            while(opponentKeys.hasNext()) {
                String curOpponent = (String)opponentKeys.next();
                JSONObject oppJSON = teamJSON.getJSONObject(curOpponent);
                int wins = oppJSON.getInt("W");
                teams.get(teamIdx).getOpponents().add(new Opponent(curOpponent, wins));
            }
            teamIdx++;
        }

        return teams;
    }

    public static LinkedList<Team> sort(LinkedList<Team> scores) {
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
