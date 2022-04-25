package org.jboss.perf.handler;

import org.jboss.perf.context.AbstractContext;

public interface RestHandler<T extends AbstractContext> {

    void handle(T requestContext) throws Exception;

}