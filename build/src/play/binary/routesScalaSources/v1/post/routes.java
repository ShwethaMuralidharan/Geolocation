// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/z003yw8/Workspaces/Hackathon/Geolocation/conf/posts.routes
// @DATE:Mon May 20 10:01:19 IST 2019

package v1.post;

import posts.RoutesPrefix;

public class routes {
  
  public static final v1.post.ReversePostController PostController = new v1.post.ReversePostController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final v1.post.javascript.ReversePostController PostController = new v1.post.javascript.ReversePostController(RoutesPrefix.byNamePrefix());
  }

}
