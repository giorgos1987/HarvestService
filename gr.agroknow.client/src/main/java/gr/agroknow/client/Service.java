package gr.agroknow.client;




import javax.ws.rs.Path;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;






@Path("/service/")
public class Service {
	 long currentId = 123;
	 Map<Long, Harvest> harvests = new HashMap<Long, Harvest>();
	
	
	 
	 

	@POST
	    @Path("/harvest/")//@Consumes(value={"application/xml"})
	    public Response addHarvest(Harvest harvest ) throws IOException, InterruptedException {
		 	
		    if ((String)harvest.getPassword() == "gpap"){
	        	
		    	//add to list 
		    	
		    	harvest.setId(++currentId);//
		    	System.out.println("---- Harvester id is: " + currentId);
		    	harvests.put(harvest.getId(),harvest);
		    	
		    	FileWriter fstream = new FileWriter("out.txt");//C:\\harvest\\
    	        BufferedWriter out = new BufferedWriter(fstream);
		    	
				try {
					
					Process	ps = Runtime.getRuntime().exec(new String[]{"java","-jar","harvester.jar",harvest.getUrl(),harvest.getDirectory(),harvest.getPrefix()});
					ps.waitFor();
					System.out.println("process created");
					java.io.InputStream is = ps.getInputStream();
			    	// byte b[] = new byte[is.available()];
			    	 
			    	 
			         for (int i = 0; i < is.available(); i++) {
			        	harvest.setStatus("pending");
			            System.out.println("" + is.read());
			            out.write(is.read());
			            
			         }
			         
			         out.close();
			    	// wait for 10 seconds and then destroy the process
			         Thread.sleep(10000);
			         harvest.setStatus("completed");
			         ps.destroy();
			    	 
			         return  Response.ok().type("application/xml").entity(harvest).build();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					  harvest.setStatus("broken");
					return Response.notModified().build();	
				}
		    	
		    	 
		    //	return  Response.ok().type("application/xml").entity(harvest).build();
	        }else{        
	         
	        	 
				 return Response.notModified().build();	
	        }
	  
	    
		   
	       
	    }
	 	
	 
	 
	   @GET
	    @Path("/harvest/{id}/")
	    @Produces(value={"application/xml"})
	    @Consumes(value={"application/xml"})
	    public Harvest getUser(@PathParam("id") String id) {
	        long idNumber = Long.parseLong(id);	        
	        
	       
	        	Harvest h = harvests.get(idNumber);
	        	
	        	
	  
	        return h;
	    }
	
	
}
