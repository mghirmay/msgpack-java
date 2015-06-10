//
// MessagePack for Java
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
package org.msgpack.value.impl;

import org.msgpack.core.MessagePacker;
import org.msgpack.value.Value;
import org.msgpack.value.ValueType;
import org.msgpack.value.ExtendedValue;
import org.msgpack.value.ImmutableExtendedValue;

import java.util.Arrays;
import java.io.IOException;


/**
 * {@code ImmutableExtendedValueImpl} Implements {@code ImmutableExtendedValue} using a {@code byte} and a {@code byte[]} fields.
 *
 * @see  org.msgpack.value.ExtendedValue
 */
public class ImmutableExtendedValueImpl extends AbstractImmutableValue implements ImmutableExtendedValue {
    private final byte type;
    private final byte[] data;

    public ImmutableExtendedValueImpl(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.EXTENDED;
    }

    @Override
    public ImmutableExtendedValue immutableValue() {
        return this;
    }

    @Override
    public ImmutableExtendedValue asExtendedValue() {
        return this;
    }

    @Override
    public byte getType() {
        return type;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public void writeTo(MessagePacker packer) throws IOException {
        packer.packExtendedTypeHeader(type, data.length);
        packer.writePayload(data);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Value)) {
            return false;
        }
        Value v = (Value) o;

        if (!v.isExtendedValue()) {
            return false;
        }
        ExtendedValue ev = v.asExtendedValue();
        return type == ev.getType() && Arrays.equals(data, ev.getData());
    }

    @Override
    public int hashCode() {
        int hash = 31 + type;
        for (byte e : data) {
            hash = 31 * hash + e;
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(Byte.toString(type));
        sb.append(",0x");
        for (byte e : data) {
            sb.append(Integer.toString((int) e, 16));
        }
        sb.append(")");
        return sb.toString();
    }
}