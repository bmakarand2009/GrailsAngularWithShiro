package com.example.security

import org.apache.shiro.authc.AuthenticationToken

class RestToken implements AuthenticationToken {

    String token

    Object getPrincipal() {
    	println "getPrincipal() called	"
        return token
    }

    Object getCredentials() {
        return null
    }
}
