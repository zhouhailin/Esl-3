/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.freeswitch.netty.handler.timeout;

import com.freeswitch.netty.channel.Channel;
import com.freeswitch.netty.channel.ChannelEvent;

/**
 * A {@link ChannelEvent} that is triggered when a {@link Channel} has been idle
 * for a while.
 * 
 * @apiviz.landmark
 * @apiviz.has org.jboss.netty.handler.timeout.IdleState oneway - -
 */
public interface IdleStateEvent extends ChannelEvent {
	/**
	 * Returns the detailed idle state.
	 */
	IdleState getState();

	/**
	 * Returns the last time when I/O occurred in milliseconds.
	 */
	long getLastActivityTimeMillis();
}
