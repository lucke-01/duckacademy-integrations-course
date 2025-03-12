package nl.duckacademy;

import nl.duckacademy.gen.client.GetCountryResponse;
import nl.duckacademy.wsclient.CountryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebServiceClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceClientApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";

            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = countryClient.getCountry(country);
            System.out.println("RESPONSE:");
            System.out.println(response.getCountry().getName());
        };
    }
}
