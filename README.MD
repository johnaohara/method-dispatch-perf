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
| interfaceDispatch    | small         | avgt | 5   | 2.538  | ± 0.238 | ns/op |
| interfaceDispatch    | large         | avgt | 5   | 49.598 | ± 4.489 | ns/op |
| interfaceDispatch    | sequential    | avgt | 5   | 28.503 | ± 1.902 | ns/op |
| interfaceDispatch    | random        | avgt | 5   | 28.130 | ± 1.757 | ns/op |
| lambdaDispatch       | small         | avgt | 5   | 3.420  | ± 0.160 | ns/op |
| lambdaDispatch       | large         | avgt | 5   | 52.717 | ± 6.969 | ns/op |
| lambdaDispatch       | sequential    | avgt | 5   | 30.210 | ± 0.833 | ns/op |
| lambdaDispatch       | random        | avgt | 5   | 30.138 | ± 3.051 | ns/op |
| methodHandleDispatch | small         | avgt | 5   | 7.024  | ± 0.175 | ns/op |
| methodHandleDispatch | large         | avgt | 5   | 55.307 | ± 7.946 | ns/op |
| methodHandleDispatch | sequential    | avgt | 5   | 43.786 | ± 0.137 | ns/op |
| methodHandleDispatch | random        | avgt | 5   | 30.895 | ± 1.146 | ns/op |
| unrollDispatch       | small         | avgt | 5   | 3.236  | ± 0.204 | ns/op |
| unrollDispatch       | large         | avgt | 5   | 55.189 | ± 2.619 | ns/op |
| unrollDispatch       | sequential    | avgt | 5   | 31.227 | ± 0.702 | ns/op |
| unrollDispatch       | random        | avgt | 5   | 30.699 | ± 1.379 | ns/op |
