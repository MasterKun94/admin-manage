package com.example.adminmanage.global.config;

import org.hibernate.dialect.MySQL57Dialect;

public class MysqlConfig extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
