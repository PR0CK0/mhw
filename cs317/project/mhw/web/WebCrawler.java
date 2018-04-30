package cs317.project.mhw.web;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Connects the the Monster Hunter : World website and grabs update
 * news to be displayed in ProgramHUD.java.
 * 
 * Uses the jsoup API.
 *
 */

public class WebCrawler implements Runnable
{
	/** String to contain the update news. */
	private String updateText;
	
	/** Label used as a wrapper for the update news, implemented in the UI. */
	private Label labelNews;
	
	
	/** 
	 * Default constructor.
	 * 
	 * @param labelNews - used as a wrapper for the update page's text.
	 */
	public WebCrawler(Label labelNews)
	{
		this.labelNews = labelNews;
	}
	
	/** 
	 * Opened in PersistentHUD.java. Reduces startup lag.
	 */
	@Override
	public void run()
	{
		try
		{
			// Update page for MH:W
			Document updatesPage = Jsoup.connect("http://monsterhunterworld.com/us/topics/update/").get();
			
			// Grab the title of the update
			Elements updateTitle = updatesPage.getElementsByClass("bg-h2");
			String titleText = updateTitle.text();
			updateText = titleText;
			
			// Grab the content of the update
			Element updateContent = updatesPage.getElementById("contents");
			String contentText = updateContent.wholeText();
			updateText += contentText;
		}
		catch (IOException e)
		{
			updateText = "Could not connect to the website.";
		}
		
		// runLater executes code in the JavaFX thread, which is required because JavaFX limits you
		// by restricting all access to JavaFX elements to the JavaFX thread
		Platform.runLater(() ->
		{				
			labelNews.setText(updateText);
		});
	}
}	