package nl.duckacademy.wsclient.config;

import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;


public class AuthenticationClientInterceptor extends ClientInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        try {
            // Access the SOAP message from the context
            SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
            SOAPHeader header = soapMessage.getSaajMessage().getSOAPHeader();

            if (header == null) {
                header = soapMessage.getSaajMessage().getSOAPPart().getEnvelope().addHeader();
            }

            // Create and add your custom header (e.g., Authorization header)
            Name headerName = soapMessage.getSaajMessage().getSOAPPart()
                    .getEnvelope()
                    .createName("Authorization", "auth", "http://duckacademy.nl/springsoap/");
            SOAPHeaderElement headerElement = header.addHeaderElement(headerName);
            headerElement.setTextContent("ValidAuthToken"); // Replace with actual token logic

            return true; // Proceed with the request
        } catch (SOAPException e) {
            return false;
        }
    }
}
