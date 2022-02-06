import java.util.LinkedList;

public class Team {
    private String team;
    private LinkedList<Opponent> opponents;

    public String getTeam() {
        return team;
    }

    public LinkedList<Opponent> getOpponents() {
        return opponents;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setOpponents(LinkedList<Opponent> opponents) {
        this.opponents = opponents;
    }

    public Team(String team) {
        this.team = team;
        this.opponents = new LinkedList<Opponent>();
    }
}
