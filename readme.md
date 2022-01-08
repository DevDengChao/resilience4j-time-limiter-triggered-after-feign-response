# Resilience4j's TimeLimiter Always Triggered Bug

This project shows Resilience4j's TimeLimiter always triggered even feign request completed inside `postConstruct()` callback.

## How this comes

I found this issue because I want to warm up feign clients before the application receives any client requests to reduce
the response time when the application was (re-)started recently.

So I found a proper place (I think), the `postConstruct()` callback, to warm up my Feign clients, but the application failed
to start then.

## How to reproduce the issue

### Dry run

+ Launch the application, and you will see twice exception stacktrace print.
+ Trace the log, and you will find out that `*BrokenHttpBinService` feign clients triggered time out fallback even them
  received response.
+ The Only differences between `*WorksHttpBinService` and `*BrokenHttpBinService` is `*BrokenHttpBinService`'s return
  type are not void.

### Or running tests

+ Run `gradlew :test`
+ `RootControllerTest` pass as expected
+ `FeignClientsWarmupTest` fails as it performs Feign request in `postConstruct()`

## The environment I used

+ OpenJDK 1.8.0_292-b10 and 11.0.11.9-hotspot
+ Spring boot 2.5.2
+ Spring cloud 2020.0.3
