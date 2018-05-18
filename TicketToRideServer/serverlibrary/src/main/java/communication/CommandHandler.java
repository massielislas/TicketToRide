package communication;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import Models.Command;
import Models.CommandExecuter;


/**
 * Created by Master_Chief on 5/16/2018.
 */

public class CommandHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
            String gsonString = readString(exchange.getRequestBody());
            Command command = (Command) Encoder.getInstance().Decode(gsonString, Command.class);
            Object toReturn = CommandExecuter.getInstance().ExecuteCommands(command);
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            writeString(Encoder.getInstance().Encode(toReturn), exchange.getResponseBody());
            exchange.getResponseBody().close();
        }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
