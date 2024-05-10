package nirTurjeman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface Fileable {
 ArrayList<?> toImport() throws FileNotFoundException, IOException, ClassNotFoundException;
 void toExport() throws FileNotFoundException, IOException;
}
