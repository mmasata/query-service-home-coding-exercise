import com.mmasata.utils.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListConcurrencyTest {

    private final ExecutorService threadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    @Test
    void threadSafetyPushTest() throws InterruptedException {
        var defaultSize = 10_000;
        var list = new LinkedList();

        //concurrently push to list
        for (int i = 0; i < defaultSize; i++) {
            int valueToPush = i;
            threadExecutor.submit(() -> list.push(valueToPush));
        }

        //wait until all threads are done
        threadExecutor.shutdown();
        threadExecutor.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(defaultSize, list.size());
    }

    @Test
    void threadSafetyPopTest() throws InterruptedException {
        var defaultSize = 10_000;
        var list = new LinkedList();

        //init list
        for (int i = 0; i < defaultSize; i++) {
            list.push(i);
        }

        //concurrently pop from list
        for (int i = 0; i < defaultSize; i++) {
            threadExecutor.submit(() -> list.pop());
        }

        //wait until all threads are done
        threadExecutor.shutdown();
        threadExecutor.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(0, list.size());
    }

}
