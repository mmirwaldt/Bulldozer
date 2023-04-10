package net.mirwaldt.bulldozer;

import java.lang.reflect.InvocationTargetException;

public class OverridingFinalToStringPuzzle {
    private static class Bull {
        public final String toString() {
            return "Bull";
        }
    }
    public static String convert(Bull bull) {
        return "%s".formatted(bull);
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        OverridingFinalToStringPuzzle i = new OverridingFinalToStringPuzzle() {
            public static String convert(Bull bull) { // It's hidden :-(
                return OverridingFinalToStringPuzzle.convert(bull).replace("Bull", "Bear");
            }
        };
        String result = (String) i.getClass().getDeclaredMethods()[0].invoke(null, new Bull()); // Not hidden anymore :-D
        if(!result.equals("Bear")) {
            throw new AssertionError("Should be \"Bear\"");
        }
        System.out.println("All good!");
    }
}
