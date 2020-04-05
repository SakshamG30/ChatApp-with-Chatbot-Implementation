// Indentify every user uniquely
    var uniqueId = Math.random().toString(36).substring(2) + (new Date()).getTime().toString(36);
    var chatbutton = document.getElementById("chatbot");
    $( document ).ready(function() {
        chatbutton.value="Activate";
    });
    function press() {
  	  if(chatbutton.value=="Activate")
  		  {
  		      chatbutton.value="Disable";
  		      chatbutton.style="background-color : purple;";
  		      return false;
  		  }
  	  else if(chatbutton.value=="Disable")
		  {
		      chatbutton.value="Activate";
		      chatbutton.style="background-color : green;";
		      return false;
		  }
  
         
    
  	  
    }
    $("#startChating").click(function() {

        if( $("#userName").val() ) { // if user provides username
           $("#chatName").hide();
           $(".hideForm").show();
        }
    });
    $("#submitMessage").click(function() {

        var userName = $("#userName").val();
        var message  = $("#message").val();
        var botstatus = $("#chatbot").val();
     $.post("message.action", {
             message: message,
             userName: userName,
             uniqueId: uniqueId,
             botstatus: botstatus
         })
         .done(function(data) {
             //empty the message input
             $("#message").val("");
         });
      
     });
    var pusher = new Pusher('3dad45b784a207d5a50f', {// Replace with your PUSHER_APP_KEY
        cluster: 'ap2', // Replace with your PUSHER_APP_CLUSTER
        encrypted: true
      });

      var channel = pusher.subscribe('struts-chat');
      channel.bind('message', function(data) {

       var textDirection = (data.uniqueId == uniqueId) ? " text-right" : "";

        $("#msgItems").append(
              `<div id="chat-item" class="row` +textDirection+ `">
        <div class="cols-xs-4">
              <p>
                  <p><b>` +data.userName+ `</b></p><img src="./thumbnail.png" style="width:25px;height:25px;" class="img-circle img-responsive">`
                  +data.message+ `
              </p>

        </div>
    </div>`
        );
      });
     
