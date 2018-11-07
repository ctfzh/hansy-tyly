package com.hansy.tyly.tempindicators.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.tempindicators.model.CustBillModel;
import com.hansy.tyly.tempindicators.model.CustIndcRstModel;
import com.hansy.tyly.tempindicators.model.CustTempindicatorsModel;

public interface TempindicatorsMapper {

	String testOne();

	void savebatch(List<CustIndcRstModel> custIndcRstModelList);

	List<CustBillModel> queryCustBill();

	List<CustTempindicatorsModel> queryALLTempindicators(@Param("prodIdList") List<String> prodIdList);

	void updatebatchStats(List<String> billIdList);

	void deleteIndicators();


}
