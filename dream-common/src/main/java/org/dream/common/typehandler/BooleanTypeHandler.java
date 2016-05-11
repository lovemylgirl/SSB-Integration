package org.dream.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/** java中的boolean和jdbc中的char之间转换;true-Y;false-N */
public class BooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		Boolean b = parameter;
		String value = b == true ? "Y" : "N";
		ps.setString(i, value);
	}

	@Override
	public Boolean getResult(ResultSet rs, String columnName) throws SQLException {
		String str = rs.getString(columnName);
		Boolean rt = Boolean.FALSE;
		if (str.equalsIgnoreCase("Y")) {
			rt = Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		String str = rs.getString(columnIndex);
		Boolean rt = Boolean.FALSE;
		if (str.equalsIgnoreCase("Y")) {
			rt = Boolean.TRUE;
		}
		return rt;
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getBoolean(columnIndex);
	}

}
