package nl.duckacademy.common.ejb;

import jakarta.ejb.Remote;

@Remote
public interface HelloWorldEJBRemote {
    public String helloWorld(String name);
}
