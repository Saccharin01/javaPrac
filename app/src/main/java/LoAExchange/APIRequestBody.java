package LoAExchange;

import LoAExchange.LookupTable.LookupTable;
import LoAExchange.dto.RequestPayload;
import LoAExchange.utils.TableResolver;

import java.util.List;

public class APIRequestBody {

    private final TableResolver itemGradeResolve;
    private final TableResolver charClassResolve;

    public APIRequestBody(TableResolver gradeTable, TableResolver classTable) {
        this.itemGradeResolve = gradeTable;
        this.charClassResolve = classTable;
    }

    public String resolveItemGrade(String input) {
        return itemGradeResolve.Resolve(input);
    }

    public String resolveCharClass(String input) {
        return charClassResolve.Resolve(input);
    }

    /**
     * payload 빌드 메서드입니다. 매개변수로 전달된 값을 활용해 DTO에서 정의한 바디를 빌드합니다.
     * @param itemName : 아이템 이름입니다. 빈 값으로 둘 경우 아이템 코드에 해당하는 모든 아이템을 검색합니다.
     * @param inputGrade : 아이템 등급입니다. 아이템 등급에 해당하는 문자열 값을 넣어야 합니다.
     * @param inputClass : 캐릭터 클래스입니다. 해당하는 문자열을 넣어야 합니다.
     * @return : DTO 에서 정의된 body를 반환합니다.
     */
    public RequestPayload createRequestPayload(String itemName, String inputGrade, String inputClass) {
        return RequestPayload.builder()
                .itemName(itemName)
                .itemGrade(resolveItemGrade(inputGrade))
                .characterClass(resolveCharClass(inputClass))
                .build();
    }

    /** createRequestPayload 메서드의 오버로딩 메서드입니다. DTO에서 아이템 이름은 기본값으로 지정되어 있기 때문에
     * 해당 메서드를 이용해 아이템 이름에 대한 선택적 매개변수를 도모합니다.
     */

    public RequestPayload createRequestPayload(String grade, String charClass) {
        return createRequestPayload("", grade, charClass);
    }



}