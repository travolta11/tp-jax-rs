package com.example.webservice.services;

import com.example.webservice.entities.Compte;
import com.example.webservice.entities.CompteList;
import com.example.webservice.repositories.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    private CompteRepository compteRepository;

    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompte(@PathParam("id") Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));

        return Response.ok(compte).build();
    }

    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getComptes() {
        CompteList compteList = new CompteList();
        compteList.setComptes(compteRepository.findAll());
        return Response.ok(compteList).build();
    }

    @Path("/comptes")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCompte(Compte compte) {
        Compte savedCompte = compteRepository.save(compte);
        return Response.status(Response.Status.CREATED)
                .entity(savedCompte)
                .build();
    }

    @Path("/comptes/{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateCompte(@PathParam("id") Long id, Compte compte) {
        if (!compteRepository.existsById(id)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        compte.setId(id);
        return Response.ok(compteRepository.save(compte)).build();
    }

    @Path("/comptes/{id}")
    @DELETE
    public Response deleteCompte(@PathParam("id") Long id) {
        if (!compteRepository.existsById(id)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        compteRepository.deleteById(id);
        return Response.noContent().build();
    }
}