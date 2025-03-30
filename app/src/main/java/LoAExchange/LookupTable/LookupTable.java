package LoAExchange.LookupTable;

public class LookupTable {
    private String label;
    private String alies;

    public LookupTable() {}
    public LookupTable(String label, String alies) {
        this.label = label;
        this.alies = alies;
    }
    public String getLabel() {
        return this.label;
    }
    public String getAlies() {
        return this.alies;
    }
}
