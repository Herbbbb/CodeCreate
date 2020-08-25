package com.zhicall.factory.pathSetting;

/**
 * 路径基站
 */
public class PathSetting {
	public static String readPath = "";
	public static String writePath = "";
	
	//template path 
	public static final String baseTemplatePath  = "resources/template/";
	public static final String controllerTemplate = baseTemplatePath + "Controller.temp";
	public static final String daoTemplate = baseTemplatePath + "Dao.temp";
	public static final String serviceTemplate = baseTemplatePath + "Service.temp";
	public static final String iServiceTemplate = baseTemplatePath + "IService.temp";
	public static final String entityTemplate = baseTemplatePath + "Entity.temp";
	public static final String customDaoTemplate = baseTemplatePath + "CustomDao.temp";
	public static final String mapperXMLTemplate = baseTemplatePath + "MapperXML.temp";
	public static final String customMapperXMLTemplate = baseTemplatePath + "CustomMapperXML.temp";
	public static final String customIServiceTemplate = baseTemplatePath + "CustomIService.temp";
	public static final String customServiceTemplate = baseTemplatePath + "CustomService.temp";

	public static final String webServiceTemplate = baseTemplatePath + "WebServiceUtil.temp";
	public static final String socketTemplate = baseTemplatePath + "SocketUtil.temp";
	public static final String logFactoryTemplate = baseTemplatePath + "LogFactory.temp";
	public static final String propeytyUtilTemplate = baseTemplatePath + "PropertyUtil.temp";
	public static final String systemConstantsTemplate = baseTemplatePath + "SystemConstants.temp";

}
