package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.TGoodsFiles;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsFilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TGoodsFilesMapper extends Mapper<TGoodsFiles> {

    List<TGoodsFiles> selectByGoodsNos(@Param("goodsNos") String goodsNos, @Param("fileStatus") String fileStatus,
                                       @Param("fileType") String fileType);

    void updateByGoodsAndFilePath(@Param("fileStatus") String fileStatus,@Param("goodsNo")String goodsNo,
                                  @Param("filePath")String filePath,@Param("fileType") String fileType);

    void updateByFileNameAndFilePath(@Param("isMain")Boolean isMain, @Param("fileName")String fileName, @Param("filePath")String filePath);
}