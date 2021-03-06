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
package com.freeswitch.netty.handler.stream;

import com.freeswitch.netty.buffer.ChannelBuffer;

/**
 * A data stream of indefinite length which is consumed by
 * {@link ChunkedWriteHandler}.
 *
 * @apiviz.landmark
 */
public interface ChunkedInput {

    /**
     * Returns {@code true} if and only if there is any data left in the stream.
     * Please note that {@code false} does not necessarily mean that the stream
     * has reached at its end. In a slow stream, the next chunk might be
     * unavailable just momentarily.
     */
    boolean hasNextChunk() throws Exception;

    /**
     * Fetches a chunked data from the stream. The returned chunk is usually a
     * {@link ChannelBuffer}, but you could extend an existing implementation to
     * convert the {@link ChannelBuffer} into a different type that your handler
     * or encoder understands. Once this method returns the last chunk and thus
     * the stream has reached at its end, any subsequent {@link #isEndOfInput()}
     * call must return {@code false}.
     *
     * @return the fetched chunk, which is usually {@link ChannelBuffer}.
     * {@code null} if there is no data left in the stream. Please note
     * that {@code null} does not necessarily mean that the stream has
     * reached at its end. In a slow stream, the next chunk might be
     * unavailable just momentarily.
     */
    Object nextChunk() throws Exception;

    /**
     * Return {@code true} if and only if there is no data left in the stream
     * and the stream has reached at its end.
     */
    boolean isEndOfInput() throws Exception;

    /**
     * Releases the resources associated with the stream.
     */
    void close() throws Exception;
}
