package com.zhicall.factory.database;

import com.zhicall.factory.database.dao.SQLTemplate;
import com.zhicall.factory.entity.Entity;

import java.util.List;

/**
 * 创建数据库语句
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class SQLCreate {
	
	public SQLCreate() {
		// TODO Auto-generated constructor stub
	}

	public static String tableCreateSQL(Entity entity){
		return SQLTemplate.createTableSQL(entity);
	}
	
	public static List<String> tableAlterSQL(Entity entity){
		return CreateAlterSQLList.startParseAndCreateSQL(entity);
	}

}
