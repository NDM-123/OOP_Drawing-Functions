package Ex1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

public class Function1 {


	public function initFromString(String s) {
		Gson gson = new Gson();

		//Option 1: from JSON String to Object 

		try 
		{
			//Option 2: from JSON file to Object
			FileReader reader = new FileReader(".json");
			Polynom a = gson.fromJson(reader,Polynom);
			System.out.println(a);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return
	}
	public function copy(); // clone
	public boolean equals(Object obj) {
		if(obj instanceof function) {
			function a = (function)obj;

		}
		return false;
	}

}
