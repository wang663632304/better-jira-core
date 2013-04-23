package pl.edu.amu.wmi.betterjira.api.function.data;

import android.util.Log;

public class Session {
    private String name;
    private String value;
    private LoginInfo loginInfo;

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public final String getValue() {
	return value;
    }

    public final void setValue(String value) {
	this.value = value;
    }

    /**
     * @return can return null if not set or logged out
     */
    public LoginInfo getLoginInfo() {
	return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
	this.loginInfo = loginInfo;
    }

    /**
     * Destroy session this is called when you want logout. You shouldn't use
     * this object after this method call
     */
    public void destroy() {
	Log.w(this.toString(), "Log out called!");
	value = null;
	name = null;
	loginInfo = null;
    }

}
