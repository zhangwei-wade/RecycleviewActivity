package com.zhangwei.framelibs.Global.Entity;

/**
 * Created by wade on 2016/1/13.
 */
public class ResultEntity {

    /**
     * eCode : 0
     * eDesc : OK, 一切正常
     * data :
     */

    private int eCode;
    private String eDesc;
    private Object data;

    public void setECode(int eCode) {
        this.eCode = eCode;
    }

    public void setEDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getECode() {
        return eCode;
    }

    public String getEDesc() {
        return eDesc;
    }

    public Object getData() {
        return data;
    }
}
