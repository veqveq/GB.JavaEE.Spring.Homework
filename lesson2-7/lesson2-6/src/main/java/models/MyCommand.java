package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCommand {

    private Commands command;
    private List<String> tags;

    public MyCommand(String command) throws IllegalArgumentException {
        this.tags = new ArrayList<>();
        scanCommand(command);
    }

    private void scanCommand(String command) throws IllegalArgumentException {
        String[] words = command.trim().split(" ");
        for (String word : words) {
            if (word.startsWith("*")) {
                this.command = Commands.create(word);
            } else if (word.startsWith("#")) {
                tags.add(word);
            }
        }
        if (this.command == null) {
            System.out.println("Command not found");
            throw new IllegalArgumentException();
        }
        if (this.command != Commands.Exit && this.tags.size() == 0) {
            System.out.println("Tags not found");
            throw new IllegalArgumentException();
        }
    }

    public Commands getCommand() {
        return command;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public boolean isFinished() {
        return command == Commands.Exit;
    }
}
