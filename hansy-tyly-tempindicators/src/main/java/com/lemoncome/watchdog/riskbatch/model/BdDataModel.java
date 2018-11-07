package com.lemoncome.watchdog.riskbatch.model;

/**
 * 大数据A5发送接口BO类
 * @author admin
 */
public class BdDataModel {
	/**
	 * 流水号
	 */
	private String orderId;
	
	private String ruleId;
	
	private String scoreId;
	
	/**
	 * 系统调用方唯一标识
	 */
	private String appKey="lemon";
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 档案代码
	 */
	private Integer documentNo;
	/**
	 * 是否保险
	 */
	private Integer isInsurance=1;
	/**
	 * 手机使用时长
	 */
	private Integer phoneUseTime;
	/**
	 * 月平均话费
	 */
	private Integer phoneMonPay;
	/**
	 * 工作时长
	 */
	private Integer workTime=1;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String mobliePhone;
	/**
	 * 银行卡号
	 */
	private String bankCard;
	/**
	 * 性别
	 */
	private Integer sex=1;
	/**
	 * 婚姻状况
	 */
	private Integer marry=1;//（1：已婚，2：未婚）
	/**
	 * 客户类型
	 */
	private Integer custType=1;//(1：成人 2：学生)
	/**
	 * 商品类型
	 */
	private String commodityType;
	/**
	 * 申请时间 时间戳
	 */
	private Long applyDate;
	/**
	 * 分期金额
	 */
	private Double loanAmt;
	/**
	 * 商品总价
	 */
	private Double price;
	/**
	 * 分期期数
	 */
	private Integer num;
	/**
	 * 每月还款
	 */
	private Double monthPayAmt;
	/**
	 * 学历
	 */
	private Integer eduDegree;
	/**
	 * 户籍省
	 */
	private String houseProvince="四川省";
	/**
	 * 户籍市
	 */
	private String houseCity="成都市";
	/**
	 * 户籍详细地址
	 */
	private String householdRegisterAddress="四川省成都市新南路888号";
	/**
	 * 居住省
	 */
	private String liveProvince="四川省";
	/**
	 * 居住市
	 */
	private String liveCity="成都市";
	/**
	 * 居住详细地址
	 */
	private String livingAddress="四川省成都市新南路888号";
	/**
	 * 住房性质
	 */
	private Integer residentialProperty;
	/**
	 *  工作单位名称
	 */
	private String unitName="成都甲乙丙丁科技有限公司";
	/**
	 * 单位类型
	 */
	private Integer unitNature=2;
	/**
	 * 单位规模
	 */
	private Integer unitScale=0;
	/**
	 * 工作省
	 */
	private String unitProvince="四川省";
	/**
	 * 工作市
	 */
	private String unitCity="成都市";
	/**
	 * 工作详细地址
	 */
	private String workAddress="四川省成都市人民南路888号";
	/**
	 * 母亲姓名
	 */
	private String motherName;
	/**
	 * 母亲号码
	 */
	private String motherPhoneNum;
	/**
	 * 父亲姓名
	 */
	private String fatherName="丁三石";
	/**
	 * 父亲号码
	 */
	private String fatherPhoneNum="13812345678";
	/**
	 * 配偶姓名
	 */
	private String spouseName;
	/**
	 * 配偶电话号码
	 */
	private String spousePhoneNum;
	/**
	 * 单位联系人姓名
	 */
	private String workPersonName="甲乙丙丁";
	/**
	 * 单位联系人电话
	 */
	private String workPersonPhoneNum;
	/**
	 * 同事姓名
	 */
	private String colleagueName;
	/**
	 * 同事电话号码
	 */
	private String colleaguePhoneNum;
	/**
	 * 其他第一联系人姓名
	 */
	private String person1Name="某思聪";
	/**
	 * 其他第一联系人电话号码
	 */
	private String person1Phone="13912345678";
	/**
	 * 其他第二联系人姓名
	 */
	private String person2Name;
	/**
	 * 其他第二联系人电话号码
	 */
	private String person2Phone;
	/**
	 * 其他第三联系人姓名
	 */
	private String person3Name;
	/**
	 * 其他第三联系人电话号码
	 */
	private String person3Phone;
	
	/**
	 * 大数据请求地址
	 */
	private String requstUrl;

	private String isFree;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(Integer documentNo) {
		this.documentNo = documentNo;
	}
	public Integer getIsInsurance() {
		return isInsurance;
	}
	public void setIsInsurance(Integer isInsurance) {
		this.isInsurance = isInsurance;
	}
	public Integer getPhoneUseTime() {
		return phoneUseTime;
	}
	public void setPhoneUseTime(Integer phoneUseTime) {
		this.phoneUseTime = phoneUseTime;
	}
	public Integer getPhoneMonPay() {
		return phoneMonPay;
	}
	public void setPhoneMonPay(Integer phoneMonPay) {
		this.phoneMonPay = phoneMonPay;
	}
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobliePhone() {
		return mobliePhone;
	}
	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getMarry() {
		return marry;
	}
	public void setMarry(Integer marry) {
		this.marry = marry;
	}
	public Integer getCustType() {
		return custType;
	}
	public void setCustType(Integer custType) {
		this.custType = custType;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public Long getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}
	public Double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getMonthPayAmt() {
		return monthPayAmt;
	}
	public void setMonthPayAmt(Double monthPayAmt) {
		this.monthPayAmt = monthPayAmt;
	}
	public Integer getEduDegree() {
		return eduDegree;
	}
	public void setEduDegree(Integer eduDegree) {
		this.eduDegree = eduDegree;
	}
	public String getHouseProvince() {
		return houseProvince;
	}
	public void setHouseProvince(String houseProvince) {
		this.houseProvince = houseProvince;
	}
	public String getHouseCity() {
		return houseCity;
	}
	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}
	public String getHouseholdRegisterAddress() {
		return householdRegisterAddress;
	}
	public void setHouseholdRegisterAddress(String householdRegisterAddress) {
		this.householdRegisterAddress = householdRegisterAddress;
	}
	public String getLiveProvince() {
		return liveProvince;
	}
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getLivingAddress() {
		return livingAddress;
	}
	public void setLivingAddress(String livingAddress) {
		this.livingAddress = livingAddress;
	}
	public Integer getResidentialProperty() {
		return residentialProperty;
	}
	public void setResidentialProperty(Integer residentialProperty) {
		this.residentialProperty = residentialProperty;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitNature() {
		return unitNature;
	}
	public void setUnitNature(Integer unitNature) {
		this.unitNature = unitNature;
	}
	public Integer getUnitScale() {
		return unitScale;
	}
	public void setUnitScale(Integer unitScale) {
		this.unitScale = unitScale;
	}
	public String getUnitProvince() {
		return unitProvince;
	}
	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}
	public String getUnitCity() {
		return unitCity;
	}
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherPhoneNum() {
		return motherPhoneNum;
	}
	public void setMotherPhoneNum(String motherPhoneNum) {
		this.motherPhoneNum = motherPhoneNum;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherPhoneNum() {
		return fatherPhoneNum;
	}
	public void setFatherPhoneNum(String fatherPhoneNum) {
		this.fatherPhoneNum = fatherPhoneNum;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpousePhoneNum() {
		return spousePhoneNum;
	}
	public void setSpousePhoneNum(String spousePhoneNum) {
		this.spousePhoneNum = spousePhoneNum;
	}
	public String getWorkPersonName() {
		return workPersonName;
	}
	public void setWorkPersonName(String workPersonName) {
		this.workPersonName = workPersonName;
	}
	public String getWorkPersonPhoneNum() {
		return workPersonPhoneNum;
	}
	public void setWorkPersonPhoneNum(String workPersonPhoneNum) {
		this.workPersonPhoneNum = workPersonPhoneNum;
	}
	public String getColleagueName() {
		return colleagueName;
	}
	public void setColleagueName(String colleagueName) {
		this.colleagueName = colleagueName;
	}
	public String getColleaguePhoneNum() {
		return colleaguePhoneNum;
	}
	public void setColleaguePhoneNum(String colleaguePhoneNum) {
		this.colleaguePhoneNum = colleaguePhoneNum;
	}
	public String getPerson1Name() {
		return person1Name;
	}
	public void setPerson1Name(String person1Name) {
		this.person1Name = person1Name;
	}
	public String getPerson1Phone() {
		return person1Phone;
	}
	public void setPerson1Phone(String person1Phone) {
		this.person1Phone = person1Phone;
	}
	public String getPerson2Name() {
		return person2Name;
	}
	public void setPerson2Name(String person2Name) {
		this.person2Name = person2Name;
	}
	public String getPerson2Phone() {
		return person2Phone;
	}
	public void setPerson2Phone(String person2Phone) {
		this.person2Phone = person2Phone;
	}
	public String getPerson3Name() {
		return person3Name;
	}
	public void setPerson3Name(String person3Name) {
		this.person3Name = person3Name;
	}
	public String getPerson3Phone() {
		return person3Phone;
	}
	public void setPerson3Phone(String person3Phone) {
		this.person3Phone = person3Phone;
	}
	public String getRequstUrl() {
		return requstUrl;
	}
	public void setRequstUrl(String requstUrl) {
		this.requstUrl = requstUrl;
	}
	public String getScoreId() {
		return scoreId;
	}
	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
}
