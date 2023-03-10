package com.github.sniffity.sniffityscreatures.config;

import com.github.sniffity.sniffityscreatures.SniffitysCreatures;
import net.minecraftforge.common.ForgeConfigSpec;


/**
 * Sniffity's Mobs - Class: SMCommonConfig <br></br?>
 *
 * Acknowledgements: The following class was developed after studying how Mowzie's Mobs handles
 * their own config.
 */

public final class SMServerConfig {

    public static ForgeConfigSpec SERVER_CONFIG;
    public static final Server SERVER;
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    private static final String LANG_PREFIX = "config." + SniffitysCreatures.MODID + ".";

    static {
        SERVER = new Server(SERVER_BUILDER);
        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    public static class GeneralConfig {

        GeneralConfig(ForgeConfigSpec.Builder builder) {
            builder.push("general");
            builder.pop();
        }
    }

    public static class Server {
        private Server(final ForgeConfigSpec.Builder builder) {
            GENERAL = new GeneralConfig(builder);
            ENTITIES = new Entities(builder);
        }

        public final GeneralConfig GENERAL;
        public final Entities ENTITIES;
    }

    public static class Entities {
        Entities(final ForgeConfigSpec.Builder builder) {
            builder.push("entities");
            WEREWOLF = new Werewolf(builder);
            builder.pop();
        }

        public final Werewolf WEREWOLF;

        public static class Werewolf {
            Werewolf(final ForgeConfigSpec.Builder builder) {
                builder.push("werewolf");
                this.enableWerewolf = builder.comment("Whether to enable werewolves or not")
                        .translation(LANG_PREFIX + "enable_werewolf")
                        .define("enable_werewolf", true);
                this.forceMinWolfShrine = builder.comment("Whether to force at least one Wolf Shrine per village")
                        .translation(LANG_PREFIX + "force_min_wolfshrine")
                        .define("force_min_wolfshrine", false);
                this.wolfShrineSpawnWeightPlains = builder.comment("Spawn Weight for Wolf Shrines in Plains Villages")
                        .translation(LANG_PREFIX + "spawn_weight_wolf_shrine_plains")
                        .defineInRange("spawn_weight_wolf_shrine_plains", 140,1,150);
                this.wolfShrineSpawnWeightTaiga = builder.comment("Spawn Weight for Wolf Shrines in Taiga Villages")
                        .translation(LANG_PREFIX + "spawn_weight_wolf_shrine_taiga")
                        .defineInRange("spawn_weight_wolf_shrine_taiga", 140,1,150);
                this.wolfShrineSpawnWeightSavanna = builder.comment("Spawn Weight for Wolf Shrines in Savanna Villages")
                        .translation(LANG_PREFIX + "spawn_weight_wolf_shrine_savanna")
                        .defineInRange("spawn_weight_wolf_shrine_savanna", 140,1,150);
                this.wolfShrineSpawnWeightDesert = builder.comment("Spawn Weight for Wolf Shrines in Desert Villages")
                        .translation(LANG_PREFIX + "spawn_weight_wolf_shrine_desert")
                        .defineInRange("spawn_weight_wolf_shrine_desert", 140,1,150);
                this.wolfShrineSpawnWeightSnowy = builder.comment("Spawn Weight for Wolf Shrines in Snowy Villages")
                        .translation(LANG_PREFIX + "spawn_weight_wolf_shrine_snowy")
                        .defineInRange("spawn_weight_wolf_shrine_snowy", 140,1,150);
                this.attributes = new Attributes(builder,250,10);
                builder.pop();
            }

            public final ForgeConfigSpec.BooleanValue enableWerewolf;
            public final ForgeConfigSpec.IntValue wolfShrineSpawnWeightPlains;
            public final ForgeConfigSpec.IntValue wolfShrineSpawnWeightTaiga;
            public final ForgeConfigSpec.IntValue wolfShrineSpawnWeightSavanna;
            public final ForgeConfigSpec.IntValue wolfShrineSpawnWeightDesert;
            public final ForgeConfigSpec.IntValue wolfShrineSpawnWeightSnowy;
            public final ForgeConfigSpec.BooleanValue forceMinWolfShrine;
            public final Attributes attributes;
        }
    }

    public static class Attributes {
        Attributes(final ForgeConfigSpec.Builder builder, float entityHealth, float entityAttack) {
            builder.push("combat_config");
            this.entityHealth = builder.comment("Set entity's health to this value")
                    .translation(LANG_PREFIX + "health_value")
                    .defineInRange("health_value", entityHealth, 0d, Double.MAX_VALUE);
            this.entityAttack = builder.comment("Set entity's attack damage to this value")
                    .translation(LANG_PREFIX + "attack_value")
                    .defineInRange("attack_value", entityAttack, 0d, Double.MAX_VALUE);
            builder.pop();
        }
        public final ForgeConfigSpec.DoubleValue entityHealth;
        public final ForgeConfigSpec.DoubleValue entityAttack;
    }


}