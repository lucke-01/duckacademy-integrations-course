package nl.duckacademy.controller;


import jakarta.ejb.EJB;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import nl.duckacademy.common.ejb.HelloWorldEJBRemote;

@Stateless
@Path("hello")
public class HelloWorldController {

    @EJB(lookup = "java:global/server/HelloWorldEJBRemoteImpl!nl.duckacademy.common.ejb.HelloWorldEJBRemote")
    private HelloWorldEJBRemote helloWorldEJBRemote;

    @GET
    @Produces("application/json")
    public String helloWorld() {
        return helloWorldEJBRemote.helloWorld("test");
    }


}
