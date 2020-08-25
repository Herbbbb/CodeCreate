package com.zhicall.factory.create.mapperXMLCreater;

import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomMapperXMLCreater extends MapperXMLCreater {

    public CustomMapperXMLCreater() {
        super.initReader();
        setPathKey("mapper");
        setTemplatePath(PathSetting.customMapperXMLTemplate);
    }

    @Override
    public void loadTemplateAndWriteFile(String className, String fileType, Map<String, Object> data) {
        FileOutputStream entityFos = null;
        try {
            Template entityTemplate = cfg.getTemplate(templatePath);
            entityFos = new FileOutputStream(writePath
                    + getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
                    + "/Custom" + className + fileType);
            entityTemplate.process(data, new OutputStreamWriter(entityFos, StandardCharsets.UTF_8));
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
