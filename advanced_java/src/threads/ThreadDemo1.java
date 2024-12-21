package threads;

public class ThreadDemo1 implements Runnable{

	@Override
	public void run() {
		System.out.println("Current execution thread is: " + Thread.currentThread());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		for(int i=0;i<10;i++) {
			Thread thread = new Thread(new ThreadDemo1());
			thread.start();
		}
	}
}
