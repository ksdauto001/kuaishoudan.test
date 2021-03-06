package com.kuaishoudan.financer.bean;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public class KSDCase {
	String sp_password;// 审批流用户默认密码
	String flow;
	String caseid;
	String casedesc;
	String username;// 用户名
	int identitytype;// 证件类型
	String identitynum;// 身份号
	String jgid;// 军官证
	int cartype;// 新车二手车
	String carbrand;// 车辆品牌
	String carseries;// 车系
	double carprice;// 车辆价格
	double sqdk;// 申请贷款
	int hkqs;// 还款期数
	String product;// 金融产品
	String sssh;// 所属商户
	int sssh_id;// 商户id
	int sssh_account;// 商户账号
	String businessname;// 企业名称
	String businessid;// 企业名称
	String remark;// 备注
	int init_statue;// 初始状态
	String phone;
	String loginemail;
	String loginname;// 登录名
	String pwd;
	String address;// 用户地址
	int qygr;// 个人企业
	String rate;// 费率
	String statue;// 状态
	String purchase_tax;// 购置税
	String gps_charge;// gps费
	String insurance;// 保险费
	String service_charge;// 服务费
	String vin;// 车架号
	int registtype;// 上牌方
	int pledge;// 抵押方
	int imgcount;// 进件图片数量
	double deduction;// 总额
	int zjtr;// 转交他人
	int commit_type;// 再次进件 进件
	String real_loan_amount;// 车价贷款额变化wei
	int zx;// 杂项费用种类
	int zxsp;//杂项审批
	List<Integer> imgtypes;
	RequestPayout requestpayout;
	//#######mgc
	String mgccar_phone;
	String mgccar_pwd;
	
	public String getMgccar_pwd() {
		return mgccar_pwd;
	}

	public void setMgccar_pwd(String mgccar_pwd) {
		this.mgccar_pwd = mgccar_pwd;
	}

	public String getMgccar_phone() {
		return mgccar_phone;
	}

	public void setMgccar_phone(String mgccar_phone) {
		this.mgccar_phone = mgccar_phone;
	}

	public int getZx() {
		return zx;
	}

	public void setZx(int zx) {
		this.zx = zx;
	}

	public int getZxsp() {
		return zxsp;
	}

	public void setZxsp(int zxsp) {
		this.zxsp = zxsp;
	}

	public String getReal_loan_amount() {
		return real_loan_amount;
	}

	public void setReal_loan_amount(String real_loan_amount) {
		this.real_loan_amount = real_loan_amount;
	}

	public int getCommit_type() {
		return commit_type;
	}

	public void setCommit_type(int commit_type) {
		this.commit_type = commit_type;
	}

	public int getSssh_account() {
		return sssh_account;
	}

	public void setSssh_account(int sssh_account) {
		this.sssh_account = sssh_account;
	}

	public int getSssh_id() {
		return sssh_id;
	}

	public void setSssh_id(int sssh_id) {
		this.sssh_id = sssh_id;
	}

	public String getSp_password() {
		return sp_password;
	}

	public void setSp_password(String sp_password) {
		this.sp_password = sp_password;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public int getZjtr() {
		return zjtr;
	}

	public void setZjtr(int zjtr) {
		this.zjtr = zjtr;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public List<Integer> getImgtypes() {
		return imgtypes;
	}

	public void setImgtypes(List<Integer> imgtypes) {
		this.imgtypes = imgtypes;
	}

	public String getLoginemail() {
		return loginemail;
	}

	public void setLoginemail(String loginemail) {
		this.loginemail = loginemail;
	}

	public int getImgcount() {
		return imgcount;
	}

	public void setImgcount(int imgcount) {
		this.imgcount = imgcount;
	}

	public int getRegisttype() {
		return registtype;
	}

	public void setRegisttype(int registtype) {
		this.registtype = registtype;
	}

	public int getPledge() {
		return pledge;
	}

	public void setPledge(int pledge) {
		this.pledge = pledge;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getPurchase_tax() {
		return purchase_tax;
	}

	public void setPurchase_tax(String purchase_tax) {
		this.purchase_tax = purchase_tax;
	}

	public String getGps_charge() {
		return gps_charge;
	}

	public void setGps_charge(String gps_charge) {
		this.gps_charge = gps_charge;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getService_charge() {
		return service_charge;
	}

	public void setService_charge(String service_charge) {
		this.service_charge = service_charge;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCarseries() {
		return carseries;
	}

	public void setCarseries(String carseries) {
		this.carseries = carseries;
	}

	public int getQygr() {
		return qygr;
	}

	public void setQygr(int qygr) {
		this.qygr = qygr;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public String getCasedesc() {
		return casedesc;
	}

	public void setCasedesc(String casedesc) {
		this.casedesc = casedesc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCartype() {
		return cartype;
	}

	public void setCartype(int cartype) {
		this.cartype = cartype;
	}

	public String getCarbrand() {
		return carbrand;
	}

	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}

	public String getSssh() {
		return sssh;
	}

	public void setSssh(String sssh) {
		this.sssh = sssh;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getInit_statue() {
		return init_statue;
	}

	public void setInit_statue(int init_statue) {
		this.init_statue = init_statue;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getIdentitytype() {
		return identitytype;
	}

	public void setIdentitytype(int identitytype) {
		this.identitytype = identitytype;
	}

	public String getIdentitynum() {
		return identitynum;
	}

	public void setIdentitynum(String identitynum) {
		this.identitynum = identitynum;
	}

	public double getCarprice() {
		return carprice;
	}

	public void setCarprice(double carprice) {
		this.carprice = carprice;
	}

	public double getSqdk() {
		return sqdk;
	}

	public void setSqdk(double sqdk) {
		this.sqdk = sqdk;
	}

	public int getHkqs() {
		return hkqs;
	}

	public void setHkqs(int hkqs) {
		this.hkqs = hkqs;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJgid() {
		return jgid;
	}

	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	public RequestPayout getRequestpayout() {
		return requestpayout;
	}

	public void setRequestpayout(RequestPayout requestpayout) {
		this.requestpayout = requestpayout;
	}

}
