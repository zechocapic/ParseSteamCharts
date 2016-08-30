package org.zechocapic.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zecho on 8/30/2016.
 */
public class SteamChartsParser {
    private String gameURL;
    private String gameTitle;
    private int gameNumber;

    public String getGameURL() {
        return gameURL;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }



    public static void main(String[] args) {
        List<String> urls = new ArrayList<String>();
        urls.add("http://steamcharts.com/app/450390"); // The Lab
        urls.add("http://steamcharts.com/app/418650"); // Space Pirate Trainer
        urls.add("http://steamcharts.com/app/436320"); // Raw Data
        urls.add("http://steamcharts.com/app/471710"); // Rec Room
        urls.add("http://steamcharts.com/app/496250"); // Paddle Up
        urls.add("http://steamcharts.com/app/494480"); // Quivr Alpha
        urls.add("http://steamcharts.com/app/380220"); // Hover Junkers
        urls.add("http://steamcharts.com/app/407060"); // AltspaceVR
        urls.add("http://steamcharts.com/app/484870"); // Battle Dome
        urls.add("http://steamcharts.com/app/451840"); // Out of Ammo
        urls.add("http://steamcharts.com/app/488310"); // Ping Pong Waves VR
        urls.add("http://steamcharts.com/app/496240"); // Onward

        List<SteamChartsParser> listeGame = new ArrayList<SteamChartsParser>();
        SteamChartsParser game;
        for(String url : urls)
        {
            try {
                Document doc = Jsoup.connect(url).get();
                game = new SteamChartsParser();
                game.setGameURL(url);
                game.setGameTitle(doc.getElementById("app-title").text());
                game.setGameNumber(Integer.parseInt(doc.select("span.num").first().text()));
                listeGame.add(game);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(listeGame, new Comparator<SteamChartsParser>() {
            @Override
            public int compare(SteamChartsParser o1, SteamChartsParser o2) {
                if (o1.getGameNumber() > o2.getGameNumber()) return -1;
                if (o1.getGameNumber() < o2.getGameNumber()) return  1;
                return 0;
            }
        });

        for (SteamChartsParser parser : listeGame)
        {
            System.out.println(parser.getGameTitle() + " : " + parser.getGameNumber());
        }

    }
}
