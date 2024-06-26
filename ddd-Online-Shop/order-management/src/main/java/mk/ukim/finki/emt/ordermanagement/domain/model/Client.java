package mk.ukim.finki.emt.ordermanagement.domain.model;

import mk.ukim.finki.emt.ordermanagement.domain.valueObject.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

public class Client {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public Client(@Value("${app.product-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Product> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/product").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Product>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
