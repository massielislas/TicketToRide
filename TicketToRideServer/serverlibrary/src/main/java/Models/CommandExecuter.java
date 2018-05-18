package Models;

/**
 * Created by Topper on 5/16/2018.
 */

public class CommandExecuter {
    private static final CommandExecuter instance = new CommandExecuter();
    private CommandExecuter(){};
    public static CommandExecuter getInstance(){
        return instance;
    }
    public Object ExecuteCommands(Command commandToExecute)
    {
        return commandToExecute.Execute();
    }
}
