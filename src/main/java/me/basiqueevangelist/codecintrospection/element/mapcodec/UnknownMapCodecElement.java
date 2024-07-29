package me.basiqueevangelist.codecintrospection.element.mapcodec;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

public record UnknownMapCodecElement(MapCodec<?> original) implements MapCodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "UnknownMapCodecElement (of " + original.getClass() + ")");
    }
}
