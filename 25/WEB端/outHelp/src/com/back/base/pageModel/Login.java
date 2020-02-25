package com.back.base.pageModel;

import java.util.HashMap;

import com.back.base.AbstractEntity;

public class Login extends AbstractEntity{
	private String id;

	private String personid;

	private String username;

	private String password;

	private String loginip;

	private String enablestate;

	private String enabletime;

	private String logintime;

	private String createtime;

	private String updatetime;

	private String name;

	private String sex;
	
    private String engname;

    private String type;

    private String phone;

    private String tel;

    private String email;

    private String address;

    private String zipcode;	
	
	private boolean UpdateFlag;
	private boolean loginFlag;
	
	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}

	private String departmentId;//部门ID
	private String departmentName;//部门name
	private String departmentCode;//部门code
	private String roleId;//角色ID
	private String roleName;//角色name
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isUpdateFlag() {
		return UpdateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		UpdateFlag = updateFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid == null ? null : personid.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip == null ? null : loginip.trim();
	}

	public String getEnablestate() {
		return enablestate;
	}

	public void setEnablestate(String enablestate) {
		this.enablestate = enablestate == null ? null : enablestate.trim();
	}

	public String getEnabletime() {
		return enabletime;
	}

	public void setEnabletime(String enabletime) {
		this.enabletime = enabletime == null ? null : enabletime.trim();
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime == null ? null : logintime.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime == null ? null : createtime.trim();
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime == null ? null : updatetime.trim();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Login) {

			Login login = (Login) obj;

			if (this.username.equals(login.username) && this.name.equals(login.name)) {

				return true;
			}
		}
		return false;
	}
	
	
    @Override

    public int hashCode() {
        return id.hashCode();

    }

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public HashMap<String, String> dicMap() {
		// TODO Auto-generated method stub
		return null;
	}
    
}