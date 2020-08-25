package com.zhicall.factory.create.daoCreater;

import com.zhicall.factory.create.BaseCreater;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;

/**
 * DAO接口 生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class DaoCreater extends BaseCreater{

	public DaoCreater() {
		super.initReader();
		setPathKey("dao");
		setTemplatePath(PathSetting.daoTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstChar(entity.getEntityName()) + "Dao";
		createFile(className);
		fillData(className, entity);
		System.out.println( "[" + className + "]创建完成");
	}
	
}
