/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.huunghiathienvu.service;

import com.huunghiathienvu.transientpojo.ChargeRequest;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

/**
 *
 * @author ThienVu
 */
public interface StripeService {
    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException, 
            ApiConnectionException, CardException, ApiException;
}
