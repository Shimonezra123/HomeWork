
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class changeSize {


        // פונקציה שמחזירה רשימת קבצי תמונה מתיקיה
        public static List<File> listImageFilesInDirectory(String directoryPath) {
            List<File> fileList = new ArrayList<>();
            File directory = new File(directoryPath);
        try {


            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && isImageFile(file)) {
                            fileList.add(file);
                        }
                    }
                }
            } else {
                System.out.println("הנתיב אינו תקין או שאינו ספריה.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

            return fileList;
        }

        // בדיקה אם קובץ הוא תמונה לפי הסיומת
        private static boolean isImageFile(File file) {
            String name = file.getName().toLowerCase();
            return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                    name.endsWith(".png") || name.endsWith(".jfif") ||
                    name.endsWith(".bmp");
        }

        // פונקציה שמקטינה את כל התמונות ברשימה
        public static void resizeImages(List<File> imageFiles, String outputDirectory) {
            for (File imageFile : imageFiles) {
                try {
                    BufferedImage originalImage = ImageIO.read(imageFile);
                    if (originalImage == null) {
                        System.out.println("לא ניתן לקרוא את הקובץ: " + imageFile.getName());
                        continue;
                    }

                    int newWidth = originalImage.getWidth() / 2;
                    int newHeight = originalImage.getHeight() / 2;

                    Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

                    Graphics2D g2d = resizedImage.createGraphics();
                    g2d.drawImage(scaledImage, 0, 0, null);
                    g2d.dispose();

                    // שמירת התמונה בתיקיית הפלט
                    String outputFileName = outputDirectory + File.separator + "resized_" + imageFile.getName();
                    ImageIO.write(resizedImage, getFileExtension(imageFile), new File(outputFileName));
                    System.out.println("נשמר: " + outputFileName);

                } catch (IOException e) {
                    System.out.println("שגיאה בקובץ: " + imageFile.getName());
                    e.printStackTrace();
                }
            }
        }

        // קבלת סיומת הקובץ
        private static String getFileExtension(File file) {
            String name = file.getName().toLowerCase();
            int lastDot = name.lastIndexOf('.');
            if (lastDot != -1 && lastDot < name.length() - 1) {
                return name.substring(lastDot + 1);
            }
            return "jpg"; // ברירת מחדל
        }

        // פונקציית main – נקודת כניסה
        public static void main(String[] args) {
            String sourceDir = "C:\\Users\\pc\\OneDrive\\תמונות\\Screenshots";            // הנתיב לתמונות המקוריות
            String outputDir = "C:\\Users\\pc\\OneDrive\\תמונות\\ניסיון";    // תיקיית יעד לתמונות מוקטנות

            // יצירת תיקיית יעד אם לא קיימת
            new File(outputDir).mkdirs();

            // שלבים
            List<File> imageFiles = listImageFilesInDirectory(sourceDir);
            resizeImages(imageFiles, outputDir);

            System.out.println("סיום התהליך.");
        }
    }








