package com.back.base.model;

import java.math.BigDecimal;

public class TAcl {
    private String id;

    private String principaltype;

    private String principalid;

    private String resourcetype;

    private String resourceid;

    private BigDecimal aclstate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPrincipaltype() {
        return principaltype;
    }

    public void setPrincipaltype(String principaltype) {
        this.principaltype = principaltype == null ? null : principaltype.trim();
    }

    public String getPrincipalid() {
        return principalid;
    }

    public void setPrincipalid(String principalid) {
        this.principalid = principalid == null ? null : principalid.trim();
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype == null ? null : resourcetype.trim();
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    public BigDecimal getAclstate() {
        return aclstate;
    }

    public void setAclstate(BigDecimal aclstate) {
        this.aclstate = aclstate;
    }
}