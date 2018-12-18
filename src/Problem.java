import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Problem {
	private String excercise, correct;
	private ArrayList<String> choice;
	private int n;
	
	public Problem() {
		choice = new ArrayList<String>();
		try {
			Path p = Paths.get("problem.txt");
			System.out.println(p.toAbsolutePath());
			String[] txt = new String(Files.readAllBytes(p)).split(";");
			n = new Random().nextInt(txt.length);
			System.out.println(n);
			String[] pb = txt[n].split(",");
			System.out.println(pb[0]);
			excercise = pb[0];			
			for(int i = 1; i < pb.length; i++) {
				choice.add(pb[i].split(":")[0]);
				if(pb[i].split(":").length > 1) {
					correct = pb[i].split(":")[0];
					System.out.println("Correct : " + correct);
				}
			}
			System.out.println(choice);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProblem() {
		return excercise;
	}

	public ArrayList<String> getChoices(){
		return choice;
	}
	
	public String getCorrect() {
		return correct;
	}
}
