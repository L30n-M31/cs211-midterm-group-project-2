package util;

public class BinaryConverter {

    /**
     * Converts a given text into its binary ASCII representation.
     *
     * Each character of the input text is replaced with its binary ASCII value.
     *
     * @param text The input text to be converted
     * @return A string containing the binary ASCII representation of the input text
     */
    public String convertToBinaryASCII(String text) {
        StringBuilder binaryASCIICode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            String binaryValue = Integer.toBinaryString(character);
            binaryASCIICode.append(binaryValue);
        }
        return binaryASCIICode.toString();
    } // end of convertToBinaryASCII method
} // end of BinaryConverter
