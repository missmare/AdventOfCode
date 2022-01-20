package mama.aoc;

public class ChitonPathCalculator {

    int iterations;
    int currentRisk;
    int totalRisk;
    AdventPair<Integer, Integer> cameFrome;
    AdventPair<Integer, Integer> atPosition;

    /** Initialize ChitonPathCalculator at position 0,0
     *
     */
    public ChitonPathCalculator() {
        this.cameFrome = null;
        this.atPosition = new AdventPair<>(0, 0);
        this.currentRisk = 0;
        this.iterations = 0;
        this.totalRisk = 0;
    }

    private ChitonPathCalculator(ChitonPathCalculator fromChiton, int xPosition, int yPosition, int newRisk) {
        this.cameFrome = fromChiton.getAtPosition();
        this.atPosition = new AdventPair<>(xPosition, yPosition);

        this.iterations = fromChiton.getIterations() + 1;
        this.currentRisk = newRisk;
        this.totalRisk = fromChiton.getTotalRisk() + newRisk;
    }

    public int getIterations() {
        return iterations;
    }

    public int getTotalRisk() {
        return totalRisk;
    }

    public AdventPair<Integer, Integer> getCameFrome() {
        return cameFrome;
    }

    public AdventPair<Integer, Integer> getAtPosition() {
        return atPosition;
    }

    public ChitonPathCalculator moveUp(int[][] chitons) {
        int newX = this.atPosition.getFirst();
        int newY = this.atPosition.getSecond() - 1;
        return new ChitonPathCalculator(this, newX, newY, chitons[newX][newY]);
    }

    public ChitonPathCalculator moveDown(int[][] chitons) {
        int newX = this.atPosition.getFirst();
        int newY = this.atPosition.getSecond() + 1;
        return new ChitonPathCalculator(this, newX, newY, chitons[newX][newY]);
    }

    public ChitonPathCalculator moveLeft(int[][] chitons) {
        int newX = this.atPosition.getFirst() - 1;
        int newY = this.atPosition.getSecond();
        return new ChitonPathCalculator(this, newX, newY, chitons[newX][newY]);
    }

    public ChitonPathCalculator moveRight(int[][] chitons) {
        int newX = this.atPosition.getFirst() + 1;
        int newY = this.atPosition.getSecond();
        return new ChitonPathCalculator(this, newX, newY, chitons[newX][newY]);
    }


}
