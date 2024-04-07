package com.example.weddinginvitation.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(9999);
        log.info("공유 객체 쓰레드리스트 생성");
        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
        while (true) {
            Socket socket = serverSocket.accept();
            log.info("접속 소켓명 : {}", socket);
            ChatThread chatThread = new ChatThread(socket, outList);
            chatThread.start();
        }

    }

}


@Slf4j
class ChatThread extends Thread {

    private Socket socket;
    private List<PrintWriter> outList;
    private PrintWriter out;
    private BufferedReader in;

    public ChatThread(Socket socket, List<PrintWriter> outList) {
        this.socket = socket;
        this.outList = outList;

        log.info("클라이언트 채팅서버 접속");

        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        String line = null;

        try {
            while ((line = in.readLine()) != null) {
                for (int i = 0; i < outList.size(); i++) {
                    PrintWriter o = outList.get(i);
                    o.println("클라이언트 접속이 끊어졌습니다.");
                    o.flush();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                outList.remove(out);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        log.info("클라이언트 채팅서버 접속종료");
    }
}