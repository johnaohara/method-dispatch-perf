/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jboss.perf;

import org.jboss.perf.context.InterfaceContextImpl;
import org.jboss.perf.context.LambdaContextImpl;
import org.jboss.perf.context.MethodHandleImpl;
import org.jboss.perf.context.UnrollContextImpl;
import org.jboss.perf.handler.*;
import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
//@Fork(0)
@State(Scope.Benchmark)
public class MethodDispatchBenchmark {

    static final Map<String, RestHandler[]> handlerChains;

    static {
        handlerChains = new HashMap<>();
        handlerChains.put("small", new RestHandler[]{new RandomHandler()});
        handlerChains.put("large", new RestHandler[]{new RandomHandler(), new ExceptionHandler(), new RoutingHandler(), new RandomHandler(), new InputHandler(), new ExceptionHandler(), new RoutingHandler(), new RandomHandler(), new InputHandler()});
        handlerChains.put("sequential", new RestHandler[]{new BlockingHandler(), new ExceptionHandler(), new InputHandler(), new RandomHandler(), new RoutingHandler()});
        handlerChains.put("random", new RestHandler[]{new InputHandler(), new RandomHandler(), new BlockingHandler(), new RoutingHandler(), new ExceptionHandler()});
    }

    @State(Scope.Benchmark)
    public static class HandlerState {
        @Param({"small", "large", "sequential", "random"})
        public String targetChain;

        public LambdaContextImpl lambdaContext;
        public UnrollContextImpl unrollContext;
        public InterfaceContextImpl interfaceContext;
        public MethodHandleImpl methodHandleContext;

        @Setup(Level.Trial)
        public void setUp() {
            RestHandler[] handlerChain = handlerChains.get(targetChain);
            lambdaContext = new LambdaContextImpl(handlerChain);
            unrollContext = new UnrollContextImpl(handlerChain);
            interfaceContext = new InterfaceContextImpl(handlerChain);
            methodHandleContext = new MethodHandleImpl(handlerChain);
        }

    }

    @Benchmark
    public int interfaceDispatch(HandlerState handlerState) throws Exception {
        InterfaceContextImpl context = handlerState.interfaceContext;
        context.handleSomething();
        return context.getCount();
    }

    @Benchmark
    public int unrollDispatch(HandlerState handlerState) throws Exception {
        UnrollContextImpl context = handlerState.unrollContext;
        context.handleSomething();
        return context.getCount();
    }

    @Benchmark
    public int lambdaDispatch(HandlerState handlerState) throws Exception {
        LambdaContextImpl context = handlerState.lambdaContext;
        context.handleSomething();
        return context.getCount();
    }

    @Benchmark
    public int methodHandleDispatch(HandlerState handlerState) throws Throwable {
        MethodHandleImpl context = handlerState.methodHandleContext;
        context.handleSomething();
        return context.getCount();
    }
}
