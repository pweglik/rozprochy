/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sr.grpc.client;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sr.proto.AwardScholarshipArguments;
import sr.proto.AwardScholarshipResult;
import sr.proto.ScholarshipManagerGrpc;
import sr.proto.ScholarshipManagerGrpc.ScholarshipManagerBlockingStub;
import sr.proto.ScholarshipManagerGrpc.ScholarshipManagerFutureStub;
import sr.proto.ScholarshipManagerGrpc.ScholarshipManagerStub;
import sr.proto.ScholarshipType;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class grpcClient {
    private static final Logger logger = Logger.getLogger(grpcClient.class.getName());

    private final ManagedChannel channel;

    private final ScholarshipManagerBlockingStub managerBlockingStub;
    private final ScholarshipManagerStub managerNonBlockingStub;
    private final ScholarshipManagerFutureStub managerFutureStub;


    /**
     * Construct client connecting to HelloWorld server at {@code host:port}.
     */
    public grpcClient(String remoteHost, int remotePort) {
        channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
                .usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .build();


        managerBlockingStub = ScholarshipManagerGrpc.newBlockingStub(channel);
        managerNonBlockingStub = ScholarshipManagerGrpc.newStub(channel);
        managerFutureStub = ScholarshipManagerGrpc.newFutureStub(channel);
    }


    public static void main(String[] args) throws Exception {
        grpcClient client = new grpcClient("127.0.0.5", 50051);
        client.test();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public void test() throws InterruptedException {
        String line = null;
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        ListenableFuture<AwardScholarshipResult> future1 = null;

        do {
            try {
                System.out.print("==> ");
                System.out.flush();
                line = in.readLine();
                switch (line) {
                    case "award": {
                        AwardScholarshipArguments request = AwardScholarshipArguments
                                .newBuilder().setStudentId(123456).setType(ScholarshipType.SCIENCE).build();
                        AwardScholarshipResult result = managerBlockingStub.awardScholarship(request);
                        System.out.println("Scholarship awarded:" + result.getIsScholarshipAwarded());
                        break;
                    }
                    case "award-value": {
                        AwardScholarshipArguments request = AwardScholarshipArguments
                                .newBuilder().setStudentId(123456).setType(ScholarshipType.SCIENCE).setValue(1000).build();
                        AwardScholarshipResult result = managerBlockingStub.awardScholarship(request);
                        System.out.println("Scholarship awarded with value:" + result.getIsScholarshipAwarded());
                        break;
                    }
                    case "reject": {
                        AwardScholarshipArguments request = AwardScholarshipArguments
                                .newBuilder().setStudentId(111111).setType(ScholarshipType.SCIENCE).setValue(1000).build();
                        AwardScholarshipResult result = managerBlockingStub.awardScholarship(request);
                        System.out.println("Scholarship rejected (wrong ID):" + result.getIsScholarshipAwarded());
                        break;
                    }
                    case "":
                        break;
                    default:
                        System.out.println("???");
                        break;
                }
            } catch (java.io.IOException ex) {
                System.err.println(ex);
            }
        }
        while (!line.equals("x"));

        shutdown();
    }
}

