package com.example.security

class MyRestTokenAuthenticationFilter extends RestTokenAuthenticationFilter {

    protected String getToken(request) {
        return request.getParameter('token')
    }
}
