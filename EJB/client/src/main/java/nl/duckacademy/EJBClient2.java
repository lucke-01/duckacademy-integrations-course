package nl.duckacademy;

import javax.naming.InitialContext;
import jakarta.

public class EJBClient2 {
    public static void main(String[] args) {
        JndiTemplate jndiTemplate = new JndiTemplate();
        ctx = (InitialContext) jndiTemplate.getContext();
    }
}
