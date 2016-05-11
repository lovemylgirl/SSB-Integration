package org.dream.service.parser;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.OrderByList;
import com.foundationdb.sql.parser.ParameterNode;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.unparser.NodeToString;

public class RemoveOrderByUnParser extends NodeToString {

	private static final SQLParser PARSER = new SQLParser();

	private static final String PLACEHOLDERS = "?";

	public String removeOrderBy(String sql) throws StandardException {
		StatementNode stmt = PARSER.parseStatement(sql);
		return toString(stmt);
	}

	@Override
	protected String orderByList(OrderByList node) throws StandardException {
		return "";
	}

	/**
	 * 解决入参问题
	 * */
	@Override
	protected String parameterNode(ParameterNode node) throws StandardException {
		return PLACEHOLDERS;
	}
}
