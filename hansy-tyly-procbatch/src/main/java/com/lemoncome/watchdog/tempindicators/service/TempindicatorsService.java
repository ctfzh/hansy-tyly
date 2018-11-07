package com.lemoncome.watchdog.tempindicators.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.tempindicators.mapper.TempindicatorsMapper;
import com.lemoncome.watchdog.tempindicators.model.CustBillModel;
import com.lemoncome.watchdog.tempindicators.model.CustIndcRstModel;
import com.lemoncome.watchdog.tempindicators.model.CustTempindicatorsModel;
import com.lemoncome.watchdog.tempindicators.util.RandomUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Service
public class TempindicatorsService {
   @Autowired
   private TempindicatorsMapper tempindicatorsMapper;
public void queryTempindicators(){
	      List<CustBillModel> custBillModellist = new ArrayList<CustBillModel>();
	      custBillModellist = tempindicatorsMapper.queryCustBill();
	      if(custBillModellist !=null && custBillModellist.size()>0){
	    	  this.Tempindicators(custBillModellist);
	      }
   }
   @SuppressWarnings("deprecation")
public void Tempindicators(List<CustBillModel> custBillModellist) {
	   try {  
           // 实例化Mongo对象，连接27017端口  
           // 本地访问地址：123.57.44.30  27017
		   // 阿里云访问地址：60.205.115.62   27017
		   Mongo mongo = new Mongo("60.205.115.62", 27017);  
           // 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立  
           DB db = mongo.getDB("lemondata");  
           // Get collection from MongoDB, database named "yourDB"  
           // 从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立  
           DBCollection collection = db.getCollection("handle_data");  
           // 创建要查询的document  
           BasicDBObject searchQuery = new BasicDBObject();  
           BasicDBList cond=new BasicDBList(); 
           List<String> prodIdList = new ArrayList<String>();
           List<String> billIdList = new ArrayList<String>();
           List<CustTempindicatorsModel> custTempindicatorsModellist = new ArrayList<CustTempindicatorsModel>();
           for(CustBillModel model : custBillModellist){
        	   prodIdList.add(model.getProdId());
        	   cond.add(model.getBillId());
           }
           searchQuery.put("orderId", new BasicDBObject("$in", cond));  
           // 使用collection的find方法查找document  
           DBCursor cursor = collection.find(searchQuery);  
           custTempindicatorsModellist = tempindicatorsMapper.queryALLTempindicators(prodIdList);
           tempindicatorsMapper.deleteIndicators();
           // 循环输出结果  
           for (DBObject obj :cursor) {
				String orderId = obj.get("orderId").toString();
				for(CustTempindicatorsModel custTempindicatorsModel : custTempindicatorsModellist){
					if(obj.containsKey(custTempindicatorsModel.getParentIndcId())){
				    	JSONObject appliyInfo = JSONObject.parseObject(obj.get(custTempindicatorsModel.getParentIndcId()).toString());
				    	saveTempindicators(orderId,appliyInfo);
				    }
				}
				 billIdList.add(orderId);
			}
           mongo.close();
           mongo=null;
           tempindicatorsMapper.updatebatchStats(billIdList);
       } catch (MongoException e) {  
           e.printStackTrace();  
       }  
   }
   private  void saveTempindicators(String orderId,JSONObject jsonobject){
		try{
		List<CustIndcRstModel> custIndcRstModelList = new ArrayList<CustIndcRstModel>();
		for (Map.Entry<String, Object> entry : jsonobject.entrySet()) {
			CustIndcRstModel custIndcRstModel = new CustIndcRstModel();
			custIndcRstModel.setSysUuid(RandomUtil.UUID());
			custIndcRstModel.setBillId(orderId);
			custIndcRstModel.setIndcId(entry.getKey());
			custIndcRstModel.setIndcValue(entry.getValue().toString());
			custIndcRstModel.setInsertTime(new Date());
			custIndcRstModel.setInsertUserId("SYSTEM");
            System.out.println(entry.getKey() + ":" + entry.getValue());
            custIndcRstModelList.add(custIndcRstModel);
		  }
		    tempindicatorsMapper.savebatch(custIndcRstModelList);
           }catch(Exception e){
           	e.printStackTrace();
           }
       }
   
/*   @PostConstruct
   public void init(){
       String s = tempindicatorsMapper.testOne();
       System.out.println("# r=" + s);
       this.queryTempindicators();

   }*/

}
