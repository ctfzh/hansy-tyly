package com.hansy.tyly.customer.utils.poi;

import com.hansy.tyly.core.excepiton.ParameterException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class InportExcelHelper {

	private static final Logger logger = LoggerFactory.getLogger(InportExcelHelper.class);
	/**
	 * @Author:YuanYan
	 * @CreateAt:2017年10月26日下午8:18:29
	 * @Params:Excel文件，Excel文件每列对应的Key(严格顺序)
	 * @Return:List<HashMap<String, Object>> String为Cols参数对应的Key
	 * @Description:
	 */
	public static List<HashMap<String, Object>> analysisExcelFile(
			MultipartFile excelFile, String[] cols) {
		if (excelFile == null || excelFile.isEmpty()) {
			throw new ParameterException("上传文件有误，文件对象或内容为空！");
		}

		if (!excelFile.getOriginalFilename().endsWith(".xlsx")) {
			throw new ParameterException("Excel版本有误，请使用2007及以上版本！");
		}

		XSSFWorkbook wb = new XSSFWorkbook();
		try {
			InputStream ins = excelFile.getInputStream();
			//InputStream ins = new FileInputStream("D:/Tencent_/abc.xlsx"); // 建立输入流
			wb = new XSSFWorkbook(ins);
		} catch (IOException e) {
			logger.info("++++++++++++++++++(Excel解析出错-S)++++++++++++++++++++++++++");
			logger.info("== "+e.getMessage());
			logger.info("++++++++++++++++++(Excel解析出错-E)++++++++++++++++++++++++++");
			throw new ParameterException("Excel数据解析失败，请联系管理员！");
		}
		// 获取第一个Sheet
		XSSFSheet xssfSheet = wb.getSheetAt(0);
		if (null == xssfSheet) {
			throw new ParameterException("Excel文件有误，无有效Sheet表单！");
		}
		int rowNum = xssfSheet.getLastRowNum();
		if(rowNum == 0){
			throw new ParameterException("Excel文件数据有误，无有效数据行！");
		}
		if(rowNum > 5002){
			throw new ParameterException("Excel文件有效行数超限，最大为5000行！");
		}
		// 获取第一行
		XSSFRow xssfRow = xssfSheet.getRow(0);
		// 获取有效列数
		int colNum = xssfRow.getLastCellNum();
		if(colNum > 50){
			throw new ParameterException("Excel文件有效列数超限，最大为50列！");
		}
		if(cols.length != colNum){
			throw new ParameterException("Excel文件格式不正确，有效列数与数据源数目不一致！");
		}
		
		logger.info("++++++++++++++++++(Excel数据(S))++++++++++++++++++++++++++");
		logger.info("== rowNum | colNum :"+(rowNum+1)+" , "+colNum);
		logger.info("++++++++++++++++++(Excel数据(E))++++++++++++++++++++++++++");
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
		Iterator<Row> rows = xssfSheet.rowIterator(); // 获得第一个表单的迭代器
		int rowNo = 0, cellNo = 0;//行号、列号
        String tempStr = "";
		while (rows.hasNext()) {
			Row row = rows.next(); // 获得行数据
			rowNo = row.getRowNum(); // 获得行号
			if(rowNo < 2 ) continue;//直接从第三行开始读取
			Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
			HashMap<String, Object> rowObj = new HashMap<String, Object>();
			cellNo = 0;//cellNo复位
			while (cells.hasNext()) {
				Cell cell = cells.next();
				cellNo = cell.getColumnIndex();
				// 根据cell中的类型来输出数据
                switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					rowObj.put(cols[cellNo], cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
                    tempStr = cell.getStringCellValue();
                    if(StringUtils.isNotBlank(tempStr)){
                        rowObj.put(cols[cellNo], tempStr.trim());
                    }
					break;
				/*case HSSFCell.CELL_TYPE_BOOLEAN:
					System.out.println(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					System.out.println(cell.getCellFormula());
					break;*/
				default:
                    logger.info("== CellType:"+cell.getCellType());
					//throw new ParameterException("Excel文件第["+(rowNo+1)+","+(cellNo+1)+"]数据格式有误！");
				}
			}
			list.add(rowObj);
		}
		return list;
	}

	public static void main(String[] args) {
		// File file = new File("D:\\Tencent_\\abc.xlxs");
		// System.out.println(file.getName());
		//String[] cols = { "custName", "custCertNo","custTel","loanAmt" };
		//analysisExcelFile(null, cols);
        String temp = "1.3012345678E10";
        System.out.println(temp.trim().replace(".","").replace("E10",""));
	}
}
