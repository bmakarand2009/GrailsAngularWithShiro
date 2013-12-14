
class ShiroRestFilters {

    def filters = {
        all(uri: "/rest/**") {
            println "In URIL REST/** ShiroRestFilters"
            before = {
                println "in Shiro Rest FILTER"
                if (!(controllerName == 'contacts' && actionName == 'hall')) {
                    println "In Shiro Rest Filter"
                    accessControl()
                }
            }
        }
    }

    def onUnauthorized(subject, d) {
        d.render status: 401, text:"HELLO Permission Denied"
    }
}
