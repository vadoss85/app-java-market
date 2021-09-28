package classes.CommandsPerformer.performers;

import java.util.Map;

public interface ICommandsPerformer {
    void execute();
    void execute(Map params);
}
