package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.mixin.dfu.mapcodec.MapResultMapCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

public record MapResultMapCodecElement(MapCodec<?> original, IntrospectionElement source) implements MapCodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "MapResultMapCodecElement");
        source.dump(out, indent + 1);
    }

    static IntrospectionElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof MapResultMapCodecAccessor mapResult)) return null;

        return new MapResultMapCodecElement(codec, CodecIntrospection.introspect(mapResult.getThis$0()));
    }
}
