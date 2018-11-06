package Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Loadable {

    public void loadFile() throws IOException;

    public List<List> getParsedLines();

    public ArrayList<String> splitOnComma(String line);
}
