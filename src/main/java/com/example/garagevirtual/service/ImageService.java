/** Clasa integreaza API-ul Pexels pentru a obtine imagini pentru vehicule.
 * @author Diaconita Adrian
 * @version 12 Ianuarie 2024
 */

package com.example.garagevirtual.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ImageService {

    // URL-ul API-ului Pexels
    private final String API_URL = "https://api.pexels.com/v1/search";
    // Cheia API-ului Pexels
    private final String API_KEY = "0vZBaNIqvjcAf95yJj4EGpKNAiJ8eENagvKUjHc1OBrqiQR8grEcwA3Q";

    // Metoda pentru obtinerea URL-ului imaginii pentru un vehicul
    public String getCarImageUrl(String marca) {
        // Transformarea numelui marcii intr-un query
        String query = (marca).replaceAll(" ", "-");

        // Construirea header-elor
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", API_KEY);

        // Construirea cererii
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = API_URL + "?query=" + query + "&per_page=1";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PexelsResponse> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PexelsResponse.class
            );

            // Extrage URL-ul primei imagini
            if (response.getBody() != null && response.getBody().getPhotos() != null &&
                    !response.getBody().getPhotos().isEmpty()) {
                System.out.println(response.getBody().getPhotos().get(0).getSrc().getMedium());
                return response.getBody().getPhotos().get(0).getSrc().getMedium();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/images/placeholder.png";
    }

    // Clasă pentru deserializarea răspunsului de la Pexels
    static class PexelsResponse {
        private List <Photo> photos;

        public List<Photo> getPhotos() {
            return photos;
        }

        public void setPhotos(List<Photo> photos) {
            this.photos = photos;
        }

        static class Photo {
            private Src src;

            public Src getSrc() {
                return src;
            }

            public void setSrc(Src src) {
                this.src = src;
            }
        }

        static class Src {
            private String medium;

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}