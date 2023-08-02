import java.util.TreeSet;

public class Solution {
    // TODO: Include your data structures here
    public class Quest implements Comparable<Quest> {
        long energy;
        long reward;

        public Quest(long energy, long reward) {
            this.energy = energy;
            this.reward = reward;
        }


        @Override
        public int compareTo(Quest o) {
            if(energy != o.energy) return Long.compare(energy, o.energy);
            else return Long.compare(reward, o.reward);
        }
    }

    TreeSet<Quest> quests = new TreeSet<>();

    public Solution() {
        // TODO: Construct/Initialise your data structures here
    }

    void add(long energy, long value) {
        // TODO: Implement your insertion operation here
        quests.add(new Quest(energy, value));
    }

    long query(long remainingEnergy) {
        // TODO: Implement your query operation here
        long totalReward = 0;
        while(!quests.isEmpty() && remainingEnergy > 0) {
            // Returns the maximum element in this set that is <= input quest,
            // assuming quest with remaining energy and highest gold reward, or null if there is no such element.
            Quest q = quests.floor(new Quest(remainingEnergy, Long.MAX_VALUE));
            if(q != null) {
                //sum reward and reduce energy, remove the quest from the set as it has been completed
                totalReward += q.reward;
                remainingEnergy -= q.energy;
                quests.remove(q);
            } else break;
        }
        return totalReward;
    }

}
