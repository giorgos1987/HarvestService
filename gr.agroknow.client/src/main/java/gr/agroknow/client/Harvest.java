package gr.agroknow.client;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

//import java.io.Serializable;


@XmlRootElement(name = "harvest")
public class Harvest {
	
	private String url;
	private String directory;
	private String prefix;
	private String password;
	private long id;
	private String status;
	
	
	
	
	public void setUrl(String url){
		this.url = url;
		
		
	}
	
	public String getUrl(){
		return url;
		
	}
	
	public void setDirectory(String directory){
		this.directory = directory;
		
		
	}
	
	public String getDirectory(){
		return directory;
		
	}
	
	public void setPrefix(String prefix){
		this.prefix = prefix;
		
		
	}
	
	public String getPrefix(){
		return prefix;
		
	}
	
	public void setPassword(String password){
		this.password = password;
		
		
	}
	
	public String getPassword(){
		return password;
		
	}
	
	public void setId(long id){
		this.id = id;
		
		
	}
	
	public long getId(){
		return id;
		
	}
	
	public void setStatus(String status){
		this.status = status;
		
		
	}
	
	public String getStatus(){
		
		return status;
		
	}
	
	
	
}
