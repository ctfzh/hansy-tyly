package com.hansy.tyly.admin.goods.service.impl;

import com.hansy.tyly.admin.dao.mapper.TGoodsBaseInfoDaoMapper;
import com.hansy.tyly.admin.dao.mapper.TGoodsBaseInfoMapper;
import com.hansy.tyly.admin.dao.mapper.TGoodsFilesMapper;
import com.hansy.tyly.admin.dao.mapper.TGoodsTypeMapper;
import com.hansy.tyly.admin.dao.model.*;
import com.hansy.tyly.admin.goods.service.IAdminGoodsService;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.NumberUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.enumClass.CodeEnum;
import com.hansy.tyly.admin.utils.enumClass.CodeNameEnum;
import com.hansy.tyly.core.configuration.CacheConfiguration;
import com.hansy.tyly.core.helper.NPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class AdminGoodsServiceImpl implements IAdminGoodsService {
    @Autowired
    private TGoodsBaseInfoMapper goodsBaseInfoMapper;
    @Autowired
    private TGoodsFilesMapper goodsFilesMapper;
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;
    @Autowired
    private TGoodsBaseInfoDaoMapper goodsBaseInfoDaoMapper;
    @Autowired
    private TGoodsTypeMapper goodsTypeMapper;






    //生成商品编号 码值+6位随机数+4位序列
    public String creatGoodsNo(String codeName){
        StringBuffer goodsNo=new StringBuffer(sysUserRoleMenuService.getCodeValueByName(codeName, CodeEnum.userType.getCode()));
        if(goodsNo.length()>0){
            goodsNo.append(RandomUtil.number(6)).append(NumberUtil.integer(4));
        }
        return  goodsNo.toString();

    }


    /**
     * 添加商品
     * @param goodsBaseInfos
     * @throws Exception
     */
    @Override
    public void addGoods(GoodsBaseInfos goodsBaseInfos) throws Exception {
        //获取商品基本信息然后保存
        TGoodsBaseInfo baseInfo= goodsBaseInfos.getGoodsBaseInfo();
        //获取类型码值
        String goodsNo=creatGoodsNo(CodeNameEnum.platform.getName());
        //保存商品附件
        List<TGoodsFiles> goodsFilesList=goodsBaseInfos.getGoodsFilesList();
        if(null!=goodsFilesList && goodsFilesList.size()>0) {
            goodsFilesList.forEach(node -> {
                //设置商品编号
                node.setUploadDate(new Date());
                node.setGoodsNo(goodsNo);
                //设置文件状态
                node.setFileStatus(sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.using.getName(),
                        CodeEnum.goodsFileStatus.getCode()));

                goodsFilesMapper.insertSelective(node);
                if (node.getIsMain()) {
                    baseInfo.setMianPicFileNo(node.getFileNo());
                }

            });
        }
        baseInfo.setGoodsNo(goodsNo);
        baseInfo.setGoodsAscription(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()
                )
        );
        baseInfo.setGoodsStatus(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()
                )
        );
        //保存商品信息
        goodsBaseInfoMapper.insertSelective(baseInfo);
    }

    /**
     * 平台修改商品信息
     * @param goodsBaseInfos
     * @throws Exception
     */
    @Override
    public void editGoods(GoodsBaseInfos goodsBaseInfos) throws Exception {
        //获取商品基本信息然后保存
        TGoodsBaseInfo baseInfo= goodsBaseInfos.getGoodsBaseInfo();
        //修改商品基本信息
        if(null!=baseInfo){
            goodsBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
        }

        //修改商品附件
        List<TGoodsFiles> goodsFilesList=goodsBaseInfos.getGoodsFilesList();
        if(null!=goodsFilesList && goodsFilesList.size()>0){

            List<String> newPath=new ArrayList<>();
            List<String> tempPath=new ArrayList<>();
            List<String> oldPath=new ArrayList<>();
            goodsFilesList.forEach(node ->{
                newPath.add(node.getFilePath());
            });
            if(newPath.size()>0){
                List<TGoodsFiles> oldGoodsFilesList=goodsFilesMapper.
                        selectByGoodsNos(goodsFilesList.get(0).getGoodsNo(),
                               sysUserRoleMenuService.getCodeValueByName(
                                       CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode())
                        );
                oldGoodsFilesList.forEach(node->{
                    oldPath.add(node.getFilePath());
                    tempPath.add(node.getFilePath());
                });
            }
            /*//删除后 剩下的旧链接都是不要的
            oldPath.removeAll(newPath);
            if(null!=oldPath && oldPath.size()>0){
                oldPath.forEach(node->{
                    goodsFilesMapper.updateByGoodsAndFilePath(
                            sysUserRoleMenuService.getCodeValueByName(
                                    CodeNameEnum.deleted.getName(),CodeEnum.goodsFileStatus.getCode()),
                            goodsFilesList.get(0).getGoodsNo(),node
                    );
                });
            }
            tempPath.removeAll(oldPath);*/
            //删除后 剩下的旧链接都是不要的
            oldPath.removeAll(newPath);
            if(null!=oldPath && oldPath.size()>0){
                TGoodsFilesExample filesExample=new TGoodsFilesExample();
                TGoodsFilesExample.Criteria fileCriteria=filesExample.createCriteria();
                fileCriteria.andFilePathIn(oldPath);
                fileCriteria.andFileTypeEqualTo(
                        sysUserRoleMenuService.getCodeValueByName(
                                CodeNameEnum.goodsFiles.getName(),CodeEnum.fileType.getCode()));
                fileCriteria.andGoodsNoEqualTo(baseInfo.getGoodsNo());
                goodsFilesMapper.deleteByExample(filesExample);
            }
            tempPath.removeAll(oldPath);

            //中间的的链接就是需要修改的
            if(null!=tempPath && tempPath.size()>0){//新增的
                tempPath.forEach(node->{
                    goodsFilesList.forEach(f->{
                        if(f.getFilePath().equals(node)){
                            goodsFilesMapper.updateByFileNameAndFilePath(f.getIsMain(),f.getFileName(),f.getFilePath());
                        }
                    });


                });
            }



            newPath.removeAll(tempPath);

            TGoodsBaseInfo oldBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(goodsFilesList.get(0).getGoodsNo());
            TGoodsFiles oldFile=goodsFilesMapper.selectByPrimaryKey(oldBaseInfo.getMianPicFileNo());
            //如果 文件为删除状态
            if(null!=oldFile && sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.deleted.getName(),CodeEnum.goodsFileStatus.getCode()).equals(oldFile.getFileStatus())){
                //先把该文件的主图状态修改为false
                oldFile.setIsMain(false);
                goodsFilesMapper.updateByPrimaryKeySelective(oldFile);
            }

            //剩下的链接就是需要新增的
            if(null!=newPath && newPath.size()>0){//新增的
                newPath.forEach(node->{
                    TGoodsFiles goodsFiles=new TGoodsFiles();
                    goodsFiles.setFilePath(node);

                    goodsFilesList.forEach(f->{
                        if(f.getFilePath().equals(node)){
                            goodsFiles.setFileName(f.getFileName());
                            goodsFiles.setIsMain(f.getIsMain());
                        }
                    });

                    goodsFiles.setFileStatus(sysUserRoleMenuService.getCodeValueByName(
                            CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));

                    goodsFiles.setGoodsNo(goodsFilesList.get(0).getGoodsNo());
                    goodsFiles.setUploadDate(new Date());
                    goodsFilesMapper.insertSelective(goodsFiles);
                });
            }

            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andIsMainEqualTo(true);
            criteria.andGoodsNoEqualTo(oldBaseInfo.getGoodsNo());
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            oldBaseInfo.setMianPicFileNo(filesList.get(0).getFileNo());
            goodsBaseInfoMapper.updateByPrimaryKeySelective(oldBaseInfo);

        }

    }
    //查看商品列表
    @Override
    public List<GoodsBaseInfos> getGoodsList(Map<String, Object> params) throws Exception {
        params.put("goodsAscription",sysUserRoleMenuService.getCodeValueByName(
                                      CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()));
        NPageHelper.startPage(params);
        return goodsBaseInfoDaoMapper.findGoodsListByParams(params);
    }

    //上下架商品
    @Override
    public void onSaleOrOffShelvesGoods(List<TGoodsBaseInfo> baseInfoList) throws Exception {
        baseInfoList.forEach(baseInfo -> {
            TGoodsBaseInfo oldBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(baseInfo.getGoodsNo());
            //判断商品是否属于平台
            if(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(
                    oldBaseInfo.getGoodsAscription())){
                throw new RuntimeException("该商品不属于平台");
            }else{
                setOndate(baseInfo);
            }
            goodsBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
        });
    }

    private void setOndate(TGoodsBaseInfo baseInfo){
        //判断状态上下架 设置上下架时间
        if(baseInfo.getOnStatus().equals(
                sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode())
                )
        ){//上架
            baseInfo.setOnDate(new Date());
        }else{
            baseInfo.setDownDate(new Date());
        }
    }

    @Override
    public void deleteGoods(String goodsNo) throws Exception {
        TGoodsBaseInfo oldBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(goodsNo);
        //判断商品是否属于平台
        if(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()).equals(
                oldBaseInfo.getGoodsAscription())){
            throw new RuntimeException("该商品不属于平台");
        }
        oldBaseInfo.setGoodsStatus(
                sysUserRoleMenuService.getCodeValueByName(
                        CodeNameEnum.delete.getName(),CodeEnum.goodsStaus.getCode()
                )
        );
        goodsBaseInfoMapper.updateByPrimaryKeySelective(oldBaseInfo);
    }

    /**
     * 平台查看商品详情
     * @param goodsNo
     * @return
     * @throws Exception
     */
    @Override
    public GoodsBaseInfos getGoodsBaseInfo(String goodsNo) throws Exception {
        GoodsBaseInfos goodsBaseInfos=goodsBaseInfoDaoMapper.getGoodsInfo(goodsNo);
        if(null!=goodsBaseInfos){
            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andFileStatusEqualTo(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));
            criteria.andGoodsNoEqualTo(goodsBaseInfos.getGoodsBaseInfo().getGoodsNo());
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            if(null!=filesList && filesList.size()>0){
                goodsBaseInfos.setGoodsFilesList(filesList);
            }
        }
        return goodsBaseInfos;
    }
    //添加商品分类
    @Override
    public void addGoodsType(TGoodsType goodsType) throws Exception {
        goodsType.setTypeNo(RandomUtil.number(4)+NumberUtil.integer(4));
        goodsType.setInsertDate(new Date());
        goodsType.setTypeStatus(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.enable.getName(),CodeEnum.goodsTypeStatus.getCode()
        ));
        goodsTypeMapper.insertSelective(goodsType);
    }

    //修改商品分类
    @Override
    public void editGoodsType(TGoodsType goodsType) throws Exception {
        //当商品分类停用时
        if(sysUserRoleMenuService.getCodeValueByName(
                CodeNameEnum.disable.getName(),CodeEnum.goodsTypeStatus.getCode()
        ).equals(goodsType.getTypeStatus())
                ){
            TGoodsBaseInfoExample example=new TGoodsBaseInfoExample();
            TGoodsBaseInfoExample.Criteria criteria=example.createCriteria();
            criteria.andTypeNoEqualTo(goodsType.getTypeNo());
            criteria.andGoodsAscriptionEqualTo(sysUserRoleMenuService.getCodeValueByName(
                    CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()
                    )
            );
            List<TGoodsBaseInfo> list=goodsBaseInfoMapper.selectByExample(example);
            if(null!=list && list.size()>0){
                throw new RuntimeException("该商品正在使用");
            }
        }
        goodsType.setUpdateDate(new Date());
        goodsTypeMapper.updateByPrimaryKeySelective(goodsType);


    }

    @Override
    public List<TGoodsType> getAllGoodsType(Map<String, Object> params) throws Exception {
        NPageHelper.startPage(params);
        return goodsTypeMapper.selectByParams(params);
    }
}
