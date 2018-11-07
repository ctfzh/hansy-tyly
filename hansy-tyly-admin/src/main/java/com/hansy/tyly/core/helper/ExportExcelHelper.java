package com.hansy.tyly.core.helper;

import com.hansy.tyly.admin.utils.Bean2MapUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

public class ExportExcelHelper {
	

	public static Workbook export(String title, String[] headers, String[] cols, List<?> datas ) {
		return export(title, Arrays.asList(headers), null, Arrays.asList(cols), datas);
	}
	
	public static Workbook export(String title, String[] headers, String[] widths, String[] cols, List<?> datas ) {
		return export(title, Arrays.asList(headers), Arrays.asList(widths), Arrays.asList(cols), datas);
	}
	
	@SuppressWarnings("unchecked")
	public static Workbook export(String title, List<String> headers, List<String> widths, List<String> cols, List<?> datas ) {
		if (datas == null) datas = new ArrayList<>();
		if ( widths == null ) {
			widths = new ArrayList<String>();
		} else {
			List<String> list = new ArrayList<String>();
			list.addAll(widths);
			widths = list;
		}
		
		int size = widths.size();
		
		if ( size < headers.size() ) {
			size = headers.size();
		}
		
		if ( size < cols.size() ) {
			size = cols.size();
		}
		
		for ( int index = widths.size(); index < size; index++ ) {
			widths.add("100");
		}
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet( new SimpleDateFormat("YYYY年MM月dd日").format(new Date()) );

		Row row;
		Cell cell;
		CellStyle cellStyle;
		Font font;
		int i, j;

		font = workbook.createFont();
		font.setFontName( "华文楷体" );
		font.setFontHeightInPoints((short)18);
		font.setBoldweight((short)500);
		font.setCharSet(Font.DEFAULT_CHARSET);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.index);
		cellStyle.setFont(font);
		
		sheet.addMergedRegion(new CellRangeAddress( 0, 0, 0, headers.size() - 1));
		row = sheet.createRow(0);
		row.setHeight((short)800);
		cell = row.createCell(0);
		cell.setCellValue( title );
		cell.setCellStyle(cellStyle);
		
		
		font = workbook.createFont();
		font.setFontName("Microsoft YaHei");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight((short)500);
		font.setCharSet(Font.DEFAULT_CHARSET);
		//headFont.setColor(IndexedColors.BLUE_GREY.index);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setFont(font);
		//cellStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setFillForegroundColor((short)65535);// 设置背景色
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		
		row = sheet.createRow( 1 );
		row.setHeight((short) 400);
		for ( i = 0; i < headers.size(); i++ ) {
			sheet.autoSizeColumn( i, true );
			cell = row.createCell( i ); 
			cell.setCellValue( headers.get( i ) );
			cell.setCellStyle(cellStyle);
			sheet.setColumnWidth(i, (short) Math.round(Integer.valueOf(widths.get(i)) * 30 ));
		}
		
		
		font = workbook.createFont();
		font.setFontName("Microsoft YaHei");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		font.setCharSet(Font.DEFAULT_CHARSET);
		
		cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);

		Object object;
		Map<String, Object> map;
		String key;
		String value;

		for ( i = 0; i < datas.size(); i++ ) {
			object = datas.get( i );
			if ( object instanceof Map ) map = (Map<String, Object>) object;
			else map = Bean2MapUtil.transBean2Map(object);
			row = sheet.createRow( 2 + i );
			row.setHeight((short) 400);
			for ( j = 0; j < cols.size(); j++ ) {
				key = cols.get( j );
				object = map.get( key );
				if ( object == null ) value = "";
				else value = object.toString();
				cell = row.createCell( j );
				cell.setCellValue(value);
			}
		}
		
		/*try {
			File file = new File("d:/export/" + new Date().getTime() + ".xls");
			System.out.println(file.getAbsolutePath());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return workbook;

	}
	/**
	 * web页面导出excel 公共方法
	 *@author xiafei
	 * title 表的标题
	 * rowName 表的列名
	 * fileName  文件名
	 * dataList 数据
	 * response 请求
	 * 
	 */
	 public void downloadExcel(
			      Map<String,Object> param,
			      HttpServletResponse response
			 ) throws Exception {
	        try {
	            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象
	            HSSFSheet sheet = workbook.createSheet(param.get("title").toString());                  // 创建工作表

	            // 产生表格标题行
	            HSSFRow rowm = sheet.createRow(0);
	            HSSFCell cellTiltle = rowm.createCell(0);
	            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
	            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
	            HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象
	            String[] rowName = new String[]{param.get("rowName").toString()};
	            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
	            cellTiltle.setCellStyle(columnTopStyle);
	            cellTiltle.setCellValue(param.get("title").toString());

	            // 定义所需列数
	            int columnNum = rowName.length;
	            HSSFRow rowRowName = sheet.createRow(2);                // 在索引2的位置创建行(最顶端的行开始的第二行)

	            // 将列头设置到sheet的单元格中
	            for (int n = 0; n < columnNum; n++) {
	                HSSFCell cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
	                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
	                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
	                cellRowName.setCellValue(text);                                 //设置列头单元格的值
	                cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式
	            }
	              
	            List<Object[]> dataList= getexchangeInfo(param.get("dataList").toString());
	            //将查询出的数据设置到sheet对应的单元格中
	            for (int i = 0; i < dataList.size(); i++) {

	                Object[] obj = dataList.get(i);//遍历每个对象
	                HSSFRow row = sheet.createRow(i + 3);//创建所需的行数

	                for (int j = 0; j < obj.length; j++) {
	                    HSSFCell cell = null;   //设置单元格的数据类型
	                    if (j == 0) {
	                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
	                        cell.setCellValue(i + 1);
	                    } else {
	                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
	                        if (!"".equals(obj[j]) && obj[j] != null) {
	                            cell.setCellValue(obj[j].toString());                       //设置单元格的值
	                        }
	                    }
	                    cell.setCellStyle(style);                                   //设置单元格样式
	                }
	            }
	            //让列宽随着导出的列长自动适应
	            for (int colNum = 0; colNum < columnNum; colNum++) {
	                int columnWidth = sheet.getColumnWidth(colNum) / 256;
	                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
	                    HSSFRow currentRow;
	                    //当前行未被使用过
	                    if (sheet.getRow(rowNum) == null) {
	                        currentRow = sheet.createRow(rowNum);
	                    } else {
	                        currentRow = sheet.getRow(rowNum);
	                    }
	                    if (currentRow.getCell(colNum) != null) {
	                        HSSFCell currentCell = currentRow.getCell(colNum);
	                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
	                            int length = currentCell.getStringCellValue().getBytes().length;
	                            if (columnWidth < length) {
	                                columnWidth = length;
	                            }
	                        }
	                    }
	                }
	                if (colNum == 0) {
	                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
	                } else {
	                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
	                }
	            }

	            if (workbook != null) {
	                try {
	                    if (response != null) {
	                        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	                        response.setHeader("Content-Disposition", "attachment;filename=\""+new String(param.get("fileName").toString().getBytes("gb2312"),"ISO8859-1"));
	                        OutputStream out = response.getOutputStream();
	                        workbook.write(out);
	                        out.close();
	                    } else {
	                        FileOutputStream outputStream = new FileOutputStream("D:/"+param.get("fileName").toString());
	                        workbook.write(outputStream);
	                        outputStream.close();
	                    }
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 /*
	     * 列头单元格样式
	     */
	    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

	        // 设置字体
	        HSSFFont font = workbook.createFont();
	        //设置字体大小
	        font.setFontHeightInPoints((short) 12);
	        //字体加粗
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        //设置字体名字
	        font.setFontName("微软雅黑");
	        //设置样式;
	        HSSFCellStyle style = workbook.createCellStyle();
	        //设置底边框;
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        //设置底边框颜色;
	        style.setBottomBorderColor(HSSFColor.BLACK.index);
	        //设置左边框;
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        //设置左边框颜色;
	        style.setLeftBorderColor(HSSFColor.BLACK.index);
	        //设置右边框;
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        //设置右边框颜色;
	        style.setRightBorderColor(HSSFColor.BLACK.index);
	        //设置顶边框;
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        //设置顶边框颜色;
	        style.setTopBorderColor(HSSFColor.BLACK.index);
	        //在样式用应用设置的字体;
	        style.setFont(font);
	        //设置自动换行;
	        style.setWrapText(false);
	        //设置水平对齐的样式为居中对齐;
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //设置垂直对齐的样式为居中对齐;
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

	        return style;

	    }

	    /*
	     * 列数据信息单元格样式
	     */
	    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
	        // 设置字体
	        HSSFFont font = workbook.createFont();
	        //设置字体名字
	        font.setFontName("微软雅黑");
	        //设置样式;
	        HSSFCellStyle style = workbook.createCellStyle();
	        //设置底边框;
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        //设置底边框颜色;
	        style.setBottomBorderColor(HSSFColor.BLACK.index);
	        //设置左边框;
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        //设置左边框颜色;
	        style.setLeftBorderColor(HSSFColor.BLACK.index);
	        //设置右边框;
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        //设置右边框颜色;
	        style.setRightBorderColor(HSSFColor.BLACK.index);
	        //设置顶边框;
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        //设置顶边框颜色;
	        style.setTopBorderColor(HSSFColor.BLACK.index);
	        //在样式用应用设置的字体;
	        style.setFont(font);
	        //设置自动换行;
	        style.setWrapText(false);
	        //设置水平对齐的样式为居中对齐;
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //设置垂直对齐的样式为居中对齐;
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        return style;
	    }
	
	
	
	public static void main(String[] args) {
/*		String name;
		List<String> titles = new ArrayList<>();
		List<String> cols = new ArrayList<>();
		List<Object> datas = new ArrayList<>();
		
		name = "测试标题";
		
		titles.add("列1");
		titles.add("列2");
		titles.add("列3");
		
		cols.add("col1");
		cols.add("col2");
		cols.add("col3");
		
		Map<String, Object> map;
		
		map = new HashMap<>();
		map.put("col1", "val1");
		map.put("col2", "val2");
		map.put("col3", "val3");
		
		datas.add(map);
		
		ExportExcelHelper.export(name, titles, null, cols, datas);*/
	    String[] rowsName = new String[]{"oo","xx","oo","xx","xx","oo"};
         System.out.println(rowsName+"sssssssssss");
		
	}
	public List<Object[]> getexchangeInfo(String json){
		List<Object[]> list = new ArrayList<Object[]>();
		//去除两端方括号
		json = json.replaceAll("[\\[\\]]", "");
		//第一次分割
		String[] splitArray = json.split("},");
		for(int i=0; i<splitArray.length;i++) {
			//去除大括号，好方法去除大括号就用了子串的方式
			if(i==splitArray.length-1) {
				splitArray[i]=splitArray[i].substring(1, splitArray[i].length()-1);
			}else {
				splitArray[i]=splitArray[i].substring(1, splitArray[i].length());
			}			
			//第二次分割
			String[] mapArray = splitArray[i].split(",");
			list.add(mapArray);
		}
		return list;
	}

}
