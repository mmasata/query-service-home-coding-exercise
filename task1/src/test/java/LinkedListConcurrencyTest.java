import com.mmasata.utils.LinkedList;
import com.mmasata.utils.List;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListConcurrencyTest {

    private static final int DEFAULT_SIZE = 10_000;

    private final ExecutorService threadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @Test
    void threadSafetyComplex() {
        var list = initializeDefaultSizeList();
        var iterations = 10_000; //the number must be even (so that every push omits a pop operation)
        var operationSwitcher = new AtomicBoolean(true);

        //concurrently switch between pop and push
        for (int i = 0; i < iterations; i++) {
            threadExecutor.submit(getPopOrPush(operationSwitcher, list));
        }

        waitUntilAllThreadsAreDone();
        assertEquals(DEFAULT_SIZE, list.size());
    }

    @Test
    void threadSafetyPush() {
        var list = new LinkedList();

        //concurrently push to list
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            threadExecutor.submit(doPush(list));
        }

        waitUntilAllThreadsAreDone();
        assertEquals(DEFAULT_SIZE, list.size());
    }

    @Test
    void threadSafetyPop() {
        var list = initializeDefaultSizeList();
        var expectedSize = list.size() - DEFAULT_SIZE;

        //concurrently pop from list
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            threadExecutor.submit(doPop(list));
        }

        waitUntilAllThreadsAreDone();
        assertEquals(expectedSize, list.size());
    }

    @Test
    void threadSafetyInsertAfter() {
        var insertAfter = UUID.randomUUID().toString();
        var list = new LinkedList();
        list.push(insertAfter);

        var expectedSize = list.size() + DEFAULT_SIZE;

        //concurrently insertAfter to list
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            threadExecutor.submit(doInsertAfter(insertAfter, list));
        }

        waitUntilAllThreadsAreDone();
        assertEquals(expectedSize, list.size());
    }

    private void waitUntilAllThreadsAreDone() {
        threadExecutor.shutdown();
        try {
            threadExecutor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List initializeDefaultSizeList() {
        var list = new LinkedList();
        //init list
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            list.push(UUID.randomUUID().toString());
        }
        return list;
    }

    private Runnable doInsertAfter(String after, List list) {
        return () -> list.insertAfter(UUID.randomUUID().toString(), after);
    }

    private Runnable doPop(List list) {
        return () -> list.pop();
    }

    private Runnable doPush(List list) {
        return () -> list.push(UUID.randomUUID().toString());
    }

    private Runnable getPopOrPush(AtomicBoolean atomicBoolean, List list) {
        var result = atomicBoolean.get()
                ? doPush(list)
                : doPop(list);

        atomicBoolean.set(!atomicBoolean.get());
        return result;
    }

}
