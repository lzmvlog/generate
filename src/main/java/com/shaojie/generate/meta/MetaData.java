package com.shaojie.generate.meta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author： ShaoJie
 * @data： 2020年01月23日 21:16
 * @Description： 获取元数据
 */
@Component
@Configuration
@Slf4j
public class MetaData extends RowCountCallbackHandler {
    /**
     * RowCountCallbackHandler 数据库列的输出器
     * 获取元数据无非是 数据库字段的 列名、列的属性 整合输出
     */

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    // 设置数据库名称
    @Value("${spring.datasource.name}")
    private String catalog;

    /**
     * 处理行数据
     *
     * @param rs     表示数据库结果集的数据表  通常由*执行查询数据库的语句生成  也就是 连接查询生成的 结果集
     *               类似 jdbc 操作 互数据库
     *               Class.forName(jdbcName);
     *               // 获取连接
     *               Connection con = DriverManager.getConnection(dbUrl, dbName, dbPassWord)getCon();
     *               PreparedStatement pstmt = con.prepareStatement(sql);
     *               // 返回结果集ResultSet
     *               ResultSet rs = pstmt.executeQuery();
     * @param rowNum
     * @throws SQLException
     */
    @Override
    protected void processRow(ResultSet rs, int rowNum) throws SQLException {

    }

    /**
     * 获取设定连接下的 数据库表名 特定的数据库
     *
     * @return List 数据库的所有表
     */
    public List getTable() throws SQLException {
        // 保存 读取到数据
        List tablesList = new ArrayList();
        // getMetaData 检索<code> DatabaseMetaData </ code>对象，该对象包含*与该* <code> Connection </ code>对象表示连接的数据库有关的元数据。 *元数据包括有关数据库的表，其支持的SQL语法，其存储的过程，此连接的功能等信息。
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
        /**
         * catalog 目录名称； *必须与目录名称匹配，因为*存储在数据库中； “”检索那些没有目录的内容； * <code> null </ code>表示不应使用目录名称来缩小搜索范围
         * schemaPattern  模式名称模式；必须与存储在数据库中的模式名称*相匹配； “”检索那些没有模式的文件； * <code> null </ code>表示不应使用架构名称来缩小搜索范围
         * tableNamePattern 表格名称模式；必须与*表名匹配，因为它存储在数据库中
         * types  表格类型的列表，该列表类型必须来自{@link #getTableTypes}返回的表格类型列表中，以包括在内； <code> null </ code>返回
         */
        // 数据库名称
//            String catalog = "backstage";
        //可为:"TABLE",   "VIEW",   "SYSTEM   TABLE",
        //"GLOBAL   TEMPORARY",   "LOCAL   TEMPORARY",   "ALIAS",   "SYNONYM"
        String[] types = {"TABLE"};
        ResultSet tables = databaseMetaData.getTables(catalog, null, null, types);
        log.info("{}", catalog);
        while (tables.next()) {
            //只要表名这一列 使用SQL AS子句指定的列的标签。如果未指定SQL AS子句，则标签为列的名称
            tablesList.add(tables.getObject("TABLE_NAME"));
        }
        /*for (int i = 0; i < tablesList.size(); i++) {
            System.out.println(tablesList.get(i));
        }*/
        return tablesList;
    }
}
