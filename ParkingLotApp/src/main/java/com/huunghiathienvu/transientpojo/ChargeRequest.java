/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.transientpojo;

import java.util.Currency;

/**
 *
 * @author ThienVu
 */
public class ChargeRequest {
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    
    public enum Currency {
        EUR, USD;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the stripeEmail
     */
    public String getStripeEmail() {
        return stripeEmail;
    }

    /**
     * @param stripeEmail the stripeEmail to set
     */
    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    /**
     * @return the stripeToken
     */
    public String getStripeToken() {
        return stripeToken;
    }

    /**
     * @param stripeToken the stripeToken to set
     */
    public void setStripeToken(String stripeToken) {
        this.stripeToken = stripeToken;
    }

    /**
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
