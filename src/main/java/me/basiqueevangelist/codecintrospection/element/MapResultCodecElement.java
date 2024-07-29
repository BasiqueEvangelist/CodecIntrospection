package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.mixin.dfu.codec.MapResultCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

public record MapResultCodecElement(Codec<?> original, IntrospectionElement source) implements CodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "MapResultCodecElement");
        source.dump(out, indent + 1);
    }

    static IntrospectionElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof MapResultCodecAccessor mapResult)) return null;

        return new MapResultCodecElement(codec, CodecIntrospection.introspect(mapResult.getThis$0()));
    }
}
