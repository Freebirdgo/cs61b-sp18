import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

        /*
        // You must use this CharacterComparator and not instantiate
        // new ones, or the autograder might be upset.
        static CharacterComparator offByOne = new OffByOne();

        // Your tests go here.
        */
        @Test
        public void testEqualCharsByN() {
            OffByN obo = new OffByN(5);
            assertTrue(obo.equalChars('a', 'f'));
            assertFalse(obo.equalChars('f', 'h'));
            assertFalse(obo.equalChars('a', 'a'));
        }

}
