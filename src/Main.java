import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

import java.io.IOException;

public class Main {
    private static String getUserInput(String prompt) {
        Scanner reader = new Scanner(System.in);
        System.out.print(prompt);
        String input = reader.next();
        reader.close();

        return input;
    }

    private static void getItems(String shopURL) throws IOException {
        Document storeDoc = Jsoup.connect(shopURL).get();
        Elements items = storeDoc.getElementsByClass("s-item__link");
        for (Element item : items) {
            String itemURL = item.attr("href");
            Document itemDoc = Jsoup.connect(itemURL).get();
            Elements sold = itemDoc.getElementsByClass("vi-qty-pur-lnk");
            for (Element num : sold) {
                num.select("a").text();
                System.out.println(num.select("a").text() + ": " + itemURL);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String shopURL = getUserInput("Enter an Ebay shop URL: ");
        getItems(shopURL);
    }
}
