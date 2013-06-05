package com.zlp.entity;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.text.Html;

public class XmlCurDataHandler extends DefaultHandler {
	private ArrayList<CurData> curDataList = null;
	private CurData curData = null;
	private String preTag = null;

	private StringBuffer location = null;
	private StringBuffer pname = null;
	private StringBuffer count = null;

	public ArrayList<CurData> getCurData(InputStream xmlStream)
			throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XmlCurDataHandler handler = new XmlCurDataHandler();
		parser.parse(xmlStream, handler);
		return handler.getCurData();
	}

	public ArrayList<CurData> getCurData() {
		return curDataList;
	}

	@Override
	public void startDocument() throws SAXException {
		this.curDataList = new ArrayList<CurData>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			curData = new CurData();

			location = new StringBuffer();
			pname = new StringBuffer();
			count = new StringBuffer();
		}
		preTag = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (preTag != null) {
			String content = new String(ch, start, length);

			if ("location".equals(preTag)) {
				location.append(Html.fromHtml(content));
			} else if ("pname".equals(preTag)) {
				pname.append(Html.fromHtml(content));
			} else if ("count".equals(preTag)) {
				count.append(Html.fromHtml(content));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("item".equals(qName)) {
			curData.setLocation(location.toString());
			curData.setPname(pname.toString());
			curData.setCount(count.toString());

			curDataList.add(curData);

			location = null;
			pname = null;
			count = null;
		}
		preTag = null;
	}
}
