import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientUiController {
    public TextArea messageArea;
    public TextField txtMessage;

    static Socket socket;

    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    public AnchorPane root;

    public void initialize() throws IOException {

    new Thread(new Runnable() {
        @Override
        public void run() {
            try {

                socket=new Socket("localhost",5000);
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());

                String messageIn="";
                while (!messageIn.equals("exit")){
                    messageIn=dataInputStream.readUTF();
                    messageArea.appendText("\n Ravindu Lakshan: "+messageIn.trim());
                }

            } catch (Exception e) {
                    e.printStackTrace();
            }
        }
    }).start();

}
    @FXML
    void clickOnAction(ActionEvent event) throws IOException {
        String messageOut="";
        messageOut=txtMessage.getText();
        dataOutputStream.writeUTF(messageOut);
    }

}
