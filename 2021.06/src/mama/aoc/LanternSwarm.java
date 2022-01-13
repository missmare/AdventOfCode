package mama.aoc;

import java.util.ArrayList;
import java.util.List;

public class LanternSwarm {

    private List<LanternSwarmTimer> swarmTimers = new ArrayList<>();

    public void addLanternWithTimer(int timer) {
        if (!swarmTimers.contains(new LanternSwarmTimer(timer))) {
            swarmTimers.add(new LanternSwarmTimer(timer));
        }
        long numberOfTimers = swarmTimers.stream().filter(l -> l.getTimer() == timer).count();
        if (numberOfTimers == 0) {
            swarmTimers.add(new LanternSwarmTimer(timer));
        } else if (numberOfTimers > 1) {
            combineTimers();
        }

        //add a fish with this timer
        swarmTimers.stream()
                .filter(lantern -> (lantern.getTimer() == timer))
                .forEach(LanternSwarmTimer::addLanternFish);
    }

    public void increaseDay() {
        long newLanterns = swarmTimers.stream()
                .mapToLong(LanternSwarmTimer::passDay)
                .sum();

        if (newLanterns > 0) {
            LanternSwarmTimer newBornLanterns = new LanternSwarmTimer(8);
            newBornLanterns.setSize(newLanterns);
            swarmTimers.add(newBornLanterns);
        }
    }

    public void printSwarm(String s) {
        System.out.println(s);
        swarmTimers.stream()
                .forEach(System.out::println);
    }

    public void combineTimers() {
        List<LanternSwarmTimer> combinedTimers = new ArrayList<>();
        for (LanternSwarmTimer timer : swarmTimers) {
            int indexOfTimer = combinedTimers.indexOf(timer);
            if (indexOfTimer > -1) {
                combinedTimers.get(indexOfTimer).increaseSizeBy(timer.getSize());
            } else {
                combinedTimers.add(timer);
            }
        }
        swarmTimers = combinedTimers;
    }

    public long countSwarmSize() {
        return swarmTimers.stream()
                .mapToLong(LanternSwarmTimer::getSize).sum();
    }

    public int countTimers() {
        return swarmTimers.size();
    }
}