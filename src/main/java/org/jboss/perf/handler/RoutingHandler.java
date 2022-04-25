package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public class RoutingHandler implements RestHandler{

    private static final String name = RoutingHandler.class.getName();

    @Override
    public void handle(AbstractContext requestContext) throws Exception {
        requestContext.incCount();
    }
}
