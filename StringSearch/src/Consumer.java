import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{

	private BlockingQueue<String> queue;
	private String word;

	public Consumer(BlockingQueue<String> newQueue, String newWord) {

		this.queue = newQueue;
		this.word = newWord;
	}

	
	public void run() {

		String line;
		try {
			while ((line = queue.take()) != null) {

				if (line.contains(word)) {
					
					System.out.println(line);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
