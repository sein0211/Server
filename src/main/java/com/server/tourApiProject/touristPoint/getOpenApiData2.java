package com.server.tourApiProject.touristPoint;

import com.server.tourApiProject.DynamicScheduledConfig;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataController;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Order(2)
@Component
public class getOpenApiData2 implements org.springframework.boot.ApplicationRunner {

    @Autowired
    private TouristDataController touristDataController;
    @Autowired
    private NearTouristDataController nearTouristDataController;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        DynamicScheduledConfig scheduledConfig = new DynamicScheduledConfig() {
            @Override
            public void runner() {
                System.out.println("매일 03시마다 실행");
                touristDataController.deleteTouristData();

                int num = 0;

                //관광지
                JSONArray tour_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=12", true); //관광 정보
                for (Object o : tour_list) {
                    if (num > 1) {
                        break;
                    }
                    num += 1;
                    JSONObject item = (JSONObject) o;
                    TouristData touristData = getTouristData(item);
                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID

                    JSONArray comm_list = getJson("/detailCommon", "&defaultYN=Y&overviewYN=Y&contentId=" + contentId, true); //공통 정보
                    JSONObject comm = (JSONObject) comm_list.get(0);
                    touristData.setHomePage((String) comm.get("homepage"));
                    touristData.setOverview((String) comm.get("overview"));

                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=12&contentId=" + contentId, true); //소개 정보
                    JSONObject intro = (JSONObject) intro_list.get(0);
                    touristData.setUseTime((String) intro.get("usetime"));
                    touristData.setRestDate((String) intro.get("restdate"));
                    touristData.setExpGuide((String) intro.get("expguide"));
                    touristData.setParking((String) intro.get("parking"));
                    touristData.setChkPet((String) intro.get("chkpet"));

                    List<Double> xny = touristDataController.createTouristData(touristData);

//                    String part2 = "&mapX=" + Double.toString(xny.get(0)) + "&mapY=" + Double.toString(xny.get(1)) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=12";
//                    JSONArray near_list = getJson("/locationBasedList", part2, false); //주변 정보
//                    for (int i = 1; i < 4; i++) {
//                        JSONObject near = (JSONObject) near_list.get(i);
//                        nearTouristDataController.createNearTouristData(contentId, (Long) near.get("contentid"));
//                    }

                }
                int num2 = 0;

                //음식
                JSONArray food_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=39", true); //관광 정보
                for (Object o : food_list) {
                    if (num2 > 1) {
                        break;
                    }
                    num2 += 1;
                    JSONObject item = (JSONObject) o;
                    TouristData touristData = getTouristData(item);
                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID

                    JSONArray comm_list = getJson("/detailCommon", "&overviewYN=Y&contentId=" + contentId, true); //공통 정보
                    JSONObject comm = (JSONObject) comm_list.get(0);
                    touristData.setOverview((String) comm.get("overview"));

                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=39&contentId=" + contentId, true); //소개 정보
                    JSONObject intro = (JSONObject) intro_list.get(0);
                    touristData.setOpenTimeFood((String) intro.get("opentimefood"));
                    touristData.setRestDateFood((String) intro.get("restdatefood"));
                    touristData.setFirstMenu((String) intro.get("firstmenu"));
                    touristData.setTreatMenu((String) intro.get("treatmenu"));
                    touristData.setPacking((String) intro.get("packing"));
                    touristData.setParkingFood((String) intro.get("parkingfood"));

                    touristDataController.createTouristData(touristData);
                }

            }

            @Override
            public Trigger getTrigger() {
                return new CronTrigger("0 28 14 * * ?");
            }
        };
        scheduledConfig.startScheduler();

    }

    public TouristData getTouristData(JSONObject item) {
        TouristData touristData = new TouristData();

        touristData.setAddr1((String) item.get("addr1"));
        if (item.get("addr2") != null) {
            if (item.get("addr2").getClass().getName().equals("java.lang.String")){
                touristData.setAddr2((String) item.get("addr2"));
            }
            else if (item.get("addr2").getClass().getName().equals("java.lang.Long")){
                touristData.setAddr2(String.valueOf(item.get("addr2")));
            }
        }
        touristData.setAreaCode((Long) item.get("areacode"));
        touristData.setReadCount((Long) item.get("readcount"));
        touristData.setCat1((String) item.get("cat1"));
        touristData.setCat2((String) item.get("cat2"));
        touristData.setCat3((String) item.get("cat3"));
        touristData.setContentId((Long) item.get("contentid"));
        touristData.setContentTypeId((Long) item.get("contenttypeid"));
        touristData.setFirstImage((String) item.get("firstimage"));
        touristData.setFirstImage2((String) item.get("firstimage2"));
        if (item.get("mapx") != null) {
            if (item.get("mapx").getClass().getName().equals("java.lang.Double")){
                touristData.setMapX((Double) item.get("mapx"));
            }
            else if (item.get("mapx").getClass().getName().equals("java.lang.String")){
                touristData.setMapX(Double.valueOf((String) item.get("mapx")));
            }
        }
        if (item.get("mapy") != null) {
            if (item.get("mapy").getClass().getName().equals("java.lang.Double")){
                touristData.setMapY((Double) item.get("mapy"));
            }
            else if (item.get("mapy").getClass().getName().equals("java.lang.String")){
                touristData.setMapY(Double.valueOf((String) item.get("mapy")));
            }
        }
        touristData.setSigunguCode((Long) item.get("sigungucode"));
        touristData.setTel((String) item.get("tel"));
        touristData.setTitle((String) item.get("title"));
        if (item.get("zipcode") != null) {
            if (item.get("zipcode").getClass().getName().equals("java.lang.Long")){
                touristData.setZipcode((Long) item.get("zipcode"));
            }
            else if (item.get("zipcode").getClass().getName().equals("java.lang.String")){
                touristData.setZipcode(Long.valueOf((String) item.get("zipcode")));
            }
        }
        return touristData;
    }


    //open api 호출해서 결과 리턴하는 함수
    public JSONArray getJson(String part1, String part2, Boolean isNotNear){

        String key = "?ServiceKey=VQ0keALnEea3BkQdEGgwgCD8XNDNR%2Fg98L9D4GzWryl4UYHnGfUUUI%2BHDA6DdzYjjzJmuHT1UmuJZ7wJHoGfuA%3D%3D"; //인증키
        String result = "";

        try{
            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
            if(!isNotNear)
                System.out.println("url = " + url);
            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine(); //api로 받아온 결과

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            Long count;
            if (isNotNear){
                count = (Long)body.get("totalCount");
            }else {
                count = 4L;
            }

            if (count == 1){
                JSONObject item = (JSONObject)items.get("item");
                bf.close();
                JSONArray item_list = new JSONArray();
                item_list.add(item);
                return item_list;
            }
            else if (count > 10){
                try{
                    URL url2 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 +"&MobileOS=AND&MobileApp=tourApiProject&_type=json&numOfRows="+ count);
                    BufferedReader bf2; //빠른 속도로 데이터를 처리하기 위해
                    bf2 = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
                    result = bf2.readLine(); //api로 받아온 결과

                    JSONParser jsonParser2 = new JSONParser();
                    JSONObject jsonObject2 = (JSONObject)jsonParser2.parse(result);
                    JSONObject response2 = (JSONObject)jsonObject2.get("response");
                    JSONObject body2 = (JSONObject)response2.get("body");
                    JSONObject items2 = (JSONObject)body2.get("items");
                    JSONArray item_list2 = (JSONArray) items2.get("item");
                    bf2.close();
                    return item_list2;

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else {
                JSONArray item_list = (JSONArray) items.get("item");
                bf.close();
                return item_list;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}