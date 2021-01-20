package commands;

public class DisconnectCommand implements Command {
    @Override
    public void execute(String[] array) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataReaderServer.stop=true;
        ConnectCommand.stop=true;
        System.out.println("Bye");
    }
}
