package save;

import javax.microedition.rms.RecordStore;

public final class Save {
    private Save() {
    }

    public static byte[] loadRecord(String recordName) {
        try {
            RecordStore store;
            byte[] data = (store = RecordStore.openRecordStore(recordName, false)).getRecord(1);
            store.closeRecordStore();
            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void saveRecord(String recordName, byte[] data) {
        try {
            RecordStore store;
            if ((store = RecordStore.openRecordStore(recordName, true)).getNumRecords() > 0) {
                store.setRecord(1, data, 0, data.length);
            } else {
                store.addRecord(data, 0, data.length);
            }

            store.closeRecordStore();
        } catch (Exception ex) {
        }
    }

    public static void setBackgroundIndex(int backgroundIndex) {
        byte[] data = new byte[1];
        data[0] = (byte) backgroundIndex;
        saveRecord("bg", data);
    }

    public static int getBackgroundIndex() {
        byte[] data;
        return (data = loadRecord("bg")) == null ? 0 : data[0];
    }

    public static void markSaved() {
        byte[] data = new byte[1];
        data[0] = 1;
        saveRecord("saved", data);
    }

    public static boolean isSaved() {
        byte[] data;
        if ((data = loadRecord("saved")) == null) {
            return false;
        } else {
            return data[0] == 1;
        }
    }
}
