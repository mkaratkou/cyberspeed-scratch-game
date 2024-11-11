package ae.cyberspeed.scratch.domain.config.wincombinations;

import ae.cyberspeed.scratch.domain.util.Coordinate;

public class CoordinateParser {

    public static Coordinate parse(String coordinateStr) {
        if (coordinateStr == null) {
            throw newException("Input string is ", null);
        }
        int colonIndex = coordinateStr.indexOf(":");
        if (colonIndex < 0) {
            throw newException("Malformed coordinate format, a ':' symbol expected, but missing for input string=",
                    coordinateStr);
        }
        String iStr = coordinateStr.substring(0, colonIndex).trim();
        if (iStr.isEmpty()) {
            throw newException("Can't calculate row for input string=", coordinateStr);
        }
        int i;
        try {
            i = Integer.parseInt(iStr);
        } catch (NumberFormatException ex) {
            throw newException("Invalid row number for input string=", coordinateStr);
        }

        if (colonIndex + 1 == coordinateStr.length()) {
            throw newException("Can't calculate column for input string=", coordinateStr);
        }

        String jStr = coordinateStr.substring(colonIndex + 1).trim();
        if (jStr.isEmpty()) {
            throw newException("Can't calculate column for input string=", coordinateStr);
        }
        int j;
        try {
            j = Integer.parseInt(jStr);
        } catch (NumberFormatException ex) {
            throw newException("Invalid column number for input string=", coordinateStr);
        }

        return new Coordinate(i, j);
    }

    private static IllegalArgumentException newException(String message, String s) {
        throw new IllegalArgumentException(message + s);
    }

}
