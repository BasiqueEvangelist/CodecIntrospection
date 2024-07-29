package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.codecs.ListCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class IntrospectionElements {
    public static void init() {
        //region DFU
        for (PrimitiveElement element : PrimitiveElement.values()) {
            CodecIntrospection.registerStatic(element);
        }

        CodecIntrospection.registerCodecConverter(UnknownMappedCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(MapResultCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(UnboundedMapElement::fromCodec);
        CodecIntrospection.registerCodecConverter(PromotePartialCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(ListCodecElement::fromCodec);

        CodecIntrospection.registerMapCodecConverter(MappedMapElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(RecordElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(FieldElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(OptionalFieldElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(MapResultMapCodecElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(SimpleMapElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(KeyDispatchElement::fromMapCodec);
        //endregion

        //region Minecraft
        CodecIntrospection.registerCodecConverter(RegistryElementElement::fromCodec);
        CodecIntrospection.registerCodecConverter(RegistryEntryListElement::fromCodec);
        CodecIntrospection.registerCodecConverter(StringIdentifiableEnumElement::fromCodec);
        //endregion
    }
}
