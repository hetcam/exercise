package com.realtycrunch.exercise.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ExtractHtml implements CommandLineRunner {
    
    @Value("classpath:html/listing1.html")
    Resource listing1;

    @Override
    public void run(String... args) throws Exception {
        
        try {
            Document doc = Jsoup.parse(listing1.getFile(),null);
            System.out.println("Price: "+doc.getElementsByClass("textIntent-title2").get(0).text());
            System.out.println("Number of bedrooms: "+doc.getElementsByClass("textIntent-title2").get(2).text());
            System.out.println("Number of bathrooms: "+doc.getElementsByClass("textIntent-title2").get(3).text());
            System.out.println("Addres: "+doc.getElementsByAttributeValue("data-tn", "listing-page-address").get(0).text()+" "+
                    doc.getElementsByAttributeValue("data-tn", "listing-page-address-subtitle").get(0).text());
            
            for(Element ele : doc.getElementsByTag("img")) {
                if(!ele.attr("src").isEmpty()) {
                    System.out.println("Image URL: "+ele.attr("src"));
                }
                if(!ele.attr("data-src").isEmpty()) {
                    System.out.println("Image URL: "+ele.attr("data-src"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
