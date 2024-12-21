package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CominingFutures {
public static void main(String args[]) {
	CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 20;
	});
	
	CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(true) throw new RuntimeException("something went wrong!!!");
		return 25;
	});
	
	CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1,result2)-> result1+result2);
	
	combinedFuture
		.exceptionally(ex-> {System.out.println(ex.getMessage());
					return 0;
		})
		.thenAccept(System.out::println);

	combinedFuture.join();
}
}
