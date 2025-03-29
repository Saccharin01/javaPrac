package greeting;


import javax.annotation.Nullable;

/**
 * 사용자의 입력값을 검증하기 위한 enum 테이블을 생성합니다.
 * 해당 테이블은 korea, english, japan 이라는 문자열 입력에 대해 ko, en, jp 라는 문자열을 반환합니다.
 * 해당 클래스를 작성한 이유는 KoRea 처럼 대소문자를 섞는 케이스에 대한 대응보다 문자열의 오타에 대한 대응에 중점을 둡니다.
 * 예를 들어, kreoa, engilsh 처럼 문자열 입력에 오타가 섞여있을 경우, 해당 부분에 대한 방어를 도모합니다.
 */


public enum LangAlies {
    KOREA("ko"),
    ENGLISH("en"),
    JAPAN("jp");

    private final String input;

    LangAlies(String input){
        this.input = input;
    }

    public String getInput(){
        return this.input;
    }

    @Nullable
    public static String enumCheck(String input){
        for(LangAlies alies : values()){
            if(alies.name().equalsIgnoreCase(input)){
                return alies.getInput();
            }
        }
        return null;
    }
}