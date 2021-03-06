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
package com.freeswitch.netty.channel;

import com.freeswitch.netty.buffer.ChannelBuffer;
import com.freeswitch.netty.buffer.ChannelBufferFactory;
import com.freeswitch.netty.buffer.HeapChannelBufferFactory;
import com.freeswitch.netty.channel.socket.SocketChannelConfig;
import com.freeswitch.netty.channel.socket.nio.NioSocketChannelConfig;

import java.nio.ByteOrder;
import java.util.Map;

/**
 * A set of configuration properties of a {@link Channel}.
 * <p>
 * Please down-cast to more specific configuration type such as
 * {@link SocketChannelConfig} or use {@link #setOptions(Map)} to set the
 * transport-specific properties:
 * <p>
 * <pre>
 * {@link Channel} ch = ...;
 * {@link SocketChannelConfig} cfg = <strong>({@link SocketChannelConfig}) ch.getConfig();</strong>
 * cfg.setTcpNoDelay(false);
 * </pre>
 * <p>
 * <h3>Option map</h3>
 * <p>
 * An option map property is a dynamic write-only property which allows the
 * configuration of a {@link Channel} without down-casting its associated
 * {@link ChannelConfig}. To update an option map, please call
 * {@link #setOptions(Map)}.
 * <p>
 * All {@link ChannelConfig} has the following options:
 * <p>
 * <table border="1" cellspacing="0" cellpadding="6">
 * <tr>
 * <th>Name</th>
 * <th>Associated setter method</th>
 * </tr>
 * <tr>
 * <td>{@code "bufferFactory"}</td>
 * <td>{@link #setBufferFactory(ChannelBufferFactory)}</td>
 * </tr>
 * <tr>
 * <td>{@code "connectTimeoutMillis"}</td>
 * <td>{@link #setConnectTimeoutMillis(int)}</td>
 * </tr>
 * <tr>
 * <td>{@code "pipelineFactory"}</td>
 * <td>{@link #setPipelineFactory(ChannelPipelineFactory)}</td>
 * </tr>
 * </table>
 * <p>
 * More options are available in the sub-types of {@link ChannelConfig}. For
 * server, you can configure the parameters which are specific to a TCP/IP
 * socket as explained in {@link SocketChannelConfig} or
 * {@link NioSocketChannelConfig}.
 *
 * @apiviz.has org.jboss.netty.channel.ChannelPipelineFactory
 * @apiviz.composedOf org.jboss.netty.channel.ReceiveBufferSizePredictor
 * @apiviz.excludeSubtypes
 */
public interface ChannelConfig {

    /**
     * Sets the configuration properties from the specified {@link Map}.
     */
    void setOptions(Map<String, Object> options);

    /**
     * Sets a configuration property with the specified name and value. To
     * override this method properly, you must call the super class:
     * <p>
     * <pre>
     * public boolean setOption(String name, Object value) {
     *     if (super.setOption(name, value)) {
     *         return true;
     *     }
     *
     *     if (name.equals("additionalOption")) {
     *         ....
     *         return true;
     *     }
     *
     *     return false;
     * }
     * </pre>
     *
     * @return {@code true} if and only if the property has been set
     */
    boolean setOption(String name, Object value);

    /**
     * Returns the default {@link ChannelBufferFactory} used to create a new
     * {@link ChannelBuffer}. The default is {@link HeapChannelBufferFactory}.
     * You can specify a different factory to change the default
     * {@link ByteOrder} for server.
     */
    ChannelBufferFactory getBufferFactory();

    /**
     * Sets the default {@link ChannelBufferFactory} used to create a new
     * {@link ChannelBuffer}. The default is {@link HeapChannelBufferFactory}.
     * You can specify a different factory to change the default
     * {@link ByteOrder} for server.
     */
    void setBufferFactory(ChannelBufferFactory bufferFactory);

    /**
     * Returns the {@link ChannelPipelineFactory} which will be used when a
     * child channel is created. If the {@link Channel} does not create a child
     * channel, this property is not used at all, and therefore will be ignored.
     */
    ChannelPipelineFactory getPipelineFactory();

    /**
     * Sets the {@link ChannelPipelineFactory} which will be used when a child
     * channel is created. If the {@link Channel} does not create a child
     * channel, this property is not used at all, and therefore will be ignored.
     */
    void setPipelineFactory(ChannelPipelineFactory pipelineFactory);

    /**
     * Returns the connect timeout of the channel in milliseconds. If the
     * {@link Channel} does not support connect operation, this property is not
     * used at all, and therefore will be ignored.
     *
     * @return the connect timeout in milliseconds. {@code 0} if disabled.
     */
    int getConnectTimeoutMillis();

    /**
     * Sets the connect timeout of the channel in milliseconds. If the
     * {@link Channel} does not support connect operation, this property is not
     * used at all, and therefore will be ignored.
     *
     * @param connectTimeoutMillis the connect timeout in milliseconds. {@code 0} to disable.
     */
    void setConnectTimeoutMillis(int connectTimeoutMillis);
}
