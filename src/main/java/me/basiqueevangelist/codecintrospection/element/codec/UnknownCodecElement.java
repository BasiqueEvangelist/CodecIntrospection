package me.basiqueevangelist.codecintrospection.element.codec;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

public record UnknownCodecElement(Codec<?> original) implements CodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "UnknownCodecElement (of " + original.getClass() + ")");
    }
}
