# Resilience4j's TimeLimiter Always Triggered Bug

This project shows Resilience4j's TimeLimiter always triggered even feign request completed.

## How to use this project

+ Launch the application, and you will see twice exception stacktrace print.
+ Trace the log, and you will find out that `*BrokenHttpBinService` feign clients triggered time out fallback even them
  received response.
+ The Only differences between `*WorksHttpBinService` and `*BrokenHttpBinService` is `*BrokenHttpBinService`'s return
  type are not void.

## The environment I used

+ OpenJDK 1.8.0_292-b10 and 11.0.11.9-hotspot
+ Spring boot 2.5.2
+ Spring cloud 2020.0.3
