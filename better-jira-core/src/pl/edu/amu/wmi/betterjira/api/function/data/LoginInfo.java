package pl.edu.amu.wmi.betterjira.api.function.data;

import java.util.Date;

public class LoginInfo {
    private int loginCount;
    private int failedLoginCount;
    private Date previousLoginTime;
    private Date lastFailedLoginTime;

    public final int getLoginCount() {
	return loginCount;
    }

    public final void setLoginCount(int loginCount) {
	this.loginCount = loginCount;
    }

    public final Date getPreviousLoginTime() {
	return previousLoginTime;
    }

    public final void setPreviousLoginTime(Date previousLoginTime) {
	this.previousLoginTime = previousLoginTime;
    }

    public Date getLastFailedLoginTime() {
	return lastFailedLoginTime;
    }

    public void setLastFailedLoginTime(Date simpleDateFormat) {
	this.lastFailedLoginTime = simpleDateFormat;
    }

    /**
     * @return Can return null if failed login count == 0
     */
    public int getFailedLoginCount() {
	return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
	this.failedLoginCount = failedLoginCount;
    }

}
