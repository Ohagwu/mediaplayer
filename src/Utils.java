import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utils {
    static Clip clip;

    static public void loadSong(JLabel textfilename) {
        try {
            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                File file = new File(chooser.getSelectedFile().getAbsolutePath());
                textfilename.setText(file.getName());

                Path source = Paths.get(file.getAbsolutePath());
                Path target = Paths.get(System.getProperty("user.dir") + "/" + file.getName());

                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
