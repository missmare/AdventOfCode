package mama.aoc;

import java.util.Objects;

public class LanternSwarmTimer {

    int timer;
    long size;

    /**
     * constructor
     */
    public LanternSwarmTimer(int timer) {
        this.timer = timer;
        this.size = 0L;
    }

    //Getter and setter
    public int getTimer() {
        return timer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void increaseSizeBy(long increase) {
        this.size += increase;
    }

    //methods
    public void addLanternFish() {
        this.size++;
    }

    public long passDay() {
        this.timer--;
        if (this.timer < 0) {
            this.timer += 7;
            return this.getSize();
        }
        return 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanternSwarmTimer that = (LanternSwarmTimer) o;
        return timer == that.timer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timer, size);
    }

    @Override
    public String toString() {
        return "\t{" + "timer=" + timer + ", size=" + size + '}';
    }

}