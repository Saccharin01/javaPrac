package greeting;


//
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
    public static String enumCheck(String input){
        for(LangAlies alies : values()){
            if(alies.name().equalsIgnoreCase(input)){
                return alies.getInput();
            }
        }
        return null;
    }
}