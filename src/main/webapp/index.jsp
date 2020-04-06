<!DOCTYPE html>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <head>
      <title>Welcome To chat menu!</title>
      <meta http-equiv="Content-Type" content="text/html; charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="assets/custom.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <script src="https://js.pusher.com/4.1/pusher.min.js"></script>
      
    </head>
    <body style="background-image: url(./wall2.png); background-repeat: no-repeat;
  background-size: cover;">
       <h1 style="text-align: center; color: white;font-family: Arial, Helvetica, sans-serif;">Welcome <span id="demo">to M@IT Chat Room</span></h1>
        <div class="container" style="border: 3px solid white;width:65%;">
          <!--msgbox-->
          <div id="msgItems" class="container-fluid" style="background-color:white;">
          </div>

                <!-- querybox-->
               
                <div class="row text-center" id="queryText">

                   <div class="hideForm">
                        <div class="row">
                            <div class="col-xs-9">
                                 <input type="text" class="form-control" placeholder="Type your Message Here" id="message">
                            </div>
                            <div class="col-xs-3">
                                <button type="button" class="btn btn-primary" style="background-color: red;" id="submitMessage">Send Message</button>
                                <input type="button" class="btn btn-primary" style="background-color: green;" id="chatbot" onclick="press()"/>
                            </div>
                        </div>
                   </div>

                   <div id="chatName">
                         <form class="form-inline">
                            <div class="form-group">
                                 <input type="text" class="form-control" id="userName" placeholder="Your Username">
                            </div>
                                 <button type="button" class="btn btn-primary" id="startChating">Start Chatting!</button>
                        </form>
                   </div>

               </div>
           

        </div>
        <script type="text/javascript">
        var input = document.getElementById("message");
        input.addEventListener("keyup", function(event) {
          if (event.keyCode === 13) {
           event.preventDefault();
           document.getElementById("submitMessage").click();
          }
        });
        var input2 = document.getElementById("userName");
        input2.addEventListener("keyup", function(event2) {
          if (event2.keyCode === 13) {
           event2.preventDefault();
           document.getElementById("startChating").click();
          }
        });
      </script>

       <script src="assets/custom.js"></script>
    </body>

    </html>