package com.zhicall.factory.create.controllerCreater;

import com.zhicall.factory.create.BaseCreater;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.factory.stringCaseUtil.StringCaseUtil;

/**
 * Controller生成
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class ControllerCreater extends BaseCreater {

    public ControllerCreater() {
        super.initReader();
        setPathKey("controller");
        setTemplatePath(PathSetting.controllerTemplate);
    }

    @Override
    public void executeCreateTask(Entity entity) {
        String className = StringCaseUtil.upcaseFirstChar(entity.getEntityName()) + "Controller";
        createFile(className);
        fillData(className, entity);
    }

}
