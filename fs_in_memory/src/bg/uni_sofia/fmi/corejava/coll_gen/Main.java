package bg.uni_sofia.fmi.corejava.coll_gen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {

		public static void main() throws Exception{
			
			RootDirectory root = new RootDirectory();
			
			Directory subDir = root.mkdir("subdir");
			
			
			File file = subDir.creat("alabala");
			file.setContent("portokala");
			
			File file2 = subDir.creat("alabala");
			file2.setContent("portokala");
			
			Set<File> filesToDelete = new HashSet<>();
			filesToDelete.add(file);
			filesToDelete.add(file2);
			
			deleteFileSystemObjects(filesToDelete);
		}

		private static void deleteFileSystemObjects(Collection< ? extends FileSystemObject> objectsToDelete)
		throws DirectoryNotEmpty{
				
			for(FileSystemObject fsObj: objectsToDelete){
				
				fsObj.delete();
			}
		}

}
