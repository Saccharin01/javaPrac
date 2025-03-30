package LoAExchange;

import LoAExchange.LookupTable.LookupTable;
import LoAExchange.utils.JsonLoader;
import LoAExchange.utils.TableResolver;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class APIRequestBody {

    private final String API_KEY;
    private final String REQUEST_URL;
    private final TableResolver itemGradeResolve;
    private final TableResolver charClassResolve;

    public APIRequestBody() {
        Dotenv dotenv = Dotenv.load();
        this.API_KEY = dotenv.get("API_KEY");
        this.REQUEST_URL = dotenv.get("REQUEST_URL");

        List<LookupTable> ItemGrade = JsonLoader.loadLookupTable("java/LoAExchange/data/ItemGrade.json");
        List<LookupTable> CharClass = JsonLoader.loadLookupTable("java/LoAExchange/data/CharacterClass.json");


        this.itemGradeResolve = new TableResolver(ItemGrade, "희귀");
        this.charClassResolve = new TableResolver(CharClass, "건슬");
    }

    public String resolveItemGrade(String input) {
        return this.itemGradeResolve.Resolve(input);
    }

    public String resolveCharClass(String input) {
        return this.charClassResolve.Resolve(input);
    }

    public String getAPI_KEY(){
        return this.API_KEY;
    }
    public String getREQUEST_URL(){
        return this.REQUEST_URL;
    }

    public String buildRequestBody(){

    return null;}

}
