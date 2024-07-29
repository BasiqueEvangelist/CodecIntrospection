package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.MapResultMapCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

public record MapResultMapCodecElement(MapCodec<?> original, IntrospectionElement source) implements MapCodecIntrospectionElement {
    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "MapResultMapCodecElement");
        source.dump(out, indent + 1);
    }

    @ApiStatus.Internal
    public static IntrospectionElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof MapResultMapCodecAccessor mapResult)) return null;

        return new MapResultMapCodecElement(codec, CodecIntrospection.introspect(mapResult.getThis$0()));
    }
}
