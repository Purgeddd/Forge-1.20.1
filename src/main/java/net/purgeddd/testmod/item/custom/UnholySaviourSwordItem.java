package net.purgeddd.testmod.item.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import net.purgeddd.testmod.item.client.UnholySaviourSwordItemRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UnholySaviourSwordItem extends Item implements GeoItem {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


    // Lazy-initialized Multimap for attribute modifiers
    private static final Supplier<Multimap<Attribute, AttributeModifier>> lazyAttributes = Suppliers.memoize(() -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        // Add REACH_DISTANCE attribute to extend attack range
        builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(
                "Extended Attack Range",
                10.0f, // Increase range by x units
                AttributeModifier.Operation.ADDITION
        ));
        return builder.build();
    });

    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }


    public UnholySaviourSwordItem(Properties p_41383_) {
        super(p_41383_);
    }


    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("unholy_saviour_sword_idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player) {
        super.onCraftedBy(stack, world, player);

        // Automatically add enchantments when crafted
        stack.enchant(Enchantments.SHARPNESS, 10);
        stack.enchant(Enchantments.UNBREAKING, 3);
        stack.enchant(Enchantments.FIRE_ASPECT,2);
        stack.enchant(Enchantments.KNOCKBACK, 5);
        stack.enchant(Enchantments.MENDING,1);
        stack.enchant(Enchantments.MOB_LOOTING,3);


    }
    
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(2, entity, (e) -> {
                e.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        return state.is(Blocks.COBWEB) || state.is(Blocks.BAMBOO);
    }


    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB) || state.is(Blocks.BAMBOO)) {
            return 20.0F; // Increase this number to make it mine cobwebs faster
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // Always glow
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));

            // Attack Damage Modifier
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    BASE_ATTACK_DAMAGE_UUID,
                    "Attack Damage modifier",
                    93.5f,
                    AttributeModifier.Operation.ADDITION));

            // Attack Speed Modifier
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(
                    BASE_ATTACK_SPEED_UUID,
                    "Attack Speed modifier",
                    0f,
                    AttributeModifier.Operation.ADDITION));

            // Add Reach Distance Modifier
            builder.putAll(lazyAttributes.get());




            return builder.build();
        }

        return super.getDefaultAttributeModifiers(equipmentSlot);
    }




    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private UnholySaviourSwordItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new UnholySaviourSwordItemRenderer();
                }

                return this.renderer;
            }
        });
    }

}
