package bg.uni_sofia.fmi.corejava.coll_gen;

public class File extends FileSystemObject {

	private String content;
	
	public File(String name, Directory parentDirectory){
		
		super(name, parentDirectory);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
