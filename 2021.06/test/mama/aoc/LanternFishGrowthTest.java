package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternFishGrowthTest {

    private final String smallSwarm = "3,4,3,1,2";
    private final String bigSwarm = "4,1,1,1,5,1,3,1,5,3,4,3,3,1,3,3,1,5,3,2,4,4,3,4,1,4,2,2,1,3,5,1,1,3,2,5,1,1,4,2,5,4,3,2,5,3,3,4,5,4,3,5,4,2,5,5,2,2,2,3,5,5,4,2,1,1,5,1,4,3,2,2,1,2,1,5,3,3,3,5,1,5,4,2,2,2,1,4,2,5,2,3,3,2,3,4,4,1,4,4,3,1,1,1,1,1,4,4,5,4,2,5,1,5,4,4,5,2,3,5,4,1,4,5,2,1,1,2,5,4,5,5,1,1,1,1,1,4,5,3,1,3,4,3,3,1,5,4,2,1,4,4,4,1,1,3,1,3,5,3,1,4,5,3,5,1,1,2,2,4,4,1,4,1,3,1,1,3,1,3,3,5,4,2,1,1,2,1,2,3,3,5,4,1,1,2,1,2,5,3,1,5,4,3,1,5,2,3,4,4,3,1,1,1,2,1,1,2,1,5,4,2,2,1,4,3,1,1,1,1,3,1,5,2,4,1,3,2,3,4,3,4,2,1,2,1,2,4,2,1,5,2,2,5,5,1,1,2,3,1,1,1,3,5,1,3,5,1,3,3,2,4,5,5,3,1,4,1,5,2,4,5,5,5,2,4,2,2,5,2,4,1,3,2,1,1,4,4,1,5";

    @Test
    public void testLanternFishVerySmall() {
        LanternFishGrowth lantern = new LanternFishGrowth();
        long swarmSize = lantern.getSwarmSize(smallSwarm, 18);
        assertEquals(26, swarmSize);
    }

    @Test
    public void testLanternFishSmall() {
        LanternFishGrowth lantern = new LanternFishGrowth();
        long swarmSize = lantern.getSwarmSize(smallSwarm, 80);
        assertEquals(5934, swarmSize);
    }

    @Test
    public void testLanternFishBig() {
        LanternFishGrowth lantern = new LanternFishGrowth();
        long swarmSize = lantern.getSwarmSize(bigSwarm, 80);
        assertEquals(352195, swarmSize);
    }

    @Test
    public void testLanternFishSmallForever() {
        LanternFishGrowth lantern = new LanternFishGrowth();
        long swarmSize = lantern.getSwarmSize(smallSwarm, 256);
        assertEquals(26984457539L, swarmSize);
    }

    @Test
    public void testLanternFishBigForever() {
        LanternFishGrowth lantern = new LanternFishGrowth();
        long swarmSize = lantern.getSwarmSize(bigSwarm, 256);
        System.out.println("Swarm has size: " + swarmSize);
        assertEquals(1600306001288L, swarmSize);
    }

}