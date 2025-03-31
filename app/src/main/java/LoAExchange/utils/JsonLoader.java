package LoAExchange.utils;

import LoAExchange.LookupTable.LookupTable;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import LoAExchange.dto.ItemDTO;

import java.io.InputStream;
import java.util.List;
import java.io.File;

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

    public static List<ItemDTO> loadItemDTOList(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    new File(filePath),
                    new TypeReference<>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("[JsonLoader] ItemDTO Json 파일 로드 실패: " + filePath, e);
        }
    }
}
