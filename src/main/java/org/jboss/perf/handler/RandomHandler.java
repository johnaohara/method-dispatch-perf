package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public class RandomHandler implements RestHandler{

    private static final String name = RandomHandler.class.getName();

    @Override
    public void handle(AbstractContext requestContext) throws Exception {
        requestContext.incCount();
    }
}
