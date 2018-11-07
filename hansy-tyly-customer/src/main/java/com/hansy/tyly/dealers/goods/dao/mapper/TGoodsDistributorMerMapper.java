package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.TGoodsDistributorMer;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TGoodsDistributorMerMapper extends Mapper<TGoodsDistributorMer> {
    void updatePriceByDisNo(@Param("price") String price,@Param("disNo") String disNo,@Param("goodsNo") String goodsNo)throws Exception;
    List<Map> getGoodsDM(@Param("disNo")String disNo,@Param("onStatus")String onStatus,@Param("goodsNo") String goodsNo)throws Exception;


    List<Map> getNotGoodsDM(@Param("disNo")String disNo, @Param("onStatus")String codeValueByName, @Param("goodsNo")String goodsNo);


}