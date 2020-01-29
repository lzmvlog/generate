package com.shaojie.generate.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： ShaoJie
 * @data： 2020年01月25日 20:04
 * @Description： 数据库类型选择器
 */
@Getter
@AllArgsConstructor
public enum DataTypeEm {

    CHAR(1, "char"),
    INT(4, "int"),
    FLOAT(7,"float"),
    VARCHAR(12, "varchar"),
    DATE(91, "date"),
    DATETIME(93, "datetime");

    /**
     * 类型所占下标
     */
    private Integer index;

    /**
     * 数据库的类型
     */
    private String type;

}
