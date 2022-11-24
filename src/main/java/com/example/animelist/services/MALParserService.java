package com.example.animelist.services;

import com.example.animelist.MALDTOs.Data;
import com.example.animelist.MALDTOs.Node;
import com.example.animelist.models.Anime;
import com.example.animelist.repositories.AnimeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;

@Component
public class MALParserService {
    private final String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU3ZmIxNDVkMjk0ZGYyODYxYjUxYzRiN2NmN2E5YjAzYTE0MzNhMzlhZTIxMjZkYjc3MDllNjU1MTBlODM4Y2FkMDkwNWRlZjZkOTMyZWUxIn0.eyJhdWQiOiI2MTE0ZDAwY2E2ODFiNzcwMWQxZTE1ZmUxMWE0OTg3ZSIsImp0aSI6IjU3ZmIxNDVkMjk0ZGYyODYxYjUxYzRiN2NmN2E5YjAzYTE0MzNhMzlhZTIxMjZkYjc3MDllNjU1MTBlODM4Y2FkMDkwNWRlZjZkOTMyZWUxIiwiaWF0IjoxNjY3Mzg4OTIyLCJuYmYiOjE2NjczODg5MjIsImV4cCI6MTY2OTk4NDUyMiwic3ViIjoiMTU4MTI5NzEiLCJzY29wZXMiOltdfQ.ZDDbr9-doiEGgfYJ1r88ifzlubGy9hgM7l9I7LF7jp4RktwIjnJMCEkATpNCqy7CblWzUAbsK6xx4SEonQD8aTetfyd4ooU23RKGf-MJoUcJg_sYhOkGal-O9WjTwsfiesh0Vm3GFkoxhJCLEa6vaXI3RH2BeirH4QjlSUU79B09yaVCF5KgRnR7H9bxBH0GhfLmFAXBCMwBI6RHRNsDqt9gEDLkXFPoDBhMcWfnTfBOs-yJxRq2DaSCJeXUDJbfugw8iMqF6ga3YdySlv3DrTiYiGmhe2446wVyt35myflG53g5T9rptCcSZrsvjqagUmo8hp1MX9a0uyDOu9HgyA";

    private final AnimeRepository animeRepository;

    @Autowired
    public MALParserService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Boolean sendRequest(Integer count) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.myanimelist.net/v2/anime?q=An+Anime+Title&limit=%s&offset=0&fields=status,start_date,end_date,genres,num_episodes".formatted(count)))
                .setHeader("X-MAL-Client-ID", "6114d00ca681b7701d1e15fe11a4987e")
                .setHeader("Authorization", "Barer " + accessToken)
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response.body());
            String data = jsonNode.get("data").toString();
            List<Data> dataAnimes = objectMapper.readValue(data, new TypeReference<>() {
            });
            SaveAnimes(dataAnimes);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void SaveAnimes(List<Data> animes) {
        for (Data dataAnime : animes) {
            Node anime = dataAnime.node;
            HashSet<String> genres = new HashSet<>(anime.genres.stream().map(x -> x.name).toList());

            Anime ourAnime = new Anime(anime.title,
                    anime.status,
                    anime.start_date,
                    anime.end_date,
                    anime.main_picture.large,
                    anime.num_episodes,
                    genres);

            if (!animeRepository.existsAnimeByName(anime.title))
                animeRepository.save(ourAnime);
        }
    }
}
