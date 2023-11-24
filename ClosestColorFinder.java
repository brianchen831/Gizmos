import java.awt.Color;
//ignore this class yall it just makes our lives hella easy
public class ClosestColorFinder {

    public static String findClosestBasicColor(Color targetColor) {
        Color[] basicColors = {Color.GRAY, Color.YELLOW, Color.RED, Color.BLUE};

        int minDistance = Integer.MAX_VALUE;
        String closestColor = "";

        for (Color basicColor : basicColors) {
            int distance = calculateColorDistance(targetColor, basicColor);

            if (distance < minDistance) {
                minDistance = distance;
                closestColor = getColorName(basicColor);
            }
        }

        return closestColor;
    }

    private static int calculateColorDistance(Color color1, Color color2) {
        
        int redDiff = color1.getRed() - color2.getRed();
        int greenDiff = color1.getGreen() - color2.getGreen();
        int blueDiff = color1.getBlue() - color2.getBlue();

        return redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff;
    }

    static String getColorName(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        System.out.println(color);
        if (isBlue(red, green, blue)) return "Blue";
        if (isGray(red, green, blue)) return "Grey";
        if (isYellow(red, green, blue)) return "Yellow";
        if (isRed(red, green, blue)) return "Red";
        return "";
    }

    private static boolean isGray(int red, int green, int blue) {
        int threshold = 20;
        return Math.abs(red - green) < threshold && Math.abs(red - blue) < threshold;
    }

    private static boolean isYellow(int red, int green, int blue) {
        return red > 180 && green > 180 && blue < 80;
    }

    private static boolean isRed(int red, int green, int blue) {
        return red > 180 && green < 80 && blue < 80;
    }

    private static boolean isBlue(int red, int green, int blue) {
        
        return red < 80 && green < 113  && blue >= 170;
        
    }
}
