package LoAExchange.utils;

import LoAExchange.LookupTable.LookupTable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class JsonLoader {
    public static List<LookupTable> loadLookupTable(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream result = JsonLoader.class.getClassLoader().getResourceAsStream(resourcePath)){
            if(result == null){
                throw new IllegalArgumentException("[JsonLoader] 파일 경로가 잘못 되었습니다: " + resourcePath);
            }
            return mapper.readValue(result, new TypeReference<List<LookupTable>>(){});

        } catch (Exception error) {
            throw new RuntimeException("[JsonLoader] Json 파일 로드 실패" + resourcePath, error);
        }
    }
}
