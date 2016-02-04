import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

	private Path filePath;
	private BlockingQueue<String> queue;
	public Producer(Path newFilePath, BlockingQueue<String> newQueue) {
	
		this.filePath = newFilePath;
		this.queue = newQueue;
	}
	
	
	public void run(){
		
		try(LineReader lr = new LineReader(filePath)){
			
			String line;
			int counter = 0;
			while((line = lr.getNextLine()) != null){
					
				String newStr = filePath.getFileName() + " " + line + " " +  counter;
				counter++;
				
				queue.put(newStr);
				if(queue.size() > 995){
					
					System.out.println("over");
				}
			}
		}
		
		catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}
}
