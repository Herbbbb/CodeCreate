package com.zhicall.factory.create.mapperXMLCreater;

import com.zhicall.factory.create.BaseCreater;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.entity.Field;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体对象mapperXML 生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class MapperXMLCreater extends BaseCreater {

    public MapperXMLCreater() {
        super.initReader();
        setPathKey("mapper");
        setTemplatePath(PathSetting.mapperXMLTemplate);
    }

    @Override
    public void executeCreateTask(Entity entity) {
        String className = StringCaseUtil.upcaseFirstChar(entity.getEntityName());
        createFile(className);
        fillData(className, entity);
        System.out.println("[" + className + "]创建完成");
    }

    @Override
    protected void fillData(String className, Entity entity) {
        Map<String, Object> data = new HashMap<>(11);
        data.put("author", entity.getAuthor());
        // 首字母小写的类名
        data.put("classNameL", StringCaseUtil.lowcaseFirstChar(entity.getEntityName()));
        // 全部小写的类名
        data.put("classNameLL", StringCaseUtil.lowcaseAll(entity.getEntityName()));
        data.put("dakuohao", "{");
        data.put("dollor", "$");
        data.put("jinhao", "#");
        // 首字母大写的类名
        data.put("className", StringCaseUtil.upcaseFirstChar(entity.getEntityName()));
        data.put("daoPackage", getPackagePathWithPathKey("dao").replaceAll("/", "."));
        data.put("entityPackage", getPackagePathWithPathKey("entity").replaceAll("/", "."));
        data.put("entityID", StringCaseUtil.lowcaseAll(entity.getEntityName()));

        List<Map<String, String>> list = new ArrayList<>();
        List<Field> fieldList = entity.getFields();
        for (Field field : fieldList) {
            Map<String, String> fieldMap = new HashMap<>(2);
            fieldMap.put("fieldNameL", StringCaseUtil.lowcaseFirstChar(field.getFieldName()));
            fieldMap.put("fieldNameLL", StringCaseUtil.lowcaseAll(field.getFieldName()));
            list.add(fieldMap);
        }
        data.put("fieldList", list);

        loadTemplateAndWriteFile(className, "Mapper.xml", data);
    }

}
