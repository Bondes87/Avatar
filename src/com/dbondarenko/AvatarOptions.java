package com.dbondarenko;

import java.awt.*;

/**
 * File: AvatarOptions.java
 * The class in which the parameters for creating the avatar are calculated.
 * Created by Dmitro Bondarenko on 18.08.2017.
 */
public class AvatarOptions {

    // The amount of intensity values that can be used in one of the three components to compose color.
    private static final int AMOUNT_OF_COLOR_INTENSITY = 256;
    // The minimum intensity value that is used in one of the three components to compose color.
    private static final int MINIMUM_OF_COLOR_INTENSITY = 0;
    // The maximum intensity value that is used in one of the three components to compose color.
    private static final int MAXIMUM_OF_COLOR_INTENSITY = 255;
    // The message that the user receives, if no name has been entered for the avatar.
    private static final String ERROR_MESSAGE_AN_EMPTY_STRING = "Sorry. You forgot to enter a name.";

    private Color avatarColor;
    private int bitSequenceLength;
    private int hashCodeOfName;

    public AvatarOptions(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException(ERROR_MESSAGE_AN_EMPTY_STRING);
        } else {
            // Calculate the hash code from the string.
            hashCodeOfName = Math.abs(name.hashCode());
            // Calculate the length of the bit sequence of the string.
            bitSequenceLength = Integer.toBinaryString(hashCodeOfName).length();
            // Get the avatar color.
            avatarColor = getColorOfAvatar(hashCodeOfName);
        }
    }

    public int getHashCodeOfName() {
        return hashCodeOfName;
    }

    public Color getAvatarColor() {
        return avatarColor;
    }

    public int getBitSequenceLength() {
        return bitSequenceLength;
    }

    /**
     * The color consists of three values of intensity. The intensity value can be from 0 to 255.
     * One value is calculated from the received hash code, the other two are the numbers 0 and 255.
     * Depending on the remainder of the division of the calculated intensity,
     * we get one of six combinations of the placement of three intensities.
     *
     * @param hashCode The hash code that is used to calculate a single color intensity.
     * @return Color of avatar.
     */
    private Color getColorOfAvatar(int hashCode) {
        // Calculate the intensity value from 0 to 255.
        int colorIntensity = hashCode % AMOUNT_OF_COLOR_INTENSITY;
        // Depending on the remainder of dividing by six calculated intensity,
        // one obtains one of six combinations of the arrangement of three intensities,
        // to create a color.
        switch (colorIntensity % 6) {
            case 0:
                return new Color(colorIntensity, MINIMUM_OF_COLOR_INTENSITY, MAXIMUM_OF_COLOR_INTENSITY);
            case 1:
                return new Color(MAXIMUM_OF_COLOR_INTENSITY, colorIntensity, MINIMUM_OF_COLOR_INTENSITY);
            case 2:
                return new Color(MINIMUM_OF_COLOR_INTENSITY, MAXIMUM_OF_COLOR_INTENSITY, colorIntensity);
            case 3:
                return new Color(colorIntensity, MAXIMUM_OF_COLOR_INTENSITY, MINIMUM_OF_COLOR_INTENSITY);
            case 4:
                return new Color(MINIMUM_OF_COLOR_INTENSITY, colorIntensity, MAXIMUM_OF_COLOR_INTENSITY);
            default:
                return new Color(MAXIMUM_OF_COLOR_INTENSITY, MINIMUM_OF_COLOR_INTENSITY, colorIntensity);
        }
    }
}