CREATE DATABASE  IF NOT EXISTS `messageboard` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `messageboard`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: messageboard
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `permission` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `account_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'chen82louis','chlouis3962','$2a$10$QhzD57Ip7nW7sPxesg.yieNn60Suat6KDzkwE6Al1eF2t/1TmbqRC','2020-02-02 17:03:46',1),(2,'bigfinger349','bigfin230945','$2a$10$3OxAQJZEx.f/OvNxRf6jcuQbKiZHq6cCSw79odJTnt3fEiRNbEvs.','2008-04-04 21:44:06',1),(3,'newfuturetech','futureyeah','$2a$10$sTOQm0QN5votr1jJp/hBFOr2pVmNOuhQUFoqdL9DMgXPZlKfihbqG','2019-05-05 07:32:18',1),(4,'IAmSuperFAtty','fafa6e00','$2a$10$P/V8oc8G90ngrGlvV16vcOu7ZNZsMZusNF1mb.hQuoaxxuQ7XKDfu','2013-04-04 11:59:17',1),(5,'Automanagement66','yourBoss024','$2a$10$oeajFEsMM4c5gw5Odbrxd.qre8TFefIqARW.mJ9pTjqeEgBxK/i5.','2004-09-09 19:11:27',1),(6,'SuperCodding','SPcode35g7734','$2a$10$apL8/O65xdd8Ue992sCEpu2h7izJCR9RcMdWblW05G3wpJzUDgXjy','2017-07-12 16:23:54',1),(12,'MagicBigBat','bigbat9999','$2a$10$.djMQ6cQB7Dgacq1SU0u4eVhuKNwX0kYJKoW8kw1QWTuEnwoH3UBq','2022-12-20 12:43:31',1),(13,'cclemon','cc123123','$2a$10$h49mnENaV8g1BZw8VpaX0eWm1Ej49MGRh1YBHVBAyGEjyoZVjyU6i','2022-12-20 22:35:17',1),(22,'Admin-Leo','admin00','$2a$10$P23ashQIHAmb4w17hufE7eZBltF2n4pWjGXSkMUyI.mL4u4aWnAdO','2022-12-27 08:47:34',3),(23,'Webmaster-Lucy','webmaster00','$2a$10$gXcuHmCkt29cXYyUG4dRiu9U/VtoKjk7P20KdWRhUpCDHsZXI4OqG','2022-12-27 08:55:40',2),(24,'User-Lily','user00','$2a$10$67Jnid8cMvK9i3RlGwyiBOvKBKGX3bhQMrGFaPIScFMdMtU2.YgJ6','2022-12-27 08:58:29',1),(25,'gggggtavi','gggggtavi','$2a$10$N2J3wuLS./2Yh67lybW.b.Oxub1CAC4dKn/ITjrGVE.tKlToAoYUm','2022-12-28 12:53:15',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `member_message` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL,
  `board_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boardID_idx` (`board_id`),
  KEY `userID_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,1,'了不起喔，台中人也有啊?','2022-12-28 10:35:56',3),(2,4,'我上次去這家，結果叉燒被店貓叼走了，店貓可愛，店貓誤食。','2022-12-09 21:33:31',1),(3,2,'我都拿這台接浴室蓮蓬頭，冬天超好用ㄟ~','2022-12-07 03:29:45',6),(4,1,'eclipse真的有夠難用。','2022-12-13 19:33:11',8),(5,4,'~\\\\福利蓮/~~\\\\福利蓮/~~\\\\福利蓮/~','2022-12-16 11:07:02',7),(6,2,'印刷品質挺精良的，本體是薄鐵片+磁鐵貼牆固定，梗圖、動漫電影類海報十分齊全。','2022-12-09 12:33:15',2),(7,3,'好店推推，湯頭超讚，老闆人很好。','2022-12-13 20:38:43',1),(8,5,'想戳戳看哈哈~ ','2022-12-29 01:45:21',4),(9,6,'我有水伊布就夠了^^','2022-12-30 04:34:35',3),(10,6,'乾別再業配了= =','2022-12-27 06:45:33',6),(11,3,'奇美是不是那家做小泡芙的?怎麼跑來賣家電了?','2022-12-14 14:55:23',6),(12,2,'希望動畫完美還原肥倫的臉。','2022-12-04 13:23:27',7),(13,4,'原本以為密度高會超級硬的......','2022-12-26 09:47:18',4),(14,5,'種類是很多啦，但是希望可以客製自己喜歡的圖。','2022-12-25 03:22:37',2),(15,6,'我每次coding不出來，找資料也沒用時，放一杯爪哇咖啡跟綠色乖乖到電腦上，第二天code就會自己生出來。','2022-12-22 22:03:55',8),(16,1,'小泡芙是義美好嗎......','2022-12-14 14:55:57',6),(17,3,'這部居然也動畫化了?!','2022-12-31 17:33:04',7),(18,2,'我都叫我家的貓訓練AI打摳。','2022-12-23 17:44:39',8),(19,5,'嗯嗯我也是這麼想的。','2022-12-21 19:22:14',4),(20,6,'俄羅斯的動員兵算是走向世界了嗎?','2022-12-07 14:12:04',5),(21,3,'麵條是我吃過在台北最Q彈的。','2022-12-16 09:08:05',1),(22,2,'推廣熊屍旅行團冰雕展?','2022-12-13 01:35:45',5),(23,4,'讓我想到蹭名字熱度的Javascript。','2022-12-31 18:33:12',8),(24,1,'布丁好大的一步棋，不要不信。','2022-12-10 23:11:45',5),(25,13,'除了++9其他感覺還好耶，希望有更多內容推出。','2022-12-26 02:15:17',9),(26,12,'我都讓我家老人戴apple watch，這樣他沒出事時可以跟其他老人炫耀，出事了我一定知道，還會自動打119送醫，讚啦。','2022-12-26 02:18:41',10),(27,6,'五個相撲的少年聽起來好甲...','2022-12-26 02:23:06',9),(50,24,'履帶可靠度方面，目標是讓60噸級、7對乘載輪的履帶車輛行駛1000英里後才更換','2022-12-28 12:24:43',17),(51,22,'採用金屬混合橡膠的方式，但這也比原本金屬履帶還要輕上許多，發出的噪音減少，油耗也降低。','2022-12-28 12:26:21',17),(52,24,'懸吊系統則是採用液氣壓，垂直高度可調整正負6英吋。','2022-12-28 12:01:18',17);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messageboard`
--

DROP TABLE IF EXISTS `messageboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messageboard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `topic` varchar(30) NOT NULL,
  `member_text` varchar(1000) NOT NULL,
  `user_id` int NOT NULL,
  `create_Time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userID_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messageboard`
--

LOCK TABLES `messageboard` WRITE;
/*!40000 ALTER TABLE `messageboard` DISABLE KEYS */;
INSERT INTO `messageboard` VALUES (1,'食記：台北中山北路隱家拉麵士林店的濃厚牛骨白湯拉麵','位在捷運士林站旁的隱家拉麵，是道樂體系下的一間拉麵店。我的拉麵上桌，附贈的檸檬沙瓦也送了上來。這碗拉麵的配色很漂亮，乳白色的牛骨白湯讓淋了點辣油，我點的是粗麵則是埋在豬叉燒以以及青蔥、烤過的小蕃茄以及溏心蛋下。最主要配料的牛叉燒則是掛在碗緣，因為是低溫調理的所以看起來還有點赤紅次紅的感覺。先舀一口湯送入嘴裡，入口的時候有著很重的奶油味，不過很快的一鼓辣味就冒了出來。由於湯上有灑一些花椒，再加上叉燒都有用黑胡椒調味，所以這湯的味道算是滿重的。店家有建議說中途可以喝點檸檬沙瓦解膩，不過我是把它留到吃完整碗拉麵再來用來清一清嘴裡的味道。兩種不同的叉燒中豬叉燒的印象不是很深刻，牛叉燒則是給人很有咬勁的口感。有時候會遇到一點筋，不過整體來說還是非常好入口的。我有試著把幾片牛叉燒泡到湯裡，等到拉麵吃了一半的時候，這些牛叉燒會更熟一些，這時的口感又會和原本的有很大的不同。這是一碗口味滿重的拉麵，吃完拉麵用檸檬沙瓦清一清嘴裡的味道後，我還多喝了半杯水。就算是這樣，當我走出隱家拉麵的時候，還可以感覺到嘴裡有著那濃濃牛骨白湯的味道。如果是喜歡重口味的同好，那麼這碗算是滿值得推薦的。',5,'2022-11-24 00:10:28'),(2,'Displate Review：金屬海報值得購買嗎？','Displate 由 Karol Banaszkiewicz 於 2013 年創立，最初是作為一個平台來支持在線藝術家，幫助他們印刷和銷售他們的設計。 它的功能基本上與Threadless 和 Redbubble等其他支持藝術家的網站一樣，那裡有一個藝術家社區，他們在平台上出售他們的設計並獲得銷售額的分成。儘管如此，Displate也開始設計自己的內部藝術。您會在 Displate 上找到各種各樣的金屬海報，這些海報被組織成各種收藏。無論您的愛好或興趣是什麼——無論您是遊戲、運動、汽車還是其他人——您很可能會在Displate上找到它的金屬海報。',2,'2022-11-26 22:15:55'),(3,'人家的寶可夢有槍耶 !!! (大誤)','這款名叫《PalWorld》的遊戲，有在玩 Pokemon 的玩家可能會覺得遊戲裡面滿滿的似曾相識的感覺。但是，影片看到中段會讓你下巴都掉下來，槍已經不算什麼了，火箭筒、機關槍、坦克都出場了，完全是火力展示呀。',4,'2022-11-30 19:37:02'),(4,'中子星的核心?!','為了了解中子星尚無法解釋的特性，物理學家模擬了超過100萬個狀態方程，而透過如此大量的模型計算後，法蘭克福大學物理學家對中子星的內部結構得出了一個結論，即根據質量的不同，其核心可以是非常堅硬或非常柔軟。',1,'2022-12-02 09:13:40'),(5,'你是否也準備好再次走向世界？',' 在冬天跳上火車，來一趟俄羅斯鐵路之旅，和陌生的旅人分享超市裡最貴的伏特加，交換擁抱與巧克力、陪從小被領養到美國的俄裔女孩重返家鄉⋯⋯儘管鼻子被凍得通紅，但旅程中的所見所聞，早已粉碎了行前對於俄羅斯人冷冰冰的世俗印象。',2,'2022-12-02 04:31:20'),(6,'奇美瞬熱智慧溫控飲水機','使用一般瞬熱飲水機時，是不是一直點選溫度、調整水量，設定要花好多時間真麻煩，好想要擁有快捷鍵，3秒點選完成 。奇美技術團隊聽到您的需求了，加大觸控面板、鎖定按鍵...加水及清潔等提示一眼掌控，直覺點選特定模式，免煩惱，一按水就來。',6,'2022-12-02 22:43:39'),(7,'漫畫改編TV動畫《葬送的芙莉蓮》2023年放送決定','這是勇者一行人打倒魔王之後的故事。人類族的勇者欣梅爾、矮人族的戰士艾冉、人類族的僧侶海塔、精靈族的魔法使芙莉蓮，結束長達十年的冒險旅程，作為英雄凱旋歸來，並接受國王表揚。他們剛好遇上50年一遇的流星雨，四人組相約50年後再度相聚觀看難得一見的流星雨。但在芙莉蓮和昔日的冒險夥伴依約再會欣賞流星雨的當夜，夥伴的逝世讓長壽的芙莉蓮感受到生死無常，也讓芙莉蓮開始深入探討生命的意義。',3,'2022-12-03 14:11:35'),(8,'Java的歷史','在西元1990年12月，Sun公司的 Patrick Naughton、Mike Sheridan 與 James Gosling 主持『綠色專案』，該專案目的是開發出可以在消費性數位電子產品上運行的程式語言。當時計劃中該程式語言的名字是 Oak 。Oak 名稱的由來，是因為 James Gosling 的辦公室窗外有一顆橡樹（Oak），但後來發現 Oak 名稱已經被註冊了，工程師們邊喝咖啡邊討論著新的名稱，最後改名為 Java。(爪哇咖啡)Java的Logo總是跟咖啡杯的外型脫不了關係',1,'2022-12-03 12:47:59'),(9,'更多日韓內容，都在 Disney+','Disney+ 為您帶來最新韓劇 南宮珉《千元律師》、李星民《舊案尋兇》、金來沅、孫浩俊與孔升妍《災後調查日誌》、辛叡恩、羅門與徐志焄《第三人稱復仇》等。還有全球爆紅動畫《間諜家家酒 Part2》、山崎賢人與小田切讓《遊戲之子》、感人爆笑青春喜劇《五個相撲的少年》等，精彩內容，不斷更新！',23,'2022-12-24 17:45:36'),(10,'活得久更要活得健康','人口老化的趨勢下，健康與醫療產業成了臺灣十分重要的發展項目。隨著智能終端、無線通訊網路技術、物聯網等技術廣泛應用於健康與醫療領域，智慧健康與醫療成為解決人口老化問題的利器。\r\n現在可以如何善用穿戴式裝置、物聯網技術提供資訊化的健康生活服務，讓健康管理深入日常生活中？',24,'2022-12-24 23:31:11'),(17,'美國陸軍正在研發全新60噸級的接片式複合橡膠履帶','美國陸軍正在研發全新60噸級的接片式複合橡膠履帶（Segmented Composite Rubber Track，S-CRT）以及可調整高度的18英吋外部乘載單元(External Suspension Unit )。\r\n履帶可靠度方面，目標是讓60噸級、7對乘載輪的履帶車輛行駛1000英里後才更換，而理想則是3000英里。\r\n履帶塊本身是橡膠製成，而連接端則是使用金屬，因此它並不是完全靠橡膠連接而成，而是採用金屬混合橡膠的方式，但這也比原本金屬履帶還要輕上許多，發出的噪音減少，油耗也降低。\r\n懸吊系統則是採用液氣壓，垂直高度可調整正負6英吋。',24,'2022-12-28 06:19:46');
/*!40000 ALTER TABLE `messageboard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-28 13:07:30
