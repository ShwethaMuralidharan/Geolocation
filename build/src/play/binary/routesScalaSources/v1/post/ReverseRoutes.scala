// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/z003yw8/Workspaces/Hackathon/Geolocation/conf/posts.routes
// @DATE:Mon May 20 10:01:19 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:2
package v1.post {

  // @LINE:2
  class ReversePostController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def show(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:2
    def list(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:3
    def create(): Call = {
      
      Call("POST", _prefix)
    }
  
    // @LINE:6
    def update(id:String): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
  }


}
