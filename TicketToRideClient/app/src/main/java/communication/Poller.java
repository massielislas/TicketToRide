package communication;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import clientModel.Command;
import clientModel.UserPass;
import clientResult.PollResult;

/**
 * Created by Topper on 5/16/2018.
 */

public class Poller {
    private static final Poller instance = new Poller();
    private Timer mTimer;
    private boolean running;
    UserPass user = null;
    int lastCommandNumber = 0;
    URL url;

    private Poller(){}
    public static Poller getInstance()
    {
        return getInstance();
    }
    public void run(UserPass username)
    {
        user = username;
        running = true;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Poll();
            }
        },0, 3000);
    }
    private void Poll()
    {
        if(running) {
            String[] paramTypeArray = {"int", "UserPass.type"};
            Object[] params = {user, lastCommandNumber};
            Command pollCommand = new Command("CommandManager.class",
                    "getInstance",
                    "getNewCommands",
                    null,
                    null,
                    paramTypeArray,
                    params);
            String toSend = Encoder.Encode(pollCommand);
            String toDecode = ClientCommunicator.getClient().post(url,toSend);
            PollResult result =(PollResult) Encoder.Decode(toDecode,PollResult.class);
            // ClientCommunicator.SINGLETON().testSend();
        }
    }
    public void stop()
    {
        System.out.println("DONE!");
        running=false;
        mTimer.cancel();
        mTimer.purge();
    }
}
