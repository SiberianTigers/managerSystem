package com.jubao.pojo;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddress implements Serializable {

	  private Integer id;

	    private String address;//地区

	    private Long userId;//用户id

	    private Date createTime;//创建时间

	    private String remark;//收货人
	    
	    private Integer isdefault=0;//默认地址 1是默认
	    
	    private Integer sf;//省份
	    
	    private Integer sq;//市区
	    
	    private Integer qx;//区县
	    
	    private String addressInfo;//详细地址
	    
	    private String  zipCode;//邮编
	    
	    private String phone;//手机
	    
        
	    private String sfName;//接受省份
	    private String sqName;//市区
	    private String qxName;//区县
     

    public String getSfName() {
			return sfName;
		}

		public void setSfName(String sfName) {
			this.sfName = sfName;
		}

		public String getSqName() {
			return sqName;
		}

		public void setSqName(String sqName) {
			this.sqName = sqName;
		}

		public String getQxName() {
			return qxName;
		}

		public void setQxName(String qxName) {
			this.qxName = qxName;
		}

	public Integer getSf() {
			return sf;
		}

		public void setSf(Integer sf) {
			this.sf = sf;
		}

		public Integer getSq() {
			return sq;
		}

		public void setSq(Integer sq) {
			this.sq = sq;
		}

		public Integer getQx() {
			return qx;
		}

		public void setQx(Integer qx) {
			this.qx = qx;
		}

	public String getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}



	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", address=" + address + ", userId=" + userId + ", createTime=" + createTime
				+ ", remark=" + remark + ", isdefault=" + isdefault + ", sf=" + sf + ", sq=" + sq + ", qx=" + qx
				+ ", addressInfo=" + addressInfo + ", zipCode=" + zipCode + ", phone=" + phone + ", getSf()=" + getSf()
				+ ", getSq()=" + getSq() + ", getQx()=" + getQx() + ", getAddressInfo()=" + getAddressInfo()
				+ ", getZipCode()=" + getZipCode() + ", getPhone()=" + getPhone() + ", getIsdefault()=" + getIsdefault()
				+ ", getRemark()=" + getRemark() + ", getId()=" + getId() + ", getAddress()=" + getAddress()
				+ ", getCreateTime()=" + getCreateTime() + ", getUserId()=" + getUserId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

   
}
