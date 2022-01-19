package mama.aoc.trial3;

public class CharacterCounter {

    private char character;

    private long counter = 1L;
    public CharacterCounter(char character) {
        this.character = character;
    }

    public CharacterCounter(char character, long counter){
        this.character = character;
        this.counter = counter;
    }

    public void increaseCounterBy(long l){
        this.counter += l;
    }

    public char getCharacter() {
        return character;
    }

    public long getCounter() {
        return counter;
    }
}
