function doEvent() {
    console.log("EEEEOo")
    const evtSource = new EventSource("http://localhost:8080/notify/events");
    const eventList = document.querySelector("ul");

    evtSource.onmessage = (e) => {
        console.log(e);
        const newElement = document.createElement("li");

        newElement.textContent = `message: ${e.data}`;
        eventList.appendChild(newElement);
    };
}
function doEvent2() {
    if(typeof(EventSource) !== "undefined") {
      var source = new EventSource("http://localhost:8080/notify/stream-flux");
      source.onopen = function() {
        console.log("open");
        document.getElementById("header").innerHTML = "Getting server updates";
      };

      source.onmessage = function(event) {
        console.log("message");
        document.getElementById("content").innerHTML += event.data + "<br>";
      };
    } else {
      document.getElementById("myDIV").innerHTML = "Sorry, your browser does not support server-sent events...";
    }
}