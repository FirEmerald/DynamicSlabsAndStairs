package com.firemerald.additionalplacements.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.firemerald.additionalplacements.block.stairs.AdditionalStairBlockBase;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

@Mixin(StateDefinition.Builder.class)
public class MixinStateDefinitionBuilder {
    @ModifyConstant(method = "validateProperty", constant = @Constant(intValue = 1), require = 1)
    private <T extends Comparable<T>> int validateProperty(int tooSmallSize, Property<T> property) {
    	return property.getName().equals(AdditionalStairBlockBase.PROPERTY_VERSION_NAME) ? 0 : tooSmallSize;
    }
}
