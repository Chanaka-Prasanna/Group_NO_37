package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String get_base_url(String key){
        return dotenv.get(key);
    }
}
