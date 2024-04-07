package com.example.weddinginvitation.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

public class ChatClient {

    public static void main(String[] args) throws Exception {
        String name = args[0];

        Socket socket = new Socket("127.0.0.1", 9999);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        InputThread inputThread = new InputThread(in);
        inputThread.start();


        while ((line = keyboard.readLine()) != null) {
            out.println(name + ":" + line);
            out.flush();
        }

    }


}

@Slf4j
class InputThread extends Thread {
    BufferedReader in = null;

    public InputThread(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                log.info("{}", line);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
