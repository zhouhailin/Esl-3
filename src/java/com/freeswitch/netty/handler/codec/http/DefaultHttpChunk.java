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
package com.freeswitch.netty.handler.codec.http;

import com.freeswitch.netty.buffer.ChannelBuffer;

/**
 * The default {@link HttpChunk} implementation.
 */
public class DefaultHttpChunk implements HttpChunk {

    private ChannelBuffer content;
    private boolean last;

    /**
     * Creates a new instance with the specified chunk content. If an empty
     * buffer is specified, this chunk becomes the 'end of content' marker.
     */
    public DefaultHttpChunk(ChannelBuffer content) {
        setContent(content);
    }

    public ChannelBuffer getContent() {
        return content;
    }

    public void setContent(ChannelBuffer content) {
        if (content == null) {
            throw new NullPointerException("content");
        }
        last = !content.readable();
        this.content = content;
    }

    public boolean isLast() {
        return last;
    }
}
