package org.fageria.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
    @Override
    public void createBillingService(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {

        log.info("Billing request on the grpc got received {} ", billingRequest.toString());

        // business logic here

        BillingResponse response = BillingResponse.newBuilder().
                setAccountId("1234")
                .setStatus("active")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
