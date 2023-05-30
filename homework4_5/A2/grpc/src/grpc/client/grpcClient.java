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

package grpc.client;

import grpc.server.NotificationReply;
import grpc.server.NotificationRequest;
import grpc.server.StreamingEventsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;


import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;




public class grpcClient
{
	private final ManagedChannel channel;

	private final StreamingEventsGrpc.StreamingEventsBlockingStub stub;


	/** Construct client connecting to HelloWorld server at {@code host:port}. */
	public grpcClient(String remoteHost, int remotePort)
	{
		channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
				.usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
				.build();

		this.stub = StreamingEventsGrpc.newBlockingStub(channel);
	}



	public static void main(String[] args) throws Exception
	{
		grpcClient client = new grpcClient("127.0.0.5", 50051);
		client.test();
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}


	public void test() throws InterruptedException
	{
		NotificationRequest request = NotificationRequest.newBuilder().setCity("Cracow").build();
		Iterator<NotificationReply> notifs;
		try {
			notifs = stub.subscribe(request);
		} catch (StatusRuntimeException ex) {
			System.err.println("*** gRPC error");
			return;
		}

		while (notifs.hasNext()) {
			NotificationReply notif = notifs.next();
			System.out.println(notif.getCity());
			System.out.println(notif.getTemperature());
		}

		shutdown();
	}
}

