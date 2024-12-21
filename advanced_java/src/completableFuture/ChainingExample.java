package completableFuture;

import java.util.concurrent.CompletableFuture;

public class ChainingExample {
    public static void main(String[] args) {
    	CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10; // Initial value
        });

        // Chaining tasks
        CompletableFuture<Integer> result = future
            .thenApply(value -> value + 2) // Doubles the value
            .thenApply(value -> value * 10); // Adds 10 to the doubled value
      
        // Getting the final result
        result.thenAccept(finalValue -> {
            System.out.println("Final result: " + finalValue); // Outputs: Final result: 30
        });

        // Wait for completion
        result.join();
    }
}

