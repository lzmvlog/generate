package com.shaojie.generate;

import com.shaojie.generate.meta.MetaData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class GenerateApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MetaData metaData;

    @Test
    void contextLoads() throws SQLException {
        List table = metaData.getTable();

        for (int i = 0; i < table.size() ; i++) {
            String sql = "select * from " + table.get(i);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            RowCountCallbackHandler rcch = new RowCountCallbackHandler();
            jdbcTemplate.query(sql, rcch);

            System.out.println("column count :" + rcch.getColumnCount());
            System.out.println("column count :" + rcch.getRowCount());

            for (int j = 0; j < rcch.getColumnCount(); j++) {
                System.out.println("column name :" + rcch.getColumnNames()[j]);
                // 从0开始索引。ResultSetMetaData对象返回的列的类型（如java.sql.Types）
                System.out.println("column type :" + rcch.getColumnTypes()[j]);
            }
        }
        /*JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from sys_admin");
        SqlRowSetMetaData metaData = rowSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            Map<String, String> fieldMap = new HashMap<String, String>();
            fieldMap.put("ColumnName", metaData.getColumnName(i));
            fieldMap.put("ColumnType", String.valueOf(metaData.getColumnType(i)));
            fieldMap.put("ColumnTypeName", metaData.getColumnTypeName(i));
            fieldMap.put("CatalogName", metaData.getCatalogName(i));
            fieldMap.put("ColumnClassName", metaData.getColumnClassName(i));
            fieldMap.put("ColumnLabel", metaData.getColumnLabel(i));
            fieldMap.put("Precision", String.valueOf(metaData.getPrecision(i)));
            fieldMap.put("Scale", String.valueOf(metaData.getScale(i)));
            fieldMap.put("SchemaName", metaData.getSchemaName(i));
            fieldMap.put("TableName", metaData.getTableName(i));
            fieldMap.put("SchemaName", metaData.getSchemaName(i));
            System.out.println(fieldMap);
        }*/

        // MysqlType
    }

}
