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
package com.freeswitch.netty.util.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

final class AtomicFieldUpdaterUtil {

    private static final boolean AVAILABLE;

    static {
        boolean available = false;
        try {
            AtomicReferenceFieldUpdater<Node, Node> tmp = AtomicReferenceFieldUpdater.newUpdater(Node.class, Node.class, "next");

            // Test if AtomicReferenceFieldUpdater is really working.
            Node testNode = new Node();
            tmp.set(testNode, testNode);
            if (testNode.next != testNode) {
                // Not set as expected - fall back to the safe mode.
                throw new Exception();
            }
            available = true;
        } catch (Throwable t) {
            // Running in a restricted environment with a security manager.
        }
        AVAILABLE = available;
    }

    private AtomicFieldUpdaterUtil() {
        // Unused
    }

    static <T, V> AtomicReferenceFieldUpdater<T, V> newRefUpdater(Class<T> tclass, Class<V> vclass, String fieldName) {
        if (AVAILABLE) {
            return AtomicReferenceFieldUpdater.newUpdater(tclass, vclass, fieldName);
        } else {
            return null;
        }
    }

    static <T> AtomicIntegerFieldUpdater<T> newIntUpdater(Class<T> tclass, String fieldName) {
        if (AVAILABLE) {
            return AtomicIntegerFieldUpdater.newUpdater(tclass, fieldName);
        } else {
            return null;
        }
    }

    static boolean isAvailable() {
        return AVAILABLE;
    }

    static final class Node {
        volatile Node next;
    }
}
