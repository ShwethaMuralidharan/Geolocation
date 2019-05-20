package v1.post;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import play.libs.ws.*;
import java.util.concurrent.CompletionStage;

@With(PostAction.class)
public class PostController extends Controller  implements WSBodyReadables, WSBodyWritables {

    private HttpExecutionContext ec;
    private PostResourceHandler handler;
    private final WSClient ws;


    @Inject
    public PostController(HttpExecutionContext ec, PostResourceHandler handler,WSClient ws) {
        this.ec = ec;
        this.handler = handler;
        this.ws = ws;
    }

    public CompletionStage<Result> list() {
        return handler.find().thenApplyAsync(posts -> {
            final List<PostResource> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> show(String id) {
        return handler.lookup(id).thenApplyAsync(optionalResource -> {
            return optionalResource.map(resource ->
                    ok(Json.toJson(resource))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current());
    }

    public CompletionStage<Result> update(String id) {
        JsonNode json = request().body().asJson();
        PostResource resource = Json.fromJson(json, PostResource.class);
        return handler.update(id, resource).thenApplyAsync(optionalResource -> {
            return optionalResource.map(r ->
                    ok(Json.toJson(r))
            ).orElseGet(() ->
                    notFound()
            );
        }, ec.current());
    }

    public CompletionStage<Result> create() throws InterruptedException, ExecutionException {
        JsonNode json = request().body().asJson();
        PostResource resource = Json.fromJson(json, PostResource.class);


        WSRequest request = ws.url("https://maps.googleapis.com/maps/api/geocode/json");

        WSRequest complexRequest =
                request
                        .addHeader("Content-Type", "text/JSON")
                        //.setRequestTimeout(Duration.of(1000, ChronoUnit.MILLIS))
                        .addQueryParameter("address", resource.getAddress())
                        .addQueryParameter("key", "AIzaSyD14f4H32ub1_EWOaWcMODB02JF9rnxcqc");

        //CompletionStage<? extends WSResponse> responsePromise = complexRequest.get();

        CompletionStage<JsonNode> jsonPromise = complexRequest.get().thenApply(r -> r.getBody(json()));
        JsonNode jsonNode = jsonPromise.toCompletableFuture().join();

        JsonNode resNode = jsonNode.get("results");
        Iterator<JsonNode> itr = resNode.iterator();

        while (itr.hasNext()) {
            JsonNode temp = itr.next();
            JsonNode geoNode = temp.get("geometry");
            JsonNode locNode = geoNode.get("location");
            JsonNode latNode = locNode.get("lat");
            JsonNode lngNode = locNode.get("lng");
            resource.setLatitude(latNode.toString());
            resource.setLongitude(lngNode.toString());
        }

        return handler.create(resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current());

    }
}