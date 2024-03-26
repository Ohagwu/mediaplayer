import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class media {
    private JPanel Main;
    private JButton pauseButton;
    private JButton playButton;
    private JButton restartButton;
    private JLabel status;
    private JLabel textfilename;
    private JButton selectFileButton;
    private JButton stopButton;
    private JButton a000Button;
    long elapedtime;

    public media() {

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.loadSong(textfilename);
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.clip.start();
                    long currentPossition = Utils.clip.getMicrosecondPosition();
                    long lenth = Utils.clip.getMicrosecondLength();
                    a000Button.setText(getTimestring(currentPossition));
                    status.setText("playing");
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.clip.stop();
                    status.setText("paused");
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.clip.setFramePosition(0);
                    Utils.clip.start();
                    status.setText("playing");
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Utils.clip.stop();
                    status.setText("stop");
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }
        });









    }
    /*public void startTimer() {
        Timer timer = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentPossition = Utils.clip.getMicrosecondPosition() / 1000;

                updatelapsedtimer();



            }

        });

    }*/

    public String getTimestring(long millis){
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000*60*60));
        int minutes = (int) ((millis % (1000*60*60)) / (1000*60));
        int seconds = (int) ((millis % (1000*60*60)) / (1000*60) / 1000);
        buf
                .append(String.format("%02d",hours))
                .append(':')
                .append(String.format("%02d",minutes))
                .append(':')
                .append(String.format("%02d",seconds));



        return buf.toString();
    }

    public  void updatelapsedtimer(){
        SwingUtilities.invokeLater(()->{
            int minutes = (int) (elapedtime / (1000*60));
            int seconds = (int) (elapedtime / (1000%60));
            a000Button.setText(String.format("%02d:%02d",minutes,seconds));
        });
    }






    public static void main (String[] args){
            JFrame frame = new JFrame("media");
            frame.setContentPane(new media().Main);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(350, 500));
            frame.pack();
            frame.setVisible(true);
    }

}












