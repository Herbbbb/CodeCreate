package com.zhicall.factory.create.serviceCreater;

import com.zhicall.factory.create.BaseCreater;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Service 实现类生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class ServiceCreater extends BaseCreater {

    public ServiceCreater() {
        super.initReader();
        setPathKey("service");
        setTemplatePath(PathSetting.serviceTemplate);
    }

    @Override
    public void executeCreateTask(Entity entity) {
        String className = StringCaseUtil.upcaseFirstChar(entity.getEntityName()) + "Service";
        createFile(className);
        fillData(className, entity);
        System.out.println( "[" + className + "]创建完成");
    }

    @Override
    protected void loadTemplateAndWriteFile(String className, String fileType, Map<String, Object> data) {
        FileOutputStream entityFos = null;
        try {
            Template entityTemplate = cfg.getTemplate(templatePath);
            entityFos = new FileOutputStream(writePath
                    + getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
                    + "/" + className + "Impl" + fileType);
            entityTemplate.process(data, new OutputStreamWriter(entityFos, StandardCharsets.UTF_8)); //
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != entityFos) {
                    entityFos.flush();
                    entityFos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
