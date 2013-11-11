package com.example

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authz.UnauthorizedException;

class ContactsController {

    def index() { }

    def hall = {
        answer('hall')
    }

    def secretaryRoom = {
        println "DOMAIN is $params.domain"
        def mydomain = params.domain
        if (SecurityUtils.subject.isPermitted("domain:$mydomain"))
            answer('secretary TASK room')
        else
            displayError()
    }

     private displayError() {
         def messageStr = "HELLO Permission Denied FROM COntroller"
        log.debug "in display error message"
        response.status = 401
        // render([error: messageStr] as JSON)
        render(contentType:"text/json") {
            error(message:messageStr)
        }
    }

    def bossRoom = {
        answer('boss room')
    }

    private answer(String room) {
        render """
		Hello! I'm ${(request.currentUser?.username) ?: 'unknown'} <br>
		I'm at ${room}
"""
    }

    def deleteRoom = {
        def mydomain = params.domain
        println "In DeleteRoom"
      //  if (SecurityUtils.subject.isPermitted("contacts:delete:$mydomain")) {
            if(params.domain == "task")
                answer ("Task deleted")
            else if (params.domain == "contact")
                answer ("Contact Deleted")
     //   }

    }
}
