package com.dbondarenko;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * File: Writer.java
 * The class in which the image is written to a file.
 * Created by Dmitro Bondarenko on 18.08.2017.
 */
public class Writer {

    // The name of the file to which the image will be recorded.
    private static final String AVATAR_NAME = "avatar";
    // The format of the file to which the image will be recorded.
    private static final String AVATAR_FORMAT = "png";

    /**
     * Writes the resulting image to a file.
     *
     * @param bufferedImage The image to write to file.
     */
    public static void writeImageToFile(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            throw new NullPointerException();
        } else {
            try {
                ImageIO.write(bufferedImage, AVATAR_FORMAT, new File(AVATAR_NAME + "." + AVATAR_FORMAT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}