package com.dbondarenko;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * File: AvatarCreator.java
 * The class that creates the avatar.
 * Created by Dmitro Bondarenko on 09.08.2017.
 */
public class AvatarCreator {

    // The number of pixels that make up the width of the avatar.
    private static final int AVATAR_WIDTH = 5;
    // The number of pixels that make up the height of the avatar.
    private static final int AVATAR_HEIGHT = 5;
    // The color that is used for the avatar background.
    private static final Color BACKGROUND = new Color(255, 255, 255);

    private AvatarOptions avatarOptions;

    public AvatarCreator(AvatarOptions options) {
        if (options == null) {
            throw new NullPointerException();
        } else {
            avatarOptions = options;
        }
    }

    /**
     * Create an avatar. The image is created by painting each pixel in the desired color.
     * The color of the pixel is determined from the value of the desired bit.
     * If the bit value is 1, then this is the color of the avatar, if 0 is the background color.
     *
     * @return bufferedImage.
     */
    public BufferedImage create() {
        BufferedImage bufferedImage = new BufferedImage(AVATAR_WIDTH, AVATAR_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // The counter that is used to determine the position of a bit in a bit sequence.
        int counter = 0;
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j <= bufferedImage.getWidth() / 2; j++) {
                // The position of the bit in the bit sequence. Used to calculate the bit value.
                int positionOfBit = counter % avatarOptions.getBitSequenceLength();
                // Depending on the bit value, the pixel color is selected. If the bit value is 1,
                // then this is the color of the avatar, if 0 is the background color.
                if ((avatarOptions.getHashCodeOfName() >> positionOfBit & 1) == 1) {
                    // Paint over the pixel in the color of the avatar.
                    paintOverPixel(avatarOptions.getAvatarColor(), bufferedImage, j, i);
                } else {
                    // Paint over the pixel in the color of the background.
                    paintOverPixel(BACKGROUND, bufferedImage, j, i);
                }
                counter++;
            }
        }
        return bufferedImage;
    }

    /**
     * Paint over the pixel with the desired color. Painting is carried out at once two pixels.
     * Select the pixel on the right side of the image from the received coordinates.
     * On the left side, the pixel is selected mirror-wise to the right pixel.
     *
     * @param colorPixel    The color for painting the pixel.
     * @param bufferedImage The image in which paint over the pixels.
     * @param x             The pixel coordinate.
     * @param y             The pixel coordinate.
     */
    private void paintOverPixel(Color colorPixel, BufferedImage bufferedImage, int x, int y) {
        // Paint over the pixel on the left side of the image.
        bufferedImage.setRGB(x, y, colorPixel.getRGB());
        // Paint over the pixel on the right side of the image.
        bufferedImage.setRGB(bufferedImage.getWidth() - 1 - x, y, colorPixel.getRGB());
    }
}