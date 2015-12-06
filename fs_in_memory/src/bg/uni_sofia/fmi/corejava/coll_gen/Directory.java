package bg.uni_sofia.fmi.corejava.coll_gen;

import java.util.HashMap;
import java.util.Map;

public class Directory extends FileSystemObject{

	private String name;
	private Map<String,FileSystemObject> elements;
	
	public Directory(String name, Directory parentDirectory){
		
		super(name, parentDirectory);
		this.elements = new HashMap<>();
	}

	public Directory mkdir(String subDirName) throws FileSystemObjectAlreadyExists{
		
		Directory newDir = new Directory(subDirName, this);
		this.addToElement(newDir);
		
		return newDir;
	}
	
	public File creat(String fileName) throws FileSystemObjectAlreadyExists{
		
		File newFile = new File(fileName, this);
		this.addToElement(newFile);
		
		return newFile;
	}
	
	
	private void addToElement(FileSystemObject fsObj) throws FileSystemObjectAlreadyExists{
		
		if(this.elements.containsKey(fsObj.getName())){
			
			throw new FileSystemObjectAlreadyExists(fsObj.getName());
		}
		this.elements.put(fsObj.getName(),fsObj);
		
	}
	public File getFile(String fileName) throws FileSystemObjectNotFound{
	
		FileSystemObject fsObj = this.getSubElement(fileName);
		if(fsObj instanceof File){
			
			return (File)fsObj;
		}
		
		throw new FileSystemObjectNotFound(fileName);
	}
	
	public Directory getDir(String fileName) throws FileSystemObjectNotFound{
		
		FileSystemObject fsObj = this.getSubElement(fileName);
		if(fsObj instanceof Directory){
			
			return (Directory)fsObj;
		}
		
		throw new FileSystemObjectNotFound(fileName);
	}
	
	public FileSystemObject getSubElement(String subElName) throws FileSystemObjectNotFound{
		
		FileSystemObject subEl = this.elements.get(subElName);
		if(subEl != null){
			
			throw new FileSystemObjectNotFound(subElName);
		}
	
		return subEl;
		}
	
	void unlink(FileSystemObject fsObj){
		
		this.elements.remove(fsObj.getName());
	}

	
	@Override
	public void delete() throws DirectoryNotEmpty {
	
		if(!this.elements.isEmpty()){
			
			throw new DirectoryNotEmpty(this.name);
		}
		
		super.delete();
	}
}
