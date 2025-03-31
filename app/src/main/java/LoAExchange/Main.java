package LoAExchange;

import LoAExchange.LookupTable.LookupTable;
import LoAExchange.dto.RequestPayload;
import io.github.cdimascio.dotenv.Dotenv;
import LoAExchange.network.RequestSender;
import LoAExchange.network.RequestManager;

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

        System.out.print("아이템 등급 입력: ");
        String grade = scanner.nextLine();

        System.out.print("캐릭터 클래스 입력: ");
        String charClass = scanner.nextLine();

        System.out.print("아이템 이름 입력 (생략 가능): ");
        String itemName = scanner.nextLine();

        // Resolver 준비
        List<LookupTable> itemGradeTable = JsonLoader.loadLookupTable("ItemGrade.json");
        List<LookupTable> classTable = JsonLoader.loadLookupTable("CharacterClass.json");

        TableResolver itemGradeResolver = new TableResolver(itemGradeTable, "희귀");
        TableResolver classResolver = new TableResolver(classTable, "건슬");

        APIRequestBody builder = new APIRequestBody(itemGradeResolver, classResolver);
        RequestSender sender = new RequestSender(dotenv.get("LOSTARK_API_KEY"), dotenv.get("REQUEST_URL") + "/markets/items");

        // ➤ RequestManager 에게 책임 넘김
        RequestManager manager = new RequestManager(sender, builder);
        manager.fetchAll(itemName, grade, charClass);
    }
}