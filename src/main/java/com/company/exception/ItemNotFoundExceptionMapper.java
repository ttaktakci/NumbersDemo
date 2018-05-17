package com.company.exception;

import com.company.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ItemNotFoundExceptionMapper implements ExceptionMapper<ItemNotFoundException> {

    @Override
    public Response toResponse(ItemNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), ExceptionCodes.ITEM_NOT_FOUND, "http://www.company.com");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type("application/json")
                .build();
    }

}
