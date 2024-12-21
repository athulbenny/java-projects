package threads;

public class ThreadDemo2 {

	public static void main(String[] args) {
		
		for(int i=0;i<10;i++) {
			Threading threading = new Threading();
			threading.start();
		}
	}

}

class Threading extends Thread{
	public void run() 
	{
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread()
                + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
