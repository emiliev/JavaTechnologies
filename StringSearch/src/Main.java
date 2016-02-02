import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		Path stringSearch = Paths.get("E:/testing");
		Files.walk(stringSearch).forEach(filePath -> {
    		
    		if (Files.isRegularFile(filePath)  &&  filePath.toString().endsWith(".txt"))
            {
                System.out.println(filePath.toString());
    
            }
	
		});
		
	}
}