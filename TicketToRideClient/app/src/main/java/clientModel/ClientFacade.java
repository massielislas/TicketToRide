package clientModel;

import java.util.ArrayList;

import clientModel.Command;

/**
 * Created by zachgormley on 5/13/18.
 */

public class ClientFacade
{
    private static final ClientFacade instance = new ClientFacade();
    private ClientFacade(){}
    public static ClientFacade getInstance(){
        return instance;
    }
    public void executeCommands(Command[] commands)
    {
        for(Command c: commands){
            c.Execute();
        }
    }

}