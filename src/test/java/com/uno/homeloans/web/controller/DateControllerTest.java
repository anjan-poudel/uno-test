package com.uno.homeloans.web.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
class DateControllerTest {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void testCalculateDays_happyPath() throws MalformedURLException {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(2);
        String spec = String.format("http://localhost:%d/date/difference?fromDate=%s&toDate=%s",
                port, DATE_FORMATTER.format(from), DATE_FORMATTER.format(to));
        URL url = new URL(spec);

        ResponseEntity<Integer> response = restTemplate.getForEntity(
                url.toString(), Integer.class);
        assertEquals(1, response.getBody());
    }

    @Test
    void testCalculateDays_badDateFormat() throws MalformedURLException {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(2);
        URL url = new URL(
                String.format("http://localhost:%d/date/difference?fromDate=%s&toDate=%s",
                        port, DATE_FORMATTER.format(from), "01.01.20122")
        );

        assertThrows(RestClientException.class, () -> {
            ResponseEntity<Integer> response = restTemplate.getForEntity(
                    url.toString(), Integer.class);
        });

    }

    //@Test
    void testCalculateDays_badDateFormat_beforeTime() throws MalformedURLException {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(2);
        URL url = new URL(
                String.format("http://localhost:%d/date/difference?fromDate=%s&toDate=%s",
                        port, "01.01.1779", "01.01.1880")
        );

        assertThrows(RestClientException.class, () -> {
            ResponseEntity<Integer> response = restTemplate.getForEntity(
                    url.toString(), Integer.class);
        });

    }
}
