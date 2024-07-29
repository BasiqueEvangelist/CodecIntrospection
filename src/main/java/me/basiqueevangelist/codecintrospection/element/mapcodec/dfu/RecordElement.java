// Heavily borrowed from https://github.com/haykam821/Caricodec/blob/master/core/src/main/java/io/github/haykam821/caricodec/index/CodecIndexer.java

package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapDecoder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.basiqueevangelist.codecintrospection.MapCodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap.MappedMapDecoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.UnitMapDecoderAccessor;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.record.*;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public record RecordElement(MapCodec<?> original, List<IntrospectionElement> fields) implements MapCodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable RecordElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof RecordMapCodecAccessor recordCodec)) return null;

        List<IntrospectionElement> fieldList = new ArrayList<>();

        traverseBuilder(recordCodec.getVal$builder(), fieldList);

        return new RecordElement(codec, fieldList);
    }

    private static void traverseBuilder(RecordCodecBuilder<?, ?> builder, List<IntrospectionElement> fields) {
        traverseDecoder(((RecordCodecBuilderAccessor) (Object) builder).getDecoder(), fields);
    }

    private static void traverseDecoder(MapDecoder<?> decoder, List<IntrospectionElement> fields) {
        switch (decoder) {
            case Apply2DecoderAccessor apply2 -> {
                traverseBuilder(apply2.getVal$function(), fields);
                traverseBuilder(apply2.getVal$fa(), fields);
                traverseBuilder(apply2.getVal$fb(), fields);
            }
            case Apply3DecoderAccessor apply3 -> {
                traverseBuilder(apply3.getVal$function(), fields);
                traverseBuilder(apply3.getVal$f1(), fields);
                traverseBuilder(apply3.getVal$f2(), fields);
                traverseBuilder(apply3.getVal$f3(), fields);
            }
            case Apply4DecoderAccessor apply3 -> {
                traverseBuilder(apply3.getVal$function(), fields);
                traverseBuilder(apply3.getVal$f1(), fields);
                traverseBuilder(apply3.getVal$f2(), fields);
                traverseBuilder(apply3.getVal$f3(), fields);
                traverseBuilder(apply3.getVal$f4(), fields);
            }
            case MapCodec<?> codec -> fields.add(MapCodecIntrospection.introspect(codec));
            case MappedMapDecoderAccessor mapped -> traverseDecoder(mapped.getThis$0(), fields);
            case UnitMapDecoderAccessor unit -> {}
            case null, default -> System.out.println(decoder);
        }
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "RecordElement");

        for (var field : fields) {
            field.dump(out, indent + 1);
        }
    }
}
