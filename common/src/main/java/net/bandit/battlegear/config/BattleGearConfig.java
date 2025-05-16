package net.bandit.battlegear.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class BattleGearConfig {
    private static final Properties props = new Properties();
    private static final Path configPath = Path.of("config", "battlegear.properties");

    public static boolean ENABLE_CRUSADER_SET_BONUS = true;
    public static boolean ENABLE_GUARDIAN_SET_BONUS = true;
    public static boolean ENABLE_MYSTIC_SET_BONUS = true;
    public static boolean ENABLE_SERAPHIM_SET_BONUS = true;
    public static boolean ENABLE_TEMPLAR_SET_BONUS = true;

    public static void loadConfig() {
        try {
            if (!Files.exists(configPath)) {
                createDefault();
            }

            try (InputStream input = Files.newInputStream(configPath)) {
                props.load(input);
            }

            ENABLE_CRUSADER_SET_BONUS = Boolean.parseBoolean(props.getProperty("enable_crusader_set_bonus", "true"));
            ENABLE_GUARDIAN_SET_BONUS = Boolean.parseBoolean(props.getProperty("enable_paladin_set_bonus", "true"));
            ENABLE_MYSTIC_SET_BONUS = Boolean.parseBoolean(props.getProperty("enable_berserker_set_bonus", "true"));
            ENABLE_SERAPHIM_SET_BONUS = Boolean.parseBoolean(props.getProperty("enable_valkyrie_set_bonus", "true"));
            ENABLE_TEMPLAR_SET_BONUS = Boolean.parseBoolean(props.getProperty("enable_sentinel_set_bonus", "true"));

        } catch (IOException e) {
            System.err.println("Failed to load BattleGear config: " + e);
        }
    }

    private static void createDefault() throws IOException {
        props.setProperty("enable_crusader_set_bonus", "true");
        props.setProperty("enable_paladin_set_bonus", "true");
        props.setProperty("enable_berserker_set_bonus", "true");
        props.setProperty("enable_valkyrie_set_bonus", "true");
        props.setProperty("enable_sentinel_set_bonus", "true");
        Files.createDirectories(configPath.getParent());
        try (OutputStream output = Files.newOutputStream(configPath)) {
            props.store(output, "BattleGear Armor Set Bonuses Configuration");
        }
    }
}
