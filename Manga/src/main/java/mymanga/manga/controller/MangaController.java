package mymanga.manga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mymanga.manga.messagebroker.RabbitMQProducer;
import mymanga.manga.model.Manga;
import mymanga.manga.service.MangaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("manga")
@RestController
public class MangaController {

    @Autowired
    private RestTemplate restTemplate;

    private final MangaService mangaService;
    private final RabbitMQProducer producer;

    public MangaController(MangaService mangaService, RabbitMQProducer producer) {
        this.mangaService = mangaService;
        this.producer = producer;
    }

    @GetMapping
    public List<Manga> getMangas() {
        return mangaService.findAll();
    }

    @PostMapping
    public void AddManga() {
        producer.sendMessage("Manga added!");
    }

    @GetMapping("/call-api")
    public String callApi() {
        String url = "https://serverless-test.j-geven.workers.dev/";
        System.out.println(restTemplate.getForObject(url, String.class));
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/favorite")
    public void favoriteManga() {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("mangaId", 1);
        messageMap.put("mangaTitle", "Berserk");
        messageMap.put("mangaChapter", 387);
        messageMap.put("message", String.format("%s has just released a new chapter %d!", "Berserk", 387));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(messageMap);

            producer.sendMessage(jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/liked")
    public void likedManga() {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("reviewId", 1);
        messageMap.put("userId", 15);
        messageMap.put("mangaTitle", "Berserk");
        messageMap.put("message", String.format("Your review for %s has received a new like!", "Berserk"));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(messageMap);

            producer.sendMessage(jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
