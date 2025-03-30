package LoAExchange.utils;

import LoAExchange.LookupTable.LookupTable;
import java.util.List;


public class TableResolver {
    private final List<LookupTable> table;
    private final String defaultValue;

    public TableResolver(List<LookupTable> table, String defaultValue) {
        this.table = table;
        this.defaultValue = defaultValue;
    }

    public String Resolve(String input){
        if(input == null || input.isEmpty()) {
            return this.defaultValue;
        }

        for(LookupTable table : table) {
            if(table.getLabel().equalsIgnoreCase(input)
                ||table.getAlias().equalsIgnoreCase(input)) {
                return table.getLabel();
            }
        }
        return this.defaultValue;
    }

}
