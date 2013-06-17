package pl.edu.amu.wmi.betterjira.api.function.data;

public class Image {
    private String url48x48;
    private String url32x32;
    private String url24x24;
    private String url16x16;

    /**
     * @return can be null if no such url
     */
    public String getUrl48x48() {
	return url48x48;
    }

    public void setUrl48x48(String url48x48) {
	this.url48x48 = url48x48;
    }

    /**
     * @return can be null if no such url
     */
    public String getUrl32x32() {
	return url32x32;
    }

    public void setUrl32x32(String url32x32) {
	this.url32x32 = url32x32;
    }

    /**
     * @return can be null if no such url
     */
    public String getUrl24x24() {
	return url24x24;
    }

    public void setUrl24x24(String url24x24) {
	this.url24x24 = url24x24;
    }

    /**
     * @return can be null if no such url
     */
    public String getUrl16x16() {
	return url16x16;
    }

    public void setUrl16x16(String url16x16) {
	this.url16x16 = url16x16;
    }

    /**
     * @return can return null if no url is set
     */
    public String getBestQualityUrl() {
	if (url48x48 != null) {
	    return url48x48;
	}
	if (url32x32 != null) {
	    return url32x32;
	}
	if (url24x24 != null) {
	    return url24x24;
	}
	if (url16x16 != null) {
	    return url16x16;
	}
	return null;
    }
}
