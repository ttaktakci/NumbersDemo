package com.company.exception;

import com.company.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ItemAlreadyAvailableExceptionMapper implements ExceptionMapper<ItemAlreadyAvailableException> {

    @Override
    public Response toResponse(ItemAlreadyAvailableException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), ExceptionCodes.ITEM_ALREADY_AVAILABLE, "http://www.company.com");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .type("application/json")
                .build();
    }

}
