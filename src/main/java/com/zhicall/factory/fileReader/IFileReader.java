package com.zhicall.factory.fileReader;

import com.zhicall.factory.entity.Entity;

import java.io.IOException;
import java.util.List;

/**
 * 操作文件的接口
 *
 */
public interface IFileReader {
	List<Entity> readFile(String path) throws IOException;
}
