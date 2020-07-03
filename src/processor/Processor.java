package processor;

import java.io.BufferedReader;
import java.io.IOException;

public interface Processor {
    boolean validate(String input);

    void execute(String trim, BufferedReader bufferReader) throws IOException;
}
