package greeting;
import java.util.Map;

import greeting.LangAlies;


public class Greeting {

    private static final Map<String, String> translateTable = Map.of(
            "ko", "안녕하세요",
            "en", "hello",
            "jp", "こんにちは"
    );

    private final String lang;

    public Greeting(String lang) {
        this.lang = LangAlies.enumCheck(lang.toLowerCase());
        hello();
    }

    private void hello(){
        System.out.println(
                translateTable.getOrDefault(this.lang, "지원되지 않는 언어입니다.")
        );
    }

}