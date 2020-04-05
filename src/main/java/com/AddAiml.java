package com;

import org.ab.Bot;
import org.ab.MagicBooleans;

public class AddAiml {

	private static final boolean TRACE_MODE = false;
	static String botName = "saksham";

	public static void main(String[] args) {
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("saksham", resourcesPath);
			
			bot.writeAIMLFiles();
       

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getResourcesPath() {
		String resourcesPath = "D:/Program Files (x86)/Eclipse-Workspace/ChatApp/src/main/resources";
		return resourcesPath;
	}

}
