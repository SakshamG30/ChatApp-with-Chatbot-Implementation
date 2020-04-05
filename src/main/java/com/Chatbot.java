package com;



import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.apache.commons.lang.StringUtils;
public class Chatbot {
	private static final boolean TRACE_MODE = false;
	static String botName = "saksham";
	static String resourcesPath = getResourcesPath();
	static Bot bot = new Bot("saksham", resourcesPath);
	static Chat chatSession = new Chat(bot);
	private static final Chat ch2 = chatSession;

	@SuppressWarnings("null")
	public static void main(String[] args) {
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("saksham", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			String message = "";
			
					
			while(true) {
				System.out.print("Human : ");
				message = IOUtils.readInputTextLine();
					String request = message;
					
					String response = getResponse(request, chatSession );
					//String to = getResponse("transcript",chatSession);
					//to = to.toString();
					//to = StringUtils.substringBetween(to, "<subject>","</subject>");
					//System.out.println(to);
					System.out.println("Robot : " + response);
				}
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	

	public static String getResourcesPath() {
		String resourcesPath = "D:/Program Files (x86)/Eclipse-Workspace/ChatApp/src/main/resources";
		return resourcesPath;
	}
	public static String getResponse(String message, Chat chatSession) {
	

   	 String response = null;
			
			String request = message;
					response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						{response = response.replace("&lt;", "<");}
					while (response.contains("&gt;"))
						{response = response.replace("&gt;", ">");}
   	  		
   	 
   
   	return response;
    }
	
	public Chat getchatSession()
	{
		
		bot.brain.nodeStats();
		return ch2;
	}

}
