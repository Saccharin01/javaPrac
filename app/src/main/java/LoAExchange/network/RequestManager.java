package LoAExchange.network;

import LoAExchange.APIRequestBody;
import LoAExchange.dto.ItemDTO;
import LoAExchange.dto.MarketResponse;
import LoAExchange.dto.RequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        List<ItemDTO> allItems = new ArrayList<>();

        while (true) {
            System.out.printf("⏳ %d페이지 요청 중...\n", page);

            // Builder 직접 생성
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
                    System.out.println("📭 더 이상 아이템이 없습니다. 반복 요청 종료.");
                    break;
                }

                System.out.printf("✔ %d 페이지 수신 완료 (%d개 아이템)\n", page, response.getItems().size());
                allItems.addAll(response.getItems());
                page++;

            } catch (IOException e) {
                System.err.println("✘ 요청 실패: " + e.getMessage());
                break;
            }
        }

        // 전체 결과를 하나의 JSON 파일로 저장
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get("result.json").toFile(), allItems);
            System.out.println("📁 전체 데이터를 result.json 파일로 저장했습니다.");
        } catch (IOException e) {
            System.err.println("✘ 저장 실패: " + e.getMessage());
        }
    }
}