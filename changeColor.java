import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class changeColor {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\pc\\OneDrive\\תמונות\\סמל_מכבי_חיפה_2023.png");
            BufferedImage originalImage = ImageIO.read(inputFile);

            BufferedImage grayImage = new BufferedImage(originalImage.getWidth(),
                    originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

            // מעבר על כל הפיקסלים ושינוי הצבעים לגווני אפור
            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int rgb = originalImage.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // חישוב ערך אפור ממוצע (שיטה פשוטה)
                    int gray = (red + green + blue) / 3;

                    int newRGB = (gray << 16) | (gray << 8) | gray;
                    grayImage.setRGB(x, y, newRGB);
                }
            }

            // שמירת התמונה החדשה
            File outputFile = new File("C:\\Users\\pc\\OneDrive\\תמונות\\ניסיון\\gray_image.jpg");
            ImageIO.write(grayImage, "JPG", outputFile);

            System.out.println("התמונה הומרה לגווני אפור ונשמרה בהצלחה!");
        } catch (IOException e) {
            System.out.println("שגיאה בעת קריאת או כתיבת התמונה: " + e.getMessage());
        }
    }
}
