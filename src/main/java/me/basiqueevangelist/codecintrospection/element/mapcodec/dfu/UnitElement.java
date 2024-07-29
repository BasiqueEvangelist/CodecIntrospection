package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.CombinedMapCodecAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.EmptyMapEncoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.UnitMapDecoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record UnitElement(MapCodec<?> original, Object value) implements MapCodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable UnitElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof CombinedMapCodecAccessor combined)) return null;
        if (!(combined.getVal$encoder() instanceof EmptyMapEncoderAccessor)) return null;
        if (!(combined.getVal$decoder() instanceof UnitMapDecoderAccessor unit)) return null;

        return new UnitElement(codec, unit.getVal$instance().get());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "UnitElement (" + value + ")");
    }
}
