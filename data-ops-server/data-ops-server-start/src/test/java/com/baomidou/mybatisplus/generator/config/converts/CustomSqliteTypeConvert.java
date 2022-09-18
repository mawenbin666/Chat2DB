package com.baomidou.mybatisplus.generator.config.converts;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import static com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert.toDateType;
import static com.baomidou.mybatisplus.generator.config.converts.TypeConverts.contains;
import static com.baomidou.mybatisplus.generator.config.converts.TypeConverts.containsAny;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.BIG_DECIMAL;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.BLOB;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.BOOLEAN;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.CLOB;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.DOUBLE;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.FLOAT;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.INTEGER;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.LONG;
import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.STRING;

public class CustomSqliteTypeConvert implements ITypeConvert {
    /**
     * @inheritDoc
     * @see MySqlTypeConvert#toDateType(GlobalConfig, String)
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverts.use(fieldType)
            .test(contains("bigint").then(LONG))
            .test(containsAny("tinyint(1)", "boolean").then(BOOLEAN))
            .test(contains("int").then(INTEGER))
            .test(containsAny("text", "char", "enum").then(STRING))
            .test(containsAny("decimal", "numeric").then(BIG_DECIMAL))
            .test(contains("clob").then(CLOB))
            .test(contains("blob").then(BLOB))
            .test(contains("float").then(FLOAT))
            .test(contains("double").then(DOUBLE))
            .test(containsAny("date", "time", "year").then(t -> toDateType(config, t)))
            .or(DbColumnType.STRING);
    }

}

