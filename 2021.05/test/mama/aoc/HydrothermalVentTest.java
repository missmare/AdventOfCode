package mama.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HydrothermalVentTest {

    @Test
    public void testRegex() {
        assertTrue(checkformat("1,2 -> 3,4"));
        assertFalse(checkformat("a,b -> c,d"));
        assertFalse(checkformat("1,2->3,4"));
        assertFalse(checkformat("1,2-> 3,4"));
        assertFalse(checkformat("1,2- >3,4"));
        assertFalse(checkformat("1,2 ->3,4"));
        assertFalse(checkformat("1 ,2 -> 3,4"));
        assertTrue(checkformat("123,4567 -> 99,1"));
    }

    public boolean checkformat(String input) {
        String regex = "\\d+,\\d+ -> \\d+,\\d+";
        return input.matches(regex);
    }

    @Test
    public void testHorizontalAndVertical() {
        HydrothermalVent vent = new HydrothermalVent("1,2 -> 3,4");
        assertFalse(vent.isHorizontalOrVertical());

        vent = new HydrothermalVent("1,2 -> 1,4");
        assertTrue(vent.isHorizontalOrVertical());

        vent = new HydrothermalVent("0,1 -> 4,1");
        assertTrue(vent.isHorizontalOrVertical());
    }

    @Test
    void testDiagonal() {
        HydrothermalVent line = new HydrothermalVent("1,2 -> 3,4");
        assertTrue(line.isDiagonal());
        assertTrue(line.isValid());

        line = new HydrothermalVent("0,8 -> 8,0");
        assertTrue(line.isDiagonal());
        assertTrue(line.isValid());

        line = new HydrothermalVent("0,8 -> 7,0");
        assertFalse(line.isDiagonal());
        assertFalse(line.isValid());
    }

    @Test
    public void ensureDiagonalDirectionLowerToUpper() {
        HydrothermalVent line = new HydrothermalVent("0,1 -> 2,5");
        assertEquals(1, line.getX1());
        assertEquals(0, line.getY1());

        line = new HydrothermalVent("2,5 -> 0,1");
        assertEquals(1, line.getX1());
        assertEquals(0, line.getY1());

        line = new HydrothermalVent("0,8 -> 7,1");
        assertEquals(1, line.getX1());
        assertEquals(7, line.getY1());

        //reverse diagonal are anyway swaped
        line = new HydrothermalVent("7,1 -> 0,8");
        assertEquals(8, line.getX1());
        assertEquals(0, line.getY1());
    }

    @Test
    public void testLineDirection() {
        HydrothermalVent line = new HydrothermalVent("0,1 -> 4,5");
        assertTrue(line.isDiagonal());
        assertFalse(line.isHorizontalOrVertical());
        assertTrue(line.isXAcrossUp());
        assertTrue(line.isYAcrossUp());

        //reverse diagonal lines are swaped anyway
        line = new HydrothermalVent("7,1 -> 0,8");
        assertTrue(line.isDiagonal());
        assertFalse(line.isHorizontalOrVertical());
        assertFalse(line.isXAcrossUp());
        assertTrue(line.isYAcrossUp());

        line = new HydrothermalVent("0,8 -> 7,1");
        assertTrue(line.isDiagonal());
        assertFalse(line.isHorizontalOrVertical());
        assertTrue(line.isXAcrossUp());
        assertFalse(line.isYAcrossUp());

    }

}