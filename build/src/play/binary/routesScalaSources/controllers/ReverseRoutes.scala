// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/z003yw8/Workspaces/Hackathon/Geolocation/conf/routes
// @DATE:Mon May 20 10:01:19 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:1
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
