public class Opponent {
    private String opponent;
    private int wins;

    public int getWins() {
        return wins;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Opponent(String opponent, int wins) {
        this.wins = wins;
        this.opponent = opponent;
    }
}
