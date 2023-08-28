package com.gizaSystem.service;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Tracer tracer;

    public String sayHello() {

        // Create a span. If there was a span present in this thread it will become
// the `newSpan`'s parent.
        Span newSpan = this.tracer.nextSpan().name("user service");
// Start a span and put it in scope. Putting in scope means putting the span
// in thread local
// and, if configured, adjust the MDC to contain tracing information
        try (Tracer.SpanInScope ws = this.tracer.withSpan(newSpan.start())) {
            // ...
            // You can tag a span - put a key value pair on it for better debugging
//            newSpan.tag("taxValue", taxValue);
            // ...
            // You can log an event on a span - an event is an annotated timestamp
            newSpan.event("userService");
        }
        finally {
            // Once done remember to end the span. This will allow collecting
            // the span to send it to a distributed tracing system e.g. Zipkin
            newSpan.end();
        }
        return "hello from user service";
    }
}
