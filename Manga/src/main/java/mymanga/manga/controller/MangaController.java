package mymanga.manga.controller;

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

import java.util.List;

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

}
