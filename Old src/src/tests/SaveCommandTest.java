package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import controller.commands.SaveCommand;
import model.Document;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class SaveCommandTest {
	
	public String getPath() {
		Path currentRelativePath = FileSystems.getDefault().getPath("testSave.tex");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		SaveCommand saver = new SaveCommand(versionsItem);
		Document doc0 = new Document();
		viewer.setCurrentDocument(doc0);
		String testContents = "test String";
		doc0.setContents(testContents);
		
		String path = getPath();
		viewer.setFilename(path);
		saver.execute();
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(path));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileContents = fileContents.substring(0,fileContents.length()-1);
		System.out.println(fileContents);
		assertEquals(testContents,fileContents);
	}
}