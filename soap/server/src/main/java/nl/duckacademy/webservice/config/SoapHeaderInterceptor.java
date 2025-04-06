package nl.duckacademy.webservice.config;

import jakarta.xml.soap.Node;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.soap.SOAPMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.interceptor.EndpointInterceptorAdapter;

import java.util.Iterator;

public class SoapHeaderInterceptor extends EndpointInterceptorAdapter {
    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
        SOAPMessage soapMessage = ((org.springframework.ws.soap.saaj.SaajSoapMessage)
                messageContext.getRequest()).getSaajMessage();
        SOAPHeader header = soapMessage.getSOAPHeader();

        if (header == null) {
            throw new RuntimeException("Missing SOAP header");
        }
        // Extract the Authorization header (or any other custom header)
        Node authorizationNode = null;
        Iterator<?> headerElements = header.examineAllHeaderElements();
        while (headerElements.hasNext()) {
            SOAPHeaderElement element = (SOAPHeaderElement) headerElements.next();
            if ("Authorization".equals(element.getLocalName())) {
                authorizationNode = element;
                break;
            }
        }

        if (authorizationNode == null) {
            throw new RuntimeException("Missing Authorization header");
        }

        // Validate the header (custom logic can go here)
        String authorizationValue = authorizationNode.getTextContent();
        if (!"ValidAuthToken".equals(authorizationValue)) {
            throw new RuntimeException("Invalid Authorization token");
        }

        return true; // Proceed if the header is valid
    }
}