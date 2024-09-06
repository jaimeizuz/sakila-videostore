package org.sakila.videostore.processes.api.resources;

import java.util.Map;

import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.processes.ProcessIds;
import org.kie.kogito.incubation.processes.services.StraightThroughProcessService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ScriptTaskProcess")
public class ScriptTaskProcessResource {

    @Inject
    AppRoot appRoot;
    @Inject
    StraightThroughProcessService straightThroughProcessService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public DataContext startScriptTaskProcess(Map<String, Object> payload) {
        // path: /processes/scripts

        var id = appRoot.get(ProcessIds.class).get("ScriptTaskProcess");
        MapDataContext ctx = MapDataContext.of(payload);
        return straightThroughProcessService.evaluate(id, ctx);
    }  
}