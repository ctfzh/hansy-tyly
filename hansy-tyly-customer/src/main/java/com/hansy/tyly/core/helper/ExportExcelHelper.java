package com.hansy.tyly.core.helper;

import com.hansy.tyly.admin.utils.Bean2MapUtil;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.*;

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

	public static void main(String[] args) {
		String name;
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
		
		ExportExcelHelper.export(name, titles, null, cols, datas);
	}

}
