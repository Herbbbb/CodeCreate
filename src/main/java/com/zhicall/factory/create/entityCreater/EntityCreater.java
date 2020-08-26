package com.zhicall.factory.create.entityCreater;

import com.zhicall.factory.create.BaseCreater;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.entity.Field;
import com.zhicall.factory.entity.FieldType;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity类 生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 13:11
 */
@Slf4j
public class EntityCreater extends BaseCreater {

	public EntityCreater() {
		super.initReader();
		setPathKey("entity");
		setTemplatePath(PathSetting.entityTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstChar(entity.getEntityName());

		log.info("【" + className + "】" + "创建开始");
		createFile(className);

        log.info("【" + className + "】" + "开始填充");
		fillData(className, entity);

        log.info("【" + className + "】" + "创建完成");
	}

	@Override
	protected void fillData(String className, Entity entity) {
		List<Field> fieldList = entity.getFields();
		Map<String, Object> data = new HashMap<>(5);
		data.put("author", entity.getAuthor());
		// 首字母小写的类名
		data.put("entityNameL", StringCaseUtil.lowcaseFirstChar(className));
		// 首字母大写的类名
		data.put("entityName", className);
		data.put("package", getPackagePathWithPathKey().replaceAll("/", "."));

		// 属性列表
		List<Object> attrList = new ArrayList<>();
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			Map<String, String> fieldMap = new HashMap<>(2);
			String type = FieldType.transferFieldType(field.getFieldType());
			fieldMap.put("fieldType", type);
			fieldMap.put("fieldName", field.getFieldName());
			attrList.add(fieldMap);
		}
		data.put("attributes", attrList);
		loadTemplateAndWriteFile(className, ".java", data);
	}
}
