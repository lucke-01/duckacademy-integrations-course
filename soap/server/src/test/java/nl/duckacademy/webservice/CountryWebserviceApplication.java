package nl.duckacademy.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryWebserviceApplication {

    private static RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private Integer port;

    @Test
    void givenXmlRequest_whenServiceInvoked_thenValidResponse() throws Exception {
        String requestXML = """
                <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                    <SOAP-ENV:Header>
                        <auth:Authorization xmlns:auth="http://duckacademy.nl/springsoap/">ValidAuthToken</auth:Authorization>
                    </SOAP-ENV:Header>
                    <SOAP-ENV:Body>
                        <ns2:getCountryRequest xmlns:ns2="http://duckacademy.nl/springsoap/gen">
                            <ns2:name>Spain</ns2:name>
                        </ns2:getCountryRequest>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
                """;
        String expectedResonseXML = """
                <SOAP-ENV:Envelope
                	xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                	<SOAP-ENV:Header/>
                	<SOAP-ENV:Body>
                		<ns2:getCountryResponse
                			xmlns:ns2="http://duckacademy.nl/springsoap/gen">
                			<ns2:country>
                				<ns2:name>Spain</ns2:name>
                				<ns2:population>46704314</ns2:population>
                				<ns2:capital>Madrid</ns2:capital>
                				<ns2:currency>EUR</ns2:currency>
                			</ns2:country>
                		</ns2:getCountryResponse>
                	</SOAP-ENV:Body>
                </SOAP-ENV:Envelope>
                """;
        String url = "http://localhost:" + port + "/ws";
        RequestEntity<String> requestEntity = RequestEntity.post(url).contentType(MediaType.TEXT_XML).body(requestXML);
        ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);
        System.out.println(result);
        assertThat(result).isNotNull();
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).containsIgnoringWhitespaces(expectedResonseXML);
    }
}
