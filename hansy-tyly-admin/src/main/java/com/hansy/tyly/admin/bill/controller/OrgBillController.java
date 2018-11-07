package com.hansy.tyly.admin.bill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.bill.service.OrgBillService;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.ExportExcelHelper;
import com.hansy.tyly.core.helper.FileNameHelper;

@Api(description = "机构充值")
@RestController
@RequestMapping("/api/orgBill")
public class OrgBillController extends BaseController {

    @Autowired
    private OrgBillService orgBillService;

    @ApiOperation(value = "查询账单", notes = "{\"orgName\":\"\",\"orgTel\":\"\",\"status\":\"\",\"insertTime\":\"\"}")
    @PostMapping("/queryBill")
    public CryptoCmd queryBill(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = orgBillService.queryOrgBillByCondition(params);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "修改状态", notes = "{\"billId\":\"\"}")
    @PostMapping("/alterBillStatus")
    public CryptoCmd alterBillStatus(CryptoCmd cryptoCmd) {
        String billId = cryptoCmd.getInString("billId");
        orgBillService.alterBillStatus(billId, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "充值", notes = "{\"orgId\":\"\",\"amt\":\"\",\"extAmt\":\"\"}")
    @PostMapping("/orgRecharge")
    public CryptoCmd orgRecharge(CryptoCmd cryptoCmd) {
        String orgId = cryptoCmd.getInString("orgId");
        String amt = cryptoCmd.getInString("amt");
        String billType = cryptoCmd.getInString("extAmt");
        orgBillService.orgRecharge(orgId, new BigDecimal(amt),billType, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "查询消费明细", notes = "{\"orgName\":\"\",\"busiDate\":\"\"}")
    @PostMapping("/queryBillDtl")
    public CryptoCmd queryBillDtl(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = orgBillService.queryBillDtl(params);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "下载消费明细", notes = "{\"orgName\":\"\",\"busiDate\":\"\"}")
    @GetMapping("/dowBillDtl")
    public void dowBillDtl(HttpServletRequest request, HttpServletResponse response,
                           CryptoCmd cryptoCmd) throws IOException {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList= orgBillService.dowloadExl(params);
        String title = "消费明细";
        String[] headers = "机构名称,购买人员,使用客户,使用时间,产品名称,产品频次,金额,产品状态,".split(",");
        String[] cols = "orgName,userName,custName,busiDate,prodName,freType,billAmt,status".split(",");
        String[] widths = "150,150,150,150,150,150,150,150".split(",");
        Workbook workbook = ExportExcelHelper.export(title, headers, widths, cols, mapList);

        String filename = FileNameHelper.encode( request, title);
        OutputStream outputStream = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            filename=title+".xls";
            String headerValue = "attachment;";
            headerValue += " filename=\"" + encodeURIComponent(filename) +"\";";
            headerValue += " filename*=utf-8''" + encodeURIComponent(filename);
            response.setHeader("Content-disposition",headerValue);
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            throw new ServiceException("导出Excel失败", e);
        } finally {
            if ( outputStream != null ) try {
                outputStream.close();
            } catch (Exception e) {
            }
        }
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
