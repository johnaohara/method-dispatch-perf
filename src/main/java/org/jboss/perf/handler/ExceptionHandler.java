package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public class ExceptionHandler implements RestHandler{

    private static final String name = ExceptionHandler.class.getName();

    @Override
    public void handle(AbstractContext requestContext) throws Exception {
        requestContext.incCount();
    }
}
