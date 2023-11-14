package pers.wendaozhenlie.gunyucommon.hanler;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java List -> Jdbc VARCHAR
 */
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder(strings.toString());
        preparedStatement.setString(i, stringBuilder.substring(1, stringBuilder.length() - 1));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parse2List(resultSet.getString(s));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parse2List(resultSet.getString(i));
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parse2List(callableStatement.getString(i));
    }

    public List<String> parse2List(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String[] strings = str.split(",");
        return new ArrayList<>(Arrays.asList(strings));
    }
}
