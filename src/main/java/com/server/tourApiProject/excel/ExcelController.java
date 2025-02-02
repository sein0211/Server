package com.server.tourApiProject.excel;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.observation.course.Course;
import com.server.tourApiProject.observation.course.CourseRepository;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import com.server.tourApiProject.observation.observeFee.ObserveFeeRepository;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagRepository;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
import com.server.tourApiProject.observation.observeImage.ObserveImageRepository;
import com.server.tourApiProject.searchFirst.SearchFirst;
import com.server.tourApiProject.searchFirst.SearchFirstRepository;
import com.server.tourApiProject.star.Horoscope.Horoscope;
import com.server.tourApiProject.star.Horoscope.HoroscopeRepository;
import com.server.tourApiProject.star.constellation.Constellation;
import com.server.tourApiProject.star.constellation.ConstellationRepository;
import com.server.tourApiProject.touristPoint.area.AreaParams;
import com.server.tourApiProject.touristPoint.area.AreaService;
import com.server.tourApiProject.touristPoint.contentType.ContentType;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeService;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristData;
import com.server.tourApiProject.touristPoint.nearTouristData.NearTouristDataRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristDataService;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTagRepository;
import com.server.tourApiProject.weather.WtArea.WtArea;
import com.server.tourApiProject.weather.WtArea.WtAreaRepository;
import com.server.tourApiProject.weather.WtArea.WtAreaService;
import com.server.tourApiProject.weather.WtToday.WtToday;
import com.server.tourApiProject.weather.WtToday.WtTodayRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

//일단 readTouristDataExcel함수 복사 붙여넣기하고 함수명 수정, 아까 action에 쓴 url로 수정 그리고 for문 안에 내용 수정하면 됨
@Controller
public class ExcelController {
    private final TouristDataService touristDataService;
    private final AreaService areaService;
    private final ContentTypeService contentTypeService;
    private final TouristDataRepository touristDataRepository;
    private final NearTouristDataRepository nearTouristDataRepository;
    private final TouristDataHashTagRepository touristDataHashTagRepository;
    private final WtAreaRepository wtAreaRepository;
    private final WtTodayRepository wtTodayRepository;
    private final ConstellationRepository constellationRepository;
    private final HoroscopeRepository horoscopeRepository;
    private final ObservationRepository observationRepository;
    private final ObserveHashTagRepository observeHashTagRepository;
    private final ObserveImageRepository observeImageRepository;
    private final ObserveFeeRepository observeFeeRepository;
    private final CourseRepository courseRepository;
    private final HashTagRepository hashTagRepository;
    private final SearchFirstRepository searchFirstRepository;


    public ExcelController(TouristDataService touristDataService, AreaService areaService, ContentTypeService contentTypeService, TouristDataRepository touristDataRepository, NearTouristDataRepository nearTouristDataRepository, TouristDataHashTagRepository touristDataHashTagRepository, WtAreaService wtAreaService, WtAreaRepository wtAreaRepository, WtTodayRepository wtTodayRepository, ConstellationRepository constellationRepository, HoroscopeRepository horoscopeRepository, ObservationRepository observationRepository, ObserveHashTagRepository observeHashTagRepository, ObserveImageRepository observeImageRepository, ObserveFeeRepository observeFeeRepository, CourseRepository courseRepository, HashTagRepository hashTagRepository, SearchFirstRepository searchFirstRepository) {
        this.touristDataService = touristDataService;
        this.areaService = areaService;
        this.contentTypeService = contentTypeService;
        this.touristDataRepository = touristDataRepository;
        this.wtTodayRepository = wtTodayRepository;
        this.nearTouristDataRepository = nearTouristDataRepository;
        this.touristDataHashTagRepository = touristDataHashTagRepository;
        this.wtAreaRepository = wtAreaRepository;
        this.constellationRepository = constellationRepository;
        this.horoscopeRepository = horoscopeRepository;
        this.observationRepository = observationRepository;
        this.observeHashTagRepository = observeHashTagRepository;
        this.observeImageRepository = observeImageRepository;
        this.observeFeeRepository = observeFeeRepository;
        this.courseRepository = courseRepository;
        this.hashTagRepository = hashTagRepository;
        this.searchFirstRepository = searchFirstRepository;
    }

    @GetMapping("/excel")
    public String main() {
        return "excel";
    }

    @PostMapping("/excel/area/read")
    public String readAreaExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            AreaParams data = new AreaParams();

            data.setCode1((long) row.getCell(1).getNumericCellValue());
            data.setName1(row.getCell(2).getStringCellValue());
            data.setCode2((long) row.getCell(3).getNumericCellValue());
            data.setName2(row.getCell(4).getStringCellValue());

            areaService.createArea(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/contentType/read")
    public String readContentType1Excel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            ContentType data = new ContentType();

            data.setCat1Code(row.getCell(1).getStringCellValue());
            data.setCat1Name(row.getCell(2).getStringCellValue());
            data.setCat2Code(row.getCell(3).getStringCellValue());
            data.setCat2Name(row.getCell(4).getStringCellValue());
            data.setCat3Code(row.getCell(5).getStringCellValue());
            data.setCat3Name(row.getCell(6).getStringCellValue());
            data.setContentName(row.getCell(7).getStringCellValue());
            data.setContentType((int) row.getCell(8).getNumericCellValue());
            contentTypeService.createContentType(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/touristData/read")
    public String readTouristDataExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            TouristData data = new TouristData();

            data.setContentId((long) row.getCell(0).getNumericCellValue());
            data.setAddr(row.getCell(1).getStringCellValue());
            if (data.getAddr().equals("null"))
                data.setAddr(null);
            data.setAreaCode((long) row.getCell(2).getNumericCellValue());
            data.setCat1(row.getCell(3).getStringCellValue());
            if (data.getCat1().equals("null"))
                data.setCat1(null);
            data.setCat2(row.getCell(4).getStringCellValue());
            if (data.getCat2().equals("null"))
                data.setCat2(null);
            data.setCat3(row.getCell(5).getStringCellValue());
            if (data.getCat3().equals("null"))
                data.setCat3(null);
            data.setChkPet(row.getCell(6).getStringCellValue());
            if (data.getChkPet().equals("null"))
                data.setChkPet(null);
            data.setContentTypeId((long) row.getCell(7).getNumericCellValue());
            data.setExpGuide(row.getCell(8).getStringCellValue());
            if (data.getExpGuide().equals("null"))
                data.setExpGuide(null);
            data.setFirstImage(row.getCell(9).getStringCellValue());
            if (data.getFirstImage().equals("null"))
                data.setFirstImage(null);
            data.setFirstMenu(row.getCell(10).getStringCellValue());
            if (data.getFirstMenu().equals("null"))
                data.setFirstMenu(null);
            data.setHomePage(row.getCell(11).getStringCellValue());
            if (data.getHomePage().equals("null"))
                data.setHomePage(null);
            data.setIsCom((int) row.getCell(12).getNumericCellValue());
            data.setIsIm((int) row.getCell(13).getNumericCellValue());
            data.setIsJu((int) row.getCell(14).getNumericCellValue());
            data.setMapX(row.getCell(15).getNumericCellValue());
            data.setMapY(row.getCell(16).getNumericCellValue());
            data.setOpenTimeFood(row.getCell(17).getStringCellValue());
            if (data.getOpenTimeFood().equals("null"))
                data.setOpenTimeFood(null);
            data.setOverview(row.getCell(18).getStringCellValue());
            if (data.getOverview().equals("null"))
                data.setOverview(null);
            data.setOverviewSim(row.getCell(19).getStringCellValue());
            if (data.getOverviewSim().equals("null"))
                data.setOverviewSim(null);
            data.setPacking(row.getCell(20).getStringCellValue());
            if (data.getPacking().equals("null"))
                data.setPacking(null);
            data.setParking(row.getCell(21).getStringCellValue());
            if (data.getParking().equals("null"))
                data.setParking(null);
            data.setParkingFood(row.getCell(22).getStringCellValue());
            if (data.getParkingFood().equals("null"))
                data.setParkingFood(null);
            data.setRestDate(row.getCell(23).getStringCellValue());
            if (data.getRestDate().equals("null"))
                data.setRestDate(null);
            data.setRestDateFood(row.getCell(24).getStringCellValue());
            if (data.getRestDateFood().equals("null"))
                data.setRestDateFood(null);
            data.setSigunguCode((long) row.getCell(25).getNumericCellValue());
            data.setTel(row.getCell(26).getStringCellValue());
            if (data.getTel().equals("null"))
                data.setTel(null);
            data.setTitle(row.getCell(27).getStringCellValue());
            if (data.getTitle().equals("null"))
                data.setTitle(null);
            data.setTreatMenu(row.getCell(28).getStringCellValue());
            if (data.getTreatMenu().equals("null"))
                data.setTreatMenu(null);
            data.setUseTime(row.getCell(29).getStringCellValue());
            if (data.getUseTime().equals("null"))
                data.setUseTime(null);

            touristDataService.createTouristData(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/nearTouristData/read")
    public String readnearTouristDataExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            NearTouristData data = new NearTouristData();

            //data.setNearTouristDataId((long) row.getCell(0).getNumericCellValue());
            data.setAddr(row.getCell(1).getStringCellValue());
            if (data.getAddr().equals("null"))
                data.setAddr(null);
            data.setCat3Name(row.getCell(2).getStringCellValue());
            if (data.getCat3Name().equals("null"))
                data.setCat3Name(null);
            data.setContentId((long) row.getCell(3).getNumericCellValue());
            data.setFirstImage(row.getCell(4).getStringCellValue());
            if (data.getFirstImage().equals("null"))
                data.setFirstImage(null);
            data.setOverviewSim(row.getCell(5).getStringCellValue());
            if (data.getOverviewSim().equals("null"))
                data.setOverviewSim(null);
            data.setTitle(row.getCell(6).getStringCellValue());
            if (data.getTitle().equals("null"))
                data.setTitle(null);
            data.setTouristDataId((long) row.getCell(7).getNumericCellValue());

            nearTouristDataRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/touristDataHashTag/read")
    public String readTouristDataHashTagExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            TouristDataHashTag data = new TouristDataHashTag();

            Long contentId = (long) row.getCell(1).getNumericCellValue();
            Optional<TouristData> touristData = touristDataRepository.findById(contentId);
            if (touristData.isPresent()) {
                data.setContentId(contentId);
                data.setHashTagId((long) row.getCell(2).getNumericCellValue());
                data.setHashTagName(row.getCell(3).getStringCellValue());

                touristDataHashTagRepository.save(data);
            }
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/wtArea/read")
    public String readWtAreaExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            WtArea data = new WtArea();

            if (row.getCell(0) == null) {
                break;
            }

            data.setWtAreaId((long) row.getCell(0).getNumericCellValue());
            data.setCityName(row.getCell(1).getStringCellValue());
            System.out.println(row.getCell(1).getStringCellValue());
            data.setProvName(row.getCell(2).getStringCellValue());
            data.setLatitude(row.getCell(3).getNumericCellValue());
            data.setLongitude(row.getCell(4).getNumericCellValue());
            data.setMinLightPol(row.getCell(5).getNumericCellValue());
            data.setMaxLightPol(row.getCell(6).getNumericCellValue());

            wtAreaRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/wtToday/read")
    public String readWtTodayExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            WtToday data = new WtToday();

            if (row.getCell(0) == null) {
                break;
            }

            data.setWtTodayId((long) row.getCell(0).getNumericCellValue());
            data.setTodayWtId((int) row.getCell(1).getNumericCellValue());
            data.setTodayWtName1(row.getCell(2).getStringCellValue());
            data.setTodayWtName2(row.getCell(3).getStringCellValue());
            if (data.getTodayWtName2().equals("null"))
                data.setTodayWtName2(null);

            wtTodayRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/constellation/read")
    public String readConstellationExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            Constellation data = new Constellation();

            if (row.getCell(0) == null) {
                break;
            }

            data.setConstId((long) row.getCell(0).getNumericCellValue());
            data.setConstName(row.getCell(1).getStringCellValue());
            data.setConstStory(row.getCell(2).getStringCellValue());
            data.setConstMtd(row.getCell(3).getStringCellValue());
            data.setConstBestMonth(row.getCell(4).getStringCellValue());
            data.setConstFeature1(row.getCell(5).getStringCellValue());
            if (data.getConstFeature1().equals("null"))
                data.setConstFeature1(null);
            data.setConstFeature2(row.getCell(6).getStringCellValue());
            if (data.getConstFeature2().equals("null"))
                data.setConstFeature2(null);
            data.setConstFeature3(row.getCell(7).getStringCellValue());
            if (data.getConstFeature3().equals("null"))
                data.setConstFeature3(null);
            data.setStartDate1(row.getCell(8).getStringCellValue());
            data.setEndDate1(row.getCell(9).getStringCellValue());
            data.setStartDate2(row.getCell(10).getStringCellValue());
            if (data.getStartDate2().equals("null"))
                data.setStartDate2(null);
            data.setEndDate2(row.getCell(11).getStringCellValue());
            if (data.getEndDate2().equals("null"))
                data.setEndDate2(null);
            data.setConstEng(row.getCell(12).getStringCellValue());
            constellationRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/horoscope/read")
    public String readHoroscopeExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            Horoscope data = new Horoscope();

            if (row.getCell(0) == null) {
                break;
            }

            data.setHorId((long) row.getCell(0).getNumericCellValue());
            data.setHorImage(row.getCell(1).getStringCellValue());
            data.setHorEngTitle(row.getCell(2).getStringCellValue());
            data.setHorKrTitle(row.getCell(3).getStringCellValue());
            data.setHorPeriod(row.getCell(4).getStringCellValue());
            data.setHorDesc1(row.getCell(5).getStringCellValue());
            data.setHorDesc2(row.getCell(6).getStringCellValue());
            data.setHorDesc3(row.getCell(7).getStringCellValue());
            data.setHorDesc4(row.getCell(8).getStringCellValue());
            data.setHorDesc5(row.getCell(9).getStringCellValue());
            data.setHorDesc6(row.getCell(10).getStringCellValue());
            data.setHorDesc7(row.getCell(11).getStringCellValue());
            data.setHorDesc8(row.getCell(12).getStringCellValue());
            data.setHorDesc9(row.getCell(13).getStringCellValue());
            data.setHorDesc10(row.getCell(14).getStringCellValue());
            data.setHorDesc11(row.getCell(15).getStringCellValue());
            data.setHorDesc12(row.getCell(16).getStringCellValue());
            data.setHorGuard(row.getCell(17).getStringCellValue());
            data.setHorPersonality(row.getCell(18).getStringCellValue());
            data.setHorTravel(row.getCell(19).getStringCellValue());

            horoscopeRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationData/read")
    public String readObservationDataExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            Observation data = new Observation();
            if (row.getCell(0) == null) {
                break;
            }
            data.setObservationId((long) row.getCell(0).getNumericCellValue());
            data.setObservationName(row.getCell(1).getStringCellValue());
            data.setOutline(row.getCell(2).getStringCellValue());
            data.setIntro(row.getCell(3).getStringCellValue());
            data.setAddress(row.getCell(4).getStringCellValue());
            data.setPhoneNumber(row.getCell(5).getStringCellValue());
            if (data.getPhoneNumber().equals("null"))
                data.setPhoneNumber(null);
            data.setOperatingHour(row.getCell(6).getStringCellValue());
            if (data.getOperatingHour().equals("null"))
                data.setOperatingHour(null);
            data.setClosedDay(row.getCell(7).getStringCellValue());
            if (data.getClosedDay().equals("null"))
                data.setClosedDay(null);
            data.setGuide(row.getCell(8).getStringCellValue());
            data.setParking(row.getCell(9).getStringCellValue());
            data.setLink(row.getCell(10).getStringCellValue());
            if (data.getLink().equals("null"))
                data.setLink(null);
            data.setObserveType(row.getCell(11).getStringCellValue());
            data.setLatitude((double) row.getCell(12).getNumericCellValue());
            data.setLongitude((double) row.getCell(13).getNumericCellValue());
            data.setLight((double) row.getCell(14).getNumericCellValue());
            if (row.getCell(15).getNumericCellValue() == 1)
                data.setNature(true);
            else
                data.setNature(false);
            data.setAreaCode((long) row.getCell(16).getNumericCellValue());
            data.setCourseOrder((int) row.getCell(17).getNumericCellValue());
            observationRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationHashTag/read")
    public String readObservationHashTagExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            }
            ObserveHashTag data = new ObserveHashTag();

            data.setObserveHashTagListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setHashTagId((long) row.getCell(2).getNumericCellValue());
            data.setHashTagName(row.getCell(3).getStringCellValue());

            observeHashTagRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationFee/read")
    public String readObservationFeeExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            }
            ObserveFee data = new ObserveFee();

            data.setObserveFeeListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setFeeName(row.getCell(2).getStringCellValue());
            data.setEntranceFee(row.getCell(3).getStringCellValue());
            if (data.getEntranceFee().equals("null"))
                data.setEntranceFee(null);

            observeFeeRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationImage/read")
    public String readObservationImageExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            } else if (row.getCell(2) == null) {
                continue;
            }
            ObserveImage data = new ObserveImage();

            data.setObserveImageListId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setImage(row.getCell(2).getStringCellValue());
            data.setImageSource(row.getCell(3).getStringCellValue());
            if (data.getImageSource().equals("null"))
                data.setImageSource(null);
            observeImageRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/observationCourse/read")
    public String readObservationCourseExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            } else if (row.getCell(2) == null) {
                continue;
            }
            Course data = new Course();

            data.setCourseId((long) row.getCell(0).getNumericCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setTouristPointId((long) row.getCell(2).getNumericCellValue());
            data.setCourseOrder((int) row.getCell(3).getNumericCellValue());


            courseRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/hashtags/read")
    public String readHashTagsExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            }
            HashTag data = new HashTag();

            data.setHashTagId((long) row.getCell(0).getNumericCellValue());
            data.setHashTagName(row.getCell(1).getStringCellValue());

            hashTagRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

    @PostMapping("/excel/searchFirst/read")
    public String readSearchFirstExcel(@RequestParam("file") MultipartFile file, Model model)
            throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);
        System.out.println(worksheet.getPhysicalNumberOfRows());
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row.getCell(0) == null) {
                break;
            }
            SearchFirst data = new SearchFirst();

            data.setTypeName(row.getCell(0).getStringCellValue());
            data.setObservationId((long) row.getCell(1).getNumericCellValue());
            data.setObservationName(row.getCell(2).getStringCellValue());

            searchFirstRepository.save(data);
        }
        System.out.println("엑셀 완료");
        return "excel";
    }

}
