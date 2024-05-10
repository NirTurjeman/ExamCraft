package nirTurjeman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileToBinary implements Fileable {
protected ArrayList<DataBase> data;
	public FileToBinary(ArrayList<DataBase> data) {
		this.data = data;
	}
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DataBase> toImport() throws FileNotFoundException, IOException, ClassNotFoundException {
	    ArrayList<DataBase> newData;
	    try (ObjectInputStream inData = new ObjectInputStream(new FileInputStream("dataBase.dat"))) {
	        Object obj = inData.readObject();
	        if (obj instanceof ArrayList<?>) {
	            newData = (ArrayList<DataBase>) obj;
	            data.addAll(newData);
	        } else {
	            throw new ClassNotFoundException("Expected ArrayList from dataBase.dat");
	        }
	    }
	    return data;
	}
	@Override
	public void toExport() throws IOException {
	    try (ObjectOutputStream outQuestions = new ObjectOutputStream(new FileOutputStream("dataBase.dat"))) {
	        outQuestions.writeObject(data);
	    }
	}

}
