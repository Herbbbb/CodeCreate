package com.zhicall.factory.fileReader.wordReader;

import com.zhicall.factory.entity.Entity;
import com.zhicall.factory.fileReader.IFileReader;
import com.zhicall.factory.parse.IParse;
import com.zhicall.factory.parse.parseWord.ParseWord;
import com.zhicall.factory.pathSetting.PathSetting;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 读取word
 *
 */
public class WordReader implements IFileReader{
	@Override
	public List<Entity> readFile(String path) throws IOException {
		IParse parse = new ParseWord(getTables(path));
		return parse.startParse();
	}
	
	public TableIterator getTables(String path) throws IOException{
		// 获取文档的读取范围
		FileInputStream in = new FileInputStream(PathSetting.readPath);// 加载文档
		InputStreamReader isr = new InputStreamReader(in);
		System.out.println("文件编码格式:" + isr.getEncoding());
		POIFSFileSystem pfs = new POIFSFileSystem(in);
		HWPFDocument hwpf = new HWPFDocument(pfs);
		Range range = hwpf.getRange();
		TableIterator it = new TableIterator(range);
		return it;
	}

}
