#Build

```shell
$ mvn clean package
```

#Run

```shell
$ java -jar target/benchmarks.jar
```
#Results


| Benchmark                                    | (targetChain) | Mode | Cnt | Score  | Error   | Units |
|----------------------------------------------|---------------|------|-----|--------|---------|-------|
| MethodDispatchBenchmark.interfaceDispatch    | small         | avgt | 5   | 2.538  | ± 0.238 | ns/op |
| MethodDispatchBenchmark.interfaceDispatch    | large         | avgt | 5   | 49.598 | ± 4.489 | ns/op |
| MethodDispatchBenchmark.interfaceDispatch    | sequential    | avgt | 5   | 28.503 | ± 1.902 | ns/op |
| MethodDispatchBenchmark.interfaceDispatch    | random        | avgt | 5   | 28.130 | ± 1.757 | ns/op |
| MethodDispatchBenchmark.lambdaDispatch       | small         | avgt | 5   | 3.420  | ± 0.160 | ns/op |
| MethodDispatchBenchmark.lambdaDispatch       | large         | avgt | 5   | 52.717 | ± 6.969 | ns/op |
| MethodDispatchBenchmark.lambdaDispatch       | sequential    | avgt | 5   | 30.210 | ± 0.833 | ns/op |
| MethodDispatchBenchmark.lambdaDispatch       | random        | avgt | 5   | 30.138 | ± 3.051 | ns/op |
| MethodDispatchBenchmark.methodHandleDispatch | small         | avgt | 5   | 7.024  | ± 0.175 | ns/op |
| MethodDispatchBenchmark.methodHandleDispatch | large         | avgt | 5   | 55.307 | ± 7.946 | ns/op |
| MethodDispatchBenchmark.methodHandleDispatch | sequential    | avgt | 5   | 43.786 | ± 0.137 | ns/op |
| MethodDispatchBenchmark.methodHandleDispatch | random        | avgt | 5   | 30.895 | ± 1.146 | ns/op |
| MethodDispatchBenchmark.unrollDispatch       | small         | avgt | 5   | 3.236  | ± 0.204 | ns/op |
| MethodDispatchBenchmark.unrollDispatch       | large         | avgt | 5   | 55.189 | ± 2.619 | ns/op |
| MethodDispatchBenchmark.unrollDispatch       | sequential    | avgt | 5   | 31.227 | ± 0.702 | ns/op |
| MethodDispatchBenchmark.unrollDispatch       | random        | avgt | 5   | 30.699 | ± 1.379 | ns/op |
