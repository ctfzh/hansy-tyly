package com.lemoncome.watchdog.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemoncome.watchdog.tempindicators.service.TempindicatorsService;
import com.lemoncome.watchdog.tempindicators.util.AESUtil;
@RestController
@RequestMapping("/api/tempindicators")
public class TempindicatorsController {
    @Autowired
    private TempindicatorsService tempindicatorsService;
	 @PostMapping("/tem")
	 public void queryCodeType(String userid) {
		String aesid = null;
		try {
		aesid = AESUtil.decrypt(userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if("kanjiagou".equals(aesid)){
		 System.out.println("=====================");
		 tempindicatorsService.queryTempindicators();
		 }else{
	     System.out.println(userid+"====================="); 
		 }
	    }
}
