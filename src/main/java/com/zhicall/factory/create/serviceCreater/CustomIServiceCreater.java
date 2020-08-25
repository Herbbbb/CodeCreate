package com.zhicall.factory.create.serviceCreater;

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
 * 用户自定义Service接口 生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class CustomIServiceCreater extends IServiceCreater{

	public CustomIServiceCreater() {
		super.initReader();
		setPathKey("service");
		setTemplatePath(PathSetting.customIServiceTemplate);
	}
	
	@Override
	public void loadTemplateAndWriteFile(String className, String fileType, Map<String, Object> data) {
		FileOutputStream entityFos = null;
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			entityFos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/customInterface/ICustom" + className + fileType);
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
