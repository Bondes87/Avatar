package com.dbondarenko;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * File: AvatarCreator.java
 * The class that creates the avatar from the name.
 * Created by Dmitro Bondarenko on 09.08.2017.
 */
public class AvatarCreator {
    // The constant adjusting the size of the avatar (the number of pixels).
    private static final int SIZE_AVATAR = 5;
    // The constant adjusting the background color of the avatar.
    private static final Color COLOR_BACKGROUND = new Color(255, 255, 255);

    /**
     * Create an avatar using the input string.
     *
     * @param name The input string.
     */
    public void create(String name) {
        // Get the hash code from the string.
        int hashCodeOfName = Math.abs(name.hashCode());
        // Get the length of the bit sequence of the string.
        int bitSequenceLength = Integer.toBinaryString(hashCodeOfName).length();
        // Get avatar color.
        Color colorOfAvatar = getColorOfAvatar(hashCodeOfName);
        // Create image.
        BufferedImage bufferedImage = new BufferedImage(SIZE_AVATAR, SIZE_AVATAR, BufferedImage.TYPE_INT_RGB);
        // Bit counter.
        int counter = 0;
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j <= bufferedImage.getWidth() / 2; j++) {
                int positionOfBit = counter % bitSequenceLength;
                if ((hashCodeOfName >> positionOfBit & 1) == 1) {
                    // Set pixel color.
                    setColorPixel(colorOfAvatar, bufferedImage, j, i);
                } else {
                    // Set pixel color.
                    setColorPixel(COLOR_BACKGROUND, bufferedImage, j, i);
                }
                counter++;
            }
        }
        // Write image to file.
        writeImageToFile(bufferedImage);
    }

    /**
     * Get the avatar color using the hash code of the string.
     *
     * @param hashOfName The hash code of the string.
     * @return Color of avatar.
     */
    private Color getColorOfAvatar(int hashOfName) {
        int red = hashOfName % 256;
        int green = (red * 2) % 256;
        int blue = (red * 3) % 256;
        return new Color(red, green, blue);
    }

    /**
     * Set pixel color.
     *
     * @param colorPixel    The color of pixel.
     * @param bufferedImage The image that changes the color of the pixel.
     * @param x             The pixel coordinate.
     * @param y             The pixel coordinate.
     */
    private void setColorPixel(Color colorPixel, BufferedImage bufferedImage, int x, int y) {
        bufferedImage.setRGB(x, y, colorPixel.getRGB());
        if (SIZE_AVATAR % 2 == 0 || x != bufferedImage.getWidth() / 2) {
            bufferedImage.setRGB(bufferedImage.getWidth() - 1 - x, y, colorPixel.getRGB());
        }
    }

    /**
     * Writes the resulting image to a file.
     *
     * @param bufferedImage The Image to write to file.
     */
    private void writeImageToFile(BufferedImage bufferedImage) {
        File image = new File("avatar.png");
        try {
            ImageIO.write(bufferedImage, "png", image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
