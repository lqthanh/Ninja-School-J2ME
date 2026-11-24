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
    /** Không phát nhạc: người chơi tắt, hoặc máy không phát được ({@link #unsupported}). */
    public static boolean disabled;
    /** Máy không phát được nhạc (thiếu file hoặc lỗi MMAPI); khi đó không cho bật lại. */
    public static boolean unsupported;

    private Sound() {
    }

    public static void loadTrack(int trackId) {
        if (disabled || trackId == currentTrack) {
            return;
        }

        stopAndRelease();

        try {
            InputStream stream = "".getClass().getResourceAsStream(Enum.PATH_SOUND + trackId + Enum.EXTENSION_MIME);
            if (stream == null) {
                unsupported = true;
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
            unsupported = true;
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
        // Để loadTrack() nạp lại được cùng bài sau khi đã dừng.
        currentTrack = -1;
        System.gc();
    }

    /** Bài nhạc của map {@code mapId}. */
    public static int trackForMap(int mapId) {
        switch (mapId) {
            case 0:
            case 1:
            case 2:
                return 1;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 2;
            case 95:
            case 96:
            case 97:
                return 4;
            case 99:
                return 0;
            default:
                return 3;
        }
    }

    /** Bật hoặc tắt nhạc trong lúc chơi; khi bật thì phát bài của map {@code mapId}. */
    public static void setEnabled(boolean enabled, int mapId) {
        if (!enabled) {
            disabled = true;
            stopAndRelease();
        } else if (!unsupported) {
            disabled = false;
            loadTrack(trackForMap(mapId));
            start();
        }
    }

    public static boolean isDisabled() {
        return disabled;
    }
}
