import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUiController {
    public TextArea messageArea;
    public TextField txtMessage;

    static ServerSocket serverSocket;
    static Socket socket;

    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    String messageIn="";

    public void initialize(){
        new Thread(()->{
            try {
                serverSocket=new ServerSocket(5000);
                System.out.println("server started");
                socket=serverSocket.accept();
                System.out.println("client accept");

                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                while (!messageIn.equals("end")){
                    messageIn=dataInputStream.readUTF();
                    messageArea.appendText("\n Tharindu Dilshan :"+messageIn.trim());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
     void clickOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(txtMessage.getText().trim());
    }

}
