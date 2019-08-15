package sample.Audio;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;

public class SoundGoogle {
    private static SynthesiserV2 synthesizer = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    public static void speak(String text,String language) {
        //System.out.println(text);
        Thread thread = new Thread(() -> {
            try {
                synthesizer.setLanguage(language);
                AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
                player.play();
            } catch (IOException | JavaLayerException e) {
                System.out.println(e.getMessage());
            }
        });

        thread.setDaemon(false);
        thread.start();

    }
}
