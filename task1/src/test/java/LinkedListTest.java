import com.mmasata.exception.NullValueException;
import com.mmasata.exception.RemoveFromEmptyListException;
import com.mmasata.exception.ValueNotFoundException;
import com.mmasata.utils.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedListTest {

    @Test
    void push_cannotPushNull() {
        var list = new LinkedList();
        assertThrows(NullValueException.class, () -> list.push(null));
    }

    @Test
    void push_increaseSize() {
        var list = new LinkedList();
        assertEquals(0, list.size());

        list.push("a");
        assertEquals(1, list.size());

        list.push("b");
        assertEquals(2, list.size());
    }

    @Test
    void pop_emptyList() {
        var list = new LinkedList();
        assertThrows(RemoveFromEmptyListException.class, () -> list.pop());
    }

    @Test
    void pop_decreaseSize() {
        var list = new LinkedList();
        list.push("a");
        list.push("b");
        list.push("c");
        assertEquals(3, list.size());

        list.pop();
        assertEquals(2, list.size());

        list.pop();
        assertEquals(1, list.size());
    }

    @Test
    void pop_checkLastValue() {
        var a = "a";
        var b = "b";
        var c = "c";

        var list = new LinkedList();
        list.push(a);
        list.push(b);
        list.push(c);

        assertEquals(c, list.pop());
        assertEquals(b, list.pop());
        assertEquals(a, list.pop());
    }

    @Test
    void insertAfter_cannotPutNullValues() {
        var value = "test";
        var list = new LinkedList();
        list.push(value);

        assertThrows(NullValueException.class, () -> list.insertAfter(null, value));
    }

    @Test
    void insertAfter_notFound() {
        var list = new LinkedList();
        assertThrows(ValueNotFoundException.class, () -> list.insertAfter("value", null));
        assertThrows(ValueNotFoundException.class, () -> list.insertAfter("value", "nonExistingValue"));
    }

}
