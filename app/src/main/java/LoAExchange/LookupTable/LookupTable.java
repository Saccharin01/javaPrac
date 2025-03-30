package LoAExchange.LookupTable;

public class LookupTable {
    private String label;
    private String alias;

    public LookupTable() {}
    public LookupTable(String label, String alias) {
        this.label = label;
        this.alias = alias;
    }
    public String getLabel() {
        return this.label;
    }
    public String getAlias() {
        return this.alias;
    }
}
