package grailsangularwithshiro
import org.apache.shiro.SecurityUtils
import com.example.User

class ShiroUserFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                println "Shiro USERFILTER , Controller *"
                //load shiro subject
                def username = SecurityUtils.subject.principal

                //load domain user
                request.currentUser = (username) ? User.findByUsername(username) : null

                //continue processing
                return true
            }
        }
    }
}
