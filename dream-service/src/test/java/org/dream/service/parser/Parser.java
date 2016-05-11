package org.dream.service.parser;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import com.foundationdb.sql.unparser.NodeToString;

public class Parser {

	public static void main(String[] args) throws StandardException {
		
		SQLParser parser = new SQLParser();
		StatementNode stmt = parser
				.parseStatement("select userid,username,password from sys_user where username = ? order by id desc");
		stmt.treePrint();

		System.out.println("**********");

		
		NodeToString unparser = new NodeToString();
		String sql = unparser.toString(stmt);
		System.out.println(sql);
		
		
		RemoveOrderByUnParser removeOrderByUnParser = new RemoveOrderByUnParser();
		String withoutOrder = removeOrderByUnParser.removeOrderBy(sql);
		System.out.println(withoutOrder);
	}
}
