package com.jubao.pojo;

/**
 * 管理员实体类
 * @author lenovo
 *
 */
public class Manager {
	
	private Integer managerId; //管理员id
	private String managerName; //管理员登录名
	private String managerPwd; //管理员密码
	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPwd() {
		return managerPwd;
	}
	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}
	
	
}
