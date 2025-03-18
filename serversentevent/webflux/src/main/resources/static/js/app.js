function doEvent(type) {
    var path = type == "sse" ? "stream-sse" : "stream-flux";
    if(typeof(EventSource) !== "undefined") {
      var source = new EventSource("http://localhost:8080/notify/" + path);
      source.onopen = function() {
        console.log("open");
        document.getElementById("header").innerHTML = "Getting server updates";
      };

      source.onmessage = function(event) {
        console.log("message");
        document.getElementById("content").innerHTML += event.data + "<br>";
      };
    } else {
      document.getElementById("content").innerHTML = "Sorry, your browser does not support server-sent events...";
    }
}