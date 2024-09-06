/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.transientpojo;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author ThienVu
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private String token;
    
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.token = request.getParameter("token");
    }
    
    @Override
    public String toString() {
        return String.format("CustomWebAuthenticationDetails{token='%s'}", this.getToken());
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        CustomWebAuthenticationDetails detail = (CustomWebAuthenticationDetails) o;
        return Objects.equals(this.getToken(), detail.getToken());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), getToken());
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
}
