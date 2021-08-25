//package com.server.tourApiProject.touristPoint;
//
//import com.server.tourApiProject.DynamicScheduledConfig;
//import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataController;
//import com.server.tourApiProject.touristPoint.touristData.TouristData;
//import com.server.tourApiProject.touristPoint.touristData.TouristDataController;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.core.annotation.Order;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.List;
//
//@Order(2)
//@Component
//public class getOpenApiData2 implements org.springframework.boot.ApplicationRunner {
//
//    @Autowired
//    private TouristDataController touristDataController;
//    @Autowired
//    private NearTouristDataController nearTouristDataController;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        DynamicScheduledConfig scheduledConfig = new DynamicScheduledConfig() {
//            @Override
//            public void runner() {
//                System.out.println("order2");

//                Double [][] tourXY = new Double[9569][2];
//                Long [] tourId = new Long[9569];
//                int t = 0;
//
//                Double [][] foodXY = new Double[6336][2];
//                Long [] foodId = new Long[6336];
//                int f = 0;
//
//                //관광지
//                JSONArray tour_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=12", false); //관광 정보
//                for (Object o : tour_list) {
//                    if(t<2000)
//                        continue;
//
//                    JSONObject item = (JSONObject) o;
//                    TouristData touristData = getTouristData(item);
//                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID
//
//                    JSONArray comm_list = getJson("/detailCommon", "&defaultYN=Y&overviewYN=Y&contentId=" + contentId, false); //공통 정보
//                    JSONObject comm = (JSONObject) comm_list.get(0);
//
//                    String tmp;
//                    tmp = (String) comm.get("homepage");
//                    if (tmp == null) {
//                        touristData.setHomePage(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setHomePage(null);
//                    }else{
//                        touristData.setHomePage(extractHomePage(tmp));
//                    }
//
//                    tmp = (String) comm.get("overview");
//                    if (tmp == null) {
//                        touristData.setOverview(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setOverview(null);
//                    }else{
//                        touristData.setOverview(extractString(tmp));
//                    }
//
//                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=12&contentId=" + contentId, false); //소개 정보
//                    JSONObject intro = (JSONObject) intro_list.get(0);
//
//                    tmp = (String) intro.get("usetime");
//                    if (tmp == null) {
//                        touristData.setUseTime(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setUseTime(null);
//                    }else{
//                        touristData.setUseTime(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("restdate");
//                    if (tmp == null) {
//                        touristData.setRestDate(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setRestDate(null);
//                    }else{
//                        touristData.setRestDate(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("expguide");
//                    if (tmp == null) {
//                        touristData.setExpGuide(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setExpGuide(null);
//                    }else{
//                        touristData.setExpGuide(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("parking");
//                    if (tmp == null) {
//                        touristData.setParking(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setParking(null);
//                    }else{
//                        touristData.setParking(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("chkpet");
//                    if (tmp == null) {
//                        touristData.setChkPet(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setChkPet(null);
//                    }else{
//                        touristData.setChkPet(extractString(tmp));
//                    }
//
//                    List<Double> xny = touristDataController.createTouristData(touristData);
//                    tourXY[t][0] = xny.get(0);
//                    tourXY[t][1] = xny.get(1);
//                    tourId[t] = contentId;
//                    t++;
//                    System.out.println("t = " + t);
//                }
//
//                for (int i = 0; i<9569; i++){
//                    if (tourXY[i][0] != null){
//                        String part2 = "&mapX=" + Double.toString(tourXY[i][0]) + "&mapY=" + Double.toString(tourXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=12";
//                        JSONArray near_list = getJson("/locationBasedList", part2, true); //주변 정보
//                        for (int j = 1; j < 4; j++) {
//                            JSONObject near = (JSONObject) near_list.get(j);
//                            nearTouristDataController.createNearTouristData(tourId[i], (Long) near.get("contentid"));
//                        }
//                    }
//                }


                //음식
//                JSONArray food_list = getJson("/areaBasedList", "&listYN=Y&arrange=A&contentTypeId=39", false); //관광 정보
//                for (Object o : food_list) {
//                    if(f<280)
//                        continue;
//                    JSONObject item = (JSONObject) o;
//                    TouristData touristData = getTouristData(item);
//                    Long contentId = (Long) item.get("contentid"); //컨텐츠ID
//                    System.out.println("contentId = " + contentId + " " +f);
//
//                    JSONArray comm_list = getJson("/detailCommon", "&overviewYN=Y&contentId=" + contentId, false); //공통 정보
//                    JSONObject comm = (JSONObject) comm_list.get(0);
//
//                    String tmp;
//                    tmp = (String) comm.get("overview");
//                    if (tmp == null) {
//                        touristData.setOverview(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setOverview(null);
//                    }else{
//                        touristData.setOverview(extractString(tmp));
//                    }
//
//                    JSONArray intro_list = getJson("/detailIntro", "&contentTypeId=39&contentId=" + contentId, false); //소개 정보
//                    JSONObject intro = (JSONObject) intro_list.get(0);
//
//                    tmp = (String) intro.get("opentimefood");
//                    if (tmp == null) {
//                        touristData.setOpenTimeFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setOpenTimeFood(null);
//                    }else{
//                        touristData.setOpenTimeFood(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("restdatefood");
//                    if (tmp == null) {
//                        touristData.setRestDateFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setRestDateFood(null);
//                    }else{
//                        touristData.setRestDateFood(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("firstmenu");
//                    if (tmp == null) {
//                        touristData.setFirstMenu(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setFirstMenu(null);
//                    }else{
//                        touristData.setFirstMenu(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("treatmenu");
//                    if (tmp == null) {
//                        touristData.setTreatMenu(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setTreatMenu(null);
//                    }else{
//                        touristData.setTreatMenu(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("packing");
//                    if (tmp == null) {
//                        touristData.setPacking(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setPacking(null);
//                    }else{
//                        touristData.setPacking(extractString(tmp));
//                    }
//
//                    tmp = (String) intro.get("parkingfood");
//                    if (tmp == null) {
//                        touristData.setParkingFood(null);
//                    } else if (tmp.isEmpty()){
//                        touristData.setParkingFood(null);
//                    }else{
//                        touristData.setParkingFood(extractString(tmp));
//                    }
//
//                    List<Double> xny = touristDataController.createTouristData(touristData);
//                    foodXY[f][0] = xny.get(0);
//                    foodXY[f][1] = xny.get(1);
//                    foodId[f] = contentId;
//                    f++;
//                }
//                for (int i = 0; i<6336; i++) {
//                    if (foodXY[i][0] != null){
//                        String part2 = "&mapX=" + Double.toString(foodXY[i][0]) + "&mapY=" + Double.toString(foodXY[i][1]) + "&radius=20000&listYN=Y&arrange=S&numOfRows=4&contentTypeId=39";
//                        JSONArray near_list = getJson("/locationBasedList", part2, true); //주변 정보
//                        System.out.println("near_list.size() = " + near_list.size());
//                        for (int j = 1; j < near_list.size(); j++) {
//                            JSONObject near = (JSONObject) near_list.get(j);
//                            nearTouristDataController.createNearTouristData(foodId[i], (Long) near.get("contentid"));
//                        }
//                    }
//                }
//
//            }
//
//            @Override
//            public Trigger getTrigger() {
//                return new CronTrigger("0 52 13 * * ?");
//            }
//        };
//        scheduledConfig.startScheduler();
//
//    }
//
//    public String extractHomePage(String url){
//        if (url.contains("href=\"")){
//            int start = url.indexOf("href=\"");
//            int end = url.indexOf("\"", start+6);
//            return url.substring(start+6, end);
//        }
//        else{
//            return url;
//        }
//    }
//
//    public String extractString(String overview){
//        overview = overview.replaceAll("<br>","");
//        overview = overview.replaceAll("<br />"," ");
//        overview = overview.replaceAll("<br/>"," ");
//        overview = overview.replaceAll("<strong>","");
//        overview = overview.replaceAll("</strong>","");
//        overview = overview.replaceAll("\n"," ");
//
//        return overview;
//    }
//
//    public TouristData getTouristData(JSONObject item) {
//        TouristData touristData = new TouristData();
//        String tmp;
//
//        tmp = (String) item.get("addr1");
//        if (tmp == null) {
//            touristData.setAddr1(null);
//        } else if (tmp.isEmpty()){
//            touristData.setAddr1(null);
//        }else{
//            touristData.setAddr1(extractString(tmp));
//        }
//
//        if (item.get("addr2") != null) {
//            if (item.get("addr2").getClass().getName().equals("java.lang.String")){
//                touristData.setAddr2((String) item.get("addr2"));
//            }
//            else if (item.get("addr2").getClass().getName().equals("java.lang.Long")){
//                touristData.setAddr2(String.valueOf(item.get("addr2")));
//            }
//        }
//
//        touristData.setAreaCode((Long) item.get("areacode"));
//
//        tmp = (String) item.get("cat1");
//        if (tmp == null) {
//            touristData.setCat1(null);
//        } else if (tmp.isEmpty()){
//            touristData.setCat1(null);
//        }else{
//            touristData.setCat1(extractString(tmp));
//        }
//
//        tmp = (String) item.get("cat2");
//        if (tmp == null) {
//            touristData.setCat2(null);
//        } else if (tmp.isEmpty()){
//            touristData.setCat2(null);
//        }else{
//            touristData.setCat2(extractString(tmp));
//        }
//
//        tmp = (String) item.get("cat3");
//        if (tmp == null) {
//            touristData.setCat3(null);
//        } else if (tmp.isEmpty()){
//            touristData.setCat3(null);
//        }else{
//            touristData.setCat3(extractString(tmp));
//        }
//        touristData.setContentId((Long) item.get("contentid"));
//        touristData.setContentTypeId((Long) item.get("contenttypeid"));
//
//        tmp = (String) item.get("firstimage");
//        if (tmp == null) {
//            touristData.setFirstImage(null);
//        } else if (tmp.isEmpty()){
//            touristData.setFirstImage(null);
//        }else{
//            touristData.setFirstImage(extractString(tmp));
//        }
//
//        tmp = (String) item.get("firstimage2");
//        if (tmp == null) {
//            touristData.setFirstImage2(null);
//        } else if (tmp.isEmpty()){
//            touristData.setFirstImage2(null);
//        }else{
//            touristData.setFirstImage2(extractString(tmp));
//        }
//
//        if (item.get("mapx") != null) {
//            if (item.get("mapx").getClass().getName().equals("java.lang.Double")){
//                touristData.setMapX((Double) item.get("mapx"));
//            }
//            else if (item.get("mapx").getClass().getName().equals("java.lang.String")){
//                touristData.setMapX(Double.valueOf((String) item.get("mapx")));
//            }
//        }
//        if (item.get("mapy") != null) {
//            if (item.get("mapy").getClass().getName().equals("java.lang.Double")){
//                touristData.setMapY((Double) item.get("mapy"));
//            }
//            else if (item.get("mapy").getClass().getName().equals("java.lang.String")){
//                touristData.setMapY(Double.valueOf((String) item.get("mapy")));
//            }
//        }
//        touristData.setSigunguCode((Long) item.get("sigungucode"));
//
//        tmp = (String) item.get("tel");
//        if (tmp == null) {
//            touristData.setTel(null);
//        } else if (tmp.isEmpty()){
//            touristData.setTel(null);
//        }else{
//            touristData.setTel(extractString(tmp));
//        }
//
//        tmp = (String) item.get("title");
//        if (tmp == null) {
//            touristData.setTitle(null);
//        } else if (tmp.isEmpty()){
//            touristData.setTitle(null);
//        }else{
//            touristData.setTitle(extractString(tmp));
//        }
//
////        if (item.get("zipcode") != null) {
////            if (item.get("zipcode").getClass().getName().equals("java.lang.Long")){
////                touristData.setZipcode((Long) item.get("zipcode"));
////            }
////            else if (item.get("zipcode").getClass().getName().equals("java.lang.String")){
////                touristData.setZipcode(Long.valueOf((String) item.get("zipcode")));
////            }
////        }
//        return touristData;
//    }
//
//
//    //open api 호출해서 결과 리턴하는 함수
//    public JSONArray getJson(String part1, String part2, Boolean isNear){
//
//        String key = "?ServiceKey=BdxNGWQJQFutFYE6DkjePTmerMbwG2fzioTf6sr69ecOAdLGMH4iiukF8Ex93YotSgkDOHe1VxKNOr8USSN6EQ=="; //인증키
//        String result = "";
//
//        try{
//            URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 + "&MobileOS=AND&MobileApp=tourApiProject&_type=json");
//            if(isNear)
//                System.out.println("url = " + url);
//            BufferedReader bf; //빠른 속도로 데이터를 처리하기 위해
//            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//            result = bf.readLine(); //api로 받아온 결과
//
//            JSONParser jsonParser = new JSONParser();
//            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
//            JSONObject response = (JSONObject)jsonObject.get("response");
//            JSONObject body = (JSONObject)response.get("body");
//            Long count = (Long)body.get("totalCount");
//
//            if (count == 0){
//                JSONObject item = new JSONObject();
//                JSONArray resultForZero = new JSONArray();
//                resultForZero.add(item);
//                return resultForZero;
//            }
//            JSONObject items = (JSONObject)body.get("items");
//
//            if (isNear){
//                count = 4L;
//            }
//
//            if (count == 1){
//                JSONObject item = (JSONObject)items.get("item");
//                bf.close();
//                JSONArray item_list = new JSONArray();
//                item_list.add(item);
//                return item_list;
//            }
//            else if (count > 10){
//                try{
//                    URL url2 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService" + part1 + key + part2 +"&MobileOS=AND&MobileApp=tourApiProject&_type=json&numOfRows="+ count);
//                    BufferedReader bf2; //빠른 속도로 데이터를 처리하기 위해
//                    bf2 = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"));
//                    result = bf2.readLine(); //api로 받아온 결과
//
//                    JSONParser jsonParser2 = new JSONParser();
//                    JSONObject jsonObject2 = (JSONObject)jsonParser2.parse(result);
//                    JSONObject response2 = (JSONObject)jsonObject2.get("response");
//                    JSONObject body2 = (JSONObject)response2.get("body");
//                    JSONObject items2 = (JSONObject)body2.get("items");
//                    JSONArray item_list2 = (JSONArray) items2.get("item");
//                    bf2.close();
//                    return item_list2;
//
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//            else {
//                JSONArray item_list = (JSONArray) items.get("item");
//                bf.close();
//                return item_list;
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}