/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.service.MFATokenManagerService;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */
@Service("mfaTokenManager")
public class MFATokenManagerServiceImplement implements MFATokenManagerService {

    private SecretGenerator secretGenerator;

    private QrGenerator qrGenerator;

    private CodeVerifier codeVerifier;

    @Override
    public String generateSecretKey() {
        return secretGenerator.generate();
    }

    @Override
    public String getQRCode(String secret) throws QrGenerationException {
        QrData data = new QrData.Builder().label("MFA")
            .secret(secret)
            .issuer("Nguyen Huu Nghia - Le Tran Thien Vu")
            .algorithm(HashingAlgorithm.SHA256)
            .digits(6)
            .period(30)
            .build();
        return Utils.getDataUriForImage(
            qrGenerator.generate(data),
            qrGenerator.getImageMimeType()
        );
    }

    @Override
    public boolean verifyTotp(String code, String secret) {
        
        return codeVerifier.isValidCode(secret, code);
    }
}

