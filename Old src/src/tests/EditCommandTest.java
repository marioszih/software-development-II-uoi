package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.commands.EditCommand;
import model.Document;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;

class EditCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		EditCommand editor = new EditCommand(versionsItem);
		Document doc1 = new Document("a","a","a","0","");
		assertEquals(doc1.getContents(),"");
		viewer.setText("test");
		versionsItem.enable();
		viewer.setVersionsManager(versionsItem);
		viewer.setCurrentDocument(doc1);
		int n = Integer.parseInt(doc1.getVersionID());
		editor.execute();
		String newver = (n + 1) + "";
		assertEquals("test",doc1.getContents());
		assertEquals(newver,doc1.getVersionID());
		
	}

}
