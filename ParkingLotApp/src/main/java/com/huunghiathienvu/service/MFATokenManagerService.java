/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service;

import dev.samstevens.totp.exceptions.QrGenerationException;

/**
 *
 * @author ThienVu
 */
public interface MFATokenManagerService {
    String generateSecretKey();
    String getQRCode(final String secret) throws QrGenerationException;
    boolean verifyTotp(final String code, final String secret);
}
