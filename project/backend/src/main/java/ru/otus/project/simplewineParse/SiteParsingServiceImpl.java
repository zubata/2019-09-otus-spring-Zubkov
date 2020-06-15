package ru.otus.project.simplewineParse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.domain.History;
import ru.otus.project.domain.Producer;
import ru.otus.project.domain.Vine;
import ru.otus.project.service.SiteParsingService;
import ru.otus.project.stroge.ProducerDao;
import ru.otus.project.stroge.VineDao;
import ru.otus.project.simplewineParse.dto.VineDto;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
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
    private final int limitVineForOnePage = 66;

    @Override
    @Retryable(
            value = { SocketException.class },
            maxAttempts = 2)
    public void createVineList() throws IOException {
        String response = restTemplate.getForObject(baseUrl, String.class);
        if (response == null) throw new RuntimeException("Url not found");
        int totalCount = om.readTree(response).findValue("total_count").asInt();

        vineDao.updateAllVinesToUnavailableStatus();

        for (int i = 0; i < totalCount; i = i + limitVineForOnePage) {
            String restUrl = String.format(baseUrl + "limit=66&from=%d", i);
            try {
                response = restTemplate.getForObject(restUrl, String.class);
            } catch (ResourceAccessException e) {
                response = restTemplate.getForObject(restUrl, String.class);
                log.info("ResourceAccessException, URL: " + restUrl);
            }

            List<VineDto> tempDtoList = mapOnePage(response);
            List<Vine> tempVineList = convertDtoToEntity(tempDtoList);

            vineDao.saveAll(tempVineList);
        }
    }

    private List<VineDto> mapOnePage(String response) throws JsonProcessingException {
        List<JsonNode> listNodes = om.readTree(response).findParents("unitSalePrice");
        List<VineDto> vineDtoList = new ArrayList<>();
        listNodes.forEach(i -> {
            try {
                vineDtoList.add(om.treeToValue(i, VineDto.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        JsonNode auxNode = om.readTree(response).findPath("html");
        addExternalInfomationFromJson(auxNode, vineDtoList);
        return vineDtoList;
    }

    private void addExternalInfomationFromJson(JsonNode auxNode, List<VineDto> list) {
        Document doc = Jsoup.parse(auxNode.asText());
        for (VineDto vineDto : list) {
            Elements elements = doc.select("div[data-value-id=" + vineDto.getId() + "] ~ div.product-snippet__detail");
            String country = elements.select("div.product-snippet__info-item:has(span:contains(Страна:))").text();
            String type = elements.select("div.product-snippet__info-item:has(span:contains(Сахар:))").text();
            String capacity = elements.select("div.product-snippet__info-item:has(span:contains(Объем:))").text();
            vineDto.setCountry(country.equals("") ? "" : country.split(" ")[1]);
            vineDto.setType(type.equals("") ? "" : type.split(" ")[1]);
            vineDto.setCapacity(capacity.equals("") ? 0 : Float.parseFloat(capacity.split(" ")[1]));
        }
    }

    @Override
    public List<Vine> convertDtoToEntity(List<VineDto> vineDtoList){
        List<Vine> listVine = new ArrayList<>();
        for (VineDto vineDto : vineDtoList) {
            String manufac = vineDto.getManufacturer() != null ? vineDto.getManufacturer() : "";
            Producer tempProd = producerDao.findByName(manufac);
            if (tempProd == null) tempProd = producerDao.save(new Producer(manufac));
            Vine temp = new Vine(
                    Long.parseLong(vineDto.getId()),
                    vineDto.getYear() == null ? 0 : Integer.parseInt(vineDto.getYear()),
                    vineDto.getType(),
                    vineDto.getColor(),
                    vineDto.getCapacity(),
                    vineDto.getCountry(),
                    true,
                    vineDto.getUnitSalePrice(),
                    tempProd,
                    vineDto.getName(),
                    vineDto.getUrl(),
                    new ArrayList<>()
            );
            History tempHistory = new History(temp, temp.getCost());
            temp.addPriceToList(tempHistory);
            listVine.add(temp);
        }
        return listVine;
    }

}
