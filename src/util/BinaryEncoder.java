package util;

public class BinaryEncoder {
    /**
     * Converts a given text to its binary ASCII representation
     *
     * @param text to be converted in binary ASCII codes
     * @return the binary ASCII representation of the text input
     */
    public String convertToBinaryASCII(String text) {
        StringBuilder binaryASCIICode = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character == '\\') {
                character = '\n';
                i++;
            }
            StringBuilder binaryValue = new StringBuilder(Integer.toBinaryString(character));

            // adds leading zeros if the binary value is less than 7
            while (binaryValue.length() < 7) {
                binaryValue.insert(0, "0");
            }

            binaryASCIICode.append(binaryValue);
        }
        return binaryASCIICode.toString();
    } // end of convertToBinaryASCII
} // end of BinaryEncoder class
