import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filters {

  public static void main(String[] args)throws IOException {
    // TODO: modify this main method as you wish, to run and test your filter implementations.

    // Read in the image file.
    File f = new File("dog.png");
    BufferedImage img = ImageIO.read(f);

    // For debugging
    System.out.println("Before:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 92 40 27

    // Apply a filter implementation on img.
    //applyGrayscale(img);
    
    //applyNorak(img);

    // int[] borderColor ={235, 255, 255};
    // int borderThickness = 30;
    // applyBorder(img,borderThickness,borderColor);

    //applyMirror(img);

    applyCustom(img);

    
    // For debugging
    System.out.println("After:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 53 53 53

    // Write the result to a new image file.
    f = new File("dog_filtered.png");
    ImageIO.write(img, "png", f);
  }
  //Iterates through each pixel in the image and converts it to grayscale using the average of its RGB values.
  //Sets the RGB values of the pixel to the computed grayscale values.
  public static void applyGrayscale(BufferedImage img) {
    int sum = 0;
    for(int i = 0; i<img.getHeight(); i++){
      for(int j = 0; j<img.getWidth(); j++){
        int [] o = new int[3];
        o = Utilities.getRGBArray(i, j, img);
        int r = o[0];
        int g = o[1];
        int b = o[2];
        sum = (r+g+b)/3;
        int [] arr = {sum, sum ,sum};
        Utilities.setRGB(arr, i, j, img);
      }
    }
  }
  //Iterates through each pixel in the image, converts it to grayscale, and sets it to white if the intensity is greater than 153.
  public static void applyNorak(BufferedImage img) {
    int sum = 0;
    for(int i = 0; i<img.getHeight(); i++){
      for(int j = 0; j<img.getWidth(); j++){
        int [] o = new int[3];
        o = Utilities.getRGBArray(i, j, img); 
        int r = o[0];
        int g = o[1];
        int b = o[2];
        sum = (r+g+b)/3;
        if(sum>153){
          int [] arr = {sum, sum ,sum};
          Utilities.setRGB(arr, i, j, img);
        }
      }
    }
  }
  //Iterates through each pixel in the image and checks if it is within the border. If so, sets the RGB values of the pixel to the specified border color.
  public static void applyBorder(BufferedImage img, int borderThickness, int[] borderColor) {
    for(int i = 0; i<img.getHeight(); i++){
      for(int j = 0; j<img.getWidth(); j++){
        if (i < borderThickness || i >= (img.getHeight() - borderThickness) || j < borderThickness || j >= (img.getWidth() - borderThickness)) {
         Utilities.setRGB(borderColor, i, j, img);
        }
      }
    }
  }
  //Iterates through each row and swaps pixels horizontally to create a mirror effect.
  //declared two arrays, left and right pixel, these include the getRBG from utilities, right pixel includes getWidth - j -1
  //set two RGB with right and left pixel, with rightpixel including getWidth - j -1
  public static void applyMirror(BufferedImage img) {
  for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth() / 2; j++) {
        int[] leftPixel = Utilities.getRGBArray(i, j, img);
        int[] rightPixel = Utilities.getRGBArray(i, img.getWidth() - j - 1, img);
        Utilities.setRGB(rightPixel, i, j, img);
        Utilities.setRGB(leftPixel, i, img.getWidth() - j - 1, img);
      }
    }
  }
  
  public static void applyBlur(BufferedImage img) {
    
  }
  //Iterates through each column and swaps pixels between the top and bottom halves of the image.
  //when doing my custom image, it was a freak accident when doing my mirror
  //only thing i changed was not putting each getRGB into an array
  public static void applyCustom(BufferedImage img) {
  for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight() / 2; j++) {
        Utilities.getRGBArray(i, j, img);
        Utilities.getRGBArray(i, img.getWidth() - j - 1, img);
        Utilities.setRGB(Utilities.getRGBArray(i, img.getWidth() - j - 1, img), i, j, img);
        Utilities.setRGB(Utilities.getRGBArray(i, j, img), i, img.getWidth() - j - 1, img);
      }
    }
  }
}