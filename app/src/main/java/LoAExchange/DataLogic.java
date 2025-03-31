package LoAExchange;

import LoAExchange.database.DataImporter;

public class DataLogic {
    public static void main(String[] args) {
        DataImporter.importData("result.json");
    }
}