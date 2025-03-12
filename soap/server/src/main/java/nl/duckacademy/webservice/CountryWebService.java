package nl.duckacademy.webservice;

import nl.duckacademy.repository.CountryRepository;
import nl.duckacademy.springsoap.gen.GetCountryRequest;
import nl.duckacademy.springsoap.gen.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryWebService {
    private static final String NAMESPACE_URI = "http://duckacademy.nl/springsoap/gen";

    private final CountryRepository countryRepository;

    public CountryWebService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
