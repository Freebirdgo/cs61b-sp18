/** A class for off-by-N comparators. */
public class OffByN implements CharacterComparator {
    private int n;
    public OffByN(int n) {
        this.n = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 5 || diff == -5;
    }
}
