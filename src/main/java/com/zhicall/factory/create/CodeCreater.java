package com.zhicall.factory.create;

import com.zhicall.constants.GenerateType;
import com.zhicall.factory.create.controllerCreater.ControllerCreater;
import com.zhicall.factory.create.daoCreater.CustomDaoCreater;
import com.zhicall.factory.create.daoCreater.DaoCreater;
import com.zhicall.factory.create.entityCreater.EntityCreater;
import com.zhicall.factory.create.mapperXMLCreater.CustomMapperXMLCreater;
import com.zhicall.factory.create.mapperXMLCreater.MapperXMLCreater;
import com.zhicall.factory.create.serviceCreater.CustomIServiceCreater;
import com.zhicall.factory.create.serviceCreater.CustomServiceCreater;
import com.zhicall.factory.create.serviceCreater.IServiceCreater;
import com.zhicall.factory.create.serviceCreater.ServiceCreater;
import com.zhicall.factory.create.utilCreater.*;
import com.zhicall.factory.database.SQLCreate;
import com.zhicall.factory.database.dao.CommonDAO;
import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.entity.Field;
import com.zhicall.factory.fileReader.IFileReader;
import com.zhicall.factory.fileReader.excelReader.ExcelReader;
import com.zhicall.factory.fileReader.wordReader.WordReader;
import com.zhicall.factory.pathSetting.PathSetting;
import com.zhicall.pojo.param.GenerateParam;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局控制类 指派任务
 *
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/4 11:16
 */
public class CodeCreater {

    /**
     * 代码生成，不依赖数据库或Office
     *
     * @param writePath
     */
    public static boolean StartCreate(String writePath, GenerateParam param) {
        try {
            PathSetting.writePath = writePath;
            Entity entity = new Entity();
            // 必填字段
            entity.setAuthor(param.getAuthor());
            entity.setEntityName(param.getEntityName());
            List<Field> fields = new ArrayList<>();
            entity.setFields(fields);
            createTask(entity, param.getGenerateType());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 代码生成，依赖第三方Office且生成对应的数据库表
     *
     * @param readPath
     * @param writePath
     * @throws IOException
     * @throws TemplateException
     */
    public static void StartCreateByOffice(String readPath, String writePath) throws IOException, TemplateException {
        PathSetting.readPath = readPath;
        PathSetting.writePath = writePath;
        System.out.println("---------------start code creater---------------");
        String[] filePathArr = readPath.split("\\.");
        String fileType = filePathArr[filePathArr.length - 1];
        IFileReader reader;
        List<Entity> entityList = null;
        if (fileType.equals("doc")) {
            reader = new WordReader();
            entityList = reader.readFile(readPath);
        } else if (fileType.equals("xls") || fileType.equals("xlsx")) {
            reader = new ExcelReader();
            entityList = reader.readFile(readPath);
        }
        try {
            for (int i = 0; i < entityList.size(); i++) {
                Entity entity = entityList.get(i);
                createDatabase(entity);
                createTask(entity, GenerateType.DEFAULT);
            }
        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
        }
    }

    public static void createDatabase(Entity entity) throws IOException {
        System.out.println("执行数据库操作");
        CommonDAO cd = new CommonDAO();
        //新增数据库
        if (entity.getEntitySteate() == 0) {
            String sql = SQLCreate.tableCreateSQL(entity);
            //System.out.println(sql);
            cd.executeSql(sql);
            //resultList.add(cd.executeSql(sql));
        } else {//修改数据库字段
            List<String> sqlList = SQLCreate.tableAlterSQL(entity);
            for (int i = 0; i < sqlList.size(); i++) {
                System.out.println(sqlList.get(i));
                cd.executeSql(sqlList.get(i));
            }
        }
    }

    public static void createTask(Entity entity, GenerateType caseWhen) {
        switch (caseWhen) {
            case ENTITY:
                new EntityCreater().executeCreateTask(entity);
                break;
            case MAPPER:
                new MapperXMLCreater().executeCreateTask(entity);
                new CustomMapperXMLCreater().executeCreateTask(entity);
                break;
            case DAO:
                new DaoCreater().executeCreateTask(entity);
                new CustomDaoCreater().executeCreateTask(entity);
                break;
            case SERVICE:
                new ServiceCreater().executeCreateTask(entity);
                new IServiceCreater().executeCreateTask(entity);
                new CustomServiceCreater().executeCreateTask(entity);
                new CustomIServiceCreater().executeCreateTask(entity);
                break;
            case CONTROLLER:
                new ControllerCreater().executeCreateTask(entity);
                break;
            case UTIL:
                new WebServiceUtilCreater().executeCreateTask(entity);
                new SocketUtilCreater().executeCreateTask(entity);
                new LogFactoryCreater().executeCreateTask(entity);
                new SystemConstantsCreater().executeCreateTask(entity);
                new PropertyUtilCreater().executeCreateTask(entity);
                break;
            case DEFAULT:
                new EntityCreater().executeCreateTask(entity);
                new DaoCreater().executeCreateTask(entity);
                new CustomDaoCreater().executeCreateTask(entity);
                new MapperXMLCreater().executeCreateTask(entity);
                new CustomMapperXMLCreater().executeCreateTask(entity);
                new ServiceCreater().executeCreateTask(entity);
                new IServiceCreater().executeCreateTask(entity);
                new CustomServiceCreater().executeCreateTask(entity);
                new CustomIServiceCreater().executeCreateTask(entity);
                new ControllerCreater().executeCreateTask(entity);
                new WebServiceUtilCreater().executeCreateTask(entity);
                new SocketUtilCreater().executeCreateTask(entity);
                new LogFactoryCreater().executeCreateTask(entity);
                new SystemConstantsCreater().executeCreateTask(entity);
                new PropertyUtilCreater().executeCreateTask(entity);
                break;
            default:
                new EntityCreater().executeCreateTask(entity);
                break;
        }
        System.out.println("为" + entity.getEntityName() + "加载模板\n");
        new EntityCreater().executeCreateTask(entity);

        new DaoCreater().executeCreateTask(entity);
        new CustomDaoCreater().executeCreateTask(entity);

        new MapperXMLCreater().executeCreateTask(entity);
        new CustomMapperXMLCreater().executeCreateTask(entity);

        new ServiceCreater().executeCreateTask(entity);
        new IServiceCreater().executeCreateTask(entity);
        new CustomServiceCreater().executeCreateTask(entity);
        new CustomIServiceCreater().executeCreateTask(entity);

        new ControllerCreater().executeCreateTask(entity);

        // 工具类
        new WebServiceUtilCreater().executeCreateTask(entity);
        new SocketUtilCreater().executeCreateTask(entity);
        new LogFactoryCreater().executeCreateTask(entity);
        new SystemConstantsCreater().executeCreateTask(entity);
        new PropertyUtilCreater().executeCreateTask(entity);
        System.out.println("创建完成\n");
    }
}
