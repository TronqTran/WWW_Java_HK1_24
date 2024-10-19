package vn.edu.iuh.backend.api.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.backend.business.EmployeeRemote;
import vn.edu.iuh.backend.entities.Employee;

@Path("/employees")
public class EmployeeResource {
    @EJB
    private EmployeeRemote employeeRemote;

    @GET
    @Produces("application/json")
    public Response getAll(){
        return Response.ok(employeeRemote.getAllEmployee()).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getById(@PathParam("id") Long id){
        return Response.ok(employeeRemote.getEmployeeById(id)).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addEmployee(Employee employee){
        employeeRemote.addEmployee(employee);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateEmployee(Employee employee){
        employeeRemote.updateEmployee(employee);
        return Response.status(Response.Status.OK).build();
    }
}
