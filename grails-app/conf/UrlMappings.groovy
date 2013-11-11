class UrlMappings {

	static mappings = {
		"/$action?" (controller: 'contacts')
		"/"(controller: 'contacts', action: 'hall')
		"500"(view:'/error')

        // auth controller
        "/auth/$action?" (controller: 'auth')

        // REST api
        "/rest/$action" (controller: 'contacts')
        "/rest/" (controller: 'contacts', action: 'hall')
	}
}
