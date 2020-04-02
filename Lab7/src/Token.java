public class Token {
    private int value;
    private boolean isBlank;
    private boolean used;

    public synchronized boolean isUsed() {
        return used;
    }

    public synchronized void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Token(int value, boolean isBlank) {
        this.value = value;
        this.isBlank = isBlank;
    }
}
