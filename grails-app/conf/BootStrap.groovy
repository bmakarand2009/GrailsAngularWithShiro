import grails.util.GrailsUtil
import grails.util.Environment
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.grails.ShiroSecurityService

import com.example.User
import com.example.Role
class BootStrap {

	ShiroSecurityService shiroSecurityService

    def init = { servletContext ->
    	println "GrailsUtil.environment is $GrailsUtil.environment"
                 
                 switch(GrailsUtil.environment){
                         case "development":
                           println "#### Development Mode (Start Up)"
                           initDev()
                           break
                         case "test":
                           println "#### Test Mode (Start Up)"
                           //initTest()
                           break
                         case "production":
                           println "#### Production Mode (Start Up)"
                           //initProd()
                           break
                 }
    }
    def destroy = {
    }

    def initDev = {
    	def adminRole = Role.findOrCreateWhere(name:'ROLE_ADMIN') 
        adminRole.addToPermissions("user:*:*")
        adminRole.addToPermissions("role:*:*")
        
        adminRole.save(flush: true, failOnError: true)

        // Create the user role
        def userRole =
                Role.findOrCreateWhere(name: 'ROLE_USER').save(flush: true, failOnError: true)
        userRole.addToPermissions("user:read:*")
        userRole.addToPermissions("role:read:*")
        userRole.save(flush: true, failOnError: true)

        // Create an admin user
        def adminUser = User.findOrCreateWhere(username: "admin",
        	 passwordHash: shiroSecurityService.encodePassword('password')).save(flush: true, failOnError: true)
        // Add roles to the admin user
        assert adminUser.addToRoles(adminRole).addToRoles(userRole).save(flush: true, failOnError: true)

        // Create an standard user
        def standardUser = 
                User.findOrCreateWhere(username: "joe",
                 passwordHash: shiroSecurityService.encodePassword('password')).save(flush: true, failOnError: true)
        // Add role to the standard user
        assert standardUser.addToRoles(userRole).save(flush: true, failOnError: true)
        

 	}
}
