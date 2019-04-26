package com.decathlon.Batch;

import java.net.URI;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BatchExtractBookingToValidate {

    public BookingRepository bookingRepository;

    
    public BatchExtractBookingToValidate(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }

    /**
     * toutes les 5 secondes
     */
    @Scheduled(fixedRate = 5000)
    //@Scheduled(cron = "0 11 21 * * *")	
    public void deleteOldBooking() {
        log.info("Starting at {}", new Date());
        
        // CODE METIER ICI
       // bookingRepository.deleteOldBookings(LocalDateTime.now());
        RestTemplate restTemplate = new RestTemplate();
        try {
            //url
            URI uri = new URI("http://localhost:8080/booking/purge");

            //vos credentials
            String plainCreds = "admin:password";
            //convertit en byte
            byte[] plainCredsBytes = plainCreds.getBytes();
            //encodé en base 64
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            //transformé en String
            String base64Creds = new String(base64CredsBytes);

            //dans les headers
            HttpHeaders headers = new HttpHeaders();
            //on rajoute nos informations de connexion Basic
            headers.add("Authorization", "Basic " + base64Creds);

            HttpEntity<String> request = new HttpEntity<String>(headers);

            restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);


        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("erreur lors de l'appel à la purge");
        }
        log.info("End at {}", new Date());
    }



}
