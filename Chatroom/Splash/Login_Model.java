package Splash;

public class Login_Model {
	private ServiceLocator servicelocator;
	

	public void send(String senden) {
		 try {
			 servicelocator = ServiceLocator.getServiceLocator();
			  servicelocator.getConfiguration().getWriter().write(senden);
				servicelocator.getConfiguration().getWriter().write("\n");
				servicelocator.getConfiguration().getWriter().flush();
				Thread.sleep(100);
			
			  }catch (Exception msg){
				  msg.printStackTrace();
			  }
		
	}

	
	
	

	
	
	

	

}
