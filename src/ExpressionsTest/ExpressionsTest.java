package ExpressionsTest;
import org.junit.jupiter.api.Test;
import Expressions.Expressions;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionsTest {

    @Test
    public void test() {
        assertEquals(2.0, Expressions.calculate("1 + 1"), 0.001);
        assertEquals(2.0, Expressions.calculate("4 - 2"), 0.001);
        assertEquals(8.0, Expressions.calculate("4 * 2"), 0.001);
        assertEquals(8.0,Expressions.calculate("16 / 2"), 0.001);
        assertEquals(-1.0, Expressions.calculate("-1"), 0.001);
        assertEquals(1.0, Expressions.calculate("-(-1)"), 0.001);
        assertEquals(20.0, Expressions.calculate("5 + 3 * (7 - 2)"), 0.001);
        assertEquals(22.5, Expressions.calculate("(10 + 5) * 3 / 2"), 0.001);
    }
}