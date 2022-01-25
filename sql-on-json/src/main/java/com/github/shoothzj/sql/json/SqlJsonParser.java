package com.github.shoothzj.sql.json;

import lombok.extern.slf4j.Slf4j;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

@Slf4j
public class SqlJsonParser {

    private final FrameworkConfig config;

    public SqlJsonParser() {
        SqlStdOperatorTable operatorTable = SqlStdOperatorTable.instance();
        SqlParser.Config parserConfig = SqlParser.config()
                .withParserFactory(SqlParserImpl.FACTORY)
                .withCaseSensitive(true)
                .withQuotedCasing(Casing.UNCHANGED)
                .withQuotedCasing(Casing.UNCHANGED);
        Frameworks.ConfigBuilder builder = Frameworks.newConfigBuilder()
                .parserConfig(parserConfig).operatorTable(operatorTable);
        config = builder.build();
    }

    public SqlNode parseSql(String sql) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql, config.getParserConfig());
        return parser.parseQuery();
    }

}