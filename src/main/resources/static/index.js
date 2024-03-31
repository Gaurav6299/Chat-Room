$(document).ready((e)=>{

     $("#login").click(()=>{
        var name=$("#name").val();
        localStorage.setItem("name",name);
        $("#login_user").html(`Welcome, <b>${name}</b>`);
         connect();
     })

     $("#send-to").click(()=>{
        sendMessage();
     })

    // Logout
    $("#logout").click(()=>{
         logout()

    })

})

//logout
function logout(){
    localStorage.removeItem("name");
    $("#name").val(null)
    if(stompClient!==null){
       stompClient.disconnect();

       $("#main").removeClass('d-none');
             $("#chat_room").addClass('d-none');

    }
}


//Send Message
function sendMessage(){


   let jsonObj={
     name:localStorage.getItem("name"),
     content:$("#message-value").val()
   };

   stompClient.send("/chat/message",{},JSON.stringify(jsonObj))
   $("#message-value").val(null)

}

var stompClient=null;
function connect(){
   let socket=new SockJS("/server");
   stompClient=Stomp.over(socket);
   stompClient.connect({},function(frame){

   console.log("Conect::",frame);
      $("#main").addClass('d-none');
      $("#chat_room").removeClass('d-none');

      stompClient.subscribe("/topic/return-to",function(res){
         showMessage(JSON.parse(res.body));
      })

   })


}

//Show Message Function
function showMessage(message){

   //Calculate Time in 12 hours Format
    let time=new Date();
    var hours = time.getHours();
      var minutes = time.getMinutes();
      var ampm = hours >= 12 ? 'pm' : 'am';
      hours = hours % 12;
      hours = hours ? hours : 12; // the hour '0' should be '12'
      minutes = minutes < 10 ? '0'+minutes : minutes;
      var strTime = hours + ':' + minutes + ' ' + ampm;


    $("#message-container-table").prepend(`
          <div class="my-2 col-md-4" style="background: #a9d9a1;;margin-top: 20px;padding: 8px;border-radius: 7px;padding-left: 21px;border-bottom-right-radius: 48px; padding-bottom: 11px;">
              <b style="color:#210cb2">${message.name}  : </b> ${message.content}
              <span style="font-size: 12px;position: relative;top: 11px;left: -2px;">${strTime}</span>
          </div>

    `)
}

