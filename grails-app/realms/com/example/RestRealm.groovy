package com.example
import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UnknownAccountException
import com.example.security.RestToken
import com.example.User


/**
 * Created with IntelliJ IDEA.
 * User: poojabkar
 * Date: 11/11/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
class RestRealm {
    def restService

    static authTokenClass = RestToken

    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.token} in REST realm..."
        def token = authToken.token

        // Null username is invalid
        if (token == null) {
            throw new AccountException("Null token are not allowed by this realm.")
        }

        // If we don't have user for specified token then user is not authenticated
        def username = restService.getUsernameForToken(token)
        def user = (!username) ?: User.findByUsername(username)
        if (!user) {
            throw new UnknownAccountException("No account found for token [${token}]")
        }

        log.info "Found user '${user.username}' in DB"

        //ok. Account is found, user is authenticated
        return new SimpleAccount(user.username, user.passwordHash, "RestRealm")
    }
}
