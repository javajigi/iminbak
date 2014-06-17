package net.slipp.support.utils;

import java.util.UUID;

public class SecurityUtils {

    public static String createToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + ":" + System.currentTimeMillis();
    }

    public static boolean validUuid(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        
        String sourceUuid = parseUuid(source);
        String targetUuid = parseUuid(target);
        return sourceUuid.equals(targetUuid);
    }
    
    public static long intervalSecond(String token) {
        long currentMillis = System.currentTimeMillis();
        long tokenMillis = parseCurrentTimeMillis(token);
        return (currentMillis - tokenMillis)/1000;
    }

    private static String parseUuid(String token) {
        String[] values = token.split(":");
        if (values.length != 2) {
            return "";
        }
        return values[0];
    }
    
    private static long parseCurrentTimeMillis(String token) {
        String[] values = token.split(":");
        if (values.length != 2) {
            return 0L;
        }
        return Long.parseLong(values[1]);
    }
}
