package com;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.awt.Image;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestSearch {
	public final static String maitimg = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/MAIT_Administrative_Block.JPG/330px-MAIT_Administrative_Block.JPG";

	public static void main(String[] args) throws IOException {
		Calendar cal = Calendar.getInstance();
		String query, subtext = null, imgquery;
		String ogImage = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		query = sc.nextLine();
		imgquery = checkimgquery(query);
		imgquery = capitailizeWord(imgquery);
		query = query.replaceAll(" ", "+");
		imgquery = imgquery.replaceAll(" ", "_");
		Document finaldoc = Jsoup.connect("https://www.google.com/search?q=" + query).get();
		/*
		 * Elements finallink1 = finaldoc.select("title"); Elements finallink2 =
		 * finaldoc.select("meta"); String title = finaldoc.title(); String description
		 * = getMetaTag(finaldoc, "description"); if (description == null) { description
		 * = getMetaTag(finaldoc, "og:description"); }
		 */

		String text = finaldoc.body().text();
		String cleanText = text.replaceAll("(.)([A-Z])", "$1 $2");
		cleanText = cleanText.replaceAll("\\s+", " ");
		if (query.contains("weather")) {
			if (cal.get(Calendar.AM_PM) == Calendar.PM)
				subtext = subStringBetween(cleanText, "pm", "forecast");
			if (cal.get(Calendar.AM_PM) == Calendar.AM)
				subtext = subStringBetween(cleanText, "am", "forecast");
			subtext = truncateAfterWords(17, subtext);
			subtext = formatwtext(subtext);
		} else if (query.contains("old") || query.contains("How old")) {
			cleanText = cleanText + " \0";
			subtext = subStringBetween(cleanText, "Age", "\0");
			subtext = truncateAfterWords(2, subtext);
		} else if (query.contains("born")) {
			cleanText = cleanText + " \0";
			subtext = subStringBetween(cleanText, "birth", "\0");
			subtext = truncateAfterWords(3, subtext);
		}
		 else if (query.contains("when")) {
				cleanText = cleanText + " \0";
				subtext = subStringBetween(cleanText, "result", "\0");
				subtext = truncateAfterWords(3, subtext);
				if(!containsDigit(subtext)) {
					subtext = subStringBetween(cleanText, "Date:", "\0");
					subtext = truncateAfterWords(3, subtext);
				}
				
				    
			}
		else {
			subtext = subStringBetween(cleanText, "Description", "Wikipedia");
			if (subtext == "")
				subtext = subStringBetween(cleanText, "Description", "Population");
			if (subtext == "") {
				String release = subStringBetween(cleanText, "date:", "Director");
				if(release == "") { release = subStringBetween(cleanText, "release:", "Director"); }
				String director = subStringBetween(cleanText, "Director:", "Box");
				if(director == "") { String rtext = cleanText; rtext = rtext + " \0";
					 director = subStringBetween(rtext, "Director:", "\0");
					 director = truncateAfterWords(2, director);
				}
				String mdesc = subStringBetween(cleanText, "Description", "Release");
				if(mdesc == "") { mdesc = subStringBetween(cleanText, "Description", "Initial"); }
				subtext = "It is a movie released on " + release + " and directed by " +  director + " <br><b>Synopsis:</b> " + mdesc;
			}
			subtext = subtext + " \0";
			if (subtext.contains("www."))
				subtext = subStringBetween(subtext, "Description", "\0");
			subtext = subtext.replace("\0", "");
			
		}
		System.out.println(subtext);
		/*
		 * System.out.println(title); System.out.println(finallink2.attr("name"));
		 * System.out.println(description);
		 */
		try {
			/*Document imagedoc = Jsoup.connect("https://en.wikipedia.org/wiki/" + imgquery).get();
			ogImage = getMetaTag(imagedoc, "og:image");*/
			if (imgquery.equalsIgnoreCase("weather")||imgquery.contains("When"))
				imgquery="";
			System.out.println(imgquery);
			throw new Exception("Exception for string");
		} catch (Exception e) {
			System.out.print("");
		}

	}
//reminder What time should I set the alarm for? Try asking me a different way. transcript
	public static String searchResult(String query) throws IOException {
		Calendar cal = Calendar.getInstance();
		String subtext = null, imgquery, imglink = "";
		String ogImage = "";
		imgquery = checkimgquery(query);
		imgquery = capitailizeWord(imgquery);
		query = query.replaceAll(" ", "+");
		imgquery = imgquery.replaceAll(" ", "_");
		Document finaldoc = Jsoup.connect("https://www.google.com/search?q=" + query).get();
		String text = finaldoc.body().text();
		String cleanText = text.replaceAll("(.)([A-Z])", "$1 $2");
		cleanText = cleanText.replaceAll("\\s+", " ");
		if ((query.contains("weather")) || query.contains("Weather")) {
			if (cal.get(Calendar.AM_PM) == Calendar.PM)
				subtext = subStringBetween(cleanText, "pm", "forecast");
			if (cal.get(Calendar.AM_PM) == Calendar.AM)
				subtext = subStringBetween(cleanText, "am", "forecast");
			subtext = truncateAfterWords(17, subtext);
			subtext = formatwtext(subtext);

		} else if (query.contains("old") || query.contains("How old")) {
			cleanText = cleanText + " \0";
			subtext = subStringBetween(cleanText, "Age", "\0");
			subtext = truncateAfterWords(2, subtext);
		} else if (query.contains("born")) {
			cleanText = cleanText + " \0";
			subtext = subStringBetween(cleanText, "birth", "\0");
			subtext = truncateAfterWords(3, subtext);
		} 
		else if (query.contains("when")||query.contains("When")) {
			cleanText = cleanText + " \0";
			subtext = subStringBetween(cleanText, "result", "\0");
			subtext = truncateAfterWords(3, subtext);
			if(!containsDigit(subtext)) {
				subtext = subStringBetween(cleanText, "Date:", "\0");
				subtext = truncateAfterWords(3, subtext);
			}
		}
		else {
			String rtext = cleanText; rtext = rtext + " \0";
			subtext = subStringBetween(cleanText, "Description", "Wikipedia");
			if (subtext == "")
				subtext = subStringBetween(cleanText, "Description", "Population");
			if ((subtext == "")&&(cleanText.contains("Director"))){
				String release = subStringBetween(cleanText, "date:", "Director");
				if(release == "") { release = subStringBetween(cleanText, "release:", "Director"); }
				String director = subStringBetween(cleanText, "Director:", "Box");
				if(director == "") { 
					 director = subStringBetween(rtext, "Director:", "\0");
					 director = truncateAfterWords(2, director);
				}
				String mdesc = subStringBetween(cleanText, "Description", "Release");
				if(mdesc == "") { mdesc = subStringBetween(cleanText, "Description", "Initial"); }
				subtext = "It is a movie released on " + release + " and directed by " +  director + " <br><b>Synopsis:</b> " + mdesc;
			}
			subtext = subtext + " \0";
			if (subtext.contains("www."))
				subtext = subStringBetween(subtext, "Description", "\0");
			subtext = subtext.replace("\0", "");
		}
		try {
			Document imagedoc = Jsoup.connect("https://en.wikipedia.org/wiki/" + imgquery).get();
			ogImage = getMetaTag(imagedoc, "og:image");
			if (ogImage.contains("Maharaja_Agrasen"))
				ogImage = maitimg;
			// showimage(ogImage);
			imglink = "<br><img src='" + ogImage + "' width=\"10%\" height=\"10%\" />";
			if (imgquery.equalsIgnoreCase("weather")||ogImage.equalsIgnoreCase(""))
				imglink = "";
			throw new Exception("Exception for string");
		} catch (Exception e) {
			System.out.print("");
		}
		return (subtext + imglink);

	}

	public static String subStringBetween(String text, String after, String before) {
		Pattern pattern = Pattern.compile("(?<=\\s|^)" + after + "\\s(.*?)\\s+" + before);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			return matcher.group(1);
		}
		return "";

	}

	static String getMetaTag(Document document, String attr) {
		Elements elements = document.select("meta[name=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return "";
	}

	public static void showimage(String imgurl) {
		Image image = null;
		try {
			URL url = new URL(imgurl);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		frame.setVisible(true);
	}

	static String capitailizeWord(String str) {
		StringBuffer s = new StringBuffer();

		char ch = ' ';
		for (int i = 0; i < str.length(); i++) {

			if (ch == ' ' || ch == '-' && str.charAt(i) != ' ') {

				if (str.charAt(i) == 'o' && str.charAt(i + 1) == 'f' && str.charAt(i + 2) == ' ')
					s.append(str.charAt(i));
				else
					s.append(Character.toUpperCase(str.charAt(i)));
			}

			else
				s.append(str.charAt(i));
			ch = str.charAt(i);
		}

		return s.toString().trim();
	}

	public static String truncateAfterWords(int words, String text) {
		String regex = String.format("^((?:\\W*\\w+){%s}).*$", words);
		return text.replaceAll(regex, "$1");
	}

	public static String checkimgquery(String imgquery) {
		imgquery = imgquery.replace("?", "");
		if (imgquery.contains("mait") || imgquery.contains("agrasen")) {
			imgquery = "maharaja agrasen institute of technology";
			return imgquery;
		}
		String[] parts;
		if (imgquery.contains("what") || imgquery.contains("What")) {
			parts = imgquery.split(" ");
			imgquery = parts[parts.length - 1];
		}
		if (imgquery.contains("who") || imgquery.contains("Who") || imgquery.contains("How old")
				|| imgquery.contains("how old")) {
			parts = imgquery.split(" ");
			if (parts[parts.length - 1].equalsIgnoreCase("jr")||parts[parts.length - 1].equalsIgnoreCase("sr"))
				imgquery = parts[parts.length - 3] + " " + parts[parts.length - 2] + " " + parts[parts.length - 1];
			else
				imgquery = parts[parts.length - 2] + " " + parts[parts.length - 1];
		}
		if (imgquery.contains("born")) {
			parts = imgquery.split(" ");
			if (parts[parts.length - 2].equalsIgnoreCase("jr")||parts[parts.length - 2].equalsIgnoreCase("sr"))
				imgquery = parts[parts.length - 4] + " " + parts[parts.length - 3] + " " + parts[parts.length - 2];
			else
				imgquery = parts[parts.length - 3] + " " + parts[parts.length - 2];
		}
		return imgquery;
	}

	public static String formatwtext(String wtext) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		String[] dtext = wtext.split("°");
		String[] rtext = dtext[0].split(" ");
		String temp = rtext[rtext.length - 1];
		String weather = dtext[0].replace(temp, "");
		String tempr = temp.substring(0, 2);
		int celcius = Integer.parseInt(tempr);
		float farenhiet = (float) ((celcius * 1.8) + 32);
		rtext = dtext[dtext.length - 1].split(" ");
		wtext = "<b>Day:</b> " + dayOfWeek + "  <b>Weather:</b> " + weather + "  <b>Temperature:</b> " + farenhiet
				+ "°F " + celcius + "°C " + "  <b>Precipitation:</b> " + rtext[3] + "  <b>Wind:</b> NW " + rtext[7]
				+ " km/hr " + "  <b>Humidity:</b> " + rtext[5];
		return wtext;
	}


public final static boolean containsDigit(String s) {
    boolean containsDigit = false;

    if (s != null && !s.isEmpty()) {
        for (char c : s.toCharArray()) {
            if (containsDigit = Character.isDigit(c)) {
                break;
            }
        }
    }

    return containsDigit;
}


}
