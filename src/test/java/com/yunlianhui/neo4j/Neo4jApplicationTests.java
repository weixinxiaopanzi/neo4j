package com.yunlianhui.neo4j;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Neo4jApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
	
	private static Boolean isEffectDateLt7Days(String effectDateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate effectDate = LocalDate.parse(effectDateStr, df);
        LocalDate today = LocalDate.now();
        LocalDate deathlineDate = today.plus(7, ChronoUnit.DAYS);
        return !effectDate.isAfter(deathlineDate);
    }

	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(isEffectDateLt7Days("2018-04-28"));
//		System.out.println(URLDecoder.decode("%E6%B5%8B%E8%AF%9512","UTF-8"));
//		System.out.println(URLDecoder.decode("wq你好123", "utf-8"));
		
	}
}
