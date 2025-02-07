 /*let ws = null;
 function connect() {
 	ws = new WebSocket('ws://localhost:8080/name');
 	ws.onmessage = function(data){
 		showGreeting(data.data);
 	}
 	 setConnected(true);
 }

 function disconnect() {
     if (ws != null) {
         ws.close();
     }
     setConnected(false);
     console.log("Disconnected");
 }

 function sendName(message) {
     ws.send(message);
 }

 function showGreeting(message) {
     alert(" " + message + "");
 }*/
 // Create a new WebSocket object
 const socket = new WebSocket('ws://localhost:8080/name');

 // Connection opened
 socket.addEventListener('open', (event) => {
     console.log('WebSocket is open now.');
     // Send a message
     socket.send('Hello Server!');
 });

 // Listen for messages
 socket.addEventListener('message', (event) => {
     console.log('Message from server:', event.data);
 });

 // Listen for errors
 socket.addEventListener('error', (event) => {
     console.error('WebSocket error observed:', event);
 });

 // Connection closed
 socket.addEventListener('close', (event) => {
     console.log('WebSocket is closed now.');
 });
  function disconnect() {
    if (socket != null) {
      socket.close();
    }
}