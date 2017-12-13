package com.example.son.testtask;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by son on 08.12.2017.
 */

public class XMLHelper extends DefaultHandler {

    private String URL_MAIN = "http://ainsoft.pro/test/test.xml";
    String TAG = "XMLHelper";

    Boolean currTag = false;
    String currTagVal = "";
    private Product product = null;
    private ArrayList<Product> products = new ArrayList();

    public ArrayList<Product> getPostsList() {
        return this.products;
    }


    public void get() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mSaxParser = factory.newSAXParser();
            XMLReader mXmlReader = mSaxParser.getXMLReader();
            mXmlReader.setContentHandler(this);
            InputStream mInputStream = new URL(URL_MAIN).openStream();
            mXmlReader.parse(new InputSource(mInputStream));
        } catch (Exception e) {

            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (currTag) {
            currTagVal = currTagVal + new String(ch, start, length);
            currTag = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currTag = false;

        if (localName.equalsIgnoreCase("id"))
            product.setId(Integer.valueOf(currTagVal));

        else if (localName.equalsIgnoreCase("name"))
            product.setName(currTagVal);

        else if (localName.equalsIgnoreCase("price"))
            product.setPrice(Double.valueOf(currTagVal));

        else if (localName.equalsIgnoreCase("product"))
            products.add(product);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        Log.i(TAG, "TAG: " + localName);

        currTag = true;
        currTagVal = "";

        if (localName.equals("product"))
            product = new Product();
    }
}