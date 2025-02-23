var socket = new WebSocket("ws://localhost:8080/helloworld");
socket.addEventListener('open', (event) => {
    console.log("Connection open");
});
//enviar mensajes al servidor
function helloWebsocket() {
    console.log("saludo");
    socket.send("Hello Server!");
}
//leer mensajes del servidor
socket.addEventListener('message', (event) => {
    console.log("Message from server"+ event.data);
});

function disconnect() {
    if (socket != null) {
        socket.close();
    }
}