package org.dream.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dream.common.util.CustomDateSerializer;
import org.dream.common.util.CustomDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "evchar_user")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "token")
	private Byte token;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "wechat_id")
	private String wechatId;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "head_img_url")
	private String headImgUrl;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "mac_id")
	private String macId;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "create_time")
	private Date createTime;

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "user_status")
	private Boolean userStatus;

	@Transient
	private UserAccount userAccount;

	@Column(name = "is_owner")
	private Boolean isOwner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getToken() {
		return token;
	}

	public void setToken(Byte token) {
		this.token = token;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public Boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", token=" + token + ", nickName=" + nickName + ", wechatId=" + wechatId
				+ ", realName=" + realName + ", headImgUrl=" + headImgUrl + ", mobile=" + mobile + ", macId=" + macId
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", userStatus=" + userStatus
				+ ", userAccount=" + userAccount + ", isOwner=" + isOwner + "]";
	}
}
