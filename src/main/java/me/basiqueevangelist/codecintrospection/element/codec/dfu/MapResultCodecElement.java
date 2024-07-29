package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.dfu.MapResultCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

public record MapResultCodecElement(Codec<?> original, IntrospectionElement source) implements CodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "MapResultCodecElement");
        source.dump(out, indent + 1);
    }

    @ApiStatus.Internal
    public static IntrospectionElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof MapResultCodecAccessor mapResult)) return null;

        return new MapResultCodecElement(codec, CodecIntrospection.introspect(mapResult.getThis$0()));
    }
}
