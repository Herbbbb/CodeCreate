package com.zhicall.factory.create;

import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.fileReader.propertiesReader.PropertiesReader;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽象类 封装基本的模板读写
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public abstract class BaseCreater {

    protected static Map<String, String> map;

    private static PropertiesReader reader;

    protected String templatePath = "";

    protected String writePath = (PathSetting.writePath.endsWith("/") ? PathSetting.writePath : PathSetting.writePath + "/");

    protected Configuration cfg = new Configuration();

    private String pathKey = "";

    // 初始化加载包路径，kv存储
    protected void initReader() {
        if (reader == null) {
            reader = new PropertiesReader("resources/paramSetting.properties");
            map = reader.getPropMap();
        }
    }

    // 创建包文件夹
    protected void createFile(String className) {
        File file = new File(writePath + getPackagePathWithPathKey()
                + StringCaseUtil.lowcaseFirstChar(className));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void setPathKey(String key) {
        this.pathKey = key;
    }

    private String getPackagePath() {
        return getParam("package");
    }

    protected String getPackagePathWithPathKey() {
        return getPackagePath() + getParam(pathKey);
    }

    protected String getPackagePathWithPathKey(String key) {
        return getPackagePath() + getParam(key);
    }

    protected void setTemplatePath(String str) {
        this.templatePath = str;
    }

    private String getParam(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        else {
            System.err.println("读取" + key + "出错");
            return "";
        }
    }

    public abstract void executeCreateTask(Entity entity);

    protected void fillData(String className, Entity entity) {
        Map<String, Object> data = new HashMap<>(11);
        data.put("author", entity.getAuthor());
        // 首字母小写的类名
        data.put("classNameL", StringCaseUtil.lowcaseFirstChar(entity.getEntityName()));
        // 全部小写的类名
        data.put("classNameLL", StringCaseUtil.lowcaseAll(entity.getEntityName()));
        data.put("package", getPackagePathWithPathKey().replaceAll("/", "."));
        data.put("dakuohao", "{");
        data.put("dollor", "$");
        data.put("jinhao", "#");
        // 首字母大写的类名
        data.put("className", StringCaseUtil.upcaseFirstChar(entity.getEntityName()));
        data.put("daoPackage", getPackagePathWithPathKey("dao").replaceAll("/", "."));
        data.put("entityPackage", getPackagePathWithPathKey("entity").replaceAll("/", "."));
        data.put("servicePackage", getPackagePathWithPathKey("service").replaceAll("/", "."));
        loadTemplateAndWriteFile(className, ".java", data);
    }

    protected void loadTemplateAndWriteFile(String className, String fileType, Map<String, Object> data) {
        FileOutputStream fos = null;
        try {
            Template entityTemplate = cfg.getTemplate(templatePath);
            fos = new FileOutputStream(writePath
                    + getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
                    + "/" + className + fileType);
            entityTemplate.process(data, new OutputStreamWriter(fos, StandardCharsets.UTF_8));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
