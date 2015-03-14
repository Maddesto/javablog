package maddesto.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import maddesto.entity.Item;
import maddesto.exception.RssException;
import maddesto.rss.ObjectFactory;
import maddesto.rss.TRss;
import maddesto.rss.TRssChannel;
import maddesto.rss.TRssItem;

import org.springframework.stereotype.Service;

@Service
public class RssService {
	
	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}
	
	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}

	private List<Item> getItems(Source source) throws RssException {
		List<Item> list = new ArrayList<Item>();
		try {
			JAXBContext instance = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = instance.createUnmarshaller();
			JAXBElement<TRss> jxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jxbElement.getValue();
			
			List<TRssChannel> channels = rss.getChannel();
			for(TRssChannel channel: channels){
				List<TRssItem> items = channel.getItem();
				for(TRssItem rssItem : items){
					Item item = new Item();
					item.setDescription(rssItem.getDescription());
					item.setTitle(rssItem.getTitle());
					item.setLink(rssItem.getLink());
					item.setPublishDate(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).parse(rssItem.getPubDate()));
					list.add(item);
				}
			}
			
		} catch (JAXBException error) {
			// TODO Auto-generated catch block
			throw new RssException(error);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RssException(e);
		}
		return list;

	}

}
