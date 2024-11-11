package ae.cyberspeed.scratch.core.initialization;

import java.util.Objects;

public final class CliParser {

    public static String parseAsString(String[] args, String paramName) {

        for (int i = 0; i < args.length; ++i) {
            if (Objects.equals(paramName, args[i]) && (i + 1) < args.length) {
                return args[i + 1];
            }
        }
        return null;
    }

    public static Integer parseAsInteger(String[] args, String paramName) {

        String valueStr = parseAsString(args, paramName);
        if (valueStr == null) {
            return null;
        }
        try {
            return Integer.valueOf(valueStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private CliParser() {
    }
}
