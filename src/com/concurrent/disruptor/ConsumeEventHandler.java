package com.concurrent.disruptor;

import com.lmax.disruptor.EventHandler;

public class ConsumeEventHandler implements EventHandler<ValueEvent> {
	@Override
	public void onEvent(ValueEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		System.out.println("==================" + event.getValue());
	}

}