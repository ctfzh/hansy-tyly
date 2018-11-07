package com.hansy.tyly.admin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.dao.model.TReportConf;
import com.hansy.tyly.admin.dao.model.TReportTableConfDtl;
import com.hansy.tyly.admin.report.service.IReportSettingService;
import com.hansy.tyly.admin.utils.Context;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.ExportExcelHelper;
import com.hansy.tyly.core.helper.FileNameHelper;

import io.swagger.annotations.Api;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "平台报表配置")
@RestController
@RequestMapping("/api/report")
public class ReportSettingController {
	@Autowired
	private IReportSettingService reportSettingService;

	private final org.slf4j.Logger logger = LoggerFactory
			.getLogger(ReportSettingController.class);

	/*
	 * 平台报表详情查询
	 */
	@RequestMapping(value = "/selectReport", method = RequestMethod.POST)
	public CryptoCmd selectReport(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		// 获取报表名称
		try {
			List<TReportConf> list = reportSettingService.getReportInfo(params);
			cryptoCmd.setOut(list);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			cryptoCmd.setMessage(Context.GET_LIST_FALSE);
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;

	}

	/**
	 * 添加sql报表配置; 修改报表配置
	 */
	@RequestMapping(value = "/insertOrUpdateReport", method = RequestMethod.POST)
	public CryptoCmd insertOrUpdateReport(CryptoCmd cryptoCmd) throws Exception {
		TReportConf tReportConf = cryptoCmd.getInObject("tReportConf",
				TReportConf.class);
		try {
			String reportKey = reportSettingService
					.insertOrUpdateReportInfo(tReportConf);
			cryptoCmd.setOut(reportKey);
			cryptoCmd.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * sql配置基本信息查询
	 */
	@RequestMapping(value = "/selectReportList", method = RequestMethod.POST)
	public CryptoCmd selectReportInfo(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		try {
			TReportConf tReportConf = reportSettingService
					.getReportLIstInfo(params);
			cryptoCmd.setOut(tReportConf);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * sql报表字段列显示基本信息
	 */
	@RequestMapping(value = "/selectReportInfo", method = RequestMethod.POST)
	public CryptoCmd selectReportClumo(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		try {
			List<TReportTableConfDtl> list = reportSettingService
					.getReportcolomnInfo(params);
			cryptoCmd.setOut(list);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * 添加字段配置基本信息 修改字段配置基信息
	 * 
	 */
	@RequestMapping(value = "/insertOrUpdateColom", method = RequestMethod.POST)
	public CryptoCmd insertOrUpdateColom(CryptoCmd cryptoCmd) throws Exception {
		TReportTableConfDtl tReportTableConfDtl = cryptoCmd.getInObject(
				"tReportTableConfDtl", TReportTableConfDtl.class);
		try {
			reportSettingService.insertOrUpdateClomn(tReportTableConfDtl);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * 删除当前选择字段
	 */
	@RequestMapping(value = "/deleteClomn", method = RequestMethod.POST)
	public CryptoCmd deleteClomn(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		try {
			reportSettingService.deleteClomn(params);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * 删除当前报表
	 */
	@RequestMapping(value = "/deleteReport", method = RequestMethod.POST)
	public CryptoCmd deleteReport(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		try {
			reportSettingService.deleteReport(params);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}

	/**
	 * 查询报表
	 */
	@RequestMapping(value = "/selectReportMap", method = RequestMethod.POST)
	public CryptoCmd selectReportMap(CryptoCmd cryptoCmd) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		String reportKey = params.get("reportKey").toString();
		List list = new ArrayList();
		String[] reportKeyValue = reportKey.split(",");
		for (int i = 0; i < reportKeyValue.length; i++) {
			params.put("reportKey", reportKeyValue[i]);
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				// 查询报表sql 数据
				map.put("data", reportSettingService.getDataJson(params));
				// 查询展示条件
				map.put("conditionType",
						reportSettingService.getContitionType(params));
				// 查询echart配置信息
				map.put("echartInfo",
						reportSettingService.getReportLIstInfo(params));
				// 查询需要展示字段数
				map.put("codenum",
						reportSettingService.getReportcolomncount(params));
				list.add(map);

			} catch (Exception e) {
				e.printStackTrace();
				cryptoCmd.setSuccess(false);
			}
		}
		cryptoCmd.setOut(list);
		cryptoCmd.setSuccess(true);
		return cryptoCmd;
	}

	/**
	 * 导出excel
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public CryptoCmd exportExcel(CryptoCmd cryptoCmd,
			HttpServletResponse response) throws Exception {
		Map<String, Object> params = cryptoCmd.getParams();
		try {
			ExportExcelHelper exportExcelHelper = new ExportExcelHelper();
			exportExcelHelper.downloadExcel(params, response);
			cryptoCmd.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			cryptoCmd.setSuccess(false);
		}
		return cryptoCmd;
	}
    /**
     * 下载excel
     * @param request
     * @param response
     * @param cryptoCmd
     * @throws IOException
     */
	@RequestMapping(value = "/downLoadExcel", method = RequestMethod.GET)
	public void downLoadExcel(HttpServletRequest request,
			HttpServletResponse response, CryptoCmd cryptoCmd)
			throws IOException {
		Map<String, Object> params = cryptoCmd.getParams();
		String title = params.get("title").toString();
		
		
        String[] headers = params.get("rowName").toString().split(",");
        
        String[] cols = params.get("cols").toString().split(",");

		
        List<Map<String, Object>> dataList= getexchangeInfo(params.get("dataList").toString());

		Workbook workbook = ExportExcelHelper.export(title, headers, cols, dataList);
				

		String filename = FileNameHelper.encode(request, title);
		OutputStream outputStream = null;
		try {
			response.setContentType("application/vnd.ms-excel");
			filename = title + ".xls";
			String headerValue = "attachment;";
			headerValue += " filename=\"" + encodeURIComponent(filename)
					+ "\";";
			headerValue += " filename*=utf-8''" + encodeURIComponent(filename);
			response.setHeader("Content-disposition", headerValue);
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			throw new ServiceException("导出Excel失败", e);
		} finally {
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (Exception e) {
				}
		}
	}
	public List<Map<String, Object>> getexchangeInfo(String json){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
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
			Map<String,Object> map = new HashMap<String,Object>();
			//第二次分割
			String[] mapArray = splitArray[i].split(",");
			for(int j=0 ;j<mapArray.length ;j++) {
				String str = mapArray[j].replaceAll("\"", "");
				//第三次分割,为了防止value为空，下面加了一个长度判断
				String[] keyValue = str.split(":");
				if(keyValue.length==2) map.put(keyValue[0], keyValue[1]);
				else map.put(keyValue[0], "");
			}
			list.add(map);
		}
     return list;
	}
	 public static String encodeURIComponent(String value) {
	        try {
	            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
