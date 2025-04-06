package nl.duckacademy.wsclient.config;

import jakarta.xml.soap.*;
import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
public class AuthenticationClientInterceptor extends ClientInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        try {
            // Access the SOAP message from the context
            final SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
            final SOAPMessage soapSaajMessage = soapMessage.getSaajMessage();
            final SOAPEnvelope soapEnvelope = soapMessage.getSaajMessage().getSOAPPart().getEnvelope()
            SOAPHeader header = soapSaajMessage.getSOAPHeader();

            if (header == null) {
                header = soapEnvelope.addHeader();
            }

            // Create and add your custom header (e.g., Authorization header)
            Name headerName = soapEnvelope
                    .createName("Authorization", "auth", "http://duckacademy.nl/springsoap/");
            SOAPHeaderElement headerElement = header.addHeaderElement(headerName);
            headerElement.setTextContent("ValidAuthToken"); // Replace with actual token logic

            return true; // Proceed with the request
        } catch (SOAPException e) {
            return false;
        }
    }
}