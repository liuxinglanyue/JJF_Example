package com.concurrent.disruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer implements Runnable {

	private RingBuffer<ValueEvent> ringBuffer = null;

	public Producer(RingBuffer<ValueEvent> rb) {

		ringBuffer = rb;
	}

	@Override
	public void run() {
		// Publishers claim events in sequence
		long sequence = ringBuffer.next();
		ValueEvent event = ringBuffer.get(sequence);
		event.setValue(1234); // this could be more complex with multiple fields

		// make the event available to EventProcessors
		ringBuffer.publish(sequence);
	}
}