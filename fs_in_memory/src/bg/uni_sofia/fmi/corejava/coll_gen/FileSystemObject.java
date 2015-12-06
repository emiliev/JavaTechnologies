package bg.uni_sofia.fmi.corejava.coll_gen;

public abstract class FileSystemObject {
 
	private String name;
	private Directory parentDirectory;
	
	public FileSystemObject(String name, Directory parentDirectory){
		
		this.name = name;
		this.parentDirectory = parentDirectory;
	}

	public void rename(String newName){
		
		this.name = newName;
	}
	
	public String getName(){
		
		return this.name;
	}
	
	public Directory getParentDirectory() {
		return parentDirectory;
	}

	@Override
	public boolean equals(Object obj) {
	
		if(obj == null){
			
			return true;
		}
		
		if(obj instanceof FileSystemObject){
			
			FileSystemObject other = (FileSystemObject) obj;
			if(this.parentDirectory == null){
				
				return other.parentDirectory == null && this.name.equals(other.getName());
			}
			
			return this.name.equals(other.getName()) && this.parentDirectory.equals(other.getParentDirectory());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		
		int parentDirHash = this.parentDirectory == null ? 0 : this.parentDirectory.hashCode();
		return 7 * this.name.hashCode() +  7 * parentDirHash;
	}
	
	
	public void delete() throws DirectoryNotEmpty{
		
		this.parentDirectory.unlink(this);
	}
}
