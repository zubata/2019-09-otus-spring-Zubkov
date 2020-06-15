package ru.otus.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.domain.History;
import ru.otus.project.domain.Producer;
import ru.otus.project.domain.Vine;
import ru.otus.project.stroge.ProducerDao;
import ru.otus.project.stroge.VineDao;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteParsingServiceImpl implements SiteParsingService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://simplewine.ru/ajax/catalog/vino/?";
    private final ObjectMapper om = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger("SiteParsingService");
    private final ProducerDao producerDao;
    private final VineDao vineDao;

    @Override
    public void createVineList() throws IOException {
        String response = restTemplate.getForObject(baseUrl, String.class);
        if (response == null) throw new RuntimeException("Url not found");
        int totalCount = om.readTree(response).findValue("total_count").asInt();

        vineDao.updateAllVinesToUnavailableStatus();

        for (int i = 0; i < totalCount; i = i + 66) {
            String restUrl = String.format(baseUrl + "limit=66&from=%d", i);
            try {
                response = restTemplate.getForObject(restUrl, String.class);
            } catch (ResourceAccessException e) {
                response = restTemplate.getForObject(restUrl, String.class);
                log.info("ResourceAccessException, URL: " + restUrl);
            }
            List<Vine> temp = mapOnePage(response);

            vineDao.saveAll(temp);
        }
    }

    private List<Vine> mapOnePage(String response) throws JsonProcessingException {
        JsonNode mainNode = om.readTree(response).findPath("items");
        List<Vine> vineList = Arrays.asList(om.treeToValue(mainNode, Vine[].class));
        List<JsonNode> jsonNodes = om.readTree(response).findParents("unitSalePrice");
        addInfoFromAnotherPartOfJson(vineList, jsonNodes);
        return vineList;
    }

    private void addInfoFromAnotherPartOfJson(List<Vine> vineList, List<JsonNode> jsonNodes) {
        JsonNode temp;
        for (Vine vine : vineList) {
            long curr = vine.getId();
            for (JsonNode jsonNode : jsonNodes) {
                if (jsonNode.get("id").asLong() == curr) {

                    String manufac = (temp = jsonNode.findValue("manufacturer")) != null ? temp.asText() : "";
                    Producer tempProd = producerDao.findByName(manufac);
                    if (tempProd == null) tempProd = producerDao.save(new Producer(manufac));
                    vine.setProducer(tempProd);

                    double cost = (temp = jsonNode.findValue("unitSalePrice")) != null ? temp.asDouble() : 0;
                    History tempHistoty = new History(vine, cost);
                    vine.addPriceToList(tempHistoty);

                    vine.setCost(cost);

                    vine.setName((temp = jsonNode.findValue("name")) != null ? temp.asText() : "");
                    vine.setUrl(jsonNode.findValue("url").asText());
                }
            }
        }
    }

}
