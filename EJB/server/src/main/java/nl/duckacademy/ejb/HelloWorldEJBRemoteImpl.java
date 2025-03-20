package nl.duckacademy.ejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import nl.duckacademy.common.ejb.HelloWorldEJBRemote;

@Remote(HelloWorldEJBRemote.class)
@Stateless
public class HelloWorldEJBRemoteImpl implements HelloWorldEJBRemote {
    public String helloWorld(String name) {
        System.out.println("hello world");
        return "hello, "+ name;
    }
}
