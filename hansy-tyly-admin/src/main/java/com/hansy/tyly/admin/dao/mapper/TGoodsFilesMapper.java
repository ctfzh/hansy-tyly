package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TGoodsFiles;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TGoodsFilesMapper extends Mapper<TGoodsFiles> {
    List<TGoodsFiles> selectByGoodsNos(@Param("goodsNos") String goodsNos, @Param("fileStatus") String fileStatus);

    void updateByGoodsAndFilePath(@Param("fileStatus") String fileStatus,@Param("goodsNo")String goodsNo, @Param("filePath")String filePath);

    void updateByFileNameAndFilePath(@Param("isMain")Boolean isMain, @Param("fileName")String fileName, @Param("filePath")String filePath);
}
