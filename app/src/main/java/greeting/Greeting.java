package greeting;
import java.util.Map;

public class Greeting {
    private static final Map<String, String> translateTable = Map.of(
            "ko", "안녕하세요",
            "en", "hello",
            "jp", "こんにちは"
    );

    private static final Map<String, String> aliesArgument = Map.of(
            "korea", "ko",
            "english", "en",
            "japan", "jp"
    );

    private final String lang;

    public Greeting(String lang) {
        this.lang = normalizeInput(lang.toLowerCase());
        hello();
    }

    private String normalizeInput(String input){
        if(translateTable.containsKey(input)){
            return input;
        } else if (aliesArgument.containsKey(input)){
            return aliesArgument.get(input);
        } else {
            return null;
        }
    }

    private void hello(){
        if(lang != null){
            System.out.println(translateTable.get(lang));
        } else {
            System.out.println("지원되지 않는 언어입니다.");
        }
    }

}