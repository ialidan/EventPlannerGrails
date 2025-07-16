package com.zelifcam.EventPlanner.controller

class UrlMappings {

    static mappings = {
        "/events/$action?/$id?(.$format)?" {
            controller = "event"
            constraints {
                // apply constraints here
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
