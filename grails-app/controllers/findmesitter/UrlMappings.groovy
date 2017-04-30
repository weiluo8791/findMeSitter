package findmesitter

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
//        "/dcc"(controller: "dayCareCenter", action: "index")
//        "/dccreviewer"(controller: "reviewer", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
