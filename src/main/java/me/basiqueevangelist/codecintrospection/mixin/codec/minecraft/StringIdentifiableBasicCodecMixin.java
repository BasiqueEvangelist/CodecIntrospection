package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import me.basiqueevangelist.codecintrospection.pond.StringIdentifiableBasicCodecAccess;
import net.minecraft.util.StringIdentifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Mixin(StringIdentifiable.BasicCodec.class)
public class StringIdentifiableBasicCodecMixin implements StringIdentifiableBasicCodecAccess {
    @Unique private StringIdentifiable[] codecintrospection$values;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void storeElements(StringIdentifiable[] values, Function<String, StringIdentifiable> idToIdentifiable, ToIntFunction<StringIdentifiable> identifiableToOrdinal, CallbackInfo ci) {
        codecintrospection$values = values;
    }

    @Override
    public StringIdentifiable[] codecintrospection$values() {
        return codecintrospection$values;
    }
}
