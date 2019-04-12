package com.mpd.coursework.Parser;

import com.mpd.coursework.model.WidgetClass;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class AnXMLParser {

    public static WidgetClass[] parseFeed(String content) {

        try {

            boolean inItemTag = false;
            String currentTagName = "";
            WidgetClass currentItem = null;
            List<WidgetClass> itemList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("item")) {
                            inItemTag = true;
                            currentItem = new WidgetClass();
                            itemList.add(currentItem);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            inItemTag = false;
                        }
                        currentTagName = "";
                        break;

                    case XmlPullParser.TEXT:
                        String text = parser.getText();
                        if (inItemTag && currentItem != null) {
                            try {
                                switch (currentTagName) {
                                    case "title":
                                        currentItem.setTitle(text);
                                        break;
                                    case "description":
                                        currentItem.setDescription(text);
                                        break;
                                    case "link":
                                        currentItem.setLink(text);
                                        break;
                                    case "pubDate":
                                        currentItem.setPubDate(text);
                                        break;
                                    case "category":
                                        currentItem.setCatagory(text);
                                        break;
                                    case "geo:lat":
                                        currentItem.setGeoLat(text);
                                    case "geo:long":
                                        currentItem.setGeoLong(text);
                                    default:
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }

                eventType = parser.next();

            } // end while loop

           WidgetClass[] widgetClasses = new WidgetClass[itemList.size()];
            return itemList.toArray(widgetClasses);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}