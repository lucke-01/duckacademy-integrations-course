package nl.duckacademy;


import nl.duckacademy.common.ejb.HelloWorldEJBRemote;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBClient {
    public static void main(String[] args) {
        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
            jndiProps.put("jboss.naming.client.ejb.context", true);
            jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

            Context context = new InitialContext(jndiProps);

            // Perform a lookup for the EJB
            String jndiName = "ejb:/server/HelloWorldEJBRemoteImpl!nl.duckacademy.common.ejb.HelloWorldEJBRemote";
            HelloWorldEJBRemote helloWorldEJB =
                    (HelloWorldEJBRemote) context.lookup(jndiName);

            // Call a method on the EJB
            String message = helloWorldEJB.helloWorld("Rick");
            System.out.println("EJB Response: " + message);

        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the EJB.");
        }
    }
}