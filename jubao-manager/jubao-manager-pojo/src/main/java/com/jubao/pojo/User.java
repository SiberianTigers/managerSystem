package com.jubao.pojo;

import java.util.Date;

/***
 * �û���
 * 
 * @author 12146
 *
 */
public class User {

	private Long userid; //�û�id
	private String userCode;//�û�����
	private String password;//�û�����
	private String phone;//�ֻ�
	private String myImgage;//�û�ͷ��
	private String nickName;//�û�����
	private Short sex;//�Ա�
	private String userEmeil;//����
	private Date birthday;//����
	private Date createTime;//��������
	private Short userType;//�û�����
	private Short userVip;//��Ա����
	private Short userState;//�û�״̬
	
	
	
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMyImgage() {
		return myImgage;
	}
	public void setMyImgage(String myImgage) {
		this.myImgage = myImgage;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public String getUserEmeil() {
		return userEmeil;
	}
	public void setUserEmeil(String userEmeil) {
		this.userEmeil = userEmeil;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Short getUserType() {
		return userType;
	}
	public void setUserType(Short userType) {
		this.userType = userType;
	}
	public Short getUserVip() {
		return userVip;
	}
	public void setUserVip(Short userVip) {
		this.userVip = userVip;
	}
	public Short getUserState() {
		return userState;
	}
	public void setUserState(Short userState) {
		this.userState = userState;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", userCode=" + userCode + ", password=" + password + ", phone=" + phone
				+ ", myImgage=" + myImgage + ", nickName=" + nickName + ", sex=" + sex + ", userEmeil=" + userEmeil
				+ ", birthday=" + birthday + ", createTime=" + createTime + ", userType=" + userType + ", userVip="
				+ userVip + ", userState=" + userState + ", getUserid()=" + getUserid() + ", getUserCode()="
				+ getUserCode() + ", getPassword()=" + getPassword() + ", getPhone()=" + getPhone() + ", getMyImgage()="
				+ getMyImgage() + ", getNickName()=" + getNickName() + ", getSex()=" + getSex() + ", getUserEmeil()="
				+ getUserEmeil() + ", getBirthday()=" + getBirthday() + ", getCreateTime()=" + getCreateTime()
				+ ", getUserType()=" + getUserType() + ", getUserVip()=" + getUserVip() + ", getUserState()="
				+ getUserState() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
}
