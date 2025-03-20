package ejb;

import nl.duckacademy.common.ejb.HelloWorldEJBRemote;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * The ejb-remote quickstart functional testing.
 * @author emartins
 *
 */
public class HelloWorldEJBRemoteTest {

    private static final String DEFAULT_SERVER_HOST = "http://localhost:8080";

    private String getProviderURl() {
        final String serverHost = System.getProperty("server.host");
        return "remote+" + (serverHost != null ? serverHost : DEFAULT_SERVER_HOST);
    }

    private String getEJBBaseJndiName() {
        return "ejb:/server";
    }

    @Test
    public void testCalculator() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
        jndiProperties.put(Context.PROVIDER_URL, getProviderURl());
        final Context context = new InitialContext(jndiProperties);
        // The JNDI lookup name for a stateless session bean has the syntax of:
        // ejb:<appName>/<moduleName>/<distinctName>/<beanName>!<viewClassName>
        //
        // <appName> The application name is the name of the EAR that the EJB is deployed in
        // (without the .ear). If the EJB JAR is not deployed in an EAR then this is
        // blank. The app name can also be specified in the EAR's application.xml
        //
        // <moduleName> By the default the module name is the name of the EJB JAR file (without the
        // .jar suffix). The module name might be overridden in the ejb-jar.xml
        //
        // <distinctName> : EAP allows each deployment to have an (optional) distinct name.
        // This example does not use this so leave it blank.
        //
        // <beanName> : The name of the session been to be invoked.
        //
        // <viewClassName>: The fully qualified classname of the remote interface. Must include
        // the whole package name.
        String name = getEJBBaseJndiName() + "/HelloWorldEJBRemoteImpl!" + HelloWorldEJBRemote.class.getName();
        System.out.println(name);
        final HelloWorldEJBRemote helloWorldEJBRemote = (HelloWorldEJBRemote) context.lookup(name);
        System.out.println("Obtained a remote stateless calculator for invocation");
        System.out.println(helloWorldEJBRemote.helloWorld("test"));
    }
}