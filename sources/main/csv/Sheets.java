/**
 * 
 */
package csv;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Sheets {
  public static void main(String[] args)
      throws AuthenticationException, MalformedURLException, IOException, ServiceException {

	  SpreadsheetService service =
		        new SpreadsheetService("MySpreadsheetIntegration-v1");

		    // TODO: Authorize the service object for a specific user (see other sections)

		    // Define the URL to request.  This should never change.
		    URL SPREADSHEET_FEED_URL = new URL(
		        "https://spreadsheets.google.com/feeds/spreadsheets/public/full");

		    // Make a request to the API and get all spreadsheets.
		    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
		        SpreadsheetFeed.class);
		    List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		    if (spreadsheets.size() == 0) {
		      // TODO: There were no spreadsheets, act accordingly.
		    }

		    // TODO: Choose a spreadsheet more intelligently based on your
		    // app's needs.
		    SpreadsheetEntry spreadsheet = spreadsheets.get(0);
		    System.out.println(spreadsheet.getTitle().getPlainText());

		    // Make a request to the API to fetch information about all
		    // worksheets in the spreadsheet.
		    List<WorksheetEntry> worksheets = spreadsheet.getWorksheets();

		    // Iterate through each worksheet in the spreadsheet.
		    for (WorksheetEntry worksheet : worksheets) {
		      // Get the worksheet's title, row count, and column count.
		      String title = worksheet.getTitle().getPlainText();
		      int rowCount = worksheet.getRowCount();
		      int colCount = worksheet.getColCount();

		      // Print the fetched information to the screen for this worksheet.
		      System.out.println("\t" + title + "- rows:" + rowCount + " cols: " + colCount);
		    }


  }
}