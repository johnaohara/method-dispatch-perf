package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public class BlockingHandler implements RestHandler{

    private static final String name = BlockingHandler.class.getName();

    @Override
    public void handle(AbstractContext requestContext) throws Exception {
        requestContext.incCount();
    }
}
