package com.back.base.model;

public class TLogin implements Principal{
    private String id;

    private String username;

    private String password;

    private String loginip;

    private String enablestate;

    private String enabletime;

    private String logintime;

    private String createtime;

    private String updatetime;
    
    private boolean updateFlag;
    
    public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
    
    /**
     * 返回主体类型为 login
     */
	public String getPrincipalType() {
		return "login";
	}
}