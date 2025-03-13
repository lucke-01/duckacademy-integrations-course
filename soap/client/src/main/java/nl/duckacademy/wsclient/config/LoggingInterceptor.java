package nl.duckacademy.wsclient.config;

import org.springframework.ws.client.support.interceptor.ClientInterceptorAdapter;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class LoggingInterceptor extends ClientInterceptorAdapter {

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        // Log the SOAP Request
        SaajSoapMessage request = (SaajSoapMessage) messageContext.getRequest();
        try {
            logSoapMessage(request, "Request");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true; // Proceed with the request
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {
        // Log the SOAP Response
        SaajSoapMessage response = (SaajSoapMessage) messageContext.getResponse();
        try {
            logSoapMessage(response, "Response");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return true; // Proceed with the response
    }

    private void logSoapMessage(SaajSoapMessage soapMessage, String message) throws TransformerException {
        // Transform the SOAP message into a string for logging
        StringWriter writer = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(soapMessage.getPayloadSource(), new StreamResult(writer));

        // Log the XML
        System.out.println(message);
        System.out.println(writer);
    }
}
