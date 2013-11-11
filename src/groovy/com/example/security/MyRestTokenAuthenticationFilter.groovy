package com.example.security

class MyRestTokenAuthenticationFilter extends RestTokenAuthenticationFilter {

    protected String getToken(request) {
    	println "MyRestTokenAuthenticationFilter.getToken called"
        return request.getParameter('token')
    }
}
