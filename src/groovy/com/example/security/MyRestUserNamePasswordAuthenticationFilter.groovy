package com.example.security

class MyRestUsernamePasswordAuthenticationFilter extends RestUsernamePasswordAuthenticationFilter {

    protected String getUsername(request) {
    	println "MyRestUsernamePasswordAuthenticationFilter.getUsername called"
        return request.getParameter('username')
    }

    protected String getPassword(request) {
        return request.getParameter('password')
    }

}
