package LoAExchange.database;

import LoAExchange.dto.ItemDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DataImporter {

    public static void importData(String filePath) {
        try {
            // JSON 읽기
            ObjectMapper mapper = new ObjectMapper();
            List<ItemDTO> items = mapper.readValue(
                    new File(filePath),
                    new TypeReference<List<ItemDTO>>() {}
            );

            // DB 연결
            try (Connection conn = DataBaseConnection.getConnection()) {
                String sql = """
                    INSERT IGNORE INTO items (
                        id, name, grade, icon, bundle_count, trade_remain_count, 
                        yday_avg_price, recent_price, current_min_price
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

                PreparedStatement stmt = conn.prepareStatement(sql);

                for (ItemDTO item : items) {
                    stmt.setLong(1, item.getId());
                    stmt.setString(2, item.getName());
                    stmt.setString(3, item.getGrade());
                    stmt.setString(4, item.getIcon());
                    stmt.setInt(5, item.getBundleCount());
                    stmt.setInt(6, item.getTradeRemainCount());
                    stmt.setDouble(7, item.getYDayAvgPrice());
                    stmt.setInt(8, item.getRecentPrice());
                    stmt.setInt(9, item.getCurrentMinPrice());

                    stmt.addBatch();
                }

                stmt.executeBatch();
                System.out.println("✔ 모든 데이터를 DB에 성공적으로 삽입했습니다.");

            }
        } catch (Exception e) {
            System.err.println("✘ 데이터 삽입 실패: " + e.getMessage());
        }
    }
}