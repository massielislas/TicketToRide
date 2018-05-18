package communication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import Models.Command;


/**
 * Created by Master_Chief on 5/16/2018.
 */

public class CommandHandler implements HttpHandler {

//<<<<<<< HEAD
//    public void handle(HttpExchange exchange) throws IOException {
//            String gsonString = readString(exchange.getRequestBody());
//            Command command = (Command) Encoder.getInstance().Decode(gsonString, Command.class);
//            Object toReturn = CommandExecuter.getInstance().ExecuteCommands(command);
//            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//            writeString(Encoder.getInstance().Encode(toReturn), exchange.getResponseBody());
//            exchange.getResponseBody().close();
//        }
//
//    private void writeString(String str, OutputStream os) throws IOException {
//        OutputStreamWriter sw = new OutputStreamWriter(os);
//        sw.write(str);
//        sw.flush();
//    }
//
//    private static String readString(InputStream is) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        InputStreamReader sr = new InputStreamReader(is);
//        char[] buf = new char[1024];
//        int len;
//        while ((len = sr.read(buf)) > 0) {
//            sb.append(buf, 0, len);
//        }
//        return sb.toString();
//=======
    public void handle(HttpExchange exchange) {
        try {
            System.out.println("HELLO");
            InputStreamReader ISR = new InputStreamReader(exchange.getRequestBody());
            Encoder encode = Encoder.getInstance();
            Command toExecute = (Command) encode.Decode(ISR, Command.class);
            Class c = Class.forName(toExecute.getResultClassType());
            Object o = toExecute.Execute();
            //if o is good result exchange.sendResponseHeaders(HTTP_OK, 0);
            //^ how to call method of type C?
            //for example, I'm trying to do LoginRegisterResult.isValid()
            //but LoginRegisterResult is type c
            //else exchange.sendResponseHeaders(HTTP_BAD_REQUEST, 0);

            String jsonStr = encode.Encode(o); //FIX THIS! WE NEED TO TURN THIS OBJECT INTO RESULT CLASS
            //Trying to do something like -> (clientResult.GameResult)object

            PrintWriter out = new PrintWriter(exchange.getResponseBody());
            out.write(jsonStr);
            out.flush();
            out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
