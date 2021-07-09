package models;

public enum Commands {
    Subscribe("*sub"),
    Unsubscribe("*unsub"),
    Exit("*exit");

    private String cmd;

    Commands(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

    public static Commands create(String val){
        for (Commands c: Commands.values())
            if (c.getCmd().equals(val)) {
                return c;
            }
        return null;
    }
}
