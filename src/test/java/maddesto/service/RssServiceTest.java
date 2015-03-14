package maddesto.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import maddesto.entity.Item;
import maddesto.exception.RssException;
import junit.framework.TestCase;

public class RssServiceTest extends TestCase {
	
	private RssService rssService;

	protected void setUp() throws Exception {
		rssService = new RssService();
	}

	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("test-rss/rssKorr.xml"));
		//System.out.println(items.toString());
		assertEquals(10, items.size());
		Item item = items.get(1);
		assertEquals("JAXB: Access Restriction Warning", item.getTitle());
		assertEquals("16 06 2014 23:59:43", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(item.getPublishDate()));
	
	}

}
