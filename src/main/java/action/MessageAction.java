package action;

	
	import com.Chatbot;
	import action.text2speech;
	import com.TestSearch;
import com.pusher.rest.Pusher;
import com.SendEmail;

import java.util.LinkedHashMap;
    import java.util.Map;

import org.alicebot.ab.Chat;
import org.apache.commons.lang.StringUtils;

    public class MessageAction extends Chatbot{

         static String botName = "saksham";

		private Map<String, String> data = new LinkedHashMap<String, String>();
		private Map<String, String> data2 = new LinkedHashMap<String, String>();
		private Map<String, String> data3 = new LinkedHashMap<String, String>();

         private String message, userName, uniqueId, botstatus;

         public String execute() {
        	 String response = "This is an automated message";
        	 //Pusher pusher = new Pusher("app_id", "key", "secret");
             Pusher pusher = new Pusher("890262", "3dad45b784a207d5a50f", "6f49232e3060b3671e8d");
             pusher.setCluster("ap2"); 
             pusher.setEncrypted(true);

             data.put("message", this.getMessage());
             data.put("userName", this.getUserName());
             data.put("uniqueId", this.getUniqueId());
               
             pusher.trigger("struts-chat", "message", data);
             
			    Chat chatSession = getchatSession();
			if(botstatus.equalsIgnoreCase("Disable"))
			{
				message= message + "@bot";
			}
             
        	 if(message.contains("@bot"))
        	 {
        		 message = message.replace("@bot", "");
        		 try {
        			 
        				String request = message;
        						response = getResponse(request, chatSession);
        						
        		                   String request2 = response,input=message;
        		                   message = message.replace("?", "");
        		                   if(input.contains("?"))
        		                   response = response.replace(message, "");
        		                   //response = response.replaceAll("(.)([A-Z])", "$1 $2");
        			               data2.put("message", response);
        			               data2.put("userName", "ChatBot");
        			               data2.put("uniqueId", "1223");
                                   //System.out.println(response);
        			               pusher.trigger("struts-chat", "message", data2);
        			               
        			               if(request2.contains(message) || message.contains("search"))
                                   {
        			            	   message = message.replace("search ", "");
        			            	   if(message.contains("weather") || message.contains("Weather"))
        			            		   message="weather";
                                	   String result = TestSearch.searchResult(message);
                                       data3.put("message", result);
            			               data3.put("userName", "ChatBot");
            			               data3.put("uniqueId", "1223");

            			               pusher.trigger("struts-chat", "message", data3);
            			               if(input.contains("search")) {
            			            	   String[] parts = result.split("<br>");
                			               
                			               if(parts[0]=="")
                			            	   text2speech.speech(result);
                			               text2speech.speech(parts[0]);
            			               }
            			               
            			               
                                   }
        			               if(input.contains("transcript")||input.contains("Transcript"))
        			               {
        			            	   String to = getResponse("What is my email",chatSession);
        			            	   String tbody = getResponse("transcript",chatSession);
        			            	   //body = body.replaceAll("(.)([A-Z])", "$1 $2");
        			            	   //body = body + " \0";
        			       			   String body = StringUtils.substringBetween(tbody, "<body>","</body>");
        			       			   String subject = StringUtils.substringBetween(tbody, "<subject>","</subject>");
        			       			   //body= "Transcript " + body;
        			       			   SendEmail.sendMail(to,body,subject);
        			               }
                      }
                      catch (Exception e) {
              			e.printStackTrace();
              		}
        					
        					
        			
        			
        	 }
        	 if(message.contains("@search"))
        	 {
        		 message = message.replace("@search", "");
        		 try {
                     
        		              String result = TestSearch.searchResult(message);
        		              if(result=="")
        		            	  result= "Sorry, I can't find the results";
        		              
                                   data2.put("message", result);
        			               data2.put("userName", "ChatBot");
        			               data2.put("uniqueId", "1223");

        			               pusher.trigger("struts-chat", "message", data2);
        			               String[] parts = result.split("<br>");
        			               
        			               if(parts[0]=="")
        			            	   text2speech.speech(result);
        			               text2speech.speech(parts[0]);
                      }
                      catch (Exception e) {
              			e.printStackTrace();
              		}
        					
  }
        	

        	
             

               return "SUCCESS";
        	 
          }
    
        
         public String getBotstatus() {
			return botstatus;
		}

		public void setBotstatus(String botstatus) {
			this.botstatus = botstatus;
		}

		public Map<String, String> getData() {
              return data;
          }

          public void setData(Map<String, String> data) {
             this.data = data;
          }
          public Map<String, String> getData2() {
              return data2;
          }

          public void setData2(Map<String, String> data2) {
             this.data2 = data2;
          }

          public String getUniqueId() {
             return uniqueId;
          }

          public void setUniqueId(String uniqueId) {
             this.uniqueId = uniqueId;
           }

           public String getUserName() {
              return userName;
           }

           public void setUserName(String userName) {
              this.userName = userName;
           }

           public String getMessage() {
              return message;
           }

           public void setMessage(String message) {
              this.message = message;
           }
  }

    


