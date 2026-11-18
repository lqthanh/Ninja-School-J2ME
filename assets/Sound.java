package assets;

import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

public final class Sound {
    private static final int LOOP_COUNT = 1000;
    private static final int VOLUME_LEVEL = 100;

    private static Player player;
    private static int currentTrack = -1;
    public static boolean disabled;

    private Sound() {
    }

    public static void loadTrack(int trackId) {
        if (disabled || trackId == currentTrack) {
            return;
        }

        stopAndRelease();

        try {
            InputStream stream = Sound.class.getResourceAsStream(Enum.PATH_SOUND + trackId + Enum.EXTENSION_MIME);
            if (stream == null) {
                disabled = true;
                return;
            }

            player = Manager.createPlayer(stream, Enum.TYPE_MIME);
            player.realize();

            VolumeControl volume = (VolumeControl) player.getControl("VolumeControl");
            if (volume != null) {
                volume.setLevel(VOLUME_LEVEL);
            }

            player.prefetch();
            player.setLoopCount(LOOP_COUNT);
            currentTrack = trackId;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.gc();
            disabled = true;
        }
    }

    public static void start() {
        if (disabled || player == null) {
            return;
        }

        try {
            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void stopAndRelease() {
        if (player == null) {
            return;
        }

        try {
            player.stop();
        } catch (MediaException ex) {
        }

        try {
            player.deallocate();
        } catch (Exception ex) {
        }

        player = null;
        System.gc();
    }

    public static boolean isDisabled() {
        return disabled;
    }
}
