package LoAExchange;

import LoAExchange.LookupTable.LookupTable;
import LoAExchange.dto.RequestPayload;
import io.github.cdimascio.dotenv.Dotenv;
import LoAExchange.network.RequestSender;

import LoAExchange.utils.TableResolver;
import LoAExchange.utils.JsonLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dotenv dotenv = Dotenv.load();

        // 사용자 입력
        System.out.print("아이템 등급 입력: ");
        String grade = scanner.nextLine();

        System.out.print("캐릭터 클래스 입력: ");
        String charClass = scanner.nextLine();

        System.out.print("아이템 이름 입력 (생략 가능): ");
        String itemName = scanner.nextLine();

        // Json 파일 로드 및 비교 테이블 생성 및 주입 준비
        List<LookupTable> itemGradeTable =
                JsonLoader.loadLookupTable("ItemGrade.json");

        List<LookupTable> characterClassTable =
                JsonLoader.loadLookupTable("CharacterClass.json");

        TableResolver itemGradeResolver = new TableResolver(itemGradeTable, "희귀");
        TableResolver classResolver = new TableResolver(characterClassTable, "건슬");


        // Resolver 및 DTO 조립
        APIRequestBody builder = new APIRequestBody(itemGradeResolver, classResolver);

        RequestPayload payload = itemName.isBlank()
                ? builder.createRequestPayload(grade, charClass)
                : builder.createRequestPayload(itemName, grade, charClass);

        // 요청 및 저장
        RequestSender sender = new RequestSender(dotenv.get("LOSTARK_API_KEY"), dotenv.get("REQUEST_URL")+"/markets/items");
        try {
            String response = sender.sendRequest(payload);
            Files.write(Paths.get("result.json"), response.getBytes());
            System.out.println("✔ result.json 파일로 저장 완료!");
        } catch (IOException e) {
            System.err.println("✘ 오류 발생: " + e.getMessage());
        }
    }
}