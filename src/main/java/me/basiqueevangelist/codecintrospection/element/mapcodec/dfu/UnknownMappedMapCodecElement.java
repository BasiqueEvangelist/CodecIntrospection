package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.MapCodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.MappedElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.*;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap.ComappedMapEncoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap.FlatComappedMapEncoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap.FlatMappedMapDecoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap.MappedMapDecoderAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public record UnknownMappedMapCodecElement(MapCodec<?> original, IntrospectionElement source, Function<?, DataResult<?>> to, Function<?, DataResult<?>> from) implements MappedElement, MapCodecIntrospectionElement {
    @SuppressWarnings("unchecked")
    @ApiStatus.Internal
    public static @Nullable UnknownMappedMapCodecElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof CombinedMapCodecAccessor combined)) return null;

        Function<?, DataResult<?>> to;
        Function<?, DataResult<?>> from;
        MapCodec<?> source;

        switch (combined.getVal$encoder()) {
            case ComappedMapEncoderAccessor comapped -> {
                var func = (Function<Object, ?>) comapped.getVal$function();
                from = unknown -> DataResult.success(func.apply(unknown));

                if (!(comapped.getThis$0() instanceof MapCodec<?> src)) return null;

                source = src;
            }
            case FlatComappedMapEncoderAccessor flatComapped -> {
                from = flatComapped.getVal$function();

                if (!(flatComapped.getThis$0() instanceof MapCodec<?> src)) return null;

                source = src;
            }
            case null, default -> {
                return null;
            }
        }

        switch (combined.getVal$decoder()) {
            case MappedMapDecoderAccessor mapped -> {
                var func = (Function<Object, ?>) mapped.getVal$function();
                to = unknown -> DataResult.success(func.apply(unknown));

                if (mapped.getThis$0() != source) return null;
            }

            case FlatMappedMapDecoderAccessor flatMapped -> {
                to = flatMapped.getVal$function();

                if (flatMapped.getThis$0() != source) return null;
            }
            case null, default -> {
                return null;
            }
        }
        
        return new UnknownMappedMapCodecElement(codec, MapCodecIntrospection.introspect(source), to, from);
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
//        out.write(indent, "UnknownMappedMapCodecElement (" + to + ", " + from + ")");
        out.write(indent, "UnknownMappedMapCodecElement");
        source.dump(out, indent + 1);
    }
}
