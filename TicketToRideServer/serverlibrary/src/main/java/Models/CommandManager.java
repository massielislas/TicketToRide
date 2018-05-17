package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Topper on 5/14/2018.
 */

public class CommandManager {

    private final static CommandManager instance = new CommandManager();
    private CommandManager()
    {
        commandMap = new HashMap<>();
    }
    private Map<UserPass,Command[]> commandMap;

    public static CommandManager getInstance() {
        return instance;
    }

    public void addCommand(UserPass username, Command command) {
        if(commandMap.containsKey(username)) {
            Command[] previousCommands = commandMap.get(username);
            ArrayList<Command> oldCommandList = new ArrayList<>(Arrays.asList(previousCommands));
            oldCommandList.add(command);
            commandMap.put(username, (Command[]) oldCommandList.toArray());
        }
        else
        {
            Command[] newCommands= {command};
            commandMap.put(username,newCommands);
        }
    }

    public void getNewCommands(UserPass username, int lastCommand) {
        if(commandMap.containsKey(username)){
            Command[] allCommands = commandMap.get(username);
            Command[] toReturn = new Command[allCommands.length-lastCommand];
            System.arraycopy(allCommands,lastCommand,toReturn,0,allCommands.length-lastCommand);
        }
    }
    public void addCommandMultipleUsers(List<UserPass> userList, Command command){
        for(UserPass user:userList) {
            addCommand(user,command);
        }
    }

    public void addCommandAllUSers(Command command)
    {
        for(UserPass u: commandMap.keySet())
        {
            addCommand(u,command);
        }
    }

    public void initializeUser(UserPass username){
            commandMap.put(username,new Command[0]);
    }

}
