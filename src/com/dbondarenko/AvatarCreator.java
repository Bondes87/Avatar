package com.dbondarenko;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * File: AvatarCreator.java
 * Created by Dmitro Bondarenko on 09.08.2017.
 */
public class AvatarCreator {
    private static final int SIZE_AVATAR = 5;
    private static final Color COLOR_BACKGROUND = new Color(255, 255, 255);

    public void create(String name) {
        int hashOfName = Math.abs(name.hashCode());
        String bitSequence = Integer.toBinaryString(hashOfName);
        //boolean[][] colorPixelAvatar = new boolean[SIZE_AVATAR][SIZE_AVATAR];
        Color colorPixel = determineColorOfAvatar(hashOfName);
        BufferedImage bufferedImage = new BufferedImage(SIZE_AVATAR, SIZE_AVATAR, BufferedImage.TYPE_INT_RGB);
        int counter = 0;
        for (int i = 0; i < bufferedImage.getHeight(); i++) {
            for (int j = 0; j <= bufferedImage.getWidth() / 2; j++) {
                int positionOfBit = counter % bitSequence.length();
                if ((hashOfName >> positionOfBit & 1) == 1) {
                    setColorPixel(colorPixel, bufferedImage, i, j);
                } else {
                    setColorPixel(COLOR_BACKGROUND, bufferedImage, i, j);
                }
                /*colorPixelAvatar[i][j] = (hashOfName >> positionOfBit & 1) == 1;
                if (j != colorPixelAvatar[0].length / 2) {
                    colorPixelAvatar[i][colorPixelAvatar[0].length - 1 - j] = (hashOfName >> positionOfBit & 1) == 1;
                }*/
                counter++;
            }
        }
        /*for (int i = 0; i < colorPixelAvatar.length; i++) {
            for (int j = 0; j < colorPixelAvatar[0].length; j++) {
                if (colorPixelAvatar[i][j]) {
                    bufferedImage.setRGB(j, i, COLOR_BACKGROUND.getRGB());
                } else {
                    bufferedImage.setRGB(j, i, colorPixel.getRGB());
                }
            }
        }*/
       /* for (boolean[] ar : colorPixelAvatar) {
            System.out.println(Arrays.toString(ar));
        }*/
        writeToFile(bufferedImage);

        /*int hashOfName = name.hashCode();
        String bitSequence = Integer.toBinaryString(hashOfName);
        System.out.println(bitSequence);
        boolean[][] colorPixelAvatar = new boolean[SIZE_AVATAR][SIZE_AVATAR];
        int[] colorPixel = determineColorOfAvatar(hashOfName);
        BufferedImage image = new BufferedImage(SIZE_AVATAR, SIZE_AVATAR, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        int[] white = {255, 255, 255, 0};
        int counter = 0;
        for (int i = 0; i < colorPixelAvatar.length; i++) {
            for (int j = 0; j <= colorPixelAvatar[0].length / 2; j++) {
                colorPixelAvatar[i][j] = (hashOfName >> (counter % bitSequence.length()) & 1) == 1;
                raster.setPixel(j, i, colorPixel = (hashOfName >> (counter % bitSequence.length()) & 1) == 1 ? colorPixel : white);
                if (j != colorPixelAvatar[0].length / 2) {
                    // System.out.println(hashOfName >> (counter % bitSequence.length()) & 1);
                    colorPixelAvatar[i][colorPixelAvatar[0].length - 1 - j] = (hashOfName >> (counter % bitSequence.length()) & 1) == 1;
                    raster.setPixel(colorPixelAvatar[0].length - 1 - j, i, colorPixel = (hashOfName >> (counter % bitSequence.length()) & 1) == 1 ? colorPixel : white);
                }
                counter++;
            }
        }
        for (boolean[] ar : colorPixelAvatar) {
            System.out.println(Arrays.toString(ar));
        }

        File outputfile = new File("image.jpg");
        try {
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    private void writeToFile(BufferedImage bufferedImage) {
        File image = new File("avatar.png");
        try {
            ImageIO.write(bufferedImage, "png", image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setColorPixel(Color colorPixel, BufferedImage bufferedImage, int i, int j) {
        bufferedImage.setRGB(j, i, colorPixel.getRGB());
        if (j != bufferedImage.getWidth() / 2) {
            bufferedImage.setRGB(bufferedImage.getWidth() - 1 - j, i, colorPixel.getRGB());
        }
    }

    private Color determineColorOfAvatar(int hashOfName) {
        int red = hashOfName % 256;
        int green = (red * 2) % 256;
        int blue = (red * 3) % 256;
        return new Color(red, green, blue);
    }

}
