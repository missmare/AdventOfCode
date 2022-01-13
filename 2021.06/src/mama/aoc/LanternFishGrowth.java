package mama.aoc;

public class LanternFishGrowth {

    public int getSwarmSize(String initialSwarm, int days) {
        String[] lanternTimer = initialSwarm.split(",");
        LanternSwarm lanternSwarm = new LanternSwarm();
        for (String startTimer : lanternTimer) {
            lanternSwarm.addLanternWithTimer(Integer.parseInt(startTimer));
        }

        for (int i = 0; i < days; i++) {
            lanternSwarm.increaseDay();
            if (lanternSwarm.countTimers() > 9) {
                lanternSwarm.combineTimers();
            }
            //System.out.println("swarm has " + lanternSwarm.countTimers() + " timers after day " + i);
            //lanternSwarm.printSwarm("Swarm after day " + i);
        }

        return lanternSwarm.countSwarmSize();
    }


}
