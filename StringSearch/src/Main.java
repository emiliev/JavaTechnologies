import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
	
		//Set<String> files = new TreeSet<>();
		Path stringSearch = Paths.get("E:/testing");
		Files.walk(stringSearch).forEach(filePath -> {
    		
    		if (Files.isRegularFile(filePath)  &&  filePath.toString().endsWith(".txt"))
            {
    			FileSearch fs = new FileSearch(filePath, "caca");
                fs.read();
            }
		});
		
		
	}


}