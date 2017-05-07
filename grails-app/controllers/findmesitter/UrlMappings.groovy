package findmesitter

import findMeSitter.user.Role
import grails.plugin.springsecurity.annotation.Secured

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/search-review"(view:'/review/main')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
