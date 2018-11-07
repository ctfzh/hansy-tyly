package com.hansy.tyly.admin.sale.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pub_visit_files")
public class VisitFiles {
    /**
     * 文件编号
     */
    @Id
    @Column(name = "file_no")
    private String fileNo;

    /**
     * 拜访id
     */
    @Column(name = "visit_no")
    private String visitNo;

    /**
     * 图片路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 图片名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 图片状态
     */
    @Column(name = "file_status")
    private String fileStatus;

    /**
     * 上传时间
     */
    @Column(name = "upload_date")
    private Date uploadDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取文件编号
     *
     * @return file_no - 文件编号
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置文件编号
     *
     * @param fileNo 文件编号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    /**
     * 获取拜访id
     *
     * @return visit_no - 拜访id
     */
    public String getVisitNo() {
        return visitNo;
    }

    /**
     * 设置拜访id
     *
     * @param visitNo 拜访id
     */
    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    /**
     * 获取图片路径
     *
     * @return file_path - 图片路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置图片路径
     *
     * @param filePath 图片路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取图片名称
     *
     * @return file_name - 图片名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置图片名称
     *
     * @param fileName 图片名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取图片状态
     *
     * @return file_status - 图片状态
     */
    public String getFileStatus() {
        return fileStatus;
    }

    /**
     * 设置图片状态
     *
     * @param fileStatus 图片状态
     */
    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    /**
     * 获取上传时间
     *
     * @return upload_date - 上传时间
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * 设置上传时间
     *
     * @param uploadDate 上传时间
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "VisitFiles{" +
                "fileNo='" + fileNo + '\'' +
                ", visitNo='" + visitNo + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileStatus='" + fileStatus + '\'' +
                ", uploadDate=" + uploadDate +
                ", updateDate=" + updateDate +
                '}';
    }
}