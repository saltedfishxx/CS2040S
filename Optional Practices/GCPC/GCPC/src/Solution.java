import java.util.TreeSet;

public class Solution {

    private TreeSet<Node> teamset = new TreeSet<Node>();;
    private Node[] teams;
    private int rank;

    private class Node implements Comparable<Node> {
        int team;    // team number
        int solved;       // number of problems solved
        long penalty;      // total penalty

        public Node(int team, int solved, long penalty) {
            this.team = team;
            this.solved = solved;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Node other) {
            if (this.solved != other.solved) {
                return Integer.compare(other.solved, this.solved);
            }
            if (this.penalty != other.penalty) {
                return Long.compare(this.penalty, other.penalty);
            }
            return Integer.compare(this.team, other.team);
        }
    }

    public Solution(int numTeams) {
        teams = new Node[numTeams];
        for (int i = 0; i < numTeams; i++) {
            teams[i] = new Node(i+1, 0, 0);
        }
    }

    public int update(int team, long newPenalty) {
        Node current = teams[team-1];
        teamset.remove(current);

        current.solved++;
        current.penalty += newPenalty;
        if(team == 1) {
            while (!teamset.isEmpty()) {
                Node t = teamset.last();
                if(teams[0].compareTo(t) < 0)
                    teamset.remove(t);
                else break;
            }
        }else {
            if(teams[0].compareTo(current) > 0)
                teamset.add(current);
        }
        rank = teamset.size() + 1;
        return rank;
    }

}