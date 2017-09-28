package de.bergwerklabs.framework.party.client.command.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yannic Rieger on 04.09.2017.
 * <p>
 *
 * @author Yannic Rieger
 */
public abstract class ParentCommand implements CommandExecutor {

    private HashMap<String, ChildCommand> childCommands = new HashMap<>();
    private String command;
    private CommandExecutor executor;

    /**
     * @param command
     * @param childCommands
     */
    public ParentCommand(String command, ChildCommand... childCommands) {
        if (childCommands.length == 0) throw new IllegalArgumentException("There cannot be 0 child commands, at least 1 has to be present");
        this.command = command;
        this.addChildCommands(childCommands);
    }

    /**
     * @param command
     * @param executor
     * @param childCommands
     */
    public ParentCommand(String command, CommandExecutor executor, ChildCommand... childCommands) {
        if (childCommands.length == 0) throw new IllegalArgumentException("There cannot be 0 child commands, at least 1 has to be present");
        this.command = command;
        this.executor = executor;
        this.addChildCommands(childCommands);
        this.childCommands.entrySet().forEach(System.out::println);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase(this.command) && this.executor != null && strings.length == 0) {
            this.executor.onCommand(commandSender, command, s, strings);
        }
        else {
            List<String> params = Arrays.asList(strings);
            this.childCommands.keySet().stream()
                              .filter(key -> key.equalsIgnoreCase(strings[0])).findFirst()
                              .ifPresent(key -> {
                                  this.childCommands.get(key).onCommand(commandSender, command, key, Arrays.copyOfRange(strings, params.indexOf("leave") + 1, strings.length));
                              });
        }
        return false;
    }

    /**
     *
     * @param childCommands
     */
    private void addChildCommands(ChildCommand... childCommands) {
        Arrays.stream(childCommands).forEach(childCommand -> {
            this.childCommands.put(childCommand.getName(), childCommand);
        });
    }
}
