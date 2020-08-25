package com.zhicall;

import com.zhicall.constants.GenerateType;
import com.zhicall.factory.create.CodeCreater;
import com.zhicall.pojo.param.GenerateParam;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException, SecurityException, IllegalArgumentException {

        // 输出位置
        String writePath = "C:\\Users\\Administrator\\Desktop\\code";

        GenerateParam param = new GenerateParam(writePath, "zhicall", "zhicall", GenerateType.DEFAULT);
        CodeCreater.StartCreate(writePath, param);
//		// xml文件位置
//		String sourcePath = "C:\\Users\\Administrator\\Desktop\\SSMCodeCreater-master\\SSMCodeCreater-master\\dataExcel.xls";
//		CodeCreater.StartCreateByOffice(sourcePath, writePath);
    }
}
