package Services;
import org.springframework.web.client.RestTemplate;


public class WordGeneratorAPI {
    public static String API_BASE_URL = "https://random-word-api.herokuapp.com/word";
    private RestTemplate restTemplate = new RestTemplate();

    public String getRandomWord() {
        return restTemplate.getForObject(API_BASE_URL,String.class);
    }
}
