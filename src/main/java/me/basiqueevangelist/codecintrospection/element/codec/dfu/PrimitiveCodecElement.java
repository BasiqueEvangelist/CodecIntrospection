package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

public enum PrimitiveCodecElement implements CodecIntrospectionElement {
    BOOL(Codec.BOOL),
    BYTE(Codec.BYTE),
    SHORT(Codec.SHORT),
    INT(Codec.INT),
    LONG(Codec.LONG),
    FLOAT(Codec.FLOAT),
    DOUBLE(Codec.DOUBLE),
    STRING(Codec.STRING),
    BYTE_BUFFER(Codec.BYTE_BUFFER),
    INT_STREAM(Codec.INT_STREAM),
    LONG_STREAM(Codec.LONG_STREAM),
    PASSTHROUGH(Codec.PASSTHROUGH);

    private final Codec<?> codec;

    PrimitiveCodecElement(Codec<?> codec) {
        this.codec = codec;
    }

    @Override
    public Codec<?> original() {
        return codec;
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "PrimitiveCodecElement#" + name());
    }

}
