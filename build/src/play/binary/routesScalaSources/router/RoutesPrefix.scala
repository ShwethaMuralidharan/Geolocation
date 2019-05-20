// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/z003yw8/Workspaces/Hackathon/Geolocation/conf/routes
// @DATE:Mon May 20 10:01:19 IST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
