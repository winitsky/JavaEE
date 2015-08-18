package command;


public class CommandFactory {
    public static Command getCommand(String paramCommand) {
        CommandsEnum currentCommand = CommandsEnum.valueOf(paramCommand.toUpperCase());
        return currentCommand.createCommand();
    }
}
