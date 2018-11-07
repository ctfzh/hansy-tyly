package com.lemoncome.watchdog.tempindicators.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lemoncome.watchdog.tempindicators.model.CustBillModel;
import com.lemoncome.watchdog.tempindicators.model.CustIndcRstModel;
import com.lemoncome.watchdog.tempindicators.model.CustTempindicatorsModel;

public interface TempindicatorsMapper {

	String testOne();

	void savebatch(List<CustIndcRstModel> custIndcRstModelList);

	List<CustBillModel> queryCustBill();

	List<CustTempindicatorsModel> queryALLTempindicators(@Param("prodIdList") List<String> prodIdList);

	void updatebatchStats(List<String> billIdList);

	void deleteIndicators();


}
