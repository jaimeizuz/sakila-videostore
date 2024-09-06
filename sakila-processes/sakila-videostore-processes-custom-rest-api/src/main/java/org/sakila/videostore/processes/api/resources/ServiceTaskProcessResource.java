package org.sakila.videostore.processes.api.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.kie.kogito.Model;
import org.kie.kogito.auth.IdentityProviders;
import org.kie.kogito.auth.SecurityPolicy;
import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.incubation.common.ExtendedDataContext;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.processes.ProcessIds;
import org.kie.kogito.incubation.processes.services.StatefulProcessService;
import org.kie.kogito.incubation.processes.services.contexts.TaskMetaDataContext;
import org.kie.kogito.incubation.processes.services.humantask.HumanTaskService;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.workitem.TaskModel;
import org.sakila.videostore.processes.api.model.ServiceTaskProcessModel;
import org.sakila.videostore.processes.api.model.ServiceTaskProcessModelInput;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

@Path("/ServiceTaskProcess")
public class ServiceTaskProcessResource {

    @Inject
    AppRoot appRoot;
    
    @Inject
    //ProcessService statefulProcessService;
    StatefulProcessService statefulProcessService;
    
    @Inject
    HumanTaskService humanTaskService;

    //Process<ServiceTaskProcessModel> serviceTaskProcess;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/start")
    public Response startServiceTaskProcess(
        //@Context HttpHeaders httpHeaders, 
        //@Context UriInfo uriInfo,
        @QueryParam("businessKey") @DefaultValue("") String businessKey,
        //Map<String, Object> payload
        @Valid @NotNull ServiceTaskProcessModelInput payload) {

        var id = appRoot.get(ProcessIds.class).get("ServiceTaskProcess");

        MapDataContext ctx = MapDataContext.of(payload.toMap());

        ExtendedDataContext startProcessResponse = statefulProcessService.create(id, ctx);

        System.out.println(startProcessResponse.toString());
        System.out.println(startProcessResponse.data().toString());

        return Response.ok(startProcessResponse).build();


         /*
         This API is based on the ProcesService interface, which throws build exception that prevents the compilation.
        ProcessInstance<ServiceTaskProcessModel> pi = statefulProcessService.createProcessInstance(
            serviceTaskProcess,
            businessKey,
            Optional.ofNullable(payload).orElse(new ServiceTaskProcessModelInput()).toModel(),
            httpHeaders.getRequestHeaders(),
            httpHeaders.getHeaderString("X-KOGITO-StartFromNode"));

        return Response.created(uriInfo.getAbsolutePathBuilder().path(pi.id()).build()).entity(pi.checkError().variables().toModel()).build();
         */
    }

    /*
    @GET
    @Path("/{id}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "ServiceTaskProcess", description = "")
    public List<TaskModel> getTasks(@PathParam("id") String id, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return statefulProcessService.getTasks(
            statefulProcessService.,
            id, 
            SecurityPolicy.of(IdentityProviders.of(user, groups)))
        .orElseThrow(
            NotFoundException::new)
        .stream();
    }
    */

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startUserTask(String taskInstanceId) {
        // path: /processes/scripts

        var id = appRoot.get(ProcessIds.class).get("ServiceTaskProcess");

        ExtendedDataContext extendedDataContext = new ExtendedDataContext

        ExtendedDataContext edc = dataContext.as(ExtendedDataContext.class);
        TaskMetaDataContext mdc = edc.meta().as(TaskMetaDataContext.class);
        SecurityPolicy securityPolicy = convertPolicyObject(mdc.policy());
        String phase = mdc.phase();

        MapDataContext ctx = MapDataContext.of(payload);

        humanTaskService.
        //statefulProcessService.taskTransition(process, taskInstanceId, taskInstanceId, taskInstanceId, null, null)

        return Response.ok
    }
         */
}