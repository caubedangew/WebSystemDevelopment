/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.transientpojo;

import java.io.Serializable;

/**
 *
 * @author ThienVu
 */
public class MfaTokenData implements Serializable {
    private String qrCode;
    private String mfaCode;
    
    public MfaTokenData(String qrCode, String mfaCode) {
        this.qrCode = qrCode;
        this.mfaCode = mfaCode;
    }

    /**
     * @return the qrCode
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * @param qrCode the qrCode to set
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * @return the mfaCode
     */
    public String getMfaCode() {
        return mfaCode;
    }

    /**
     * @param mfaCode the mfaCode to set
     */
    public void setMfaCode(String mfaCode) {
        this.mfaCode = mfaCode;
    }
    
    
}
