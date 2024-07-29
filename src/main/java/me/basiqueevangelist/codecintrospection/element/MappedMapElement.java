package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.mixin.dfu.mapcodec.*;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public record MappedMapElement(MapCodec<?> original, IntrospectionElement source, Function<?, DataResult<?>> to, Function<?, DataResult<?>> from) implements MapCodecIntrospectionElement {
    @SuppressWarnings("unchecked")
    static @Nullable MappedMapElement fromMapCodec(MapCodec<?> codec) {
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
        
        return new MappedMapElement(codec, CodecIntrospection.introspect(source), to, from);
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
//        out.write(indent, "MappedMapElement (" + to + ", " + from + ")");
        out.write(indent, "MappedMapElement");
        source.dump(out, indent + 1);
    }
}
