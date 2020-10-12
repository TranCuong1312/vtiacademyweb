drop database if exists `vti_academy`;
CREATE DATABASE  IF NOT EXISTS `vti_academy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vti_academy`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: vti_academy
-- ------------------------------------------------------
-- Server version	8.0.20
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Table structure for table `about_us`
--
DROP TABLE IF EXISTS `about_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `about_us` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text,
  `video` varchar(200) DEFAULT NULL,
  `title_video` varchar(200) DEFAULT NULL,
  `description_video` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `about_us`
--
LOCK TABLES `about_us` WRITE;
/*!40000 ALTER TABLE `about_us` DISABLE KEYS */;
INSERT INTO `about_us`(`description`, `video`, `title_video`, `description_video`) VALUES
	('Enterprise passport for developer (EPD) là chương trình huấn luyện bắt buộc của các lập trình viên muốn trở thành nhân viên của những tập đoàn công nghệ hàng đầu Việt Nam. <br> Chúng tôi mang tới cho các lập trình viên tương lai những kiến thức tốt nhất về kỹ năng công nghệ, kỹ năng mềm và kiến thức quy trình phát triển phần mềm đang được áp dụng tại doanh nghiệp. Khóa huấn luyện là hộ chiếu giúp bạn trở thành kỹ sư phần mềm toàn diện được các công ty trong nước và quốc tế săn đón với mức thu nhập hấp dẫn.','https://www.youtube.com/embed/6WJw2UlluYE','Video','Đội ngũ quản lý với 20 năm kinh nghiệm trong ngành công nghiệp CNTT 15 năm làm việc liên tục với khách hàng Nhật Bản Chúng tôi tạo ra sản phẩm tuyệt vời và hy vọng mục tiêu mang lại trải nghiệm tốt nhất cho bạn.');
/*!40000 ALTER TABLE `about_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--
DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` text,
  `address2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `phone2` varchar(20) DEFAULT NULL,
  `domain` varchar(45) DEFAULT NULL,
  `facebook` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `youtube` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `contacts`
--
LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts`(`address`, `address2`, `phone`, `phone2`, `domain`, `facebook`, `email`, `youtube`) VALUES
	('Tầng 3 - số 60,62 - Bạch Mai - Hai Bà Trưng - Hà Nội','Tầng 6 Tòa nhà AC - Số 78 - Duy Tân - Cầu Giấy - Hà Nội','865 359 388','243 3668 8599','http://vtiacademy.edu.vn','https://www.facebook.com/VTIAcademy','info@vtiacademy.edu.vn','https://www.youtube.com/channel/UCy0ozVHYHzRhqbp9tNNfoig');
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_outcomes`
--
DROP TABLE IF EXISTS `course_outcomes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_outcomes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CourseOutcomes_Courses_idx` (`course_id`),
  CONSTRAINT `FK_course_outcome_courses` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_outcomes`
--
LOCK TABLES `course_outcomes` WRITE;
/*!40000 ALTER TABLE `course_outcomes` DISABLE KEYS */;
INSERT INTO `course_outcomes`(`course_id`, `name`) VALUES
	(2,'Trở thành lập trình viên chuyên nghiệp Full-Stack'),
    (2,'Thiết kế, xây dựng và thao tác thành thạo cơ sở dữ liệu'),
    (2,'Xây dựng giao diện web với HTML, CSS, HTML5, CSS3, Javascript, JQuery, Ajax, Angular 5+'),
    (2,'Tham gia tốt vào nhóm phát tiển phần mềm Agile/Scrum'),
    (2,'Giàu kinh nghiệm thực tế trong các dự án thật tại doanh nghiệp'),
    (15,'Trở thành lập trình viên chuyên nghiệp ÁP.NET Full-Stack'),
    (15,'Sử dụng thành thạo ngôn ngữ lập trình C# từ cơ bản đến nâng cao'),
    (15,'Thiết kế, xây dựng và thao tác thành thạo cơ sở dữ liệu'),
    (15,'Xây dựng giao diện web với HTML, CSS, HTML5, CSS3, Javascript, JQuery, Ajax, Angular 5+'),
    (15,'Tham gia tốt vào nhóm phát tiển phần mềm Agile/Scrum'),
    (15,'Giàu kinh nghiệm thực tế trong các dự án thật tại doanh nghiệp'),
    (5,'Làm chủ kiến thức về lập trình web với PHP cơ bản, PHP nâng cao'),
    (5,'Tạo lập và truy vấn dễ dàng cơ sở dữ liệu quan hệ MySQL'),
    (5,'Phát triển các ứng dụng web trên nền tảng ngôn ngữ PHP'),
    (5,'Linh hoạt sử dụng các cấu trúc dữ liệu phù hợp trong các tình huống cụ thể'),
    (1,'Nắm vững các đối tượng giao diện và nâng cao'),
    (1,'Làm việc với đối tuojng Service, GoogleMap, Camera'),
    (1,'Thành thạo các công cụ xây dựng ứng dụng mobile trên hệ điều hành Android'),
    (6,'80% thời gian đào tạo tập trung vào kiểm thử phần mềm với các dự án thực tế được trích từ nhiều dự án trong doanh nghiệp. 20% còn lại bạn được đào tạo kiến thức chuyên sâu từ cơ bản đến nâng cao '),
    (6,'Nắm được quy trình kiểm thử phần meegm chuẩn, rèn luyện và phát triển kỹ năng kiểm thử phần mềm trong doanh nghiệp'),
    (6,'Nắm dược các loại hình kiểm thử và các mức kierm thử trong doanh nghiệp'),
    (6,'Tự tin nhạy bén khi triển khai,nâng cao tính hiệu quả kiểm thử phần mềm trong các dự án thực tế'),
    (6,'Hoàn thành project tốt nghiệp theo đúng tiêu chuẩn'),
    (6,'Cuối khóa bạn sẽ được training kỹ năng viết CV và phỏng vấn cùng các chuyên gia'),
    (6,'VTI Academy CAM KẾT VIỆC LÀM sau 2 tháng hoàn thành khóa học');
/*!40000 ALTER TABLE `course_outcomes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_syllabuses`
--
DROP TABLE IF EXISTS `course_syllabuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_syllabuses` (
  `course_id` int NOT NULL,
  `subcourse_id` int NOT NULL,
  KEY `FK_CourseSyllabus_Courses_idx` (`course_id`),
  KEY `FK_CourseSyllabus_SubCourses_idx` (`subcourse_id`),
  CONSTRAINT `FK_course_syllabus_course` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE cascade,
  CONSTRAINT `FK_course_syllabus_subcourse` FOREIGN KEY (`subcourse_id`) REFERENCES `subcourses` (`id`)  ON DELETE cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `course_syllabuses`
--
LOCK TABLES `course_syllabuses` WRITE;
/*!40000 ALTER TABLE `course_syllabuses` DISABLE KEYS */;
INSERT INTO `course_syllabuses` VALUES
	(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
    (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),
    (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),
    (4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),
    (5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),
    (6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),
    (7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7);
/*!40000 ALTER TABLE `course_syllabuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--
DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `intro` text,
  `curriculum` varchar(200) DEFAULT NULL,
  `create_date` DATETIME DEFAULT NOW() NULL,
  `is_active` bit(1) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `courses`
--
LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`name`,`img`, `intro`, `curriculum`, `is_active`, `note`)
VALUES
    ('Khóa học java web full-stack','java.jpg','	HOÀN THÀNH KHÓA ĐÀO TẠO JAVA WEB\n•	Trở thành lập trình viên chuyên nghiệp Full – Stack \n•	Sử dụng thành thạo ngôn ngữ JavaScript và Java \n•	Xây dựng và thao tác thành tạo cơ sở dữ liệu \n•	Sử dụng được các công cụ lập trình phần mềm hiện đại \n•	Tham gia tốt vào nhóm phát triển phần mềm Agile \n•	Giàu kinh nghiệm thực tế trong các dự án thật tại doanh nghiệp ','CCCCCCCC',_binary '','CCCCCCCC'),
    ('Khóa học PHP','PHP.jpg','HOÀN THÀNH KHÓA ĐÀO TẠO FULL – STACK WEB PHP\n•	Làm chủ kiến thức về lập trình web với PHP cơ bản, PHP nâng cao.\n•	Tạo lập và truy vấn dễ dàng cơ sở dữ liệu quan hệ MySQL\n•	Phát triển các ứng dụng web trên nền tảng ngôn ngữ PHP\n•	Linh hoạt sử dụng các cấu trúc dữ liệu phù hợp trong các tình huống cụ thể \n•	Triển khai thành thạo ứng dụng web sử dụng Framework Laravel .\n•	Giàu kinh nghiệm trong các dự án thực tế tại các doanh nghiệp','BBB',_binary '','as'),
    ('Khóa học ASP.net','Asp.net.jpg','HOÀN THÀNH KHÓA ĐÀO TẠO FULL – STACK WEB ASP.NET\n•	Nắm được lập trình hướng đối tượng với C# và .NET\n•	Hiểu toàn diện về .NET và ngôn ngữ lập trình C#\n•	Sử dụng thành thạo các điều khiển ASP.NET\n•	Phát triển ứng dụng trên nền web sử dụng ASP.Net\n•	Có kinh nghiệm thực hiện dự án web ASP.Net với C# \n•	Xây dựng giao web với HTML, CSS, HTML5, CSS3, Javascript, Jquery, Ajax.\n','CCCC',_binary '','CCC'),
	('Khóa học lập trình mobile (Android)','Android.jpg','HOÀN THÀNH KHÓA ĐÀO TẠO LẬP TRÌNH MOBILE (ANDROID) \n•	Làm chủ kiến thức nền tảng về lập trình ứng dụng Android \n•	Nắm vững các đối tượng giao diện và nâng cao\n•	Hiểu biết về thread, Asyntask, XML parser, Webview\n•	Làm việc với đối tượng Service, GoogleMap, Camera\n•	Thành thạo các công cụ xây dựng ứng dụng mobile trên hệ điều hành Android.\n•	Xây dựng và triển khai các ứng dụng thực tế theo công nghệ mới.','CCCCC',_binary '\0',''),
    ('Khóa học tester','tester.jpg','G','CCCCCCCC',_binary '','CCC'),
    ('Khóa học NonIT','nonIT.jpg','CCCCCCCC','CCCCCCCC',_binary '','CCCCCCCC'),
    ('Khóa học online','online.png','CCCCCCCC','CCCCCCCC',_binary '','CCCCCCCC');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intro`
--
DROP TABLE IF EXISTS `intro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `intro`
--
LOCK TABLES `intro` WRITE;
/*!40000 ALTER TABLE `intro` DISABLE KEYS */;
INSERT INTO `intro`(`name`, `description`, `img`) VALUES
	('học viên','16998', null),
    ('doanh nghiệp','36', null),
    ('dự án','435', null);
/*!40000 ALTER TABLE `intro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentors`
--
DROP TABLE IF EXISTS `mentors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `position` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `mentors`
--
LOCK TABLES `mentors` WRITE;
/*!40000 ALTER TABLE `mentors` DISABLE KEYS */;
INSERT INTO `mentors` (`name`, `position`, `img`, `content`)
VALUES
	('TRẦN  XUÂN  KHÔI','Chuyên gia công nghệ hàng đầu Việt Nam','1x.PNG','More'),
    ('ĐĂNG  TUẤN  THÀNH','Chuyên gia giải phap công nghệ Cloud','4x.jpg','More'),
    ('NGUYỄN  ĐỨC  CƯỜNG','Chuyên gia công nghệ thông tin','5x.jpg','More'),
    ('NGUYỄN  NHẬT  ÁNH  LINH','Chuyên gia lập trình Asp.net','6x.jpg','More'),
    ('NGUYỄN QUYẾT','Chuyên gia công nghệ thông tin','2x.PNG','More'),
    ('NGUYỄN NGỌC DUY','Giảng viên lập trình Java Web','3x.PNG','More');
/*!40000 ALTER TABLE `mentors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--
DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text,
  `author` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `short_des` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `news`
--
LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO 
	`news` 	(`title`, `short_des`, `author`, `create_date`,  `image`, `content`)
VALUES 
			('Lễ bế giảng','Chiều ngày 12/08/2020 vừa qua đã tổ chức buổi lễ bế giảng cho các bạn học viên khóa đào tạo chương trình Hộ Chiếu Lập Trình Viên Doanh Nghiệp. Vui bế giảng nhưng mọi người vẫn tuân thủ quy định theo khẩu trang', 'VTI Academy','2020-08-12 15:00:00','le-be-giang-3.jpg','Chia sẻ tại lễ bế giảng, ông Nguyễn Quyết – Giám đốc đào tạo của VTC Academy vô cùng xúc động và tự hào sau khi cùng các hoc viên thân yêu trải qua bao nhiêu nỗ lực cũng như cố gắng để đi ngày hôm này. Bên cạnh đó Ông Nguyễn Duy Tuấn – Mentor PM của VTI Việt Nam và bà Trần Hồng – CEO HRI Việt Nam cũng chia sẻ niềm vui của mình khi học viện đã đào tạo được những học viên suất xắc và tiềm năng cho ngành công nghệ thông tin. <br/>Các học viên đều mang tâm trạng rất vui và phấn khởi tới dự lễ bế giảng. Các bạn cũng bày tỏ sự hài lòng về sự chất lượng, chuyên nghiệp của chương trình học cũng như về đội ngũ mentor và môi trường học tập. Lễ bế giảng khép lại với nhiều cung bậc cảm xúc. Cảm ơn các bạn luôn theo dõi, ủng hộ và tin tưởng học viện! '),
			('Chuỗi sự kiện Techsharing','Series TechSharing 2020 là chuỗi chia sẻ kiến thức về công nghệ do VTI Academy tổ chức. Các chuyên gia hàng đầu về CNTT tham gia sự kiện với vai trò là khách mời danh dự, mỗi một phần sẽ là một nhân vật khác nhau.','VTI Academy','2020-06-19 12:00:00','techsharing-1.jpg','Được gặp gỡ chuyên gia CNTT hàng tuần với VTI Academy “món ăn tinh thần” không thể thiếu đối với các bạn đam mê công nghệ. Không chỉ là các câu hỏi mở dành cho diễn giả, chúng ta còn được nghe những kiến thức thực tế vô cùng bổ ích và hấp dẫn truyền tải tâm huyết trong 180 phút chia sẻ. <br/>Sự kiện thu hút sự quan tâm và  chú ý của rất đông các bạn sinh viên. Nếu bạn đã là fan của công nghệ, bạn đang tìm hiểu, mong muốn học tập thì sự kiện này là một bước đệm, là sự kết nối hoàn hảo giúp bạn đạt được ước muốn này.'),
			('Lễ ký kết thỏa thuận hợp tác giữa VTI Việt Nam & Công ty cổ phần Smartosc.','Sáng ngày 18/11/2019, tại trụ sở chính của công ty cổ phần SmartOSC, lễ ký kết thỏa thuận hợp tác giữa VTI Việt Nam và SmartOSC đã chính thức diễn ra. Tham dự tại buổi lễ có đại diện VTI Việt Nam – Ông Trần Xuân Khôi, CEO VTI Academy – Ông Nguyễn Quyết, về phía đại diện SmartOSC, Tổng giám đốc - Ông Nguyễn Thái Sơn và Phó tổng giám đốc Lê Mai Anh.','VTI Academy','2019-11-18 12:00:00','le-ky-thoa-thuan-smartosc-3.jpg','Với mục đích cung ứng nguồn nhân lực công nghệ thông tin (CNTT) chất lượng cao, và giải quyết bài toán khan hiếm nguồn nhân lực CNTT, đại diện hai bên đã thẳng thắn chia sẻ với mong muốn hợp tác, đối tác để cùng đạt được kết quả tốt nhất trong thời gian tới. Đại diện VTI Việt Nam – Ông Trần Xuân Khôi cũng nhất trí với những thỏa thuận đã ký kết của hai bên trên tinh thần đảm bảo các tiêu chí, những yêu cầu về đào tạo nguồn nhân lực CNTT chất lượng cao đạt tiêu chuẩn theo đúng chương trình đào tạo chuẩn doanh nghiệp. Về phía SmartOSC – Tổng giám đốc Ông Nguyễn Thái Sơn cũng hy vọng, với đầu ra của nguồn nhân lực do VTI Việt Nam cung ứng, SmartOSC luôn sẵn sàng tạo cơ hội mở, môi trường làm việc chuyên nghiệp với mức thu nhập cạnh tranh nhất để những ứng viên ưu tú phát triển một cách toàn diện và bền vững.'),
			('Lễ ký kết thỏa thuận hợp tác giữa NEU và VTI Việt Nam','Sáng ngày 24/10/2019 tại Hội trường A2, lễ ký kết hợp tác đối tác giữa Trường Kinh Tế Quốc Dân (NEU) với đại điện VTI Việt Nam - Ông Nguyễn Quang Huy (Phó tổng giám đốc nhân sự) đã chính thức diễn ra trước sự chứng kiến của Đảng ủy, Ban lãnh đạo nhà trường, PGS.TS đầu ngành, nguyên các lãnh đạo nhà trường, cùng nhiều doanh nghiệp đến tham dự, tập thể thầy cô giáo, cựu sinh viên và hàng trăm sinh viên ưu tú của nhà trường.','VTI Academy','2019-02-24 08:00:00','le-ky-thoa-thuan-NEU-1.jpg','Là một trong số doanh nghiệp CNTT có tốc độ phát triển mạnh nhất trong những năm gần đây, VTI Việt Nam tự hào là đơn vị hợp tác đầu tiên nhận được được sự tín nhiệm từ phía nhà trường. Với phương châm kết nối, hỗ trợ, cùng phát triển trong sự nghiệp của hai bên nói riêng cũng như định hướng chung trong sự phát triển của đất nước; Về phía nhà trường, đại diện Ban lãnh đạo mong muốn trong thời gian tới thỏa thuận hợp tác này sẽ đạt được kết quả tốt đẹp. Theo đó, đại diện VTI Việt Nam - Ông Nguyễn Quang Huy cũng hy vọng, lễ ký kết này với NEU sẽ là một bước phát triển cao nhất trong quan hệ hợp tác đối tác bền vững của hai bên, tạo động lực phát triển trong sự nghiệp tương lai của hơn 1000 sinh viên ưu tú của trường.');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--
DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `refresh_token`
--
LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token`(`token`, `created_date`, `username`) VALUES
	('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODE3ODUyLCJleHAiOjE1OTU4MTc5Mzh9.kUAkNiRK2Lo8az6xTkrmQ-N6a6N_s3TFi0hM0ETEOf2nT3ihSVfoe3FwyjIUUBj8','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODE3OTI0LCJleHAiOjE1OTU4MTgwMTB9.kZ9OmVm-qIL9QUqc-dQ8mPT7xy4rIlKFWdrAxM9oVfnS8itgpRm7K5ydboUHa-vE','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhYmMiLCJpYXQiOjE1OTU4MTkxMzEsImV4cCI6MTU5NTgxOTIxN30.sqVAeprry0PLZ9JpUQrM3g81Pkdzg4VAE5teMnPVYJsbM0aarbFc0YLdOiSjLIGl','2020-07-27 00:00:00.000000','abc'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODE5NTExLCJleHAiOjE1OTU4MTk1OTd9.rvYmiIK9ONfRp2KOOFj4tZbPdJwmARi2KIYOgZhdbBNannceneTz-BKBqiD5q3TX','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODE5NTExLCJleHAiOjE1OTU4MTk1OTd9.rvYmiIK9ONfRp2KOOFj4tZbPdJwmARi2KIYOgZhdbBNannceneTz-BKBqiD5q3TX','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODIyOTE1LCJleHAiOjE1OTU4MjMwMDJ9.WD5lo0fy_vstW4NLuWu_oUTML7BRBGtCL1ZefnigLHoz_It_FzzKEmBpJhIkjCRJ','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODIyOTE2LCJleHAiOjE1OTU4MjMwMDJ9.pa62mO66wdmvpoeo9_FjbWZk93LNA2TL3nrJtkRqRjdJ_MRWgOP4X0SMNcGPhVcn','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1ODI0ODc3LCJleHAiOjE1OTU4MjQ5NjR9.unPrPoghejyFZa0s_OsRm9AEPVLqG5XfvoS_ORkqfUG1mmzz_FfDc4hI_8lefizf','2020-07-27 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1OTE5MTAyLCJleHAiOjE1OTU5MTkxODl9.KT_KJhXUuc6t2iGhmUD-axNM3SDfxuKrdtv_lZd7CBxJI4CthCLZgTBvSkX9cCsO','2020-07-28 00:00:00.000000','phan'),
    ('eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJwaGFuIiwiaWF0IjoxNTk1OTE5NzAxLCJleHAiOjE1OTU5MTk3ODd9.hWn3A8M-7yi6wV8vG8umUpGTbeaF0EF1BhGamQkKjawGaQ9-mLJ5gtAm2mfb2MBn','2020-07-28 00:00:00.000000','phan');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--
DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(200) DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `reviewer_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `office` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `review_type` enum('CHUYENGIA','DOANHNGHIEP','HOCVIEN') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `reviews`
--
LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` (`image`, `content`, `reviewer_name`, `office`, `review_type`)
VALUES 
	('thanh.jpg','Minh quyết định lựa chọn khóa đào tạo của bên VTI Academy vì khung chương trình đào tạo rõ ràng, hệ thống LMS trực tuyến khá hấp dẫn, tạo hứng thú học tập cho bọn em. Đặc biệt, việc cam kết đầu ra của học viện chính là lý do cuối cùng em quyết định theo học khóa đào tạo này!','Nguyễn Tất Thành','Sinh viên năm 3 - Trường NEU','HOCVIEN'),
    ('ngoc.jpg','Môi trường học tập rất thoải mái, các Mentor nhiệt tình giảng dạy, hỗ trợ mọi lúc, video bài giảng dễ hiểu, k dàn trải, lịch học hợp lí, học nghiêm túc thì sẽ có thể đi làm ngay ở công ty.','Nguyễn Thị Ngọc','Sinh viên năm 4 -  Trường NEU','HOCVIEN'),
    ('tuanAnh.jpg','Trải nghiệm ngay từ những buổi học đầu trong chương huấn luyện EPD của học viện VTI mình cảm thấy rất thú vị có hào hứng tìm hiểu khối kiến thức mới này. Giảng viên tận tâm, chia sẻ, giải đáp rất kỹ trước những vướng mắc mình gặp phải. Đặc biệt, hỗ trợ nhiệt tình ngay cả khi kết thúc giờ đào tạo.','Cấn Tuấn Anh','Sinh viên IT – Trường UNETI','HOCVIEN'),
	('hung.jpg','Anh trai mình định hướng học ngành CNTT và từ trước bản thân mình cũng thích ngành này. Việc học tập theo một chương trình mới mình cũng lo lắng, hy vọng thời gian tới mình sẽ cố gắng để sau khi kết thúc khóa học mình sẽ làm được việc và có mức lương ổn định.','Nguyễn Thế Hưng','Nghề nghiệp tự do','HOCVIEN'),
  	('toan.jpg','Mình là dân không chuyên CNTT, mình sẵn sàng học tập nghiêm túc và rẽ sang ngành này vì hai lý do chính. Thứ nhất, đầu ra của ngành IT rất lớn, cơ hội nhiều, thứ 2 mức thu nhập ổn định. Khóa NON – IT mình đang theo đuổi tại VTI Academy sẽ rất khó và có thể phải đi chậm hơn các bạn có sẵn kiến thức nền về công nghệ. Tuy nhiên, mình cũng muốn thử sức và sẽ phấn đấu hết mình.','Nguyễn Anh Toàn','Nghề nghiệp tự do','HOCVIEN'),
  	('thao.jpg','VTI Academy có lộ trình học tập rõ ràng, hiệu quả. Đặc biệt là kiến thức chuẩn doanh nghiệp. Ngoài chương trình đào tạo offline mình còn được học online trên hệ thống LMS, như thế làm tăng rất nhiều khả năng tự học, tính tự giác chủ động tiếp thu kiến thức.  Đội ngũ các anh chị, Mentor bên VTI Academy thật sự nhiệt tình, thân thiện và chuyên nghiệp giúp đỡ mình rất nhiều.','Cao Thu Thảo','Sinh viên năm 3 - Trường NEU','HOCVIEN'),
    ('duyTuan.jpg', 'Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua', 'Nguyễn Duy Tuấn', 'Project Manager VTI', 'CHUYENGIA'),
    ('ngocDuy.jpg', 'Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua', 'Nguyễn Ngọc Duy', 'Mentor VTI Academy', 'CHUYENGIA'),
    ('minhGiang.jpg', 'Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua', 'Tống Minh Giang', 'Developer - CMC Global', 'CHUYENGIA'),
    ('dinhHao.jpg', 'Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua', 'Vũ Đình Hào', 'Developer - Viettel', 'CHUYENGIA'),
    ('trung.jpg', 'Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua', 'Trung LD', 'Developer', 'CHUYENGIA');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcourses`
--
DROP TABLE IF EXISTS `subcourses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcourses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `create_date` DATETIME DEFAULT NOW() NULL,
  `content` text,
  `is_active` bit(1) DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `subcourses`
--
LOCK TABLES `subcourses` WRITE;
/*!40000 ALTER TABLE `subcourses` DISABLE KEYS */;
INSERT INTO `subcourses`(`name`, `content`, `is_active`, `note`) VALUES
    ('DATABASE SYSTEM','TEXT',_binary '','asdasd'),
    ('BACK-END BASIC','Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua',_binary '',NULL),
    ('BACK-END ADVANCED','TEXT',_binary '',NULL),
    ('FRONT-END BASIC','TEXT',_binary '',NULL),
    ('FRONT-END ADVANCE','TEXT',_binary '',NULL),
    ('MOCK-PROJECT','TEXT',_binary '',NULL),
    ('CAREER ORIENTATION','TEXT',_binary '','asdasd');
/*!40000 ALTER TABLE `subcourses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--
DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `company_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `vat_number` varchar(45) DEFAULT NULL,
  `street` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `city` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `country` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `note` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_UNIQUE` (`user_id`),
  CONSTRAINT `FK_User_Details_User` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `user_details`
--
LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES
	(1,1,'tran','phan anh','0977676708','VTI','45345345','phum hung','Hanoi','25000','Ha Noi','2020-07-14 00:00:00',NULL);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `users`
--
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users`(`username`, `password`, `is_active`, `email`, `role`) VALUES
	('phan','$2a$10$1bXdFFf1NNKfOKZdUOcE8e6.35eSpYCbw4lY0qy/lH2CbRsr25emC',_binary '','phananhtran1414@gmail.com','ADMIN'),
    ('abc','$2a$10$QEmMysJPNsfMDLd/bPEocujNUr1o9WMysCKoY07dq75YsZ63ZsHeK',_binary '','abc1414@gmail.com','USER'),
    ('quang','$2a$10$Z6PbGNc/doL9RHCnw1JwkOnDlEGWGEcFuuDYTKuVHZYPQCx6OsFqW',_binary '','hongquang691999@gmail.com','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_courses`
--
DROP TABLE IF EXISTS `users_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_courses` (
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  KEY `FK_UsersCourses_Users_idx` (`user_id`),
  KEY `FK_UsersCourses_Courses_idx` (`course_id`),
  CONSTRAINT `FK_users_courses_courses` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FK_users_courses_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `users_courses`
--
LOCK TABLES `users_courses` WRITE;
/*!40000 ALTER TABLE `users_courses` DISABLE KEYS */;
INSERT INTO `users_courses` VALUES (1,5),(1,6);
/*!40000 ALTER TABLE `users_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formregister`
--
DROP TABLE IF EXISTS `formregister`;
CREATE TABLE `vti_academy`.`formregister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `date` DATETIME DEFAULT NOW(),
  `type` INT NULL,
  `courses_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `coursesId_idx` (`courses_id` ASC) VISIBLE,
  CONSTRAINT `courses_id`
    FOREIGN KEY (`courses_id`)
    REFERENCES `vti_academy`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
--
-- Dumping data for table `formregister`
--
LOCK TABLES `formregister` WRITE;
/*!40000 ALTER TABLE `formregister` DISABLE KEYS */;
/*!40000 ALTER TABLE `formregister` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `vti_academy`.`enterprise` (
  `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `image` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `icon` VARCHAR(255) NOT NULL,
  `name` VARCHAR(500) NOT NULL)
ENGINE = InnoDB;
INSERT INTO `enterprise`(`image`,`content`,`icon`,`name`) VALUES
	('carousel_1.PNG','Là một công ty cung cấp nguồn lực Công nghệ thông tin cho thị trường trong nước và quốc tế. Chúng tôi luôn chờ đợi các học viên từ VTI Academy','HRI.PNG','Công ty TNHH HRI Việt Nam'),
    ('carousel_1.PNG','Hàng năm chúng tôi thiếu hàng nghìn lập trình viên mới. Chúng tôi tin tưởng VTI Academy là lời giải cho việc thiếu hụt nguồn lực Công nghệ thông tin trong thời đại 4.0','CMC.PNG','Công ty TNHH CMC Global'),
    ('carousel_2.PNG','Nhân viên mới của chúng tôi phải mất cả năm mới có đầy đủ kiến thức chuyên môn, kỹ năng làm việc và quy trình phát triển dự án phần mềm. VTI Academy đã thuyết phục được chúng tôi bởi chương trình huấn luyện bài bản và đầy đủ cho một nhân viên mới cần phải có.','RKEI.PNG','Công ty TNHH Rikkeisoft');

DROP TABLE IF EXISTS `mentor_comment`;
CREATE TABLE `vti_academy`.`mentor_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `comment` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO `mentor_comment` (`name`, `position`, `comment`)
  VALUES ('Nguyen Duy Tuan', 'Project Management VTI', ' Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua Hay lam hay qua');


DROP TABLE IF EXISTS `mainbanner`;
CREATE TABLE `vti_academy`.`mainbanner`(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(45) NULL,
	`url` VARCHAR(255) NULL,
	`image` VARCHAR(45) NULL
);
insert into 
	mainbanner	(`name`, `url`, `image`)
values 
					('Khóa Học Java', 	'http://localhost:4200/courses/1', 	'banner 1.png'	),
					('Khóa Học PHP', 	'http://localhost:4200/courses/2', 	'banner 2.png'	),
					('Khóa Học Tester', 'http://localhost:4200/courses/5',	'banner 4.png'	);
                    
                    
DROP TABLE IF EXISTS `vti_academy`.`other_config`;
CREATE TABLE `vti_academy`.`other_config` (
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`training_program_img` VARCHAR(200) NOT NULL,
	`business_training_text` VARCHAR(10000) NOT NULL
);

INSERT INTO `vti_academy`.`other_config`(`training_program_img`, `business_training_text`)
VALUES ('bbb.png','Đào tạo doanh nghiệp text đào tạo doanh nghiệp text đào tạo doanh nghiệp text đào tạo doanh nghiệp text');
    
-- Dump completed on 2020-07-29 10:02:44


