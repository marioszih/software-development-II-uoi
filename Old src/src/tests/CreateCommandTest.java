package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.commands.CreateCommand;
import model.DocumentManager;
import model.Document;
import model.VersionsManager;
import view.LatexEditorView;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;

class CreateCommandTest {

	@Test
	void test() {
		LatexEditorView viewer = new LatexEditorView();
		VersionsStrategy versionsStrategy = new StableVersionsStrategy();
		VersionsManager versionsItem = new VersionsManager(versionsStrategy,viewer);
		DocumentManager manager = new DocumentManager();
		Document doc0 = new Document("","","","","");
		Document doc1 = new Document("","","","","");
		Document doc2 = new Document("","","","","");
		Document doc3 = new Document("","","","","");
		Document doc4 = new Document("","","","","");
		CreateCommand creator = new CreateCommand(manager,versionsItem);
		String article = manager.getContents("articleTemplate");
		String book = manager.getContents("bookTemplate");
		String letter = manager.getContents("letterTemplate");
		String report = manager.getContents("reportTemplate");
		viewer.setType("emptyTemplate");
		creator.execute();
		doc0 = viewer.getCurrentDocument();
		assertEquals("",doc0.getContents());
		viewer.setType("articleTemplate");
		creator.execute();
		doc1 = viewer.getCurrentDocument();
		assertEquals(article,doc1.getContents());
		viewer.setType("bookTemplate");
		creator.execute();
		doc2 = viewer.getCurrentDocument();
		assertEquals(book,doc2.getContents());
		viewer.setType("letterTemplate");
		creator.execute();
		doc3 = viewer.getCurrentDocument();
		assertEquals(letter,doc3.getContents());
		viewer.setType("reportTemplate");
		creator.execute();
		doc4 = viewer.getCurrentDocument();
		assertEquals(report,doc4.getContents());
	}

}
