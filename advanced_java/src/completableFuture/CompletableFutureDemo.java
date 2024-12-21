package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // Creating a CompletableFuture
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Simulating a long-running task
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42; // The result of the computation
        });

        // This line will run immediately without waiting for the CompletableFuture to complete
        System.out.println("Doing other work...");

        // Getting the result (blocking call)
        future.thenAccept(result -> {
            System.out.println("The result is: " + result + 4);
        });

        // Wait for the future to complete before exiting
        future.join();
    }
}

