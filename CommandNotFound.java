package Commands;

public class CommandNotFound implements Command{

    @Override
    public Object execute() {
        return "Command not known";
    }

}
