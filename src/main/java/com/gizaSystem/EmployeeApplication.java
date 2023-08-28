package com.gizaSystem;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.tracing.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class EmployeeApplication {

//	private static Gauge gauge;
////
//	private static MeterRegistry meterRegistry;
////
//	private static List<Integer> list = new ArrayList<>();

//	@Bean
//	public Tracer getTracer() {
//		return new Tracer() {
//			@Override
//			public Span nextSpan() {
//				return null;
//			}
//
//			@Override
//			public Span nextSpan(Span span) {
//				return null;
//			}
//
//			@Override
//			public SpanInScope withSpan(Span span) {
//				return null;
//			}
//
//			@Override
//			public ScopedSpan startScopedSpan(String s) {
//				return null;
//			}
//
//			@Override
//			public Span.Builder spanBuilder() {
//				return null;
//			}
//
//			@Override
//			public TraceContext.Builder traceContextBuilder() {
//				return null;
//			}
//
//			@Override
//			public CurrentTraceContext currentTraceContext() {
//				return null;
//			}
//
//			@Override
//			public SpanCustomizer currentSpanCustomizer() {
//				return null;
//			}
//
//			@Override
//			public Span currentSpan() {
//				return null;
//			}
//
//			@Override
//			public Map<String, String> getAllBaggage() {
//				return null;
//			}
//
//			@Override
//			public Baggage getBaggage(String s) {
//				return null;
//			}
//
//			@Override
//			public Baggage getBaggage(TraceContext traceContext, String s) {
//				return null;
//			}
//
//			@Override
//			public Baggage createBaggage(String s) {
//				return null;
//			}
//
//			@Override
//			public Baggage createBaggage(String s, String s1) {
//				return null;
//			}
//		};
//	}
//	public EmployeeApplication(MeterRegistry registry) {
//		meterRegistry = registry;
//	}
//	@Bean
//	public TimedAspect timedAspect(MeterRegistry registry) {
//		return new TimedAspect(registry);
//	}

//	@Bean
//	public MeterRegistry getMeterRegistry(MeterRegistry registry) {
////		CompositeMeterRegistry meterRegistry = new CompositeMeterRegistry();
////		return meterRegistry;
//		return registry;
//	}
//	@Bean
//	public Gauge getGauge(MeterRegistry registry) {
//		return new Gauge() {
//			@Override
//			public double value() {
//				return 0;
//			}
//
//			@Override
//			public Id getId() {
//				return null;
//			}
//		};
//	}
	public static void main(String[] args) {

		SpringApplication.run(EmployeeApplication.class, args);

//		list.add(5);
//		gaugeExample(meterRegistry);
//
//		list.add(2);
//		list.add(2);
//		list.add(2);
//		list.add(2);
	}

//	static void gaugeExample(MeterRegistry meterRegistry) {
//		gauge = Gauge.builder("list.size",() -> list.size()).
//                description("list size").
//                register(meterRegistry);
//	}
}
