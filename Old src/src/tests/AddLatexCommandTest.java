package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.commands.AddLatexCommand;
import model.Document;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class AddLatexCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		AddLatexCommand latexCommander = new AddLatexCommand(versionsItem);
		Document doc1 = new Document("a","a","a","0","");
		assertEquals(doc1.getContents(),"");
		viewer.setText("test");
		versionsItem.enable();
		viewer.setVersionsManager(versionsItem);
		viewer.setCurrentDocument(doc1);
		int n = Integer.parseInt(doc1.getVersionID());
		latexCommander.execute();
		String newver = (n + 1) + "";
		assertEquals("test",doc1.getContents());
		assertEquals(newver,doc1.getVersionID());
		
	}

}
