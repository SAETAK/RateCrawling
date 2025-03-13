package com.inhoan.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.IOException;

public class DeletePassword {
	public static void main(String[] args) {
        	String rateUrl = "http://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=환율";
		String mariadbUrl = "jdbc:mariadb://localhost:3306/crawling";
       		String mariadbUser = "root";
        	String mariadbPassword = "my password";

		try {
			Document naverAddr = Jsoup.connect(rateUrl).get();
        		Elements exchangeRate = naverAddr.getElementsByAttributeValue("class", "nb_txt _pronunciation");
		
			if (exchangeRate.size() > 1) {
				String rate = exchangeRate.get(1).text();
				String removeStr = rate.replaceAll("[^0-9]","");
				double rateValue = Double.parseDouble(removeStr);

                		//System.out.println(rate);
				System.out.println("현재 환율 : " + rateValue + "원");
				insertExchangeRate (rateValue, mariadbUrl, mariadbUser, mariadbPassword);
			} else {
				System.out.println("환율 데이터를 찾을 수 없습니다.");
			}
    		} catch (IOException e) {
			e.printStackTrace();
		}
    	}

	public static void insertExchangeRate (double rateValue, String mariadbUrl, String mariadbUser, String mariadbPassword) {
		String insertQuery = "INSERT INTO exchange_rate (rate) VALUES (?)";
		try (Connection conn = DriverManager.getConnection(mariadbUrl, mariadbUser, mariadbPassword);
		     PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
			pstmt.setDouble(1, rateValue);
			int rows = pstmt.executeUpdate();
			System.out.println(rows + "개의 행이 삽입 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
