package hw2xu;
import java.util.Random;

/**
 * Class file to run the comparisons of Single Linked Lists and SkipLists.<br>
 * This is Part 3 of the assignment, worth 30 points.<br>
 * Please see below for example output: <br>
 * <pre>
 * {@code
*	Comparing insert:
*	SkipList insert time: 5.6464 ms
*	Number of visited nodes: 110303
*	SLL insert time: 1.6162 ms
*	Number of visited nodes: 319600
*
*	Comparing levels:
*	SkipList level 0: 5->9->10->12->18->25->26->27->39->47->50->52->53->59->61->61->72->72->75->76->76->79->83->86->88->91->94->109->112->123->125->129->133->139->139->141->148->153->155->162->169->170->171->171->175->178->179->184->184->186->188->188->190->192->194->195->198->199->201->201->207->208->209->209->211->225->226->226->227->229->230->237->237->237->238->239->240->241->244->244->245->246->246->251->254->256->257->261->261->262->264->270->272->272->277->278->280->280->280->281->286->287->288->288->288->295->295->298->298->301->303->305->305->306->309->311->314->314->315->315->316->319->325->328->334->336->341->342->344->345->347->351->351->354->357->357->361->366->366->376->379->379->382->393->394->396->397->399->403->410->418->419->419->425->426->429->435->435->438->441->445->449->450->452->457->461->462->464->464->466->469->475->477->478->479->480->480->484->487->487->491->493->494->495->497->500->501->507->509->519->521->524->526->527->529->533->534->538->539->543->543->548->548->551->553->554->554->560->564->564->569->570->570->572->575->585->585->588->591->592->596->597->600->600->600->602->605->607->615->617->623->627->631->633->638->639->641->641->641->647->648->653->657->659->659->672->674->675->676->684->692->695->697->700->704->706->706->711->711->712->717->718->730->733->733->733->736->738->742->742->745->746->751->755->756->758->758->760->771->772->773->773->774->775->775->776->777->783->786->786->789->789->792->800->801->806->808->808->809->809->815->821->821->823->823->824->826->826->826->830->838->842->845->848->851->851->852->854->857->859->861->862->862->863->863->864->866->866->876->877->882->882->885->888->890->897->904->904->908->909->910->915->919->926->932->936->937->944->944->945->947->947->957->957->963->964->967->970->972->973->974->976->979->981->987->989->990->992->994->994->996->996->997->997->998->1003->1003->1003->1007->1009->1014->1022->1022->1022->1024->1025->1026->1034->1034->1041->1042->1042->1047->1048->1051->1052->1053->1056->1057->1059->1065->1066->1071->1071->1074->1077->1088->1097->1106->1107->1108->1111->1112->1120->1120->1133->1134->1134->1135->1138->1145->1146->1148->1151->1152->1154->1155->1160->1161->1163->1167->1169->1177->1178->1180->1180->1181->1184->1185->1185->1188->1197->1199->1205->1211->1212->1212->1213->1216->1216->1222->1226->1227->1231->1231->1240->1241->1247->1247->1250->1253->1257->1263->1266->1269->1282->1286->1289->1291->1294->1297->1298->1302->1303->1303->1308->1308->1308->1309->1312->1315->1316->1316->1317->1319->1319->1324->1328->1328->1331->1332->1335->1339->1339->1341->1343->1346->1346->1349->1356->1361->1367->1368->1370->1370->1374->1374->1374->1375->1375->1375->1382->1386->1387->1389->1390->1397->1397->1399->1401->1404->1405->1408->1410->1411->1413->1416->1418->1418->1423->1428->1429->1431->1431->1432->1432->1432->1435->1437->1437->1440->1442->1442->1443->1444->1446->1446->1447->1448->1452->1454->1454->1456->1459->1464->1468->1469->1470->1471->1473->1474->1474->1477->1480->1481->1482->1485->1492->1493->1495->1502->1505->1514->1514->1516->1518->1520->1522->1523->1523->1527->1531->1532->1533->1534->1537->1539->1542->1544->1544->1546->1547->1547->1549->1553->1555->1557->1557->1558->1558->1562->1571->1572->1573->1573->1578->1578->1585->1586->1586->1587->1587->1591->1592->1593->1596->1600->1601->1604->1604->1610->1610->1611->1611->1611->1619->1620->1622->1625->1627->1630->1631->1636->1639->1639->1641->1642->1643->1644->1645->1645->1649->1653->1653->1654->1657->1661->1664->1668->1668->1668->1669->1673->1675->1676->1678->1678->1679->1679->1681->1682->1686->1690->1691->1694->1695->1695->1699->1703->1704->1705->1709->1712->1712->1714->1718->1719->1722->1722->1733->1733->1738->1740->1742->1743->1743->1747->1747->1747->1748->1750->1751->1751->1753->1758->1759->1759->1760->1761->1763->1764->1766->1772->1775->1777->1777->1778->1780->1781->1785->1788->1789->1793->1794->1794->1796->1798->1799->1800->1801->1809->1811->1811->1812->1815->1815->1816->1816->1825->1827->1828->1829->1830->1836->1837->1842->1842->1845->1848->1849->1850->1855->1856->1859->1860->1865->1871->1872->1874->1875->1876->1883->1889->1890->1899->1901->1904->1904->1905->1906->1911->1911->1912->1914->1914->1916->1916->1917->1920->1926->1930->1931->1936->1938->1940->1942->1946->1948->1948->1949->1951->1953->1956->1957->1963->1965->1967->1969->1970->1974->1979->1980->1982->1985->1991->1991->1991->1991->1992->1995->
*	SLL: 1535->366->1493->634->620->1848->1160->58->1387->1768->1668->945->1892->1334->578->1558->57->1756->732->1520->213->1223->1351->1885->690->1258->113->704->437->383->368->1813->772->1035->1354->65->1783->1021->1507->219->92->739->1040->720->213->1416->650->1304->684->350->1438->419->310->484->514->1039->741->1172->1618->1482->1400->1532->1608->1966->445->390->506->1357->407->1942->1341->1850->682->1296->1208->1986->131->1380->611->1909->1349->1479->686->1221->142->1599->318->1626->424->267->1922->1407->1563->1068->468->1852->1184->659->507->1025->1312->1705->300->1564->1818->1861->1089->216->1940->141->1826->1698->1561->253->1072->1320->721->1597->177->97->558->274->1669->595->1266->1363->231->261->862->1190->255->753->486->55->164->101->1872->676->1935->1351->91->1160->1082->995->1932->962->247->1634->1363->961->574->88->1100->1438->62->1466->1265->1110->949->667->1549->1459->411->162->1788->902->1944->1113->543->1027->1349->1539->1627->1864->1968->699->1601->1659->937->198->920->528->473->1013->1973->1940->579->969->1725->185->1107->786->49->1901->834->886->408->233->140->491->491->1332->1560->305->826->178->518->447->1724->652->664->1304->1155->1469->807->345->1774->646->886->1027->1144->1006->1283->1807->1662->890->1690->979->189->1925->676->1247->1977->892->924->1780->1291->457->2->1954->834->1465->1113->92->426->92->571->1787->986->777->1516->1723->7->1387->1179->1830->1078->828->1349->1803->1247->1149->1780->1883->1165->159->389->1488->1426->1473->103->1370->1559->70->1663->679->800->898->281->1821->1646->1009->985->1303->1311->580->399->635->608->1097->776->1492->514->1781->1249->1473->1786->1249->727->361->1363->1807->298->89->1326->1399->1152->1035->73->914->981->1908->350->1602->592->795->1570->108->1->1249->478->1299->359->65->922->706->414->1190->117->502->1893->1715->209->1638->1390->227->1612->256->1806->1518->985->657->1796->260->61->1040->1207->137->1541->1335->1943->1661->1172->287->1462->1463->459->1064->32->429->609->818->1396->531->162->650->423->685->1172->1972->350->1683->1924->594->793->1360->671->1566->208->598->1645->1690->453->627->1143->913->888->1720->681->505->393->1835->947->1688->1144->619->1769->1984->217->1627->1691->1353->1748->1214->479->473->152->365->953->1586->751->807->876->1624->1997->1510->1259->1373->658->170->872->1339->1135->1524->1294->1822->949->1873->1930->188->873->1777->484->529->707->675->588->859->534->543->671->142->730->1535->296->5->965->1275->1219->519->1400->1839->1069->274->1733->203->23->1590->1567->1624->345->736->1546->1090->1140->289->769->1078->1840->587->694->1353->324->968->1828->466->1187->470->512->677->115->1171->942->173->1898->1217->400->661->337->182->985->1620->1178->1794->304->115->1806->670->912->91->607->206->1468->1104->460->722->1664->1700->499->1743->1714->329->720->341->1687->839->1412->860->1203->166->1025->1984->600->1705->1042->134->847->839->7->778->453->1783->381->1911->892->359->1212->1745->1459->73->1659->323->16->427->286->512->1081->1265->1199->24->1346->1810->699->153->752->987->787->463->844->243->978->1770->292->1581->1006->1818->1691->275->1864->186->1376->1510->318->1846->1373->815->0->778->1910->80->1023->488->673->412->1217->1877->277->895->674->1393->410->1915->1364->1874->1856->65->55->1804->12->168->1235->1567->1965->653->178->940->61->728->955->677->1347->54->1449->1937->629->904->10->232->1882->1720->1235->337->1741->371->81->1638->510->807->1098->144->1955->586->1634->565->1516->1124->1866->330->532->1915->898->710->1715->1657->863->278->1793->92->1112->1158->948->809->1514->930->1018->30->652->153->1250->1859->476->1595->981->1148->200->223->1058->1406->1289->1028->103->859->1722->1330->1276->1980->1558->1835->873->778->1215->1843->279->1192->875->681->500->1137->1065->449->400->1289->1906->992->1589->131->298->1139->1379->1007->5->1308->521->1325->918->123->1456->995->211->1621->1217->1382->1972->1635->1447->440->1963->905->671->207->1581->1745->1116->366->750->643->1175->1394->411->324->739->1195->1169->297->830->1285->1883->1366->1156->946->1640->311->1246->1110->1067->601->680->1486->988->534->1519->957->876->284->1833->677->502->1113->31->238->384->389->853->1175->673->220->1355->1590->1020->1879->1724->589->1211->1173->1347->1659->599->1563->1693->1452->1763->1861->1385->815->1897->1032->1468->1010->1260->1334->706->251->1757->1468->863->1077->1992->1627->275->1733->
*
*	Comparing find:
*	SkipList find time: 0.4765 ms
*	Number of visited nodes: 15094
*	SLL find time: 2.8422 ms
*	Number of visited nodes: 515291
*
 *	Comparing Delete:
 *	SkipList delete time: 0.7423 ms
 *	Number of visited nodes: 15491
 *	SLL delete time: 0.3048 ms
 *	Number of visited nodes: 6117
 *
 *	Entire skiplist:
 *	10: 123->
 *	9: 123->1416->
 *	8: 123->570->1416->1544->
 *	7: 109->123->570->957->1416->1544->
 *	6: 109->123->306->570->957->1416->1505->1544->1753->
 *	5: 109->123->306->457->543->570->711->789->957->1154->1416->1459->1505->1544->1592->1753->1759->1938->
 *	4: 109->123->306->457->507->543->570->639->657->711->771->789->808->809->957->1154->1416->1459->1505->1544->1592->1678->1681->1751->1753->1759->1811->1837->1938->1957->1969->1979->1982->1991->
 *	3: 26->109->123->270->306->311->334->366->429->457->507->543->570->597->600->639->657->711->733->771->774->789->808->809->821->838->842->888->890->915->957->1066->1154->1160->1181->1185->1269->1312->1319->1416->1429->1459->1464->1471->1482->1505->1544->1547->1586->1592->1604->1625->1649->1669->1678->1679->1681->1682->1751->1753->1759->1785->1811->1837->1874->1938->1957->1969->1979->1982->1991->
 *	2: 26->79->83->94->109->123->125->153->201->226->237->257->261->261->270->280->280->306->309->311->334->344->351->366->426->429->457->478->487->494->507->509->543->570->570->597->600->600->605->615->627->638->639->641->657->706->711->733->733->771->772->774->775->777->789->808->808->809->821->823->826->838->842->848->862->863->882->888->890->915->947->957->957->1066->1108->1112->1134->1154->1160->1181->1185->1185->1212->1212->1269->1312->1316->1317->1319->1335->1356->1367->1416->1429->1459->1464->1471->1482->1495->1505->1523->1527->1544->1547->1549->1586->1592->1604->1625->1649->1669->1678->1679->1681->1682->1738->1750->1751->1753->1758->1759->1761->1766->1785->1811->1816->1837->1874->1876->1906->1938->1957->1969->1979->1982->1991->1991->
 *	1: 5->26->27->52->53->59->79->83->94->109->112->123->125->139->153->169->186->190->198->201->226->229->237->237->238->239->240->241->254->257->261->261->264->270->272->280->280->287->295->306->309->311->319->328->334->344->351->361->366->376->394->397->399->410->419->426->429->435->450->457->464->478->480->487->493->494->501->507->509->543->551->564->564->569->570->570->575->596->597->600->600->602->605->615->623->627->633->638->639->641->641->648->657->674->704->706->711->733->733->742->771->772->773->773->774->775->777->789->800->801->808->808->809->821->823->826->826->826->838->842->848->851->859->862->863->863->866->882->888->890->904->904->908->915->926->947->947->957->957->973->996->997->997->1003->1009->1022->1022->1024->1041->1066->1106->1108->1112->1120->1134->1151->1154->1160->1180->1181->1184->1185->1185->1212->1212->1216->1226->1231->1240->1269->1294->1312->1316->1317->1319->1319->1324->1335->1343->1356->1367->1404->1416->1418->1429->1431->1437->1437->1442->1443->1447->1454->1459->1464->1471->1474->1477->1482->1495->1505->1518->1523->1527->1531->1544->1544->1546->1547->1547->1549->1553->1585->1586->1592->1596->1604->1610->1611->1619->1625->1630->1649->1653->1669->1678->1679->1681->1682->1691->1695->1705->1738->1743->1750->1751->1753->1758->1759->1760->1761->1766->1780->1781->1785->1811->1816->1828->1829->1837->1842->1850->1874->1876->1899->1905->1906->1914->1930->1931->1938->1949->1957->1965->1969->1979->1982->1991->1991->
 *	0: 5->9->18->26->27->39->50->52->53->59->61->61->72->75->76->79->83->91->94->109->112->123->125->139->139->148->153->169->170->171->175->179->184->186->188->190->192->194->198->199->201->201->209->225->226->226->229->237->237->237->238->239->240->241->244->246->251->254->256->257->261->261->264->270->272->272->277->278->280->280->280->281->287->288->295->298->301->306->309->311->314->314->315->316->319->325->328->334->336->344->351->351->357->357->361->366->366->376->379->393->394->396->397->399->410->418->419->426->429->435->438->441->449->450->457->462->464->469->478->479->480->480->487->487->493->494->500->501->507->509->521->527->538->539->543->543->548->551->553->554->554->560->564->564->569->570->570->575->596->597->600->600->600->602->605->607->615->623->627->631->633->638->639->641->641->641->648->653->657->659->659->672->674->684->692->704->706->706->711->711->712->717->733->733->733->736->742->756->758->771->772->773->773->774->775->775->777->786->789->789->792->800->801->808->808->809->809->821->821->823->823->824->826->826->826->838->842->848->851->851->852->857->859->861->862->862->863->863->864->866->866->876->882->882->885->888->890->904->904->908->909->910->915->926->936->937->944->945->947->947->957->957->963->964->970->973->976->981->987->990->994->996->996->997->997->998->1003->1003->1003->1009->1014->1022->1022->1022->1024->1025->1034->1034->1041->1042->1042->1047->1048->1051->1053->1059->1065->1066->1071->1088->1097->1106->1108->1111->1112->1120->1120->1134->1134->1138->1146->1148->1151->1154->1160->1163->1169->1177->1180->1180->1181->1184->1185->1185->1188->1211->1212->1212->1213->1216->1222->1226->1227->1231->1231->1240->1241->1247->1247->1257->1266->1269->1282->1286->1289->1291->1294->1297->1303->1303->1308->1308->1309->1312->1315->1316->1316->1317->1319->1319->1324->1328->1332->1335->1339->1341->1343->1346->1346->1349->1356->1361->1367->1368->1370->1370->1374->1374->1375->1375->1386->1389->1397->1397->1399->1401->1404->1405->1411->1413->1416->1418->1429->1431->1431->1432->1432->1437->1437->1442->1442->1443->1444->1446->1447->1448->1452->1454->1454->1456->1459->1464->1468->1469->1470->1471->1474->1474->1477->1480->1481->1482->1493->1495->1505->1514->1518->1520->1522->1523->1523->1527->1531->1532->1533->1534->1537->1539->1542->1544->1544->1546->1547->1547->1549->1553->1555->1557->1557->1558->1571->1572->1578->1585->1586->1586->1587->1592->1596->1600->1601->1604->1604->1610->1611->1611->1619->1620->1625->1627->1630->1631->1639->1639->1649->1653->1653->1654->1668->1668->1668->1669->1678->1678->1679->1679->1681->1682->1690->1691->1694->1695->1695->1705->1712->1714->1738->1743->1743->1747->1747->1750->1751->1751->1753->1758->1759->1759->1760->1761->1764->1766->1775->1777->1778->1780->1781->1785->1788->1789->1793->1794->1796->1799->1809->1811->1811->1812->1815->1816->1816->1828->1829->1830->1836->1837->1842->1842->1845->1848->1850->1859->1860->1872->1874->1876->1883->1889->1890->1899->1904->1905->1906->1911->1911->1912->1914->1914->1917->1926->1930->1931->1938->1940->1948->1949->1953->1957->1963->1965->1967->1969->1979->1980->1982->1985->1991->1991->1991->1991->1992->1995->
 *
* }
 *</pre>
 * @author Max Shi
 *
 */
public class ListComparison {
	
	public static void main(String[] args) {
		int NUMBER_NODES = 800;
		int interval = 2000;
		Random r = new Random();
		SkipList skipList = new SkipList();
		SingleLinkedList SLL = new SingleLinkedList();
		
		//compare insert
		System.out.println("\nComparing insert:");
		long startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			skipList.insert(r.nextInt(interval));
		}
		long endTime = System.nanoTime();
		System.out.printf("SkipList insert time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			SLL.insert(r.nextInt(interval));
		}
		endTime = System.nanoTime();
		System.out.printf("SLL insert time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		//compare first levels
		System.out.println("\nComparing levels:");
		System.out.print("SkipList level ");
		skipList.print(0);
		System.out.print("             SLL: " );
		SLL.print();

		//compare find
		System.out.println("\nComparing find:");
		startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			skipList.find(r.nextInt(interval));
		}
		endTime = System.nanoTime();
		System.out.printf("SkipList find time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			SLL.find(r.nextInt(interval));
		}
		endTime = System.nanoTime();
		System.out.printf("SLL find time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		//compare delete		
		System.out.println("\nComparing Delete:");
		startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			skipList.delete(r.nextInt(interval));
		}
		endTime = System.nanoTime();
		System.out.printf("SkipList delete time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<NUMBER_NODES; i++) {
			SLL.delete(r.nextInt(interval));
		}
		endTime = System.nanoTime();
		System.out.printf("SLL delete time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		System.out.println("\nEntire skiplist:");
		skipList.print();
	}

}
