import java.nio.file.Path;

public class FileSearch {

	private Path filePath;
	private String searchedWord;
	
	public FileSearch(Path newFilePath, String newWord) {
	
		this.filePath = newFilePath;
		this.searchedWord = newWord;
	}
	
	
	void read(){
		
		try(LineReader lr = new LineReader(filePath)){
			
			String line;
			while((line = lr.getNextLine()) != null){
					
					if(line.contains(searchedWord)){
					System.out.println(line);
				}
			}
		}
		
		catch (Exception e){
			
			throw new RuntimeException(e);
		}
	}
}
