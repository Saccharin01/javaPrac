package LoAExchange.network;

import LoAExchange.APIRequestBody;
import LoAExchange.dto.MarketResponse;
import LoAExchange.dto.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestManager {

    private final RequestSender sender;
    private final APIRequestBody builder;
    private final ObjectMapper mapper;

    public RequestManager(RequestSender sender, APIRequestBody builder) {
        this.sender = sender;
        this.builder = builder;
        this.mapper = new ObjectMapper();
    }

    public void fetchAll(String itemName, String grade, String charClass) {
        int page = 0;

        while (true) {
            System.out.printf("⏳ %d페이지 요청 중...\n", page);

            RequestPayload.RequestPayloadBuilder payloadBuilder = RequestPayload.builder()
                    .itemGrade(builder.resolveItemGrade(grade))
                    .characterClass(builder.resolveCharClass(charClass))
                    .pageNo(page);

            if (!itemName.isBlank()) {
                payloadBuilder.itemName(itemName);
            }

            RequestPayload payload = payloadBuilder.build();

            try {
                String responseJson = sender.sendRequest(payload);
                MarketResponse response = mapper.readValue(responseJson, MarketResponse.class);

                if (response.getItems().isEmpty()) {
                    System.out.println("더 이상 검색된 아이템이 없습니다. 반복 요청 종료.");
                    break;
                }

                Files.write(Paths.get("result_page_" + page + ".json"), responseJson.getBytes());
                System.out.printf("✔ %d 페이지 저장 완료 (%d개 아이템)\n", page, response.getItems().size());
                page++;

            } catch (IOException e) {
                System.err.println("요청 중 오류 발생: " + e.getMessage());
                break;
            }
        }
    }
}