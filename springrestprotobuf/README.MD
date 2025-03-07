# Using ProtoBuff with Rest

## Description

Here we are using REST with protobuf to replace JSON
which means that the final message will be binary

## Difference with gRPC

the difference with gRPC and this approach is that 
using this approach we are still using REST,
but with messages proto binaries.

With gRPC we are calling methods not END-POINTS 
although gRPC uses HTTP2 under the hoods

In gRPC, aside of "message", we would use

```
service ChatService {
    rpc Chat(stream ChatMessage) returns (stream ChatMessage);
}
```

REST Approach dependencies

```
# to operate with proto messages
 <dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-protobuf</artifactId>
</dependency>
```

gRPC approach dependencies

```
# to operate with proto messages
 <dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-protobuf</artifactId>
</dependency>
# to operate with RPC (remote procedural call)
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-stub</artifactId>
</dependency>
```

## To generate protoBuff body

1. Go to GenerateBinaryBody, modify the object and execute it
[GenerateBinaryBody](rest-web/src/main/java/nl/duckacademy/generate)
2. The resulting file will be allocated here:
[binary file](protobuf_bin)
