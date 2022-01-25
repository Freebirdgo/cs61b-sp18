import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualCharsByN() {
        OffByN obn = new OffByN(5);
        assertTrue(obn.equalChars('a', 'f'));
        assertFalse(obn.equalChars('f', 'h'));
        assertFalse(obn.equalChars('a', 'a'));
    }

}
