import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class LineReader implements AutoCloseable{

	private BufferedReader br;

	public LineReader(Path filePath) throws IOException {
		
		 this.br = new BufferedReader(new FileReader(filePath.toString()));

	}

	public String getNextLine() throws IOException{
		
		String nextLine = this.br.readLine();
		return nextLine;
	}

	@Override
	public void close() throws Exception {
		
		br.close();
	}
	
	
}
