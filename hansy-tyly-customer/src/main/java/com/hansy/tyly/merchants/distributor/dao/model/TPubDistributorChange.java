package com.hansy.tyly.merchants.distributor.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pub_distributor_change")
public class TPubDistributorChange {
    /**
     * ����
     */
    @Id
    @Column(name = "table_key")
    private String tableKey;

    /**
     * �����̱��
     */
    @Column(name = "distributor_no")
    private String distributorNo;

    /**
     * ����������
     */
    @Column(name = "distributor_name")
    private String distributorName;

    /**
     * �����̱��
     */
    @Column(name = "legal_name")
    private String legalName;

    /**
     * ������ע���
     */
    @Column(name = "distributor_reg_no")
    private String distributorRegNo;

    /**
     * ��ҵ����
     */
    @Column(name = "company_type")
    private String companyType;

    /**
     * �����̵�ַ
     */
    @Column(name = "distributor_addre")
    private String distributorAddre;

    /**
     * ��������ϵ��
     */
    @Column(name = "distributor_contact")
    private String distributorContact;

    /**
     * ��������ϵ�˵绰
     */
    @Column(name = "distributor_contact_phone")
    private String distributorContactPhone;

    /**
     * ������״̬
     */
    @Column(name = "distributor_status")
    private String distributorStatus;

    /**
     * ����ʱ��
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * ��������
     */
    @Column(name = "staff_no")
    private String staffNo;

    /**
     * ��ȡ����
     *
     * @return table_key - ����
     */
    public String getTableKey() {
        return tableKey;
    }

    /**
     * ��������
     *
     * @param tableKey ����
     */
    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * ��ȡ�����̱��
     *
     * @return distributor_no - �����̱��
     */
    public String getDistributorNo() {
        return distributorNo;
    }

    /**
     * ���þ����̱��
     *
     * @param distributorNo �����̱��
     */
    public void setDistributorNo(String distributorNo) {
        this.distributorNo = distributorNo;
    }

    /**
     * ��ȡ����������
     *
     * @return distributor_name - ����������
     */
    public String getDistributorName() {
        return distributorName;
    }

    /**
     * ���þ���������
     *
     * @param distributorName ����������
     */
    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    /**
     * ��ȡ�����̱��
     *
     * @return legal_name - �����̱��
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * ���þ����̱��
     *
     * @param legalName �����̱��
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * ��ȡ������ע���
     *
     * @return distributor_reg_no - ������ע���
     */
    public String getDistributorRegNo() {
        return distributorRegNo;
    }

    /**
     * ���þ�����ע���
     *
     * @param distributorRegNo ������ע���
     */
    public void setDistributorRegNo(String distributorRegNo) {
        this.distributorRegNo = distributorRegNo;
    }

    /**
     * ��ȡ��ҵ����
     *
     * @return company_type - ��ҵ����
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * ������ҵ����
     *
     * @param companyType ��ҵ����
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * ��ȡ�����̵�ַ
     *
     * @return distributor_addre - �����̵�ַ
     */
    public String getDistributorAddre() {
        return distributorAddre;
    }

    /**
     * ���þ����̵�ַ
     *
     * @param distributorAddre �����̵�ַ
     */
    public void setDistributorAddre(String distributorAddre) {
        this.distributorAddre = distributorAddre;
    }

    /**
     * ��ȡ��������ϵ��
     *
     * @return distributor_contact - ��������ϵ��
     */
    public String getDistributorContact() {
        return distributorContact;
    }

    /**
     * ���þ�������ϵ��
     *
     * @param distributorContact ��������ϵ��
     */
    public void setDistributorContact(String distributorContact) {
        this.distributorContact = distributorContact;
    }

    /**
     * ��ȡ��������ϵ�˵绰
     *
     * @return distributor_contact_phone - ��������ϵ�˵绰
     */
    public String getDistributorContactPhone() {
        return distributorContactPhone;
    }

    /**
     * ���þ�������ϵ�˵绰
     *
     * @param distributorContactPhone ��������ϵ�˵绰
     */
    public void setDistributorContactPhone(String distributorContactPhone) {
        this.distributorContactPhone = distributorContactPhone;
    }

    /**
     * ��ȡ������״̬
     *
     * @return distributor_status - ������״̬
     */
    public String getDistributorStatus() {
        return distributorStatus;
    }

    /**
     * ���þ�����״̬
     *
     * @param distributorStatus ������״̬
     */
    public void setDistributorStatus(String distributorStatus) {
        this.distributorStatus = distributorStatus;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return insert_date - ����ʱ��
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * ��������ʱ��
     *
     * @param insertDate ����ʱ��
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * ��ȡ��������
     *
     * @return staff_no - ��������
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * ������������
     *
     * @param staffNo ��������
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}