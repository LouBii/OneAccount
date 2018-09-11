package com.loubii.account.util;

/**
 * @author luo
 * @date 2017/8/31
 */
import java.util.regex.Pattern;

/**
 * Created by mardawang on 2017/5/18.
 * <p>
 * {@"DC":@"储蓄卡",@"CC":@"信用卡",@"SCC":@"准贷记卡",@"PC":@"预付费卡"}
 */

public class BankInfoBeanPart {
    String s1 = "^(621096|621098|622150|622151|622181|622188|622199|955100|621095|620062|621285|621798|621799|621797|620529|621622|621599|621674|623218|623219)";
    String s2 = "^(62215049|62215050|62215051|62218850|62218851|62218849)";
    String s3 = "^(622812|622810|622811|628310|625919)";

    String s4 = "^(620200|620302|620402|620403|620404|620406|620407|620409|620410|620411|620412|620502|620503|620405|620408|620512|620602|620604|620607|620611|620612|620704|620706|620707|620708|620709|620710|620609|620712|620713|620714|620802|620711|620904|620905|621001|620902|621103|621105|621106|621107|621102|621203|621204|621205|621206|621207|621208|621209|621210|621302|621303|621202|621305|621306|621307|621309|621311|621313|621211|621315|621304|621402|621404|621405|621406|621407|621408|621409|621410|621502|621317|621511|621602|621603|621604|621605|621608|621609|621610|621611|621612|621613|621614|621615|621616|621617|621607|621606|621804|621807|621813|621814|621817|621901|621904|621905|621906|621907|621908|621909|621910|621911|621912|621913|621915|622002|621903|622004|622005|622006|622007|622008|622010|622011|622012|621914|622015|622016|622003|622018|622019|622020|622102|622103|622104|622105|622013|622111|622114|622017|622110|622303|622304|622305|622306|622307|622308|622309|622314|622315|622317|622302|622402|622403|622404|622313|622504|622505|622509|622513|622517|622502|622604|622605|622606|622510|622703|622715|622806|622902|622903|622706|623002|623006|623008|623011|623012|622904|623015|623100|623202|623301|623400|623500|623602|623803|623901|623014|624100|624200|624301|624402|623700|624000)";
    String s5 = "^(622200|622202|622203|622208|621225|620058|621281|900000|621558|621559|621722|621723|620086|621226|621618|620516|621227|621288|621721|900010|623062|621670|621720|621379|621240|621724|621762|621414|621375|622926|622927|622928|622929|622930|622931|621733|621732|621372|621369|621763)";
    String s6 = "^(402791|427028|427038|548259|621376|621423|621428|621434|621761|621749|621300|621378|622944|622949|621371|621730|621734|621433|621370|621764|621464|621765|621750|621377|621367|621374|621731|621781)";
    String s7 = "^(9558)";
    String s8 = "^(370246|370248|370249|370247|370267|374738|374739)";
    String s9 = "^(427010|427018|427019|427020|427029|427030|427039|438125|438126|451804|451810|451811|458071|489734|489735|489736|510529|427062|524091|427064|530970|530990|558360|524047|525498|622230|622231|622232|622233|622234|622235|622237|622239|622240|622245|622238|451804|451810|451811|458071|628288|628286|622206|526836|513685|543098|458441|622246|544210|548943|356879|356880|356881|356882|528856|625330|625331|625332|622236|524374|550213|625929|625927|625939|625987|625930|625114|622159|625021|625022|625932|622889|625900|625915|625916|622171|625931|625113|625928|625914|625986|625925|625921|625926|625942|622158|625917|625922|625934|625933|625920|625924|625017|625018|625019)";
    String s10 = "^(45806|53098|45806|53098)";
    String s11 = "^(622210|622211|622212|622213|622214|622220|622223|622225|622229|622215|622224)";
    String s12 = "^(620054|620142|620184|620030|620050|620143|620149|620124|620183|620094|620186|620148|620185)";
    String s13 = "^(620114|620187|620046)";

    String s14 = "^(622841|622824|622826|622848|620059|621282|622828|622823|621336|621619|622821|622822|622825|622827|622845|622849|623018|623206|621671|622840|622843|622844|622846|622847|620501)";
    String s15 = "^(95595|95596|95597|95598|95599)";
    String s16 = "^(103)";
    String s17 = "^(403361|404117|404118|404119|404120|404121|463758|519412|519413|520082|520083|552599|558730|514027|622836|622837|628268|625996|625998|625997|622838|625336|625826|625827|544243|548478|628269)";
    String s18 = "^(622820|622830)";

    String s19 = "^(621660|621661|621662|621663|621665|621667|621668|621669|621666|456351|601382|621256|621212|621283|620061|621725|621330|621331|621332|621333|621297|621568|621569|621672|623208|621620|621756|621757|621758|621759|621785|621786|621787|621788|621789|621790|622273|622274|622771|622772|622770|621741|621041)";
    String s20 = "^(621293|621294|621342|621343|621364|621394|621648|621248|621215|621249|621231|621638|621334|621395|623040|622348)";
    String s21 = "^(625908|625910|625909|356833|356835|409665|409666|409668|409669|409670|409671|409672|512315|512316|512411|512412|514957|409667|438088|552742|553131|514958|622760|628388|518377|622788|628313|628312|622750|622751|625145|622479|622480|622789|625140|622346|622347)";
    String s22 = "^(518378|518379|518474|518475|518476|524865|525745|525746|547766|558868|622752|622753|622755|524864|622757|622758|622759|622761|622762|622763|622756|622754|622764|622765|558869|625905|625906|625907|625333)";
    String s23 = "^(53591|49102|377677)";
    String s24 = "^(620514|620025|620026|620210|620211|620019|620035|620202|620203|620048|620515|920000)";
    String s25 = "^(620040|620531|620513|921000|620038)";

    String s26 = "^(621284|436742|589970|620060|621081|621467|621598|621621|621700|622280|622700|623211|623668)";
    String s27 = "^(421349|434061|434062|524094|526410|552245|621080|621082|621466|621488|621499|622966|622988|622382|621487|621083|621084|620107)";
    String s28 = "^(436742193|622280193)";
    String s29 = "^(553242)";
    String s30 = "^(625362|625363|628316|628317|356896|356899|356895|436718|436738|436745|436748|489592|531693|532450|532458|544887|552801|557080|558895|559051|622166|622168|622708|625964|625965|625966|628266|628366|622381|622675|622676|622677)";
    String s31 = "^(5453242|5491031|5544033)";
    String s32 = "^(622725|622728|436728|453242|491031|544033|622707|625955|625956)";
    String s33 = "^(53242|53243)";

    String s34 = "^(622261|622260|622262|621002|621069|621436|621335)";
    String s35 = "^(620013)";
    String s36 = "^(405512|601428|405512|601428|622258|622259|405512|601428)";
    String s37 = "^(49104|53783)";
    String s38 = "^(434910|458123|458124|520169|522964|552853|622250|622251|521899|622253|622656|628216|622252|955590|955591|955592|955593|628218|625028|625029)";
    String s39 = "^(622254|622255|622256|622257|622284)";
    String s40 = "^(620021|620521)";

    String s41 = "^(402658|410062|468203|512425|524011|622580|622588|622598|622609|95555|621286|621483|621485|621486|621299)(\\d{10}|\\d{11})$";
    String s42 = "^(690755)";
    String s43 = "^(690755)";
    String s44 = "^(356885|356886|356887|356888|356890|439188|439227|479228|479229|521302|356889|545620|545621|545947|545948|552534|552587|622575|622576|622577|622578|622579|545619|622581|622582|545623|628290|439225|518710|518718|628362|439226|628262|625802|625803)";
    String s45 = "^(370285|370286|370287|370289)";
    String s46 = "^(620520)";
    //民生银行
    String s47 = "^(622615|622616|622618|622622|622617|622619|415599|421393|421865|427570|427571|472067|472068|622620)";
    String s48 = "^(545392|545393|545431|545447|356859|356857|407405|421869|421870|421871|512466|356856|528948|552288|622600|622601|622602|517636|622621|628258|556610|622603|464580|464581|523952|545217|553161|356858|622623|625912|625913|625911)";
    String s49 = "^(377155|377152|377153|377158)";

    String s50 = "^(303)";
    String s51 = "^(90030)";
    String s52 = "^(620535)";
    String s53 = "^(620085|622660|622662|622663|622664|622665|622666|622667|622669|622670|622671|622672|622668|622661|622674|622673|620518|621489|621492)";
    String s54 = "^(356837|356838|486497|622657|622685|622659|622687|625978|625980|625981|625979|356839|356840|406252|406254|425862|481699|524090|543159|622161|622570|622650|622655|622658|625975|625977|628201|628202|625339|625976)";

    String s55 = "^(433670|433680|442729|442730|620082|622690|622691|622692|622696|622698|622998|622999|433671|968807|968808|968809|621771|621767|621768|621770|621772|621773|622453|622456)";
    String s56 = "^(622459)";
    String s57 = "^(376968|376969|376966)";
    String s58 = "^(400360|403391|403392|404158|404159|404171|404172|404173|404174|404157|433667|433668|433669|514906|403393|520108|433666|558916|622678|622679|622680|622688|622689|628206|556617|628209|518212|628208|356390|356391|356392|622916|622918|622919)";

    String s59 = "^(622630|622631|622632|622633|999999|621222|623020|623021|623022|623023)";
    String s60 = "^(523959|528709|539867|539868|622637|622638|628318|528708|622636|625967|625968|625969)";

    String s61 = "^(621626|623058)";
    String s62 = "^(602907|622986|622989|622298|627069|627068|627066|627067|412963|415752|415753|622535|622536|622538|622539|998800|412962|622983)";
    String s63 = "^(531659|622157|528020|622155|622156|526855|356869|356868|625360|625361|628296|435744|435745|483536|622525|622526|998801|998802)";
    String s64 = "^(620010)";
    //兴业银行
    String s65 = "^(438589)";
    String s66 = "^(90592)";
    String s67 = "^(966666|622909|438588|622908)";
    String s68 = "^(461982|486493|486494|486861|523036|451289|527414|528057|622901|622902|622922|628212|451290|524070|625084|625085|625086|625087|548738|549633|552398|625082|625083|625960|625961|625962|625963)";
    String s69 = "^(620010)";

    String s70 = "^(621050|622172|622985|622987|620522|622267|622278|622279|622468|622892|940021)";
    String s71 = "^(438600)";
    String s72 = "^(356827|356828|356830|402673|402674|486466|519498|520131|524031|548838|622148|622149|622268|356829|622300|628230|622269|625099|625953)";

    String s73 = "^(622516|622517|622518|622521|622522|622523|984301|984303|621352|621793|621795|621796|621351|621390|621792|621791)";
    String s74 = "^(84301|84336|84373|84385|84390|87000|87010|87030|87040|84380|84361|87050|84342)";
    String s75 = "^(356851|356852|404738|404739|456418|498451|515672|356850|517650|525998|622177|622277|628222|622500|628221|622176|622276|622228|625957|625958|625993|625831)";
    String s76 = "^(622520|622519)";
    String s77 = "^(620530)";

    //    String s78 = "^(622516|622517|622518|622521|622522|622523|984301|984303|621352|621793|621795|621796|621351|621390|621792|621791)";
    String s79 = "^(622568|6858001|6858009|621462)";
    String s80 = "^(9111)";
    String s81 = "^(406365|406366|428911|436768|436769|436770|487013|491032|491033|491034|491035|491036|491037|491038|436771|518364|520152|520382|541709|541710|548844|552794|493427|622555|622556|622557|622558|622559|622560|528931|558894|625072|625071|628260|628259|625805|625806|625807|625808|625809|625810)";
    String s82 = "^(685800|6858000)";

    String s83 = "^(621268|622684|622884|621453)";
    String s84 = "^(603445|622467|940016|621463)";

    String s85 = "^(622449|940051)";
    String s86 = "^(622450|628204)";
    //温州银行
    String s87 = "^(621977)";
    String s88 = "^(622868|622899|628255)";

    String s89 = "^(622877|622879|621775|623203)";
    String s90 = "^(603601|622137|622327|622340|622366)";
    String s91 = "^(628251|622651|625828)";

    String s92 = "^(621076|622173|622131|621579|622876)";
    String s93 = "^(504923|622422|622447|940076)";
    String s94 = "^(628210|622283|625902)";
    //南京银行
    String s95 = "^(621777|622305|621259)";
    String s96 = "^(622303|628242|622595|622596)";

    String s97 = "^(621279|622281|622316|940022)";
    String s98 = "^(621418)";
    String s99 = "^(625903|622778|628207|512431|520194|622282|622318)";
    String s100 = "^(625903|622778|628207|512431|520194|622282|622318)";
    //北京银行
    String s101 = "^(522001|622163|622853|628203|622851|622852)";

    String s102 = "^(620088|621068|622138|621066|621560)";
    String s103 = "^(625526|625186|628336)";

    String s104 = "^(622946)";
    String s105 = "^(622406|621442)";
    String s106 = "^(622407|621443)";
    String s107 = "^(622360|622361|625034|625096|625098)";
    //渣打银行
    String s108 = "^(622948|621740|622942|622994)";
    String s109 = "^(622482|622483|622484)";

    String s110 = "^(621062|621063)";
    String s111 = "^(625076|625077|625074|625075|622371|625091)";
    //东亚银行
    String s112 = "^(622933|622938|623031|622943|621411)";
    String s113 = "^(622372|622471|622472|622265|622266|625972|625973)";
    String s114 = "^(622365)";

    String s115 = "^(621469|621625)";
    String s116 = "^(622128|622129|623035)";
    String s117 = "^(909810|940035|621522|622439)";

    String s118 = "^(622328|940062|623038)";
    String s119 = "^(625288|625888)";

    String s120 = "^(622333|940050)";
    String s121 = "^(621439|623010)";
    String s122 = "^(622888)";

    String s123 = "^(622302)";
    String s124 = "^(622477|622509|622510|622362|621018|621518)";

    String s125 = "^(622297|621277)";
    String s126 = "^(622375|622489)";
    String s127 = "^(622293|622295|622296|622373|622451|622294|625940)";

    String s128 = "^(622871|622958|622963|622957|622861|622932|622862|621298)";
    String s129 = "^(622798|625010|622775|622785)";

    String s130 = "^(621016|621015)";
    String s131 = "^(622487|622490|622491|622492)";
    String s132 = "^(622487|622490|622491|622492|621744|621745|621746|621747)";

    String s133 = "^(623078)";
    String s134 = "^(622384|940034)";

    String s135 = "^(940015|622331)";
    String s136 = "^(6091201)";
    String s137 = "^(622426|628205)";

    String s138 = "^(621019|622309|621019)";
    String s139 = "^(6223091100|6223092900|6223093310|6223093320|6223093330|6223093370|6223093380|6223096510|6223097910)";

    String s140 = "^(621213|621289|621290|621291|621292|621042|621743)";
    String s141 = "^(623041|622351)";
    String s142 = "^(625046|625044|625058|622349|622350)";
    String s143 = "^(620208|620209|625093|625095)";
    //厦门银行
    String s144 = "^(622393|940023)";
    String s145 = "^(6886592)";
    String s146 = "^(623019|621600|)";

    String s147 = "^(622388)";
    String s148 = "^(621267|623063)";
    String s149 = "^(620043|)";

    String s150 = "^(622865|623131)";
    String s151 = "^(940012)";
    String s152 = "^(622178|622179|628358)";
    //汉口银行
    String s153 = "^(990027)";
    String s154 = "^(622325|623105|623029)";

    String s155 = "^(566666)";
    String s156 = "^(622455|940039)";
    String s157 = "^(623108|623081)";
    String s158 = "^(622466|628285)";

    String s159 = "^(603708)";
    String s160 = "^(622993|623069|623070|623172|623173)";
    String s161 = "^(622383|622385|628299)";

    String s162 = "^(622498|622499|623000|940046)";
    String s163 = "^(622921|628321)";
    //乌鲁木齐商业银行
    String s164 = "^(621751|622143|940001|621754)";
    String s165 = "^(622476|628278)";

    String s166 = "^(622486)";
    String s167 = "^(603602|623026|623086)";
    String s168 = "^(628291)";

    String s169 = "^(622152|622154|622996|622997|940027|622153|622135|621482|621532)";
    String s170 = "^(622442)";
    String s171 = "^(940053)";
    String s172 = "^(622442|623099)";

    String s173 = "^(622421)";
    String s174 = "^(940056)";
    String s175 = "^(96828)";
    //宁夏银行
    String s176 = "^(621529|622429|621417|623089|623200)";
    String s177 = "^(628214|625529|622428)";

    String s178 = "^(9896)";
    String s179 = "^(622134|940018|623016)";

    String s180 = "^(621577|622425)";
    String s181 = "^(940049)";
    String s182 = "^(622425)";

    String s183 = "^(622139|940040|628263)";
    String s184 = "^(621242|621538|621496)";

    String s185 = "^(621252|622146|940061|628239)";
    String s186 = "^(621419|623170)";

    String s187 = "^(62249802|94004602)";
    String s188 = "^(621237|623003)";
    //青海银行
    String s189 = "^(622310|940068)";
    String s190 = "^(622817|628287|625959)";
    String s191 = "^(62536601)";

    String s192 = "^(622427)";
    String s193 = "^(940069)";
    String s194 = "^(623039)";
    String s195 = "^(622321|628273)";
    String s196 = "^(625001)";

    String s197 = "^(694301)";
    String s198 = "^(940071|622368|621446)";
    String s199 = "^(625901|622898|622900|628281|628282|622806|628283)";
    String s200 = "^(620519)";

    String s201 = "^(683970|940074)";
    String s202 = "^(622370)";
    String s203 = "^(621437)";
    String s204 = "^(628319)";

    String s205 = "^(622336|621760)";
    String s206 = "^(622165)";
    String s207 = "^(622315|625950|628295)";

    String s208 = "^(621037|621097|621588|622977)";
    String s209 = "^(62321601)";
    String s210 = "^(622860)";
    String s211 = "^(622644|628333)";

    String s212 = "^(622478|940013|621495)";
    String s213 = "^(625500)";
    String s214 = "^(622611|622722|628211|625989)";

    String s215 = "^(622717)";
    String s216 = "^(628275|622565|622287)";
    //内蒙古银行
    String s217 = "^(622147|621633)";
    String s218 = "^(628252)";

    String s219 = "^(623001)";
    String s220 = "^(628227)";

    String s221 = "^(621456)";
    String s222 = "^(621562)";
    String s223 = "^(628219)";

    String s224 = "^(621037|621097|621588|622977)";
    String s225 = "^(62321601)";
    String s226 = "^(622475|622860)";
    String s227 = "^(625588)";
    String s228 = "^(622270|628368|625090|622644|628333)";

    String s229 = "^(623088)";
    String s230 = "^(622829|628301|622808|628308)";

    String s231 = "^(622127|622184|621701|621251|621589|623036)";
    String s232 = "^(628232|622802|622290)";

    String s233 = "^(622531|622329)";
    String s234 = "^(622829|628301)";

    String s235 = "^(621578|623066|622452|622324)";
    String s236 = "^(622815|622816|628226)";
    String s237 = "^(622906|628386|625519|625506)";

    String s238 = "^(621592)";
    String s239 = "^(628392)";
    //商丘市商业银行
    String s240 = "^(621748)";
    String s241 = "^(628271)";

    String s242 = "^(621366|621388)";
    String s243 = "^(628328)";

    String s244 = "^(621239|623068)";
    String s245 = "^(621653004)";
    String s246 = "^(622169|621519|621539|623090)";
    String s247 = "^(621238|620528)";
    String s248 = "^(628382|625158)";

    String s249 = "^(621004)";
    String s250 = "^(628217)";

    String s251 = "^(621416)";
    String s252 = "^(628217)";
    //德州银行
    String s253 = "^(622937)";
    String s254 = "^(628397)";
    //德州银行
    String ss254 = "^(628397)";
    //云南农村信用社
    String s255 = "^(622469|628307)";
    //柳州银行
    String s256 = "^(622292|622291|621412)";
    String s257 = "^(622880|622881)";
    String s258 = "^(62829)";

    String s259 = "^(623102)";
    String s260 = "^(628234)";

    String s261 = "^(628306)";

    String s262 = "^(622391|940072)";
    String s263 = "^(628391)";

    String s264 = "^(622967|940073)";
    String s265 = "^(628233)";
    String s266 = "^(628257)";

    String s267 = "^(621269|622275)";
    String s268 = "^(940006)";
    String s269 = "^(628305)";
    //贵阳银行
    String s270 = "^(622133|621735)";
    String s271 = "^(888)";
    String s272 = "^(628213)";

    String s273 = "^(622990|940003)";
    String s274 = "^(628261)";

    String s275 = "^(622311|940057)";
    String s276 = "^(628311)";

    String s277 = "^(622363|940048)";
    String s278 = "^(628270)";
    //    葫芦岛市商业银行
    String s279 = "^(622398|940054)";

    String s280 = "^(940055)";
    String s281 = "^(622397)";

    String s282 = "^(603367|622878)";
    String s283 = "^(622397)";

    String s284 = "^(603506)";

    String s285 = "^(622399|940043)";

    String s286 = "^(622420|940041)";

    String s287 = "^(622338)";
    String s288 = "^(940032)";

    String s289 = "^(622394|940025)";

    String s290 = "^(621245)";

    String s291 = "^(621328)";

    String s292 = "^(621651)";

    String s293 = "^(621077)";

    String s294 = "^(622409|621441)";
    String s295 = "^(622410|621440)";
    String s296 = "^(622950|622951)";
    String s297 = "^(625026|625024|622376|622378|622377|625092)";

    String s298 = "^(622359|940066)";

    String s299 = "^(622886)";

    String s300 = "^(940008|622379)\\d{13}";
    String s301 = "^(628379)";

    String s302 = "^(620011|620027|620031|620039|620103|620106|620120|620123|620125|620220|620278|620812|621006|621011|621012|621020|621023|621025|621027|621031|620132|621039|621078|621220|621003)";
    String s303 = "^(625003|625011|625012|625020|625023|625025|625027|625031|621032|625039|625078|625079|625103|625106|625006|625112|625120|625123|625125|625127|625131|625032|625139|625178|625179|625220|625320|625111|625132|625244)";

    String s304 = "^(622355|623042)";
    String s305 = "^(621043|621742)";
    String s306 = "^(622352|622353|625048|625053|625060)";
    String s307 = "^(620206|620207)";

    String s308 = "^(622547|622548|622546)";
    String s309 = "^(625198|625196|625147)";
    String s310 = "^(620072)";
    String s311 = "^(620204|620205)";

    String s312 = "^(621064|622941|622974)";
    String s313 = "^(622493)";

    String s314 = "^(621274|621324)";


    /**
     * bankName : 中国邮政储蓄银行
     * bankCode : PSBC
     * patterns : [{"reg":"^(621096|621098|622150|622151|622181|622188|622199|955100|621095|620062|621285|621798|621799|621797|620529|621622|621599|621674|623218|623219)","cardType":"DC"},{"reg":"^(62215049|62215050|62215051|62218850|62218851|62218849)","cardType":"DC"},{"reg":"^(622812|622810|622811|628310|625919)","cardType":"CC"}]
     */

    private String totalBankcode;
    private String bankName;
    private String bankCode;

    private String reg;
    private String cardType;

    //构造函数
    public BankInfoBeanPart(String totalBankcode) {
        this.totalBankcode = totalBankcode;
    }

    public String getBankName() {
        if (Pattern.matches(s1, totalBankcode) || Pattern.matches(s2, totalBankcode) || Pattern.matches(s3, totalBankcode)) {
            return "邮储银行";
        } else if (Pattern.matches(s4, totalBankcode) || Pattern.matches(s5, totalBankcode) || Pattern.matches(s6, totalBankcode) || Pattern.matches(s7, totalBankcode) || Pattern.matches(s8, totalBankcode) || Pattern.matches(s9, totalBankcode) || Pattern.matches(s10, totalBankcode) || Pattern.matches(s11, totalBankcode) || Pattern.matches(s12, totalBankcode) || Pattern.matches(s13, totalBankcode)) {
            return "工商银行";
        } else if (Pattern.matches(s14, totalBankcode) || Pattern.matches(s15, totalBankcode) || Pattern.matches(s16, totalBankcode) || Pattern.matches(s17, totalBankcode) || Pattern.matches(s18, totalBankcode)) {
            return "农业银行";
        } else if (Pattern.matches(s19, totalBankcode) || Pattern.matches(s20, totalBankcode) || Pattern.matches(s21, totalBankcode) || Pattern.matches(s22, totalBankcode) || Pattern.matches(s23, totalBankcode) || Pattern.matches(s24, totalBankcode) || Pattern.matches(s25, totalBankcode)) {
            return "中国银行";
        } else if (Pattern.matches(s26, totalBankcode) || Pattern.matches(s27, totalBankcode) || Pattern.matches(s28, totalBankcode) || Pattern.matches(s29, totalBankcode) || Pattern.matches(s30, totalBankcode) || Pattern.matches(s31, totalBankcode) || Pattern.matches(s32, totalBankcode) || Pattern.matches(s33, totalBankcode)) {
            return "建设银行";
        } else if (Pattern.matches(s34, totalBankcode) || Pattern.matches(s35, totalBankcode) || Pattern.matches(s36, totalBankcode) || Pattern.matches(s37, totalBankcode) || Pattern.matches(s38, totalBankcode) || Pattern.matches(s39, totalBankcode) || Pattern.matches(s40, totalBankcode)) {
            return "交通银行";
        } else if (Pattern.matches(s41, totalBankcode) || Pattern.matches(s42, totalBankcode) || Pattern.matches(s43, totalBankcode) || Pattern.matches(s44, totalBankcode) || Pattern.matches(s45, totalBankcode) || Pattern.matches(s46, totalBankcode)) {
            return "招商银行";
        } else if (Pattern.matches(s47, totalBankcode) || Pattern.matches(s48, totalBankcode) || Pattern.matches(s49, totalBankcode)) {
            return "民生银行";
        } else if (Pattern.matches(s50, totalBankcode) || Pattern.matches(s51, totalBankcode) || Pattern.matches(s52, totalBankcode) || Pattern.matches(s53, totalBankcode) || Pattern.matches(s54, totalBankcode)) {
            return "光大银行";
        } else if (Pattern.matches(s55, totalBankcode) || Pattern.matches(s56, totalBankcode) || Pattern.matches(s57, totalBankcode) || Pattern.matches(s58, totalBankcode)) {
            return "中信银行";
        } else if (Pattern.matches(s59, totalBankcode) || Pattern.matches(s60, totalBankcode)) {
            return "华夏银行";
        } else if (Pattern.matches(s61, totalBankcode) || Pattern.matches(s62, totalBankcode) || Pattern.matches(s63, totalBankcode) || Pattern.matches(s64, totalBankcode)) {
            return "平安银行";
        } else if (Pattern.matches(s65, totalBankcode) || Pattern.matches(s66, totalBankcode) || Pattern.matches(s67, totalBankcode) || Pattern.matches(s68, totalBankcode) || Pattern.matches(s69, totalBankcode)) {
            return "兴业银行";
        } else if (Pattern.matches(s70, totalBankcode) || Pattern.matches(s71, totalBankcode) || Pattern.matches(s72, totalBankcode)) {
            return "上海银行";
        } else if (Pattern.matches(s73, totalBankcode) || Pattern.matches(s74, totalBankcode) || Pattern.matches(s75, totalBankcode) || Pattern.matches(s76, totalBankcode) || Pattern.matches(s77, totalBankcode)) {
            return "浦发银行";
        } else if (Pattern.matches(s79, totalBankcode) || Pattern.matches(s80, totalBankcode) || Pattern.matches(s81, totalBankcode) || Pattern.matches(s82, totalBankcode)) {
            return "广发银行";
        } else if (Pattern.matches(s83, totalBankcode)) {
            return "渤海银行";
        } else if (Pattern.matches(s84, totalBankcode)) {
            return "广州银行";
        } else if (Pattern.matches(s85, totalBankcode) || Pattern.matches(s86, totalBankcode)) {
            return "金华银行";
        } else if (Pattern.matches(s87, totalBankcode) || Pattern.matches(s88, totalBankcode)) {
            return "温州银行";
        } else if (Pattern.matches(s89, totalBankcode) || Pattern.matches(s90, totalBankcode) || Pattern.matches(s91, totalBankcode)) {
            return "徽商银行";
        } else if (Pattern.matches(s92, totalBankcode) || Pattern.matches(s93, totalBankcode) || Pattern.matches(s94, totalBankcode)) {
            return "江苏银行";
        } else if (Pattern.matches(s95, totalBankcode) || Pattern.matches(s96, totalBankcode)) {
            return "南京银行";
        } else if (Pattern.matches(s97, totalBankcode) || Pattern.matches(s98, totalBankcode) || Pattern.matches(s99, totalBankcode)) {
            return "宁波银行";
        } else if (Pattern.matches(s100, totalBankcode) || Pattern.matches(s101, totalBankcode)) {
            return "北京银行";
        } else if (Pattern.matches(s102, totalBankcode) || Pattern.matches(s103, totalBankcode)) {
            return "北京农村商业银行";
        } else if (Pattern.matches(s104, totalBankcode) || Pattern.matches(s105, totalBankcode) || Pattern.matches(s106, totalBankcode) || Pattern.matches(s107, totalBankcode)) {
            return "汇丰银行";
        } else if (Pattern.matches(s108, totalBankcode) || Pattern.matches(s109, totalBankcode)) {
            return "渣打银行";
        } else if (Pattern.matches(s110, totalBankcode) || Pattern.matches(s111, totalBankcode)) {
            return "花旗银行";
        } else if (Pattern.matches(s112, totalBankcode) || Pattern.matches(s113, totalBankcode) || Pattern.matches(s114, totalBankcode)) {
            return "东亚银行";
        } else if (Pattern.matches(s115, totalBankcode)) {
            return "广东华兴银行";
        } else if (Pattern.matches(s116, totalBankcode)) {
            return "深圳农村商业银行";
        } else if (Pattern.matches(s117, totalBankcode)) {
            return "广州农村商业银行";
        } else if (Pattern.matches(s118, totalBankcode) || Pattern.matches(s119, totalBankcode)) {
            return "东莞农村商业银行";
        } else if (Pattern.matches(s120, totalBankcode) || Pattern.matches(s121, totalBankcode) || Pattern.matches(s122, totalBankcode)) {
            return "东莞市商业银行";
        } else if (Pattern.matches(s123, totalBankcode) || Pattern.matches(s124, totalBankcode)) {
            return "广东省农村信用社联合社";
        } else if (Pattern.matches(s125, totalBankcode) || Pattern.matches(s126, totalBankcode) || Pattern.matches(s127, totalBankcode)) {
            return "大新银行";
        } else if (Pattern.matches(s128, totalBankcode) || Pattern.matches(s129, totalBankcode)) {
            return "永享银行";
        } else if (Pattern.matches(s130, totalBankcode) || Pattern.matches(s131, totalBankcode) || Pattern.matches(s132, totalBankcode)) {
            return "星展银行香港有限公司";
        } else if (Pattern.matches(s133, totalBankcode) || Pattern.matches(s134, totalBankcode)) {
            return "恒丰银行";
        } else if (Pattern.matches(s136, totalBankcode) || Pattern.matches(s135, totalBankcode) | Pattern.matches(s137, totalBankcode)) {
            return "天津市商业银行";
        } else if (Pattern.matches(s138, totalBankcode) || Pattern.matches(s139, totalBankcode)) {
            return "浙商银行";
        } else if (Pattern.matches(s140, totalBankcode) || Pattern.matches(s141, totalBankcode) || Pattern.matches(s142, totalBankcode) || Pattern.matches(s143, totalBankcode)) {
            return "南洋商业银行";
        } else if (Pattern.matches(s144, totalBankcode) || Pattern.matches(s145, totalBankcode) || Pattern.matches(s146, totalBankcode)) {
            return "厦门银行";
        } else if (Pattern.matches(s147, totalBankcode) || Pattern.matches(s148, totalBankcode) || Pattern.matches(s149, totalBankcode)) {
            return "福建海峡银行";
        } else if (Pattern.matches(s150, totalBankcode) || Pattern.matches(s151, totalBankcode) || Pattern.matches(s152, totalBankcode)) {
            return "吉林银行";
        } else if (Pattern.matches(s153, totalBankcode) || Pattern.matches(s154, totalBankcode)) {
            return "汉口银行";
        } else if (Pattern.matches(s155, totalBankcode) || Pattern.matches(s156, totalBankcode) || Pattern.matches(s157, totalBankcode) || Pattern.matches(s158, totalBankcode)) {
            return "盛京银行";
        } else if (Pattern.matches(s159, totalBankcode) || Pattern.matches(s160, totalBankcode) || Pattern.matches(s161, totalBankcode)) {
            return "大连银行";
        } else if (Pattern.matches(s162, totalBankcode) || Pattern.matches(s163, totalBankcode)) {
            return "河北银行";
        } else if (Pattern.matches(s164, totalBankcode) || Pattern.matches(s165, totalBankcode)) {
            return "乌鲁木齐商业银行";
        } else if (Pattern.matches(s166, totalBankcode) || Pattern.matches(s167, totalBankcode) || Pattern.matches(s168, totalBankcode)) {
            return "绍兴银行";
        } else if (Pattern.matches(s169, totalBankcode)) {
            return "成都商业银行";
        } else if (Pattern.matches(s170, totalBankcode) || Pattern.matches(s171, totalBankcode) || Pattern.matches(s172, totalBankcode)) {
            return "抚顺银行";
        } else if (Pattern.matches(s173, totalBankcode) || Pattern.matches(s174, totalBankcode) || Pattern.matches(s175, totalBankcode)) {
            return "郑州银行";
        } else if (Pattern.matches(s176, totalBankcode) || Pattern.matches(s177, totalBankcode)) {
            return "宁夏银行";
        } else if (Pattern.matches(s178, totalBankcode) || Pattern.matches(s179, totalBankcode)) {
            return "重庆银行";
        } else if (Pattern.matches(s180, totalBankcode) || Pattern.matches(s181, totalBankcode) || Pattern.matches(s182, totalBankcode)) {
            return "哈尔滨银行";
        } else if (Pattern.matches(s183, totalBankcode) || Pattern.matches(s184, totalBankcode)) {
            return "兰州银行";
        } else if (Pattern.matches(s185, totalBankcode) || Pattern.matches(s186, totalBankcode)) {
            return "青岛银行";
        } else if (Pattern.matches(s187, totalBankcode) || Pattern.matches(s188, totalBankcode)) {
            return "秦皇岛市商业银行";
        } else if (Pattern.matches(s189, totalBankcode) || Pattern.matches(s190, totalBankcode) || Pattern.matches(s191, totalBankcode)) {
            return "青海银行";
        } else if (Pattern.matches(s192, totalBankcode) || Pattern.matches(s193, totalBankcode) || Pattern.matches(s194, totalBankcode) || Pattern.matches(s195, totalBankcode) || Pattern.matches(s196, totalBankcode)) {
            return "台州银行";
        } else if (Pattern.matches(s197, totalBankcode) || Pattern.matches(s198, totalBankcode) || Pattern.matches(s199, totalBankcode) || Pattern.matches(s200, totalBankcode)) {
            return "长沙银行";
        } else if (Pattern.matches(s201, totalBankcode) || Pattern.matches(s202, totalBankcode) || Pattern.matches(s203, totalBankcode) || Pattern.matches(s204, totalBankcode)) {
            return "泉州银行";
        } else if (Pattern.matches(s205, totalBankcode) || Pattern.matches(s206, totalBankcode) || Pattern.matches(s207, totalBankcode)) {
            return "包商银行";
        } else if (Pattern.matches(s208, totalBankcode) || Pattern.matches(s209, totalBankcode) || Pattern.matches(s210, totalBankcode) || Pattern.matches(s211, totalBankcode)) {
            return "龙江银行";
        } else if (Pattern.matches(s212, totalBankcode) || Pattern.matches(s213, totalBankcode) || Pattern.matches(s214, totalBankcode)) {
            return "上海农商银行";
        } else if (Pattern.matches(s215, totalBankcode) || Pattern.matches(s216, totalBankcode)) {
            return "浙江泰隆商业银行";
        } else if (Pattern.matches(s217, totalBankcode) || Pattern.matches(s218, totalBankcode)) {
            return "内蒙古银行";
        } else if (Pattern.matches(s219, totalBankcode) || Pattern.matches(s220, totalBankcode)) {
            return "广西北部湾银行";
        } else if (Pattern.matches(s221, totalBankcode) || Pattern.matches(s222, totalBankcode) || Pattern.matches(s223, totalBankcode)) {
            return "桂林银行";
        } else if (Pattern.matches(s224, totalBankcode) || Pattern.matches(s225, totalBankcode) || Pattern.matches(s226, totalBankcode) || Pattern.matches(s227, totalBankcode) || Pattern.matches(s228, totalBankcode)) {
            return "龙江银行";
        } else if (Pattern.matches(s229, totalBankcode) || Pattern.matches(s230, totalBankcode)) {
            return "成都农村商业银行";
        } else if (Pattern.matches(s231, totalBankcode) || Pattern.matches(s232, totalBankcode)) {
            return "福建省农村信用社联合社";
        } else if (Pattern.matches(s233, totalBankcode) || Pattern.matches(s234, totalBankcode)) {
            return "天津农村商业银行";
        } else if (Pattern.matches(s235, totalBankcode) || Pattern.matches(s236, totalBankcode)) {
            return "江苏省农村信用社联合社";
        } else if (Pattern.matches(s237, totalBankcode)) {
            return "湖南省农村信用社联合社";
        } else if (Pattern.matches(s238, totalBankcode) || Pattern.matches(s239, totalBankcode)) {
            return "江西省农村信用社联合社";
        } else if (Pattern.matches(s240, totalBankcode) || Pattern.matches(s241, totalBankcode)) {
            return "商丘市商业银行";
        } else if (Pattern.matches(s242, totalBankcode) || Pattern.matches(s243, totalBankcode)) {
            return "华融湘江银行";
        } else if (Pattern.matches(s244, totalBankcode)) {
            return "衡水市商业银行";
        } else if (Pattern.matches(s245, totalBankcode)) {
            return "重庆南川石银村镇银行";
        } else if (Pattern.matches(s246, totalBankcode)) {
            return "湖南省农村信用社联合社";
        } else if (Pattern.matches(s247, totalBankcode)) {
            return "邢台银行";
        } else if (Pattern.matches(s248, totalBankcode)) {
            return "临汾市尧都区农村信用合作联社";
        } else if (Pattern.matches(s249, totalBankcode) || Pattern.matches(s250, totalBankcode)) {
            return "东营银行";
        } else if (Pattern.matches(s251, totalBankcode) || Pattern.matches(s252, totalBankcode)) {
            return "上饶银行";
        } else if (Pattern.matches(s253, totalBankcode) || Pattern.matches(s254, totalBankcode)) {
            return "德州银行";
        } else if (Pattern.matches(ss254, totalBankcode)) {
            return "承德银行";
        } else if (Pattern.matches(s255, totalBankcode)) {
            return "云南农村信用社";
        } else if (Pattern.matches(s257, totalBankcode) || Pattern.matches(s258, totalBankcode) || Pattern.matches(s256, totalBankcode)) {
            return "柳州银行";
        } else if (Pattern.matches(s259, totalBankcode) || Pattern.matches(s260, totalBankcode)) {
            return "威海市商业银行";
        } else if (Pattern.matches(s261, totalBankcode)) {
            return "湖州银行";
        } else if (Pattern.matches(s262, totalBankcode) || Pattern.matches(s263, totalBankcode)) {
            return "潍坊银行";
        } else if (Pattern.matches(s264, totalBankcode) || Pattern.matches(s265, totalBankcode)) {
            return "赣州银行";
        } else if (Pattern.matches(s266, totalBankcode)) {
            return "日照银行";
        } else if (Pattern.matches(s267, totalBankcode) || Pattern.matches(s268, totalBankcode) || Pattern.matches(s269, totalBankcode)) {
            return "南昌银行";
        } else if (Pattern.matches(s270, totalBankcode) || Pattern.matches(s271, totalBankcode) || Pattern.matches(s272, totalBankcode)) {
            return "贵阳银行";
        } else if (Pattern.matches(s273, totalBankcode) || Pattern.matches(s274, totalBankcode)) {
            return "锦州银行";
        } else if (Pattern.matches(s275, totalBankcode) || Pattern.matches(s276, totalBankcode)) {
            return "齐商银行";
        } else if (Pattern.matches(s277, totalBankcode) || Pattern.matches(s278, totalBankcode)) {
            return "珠海华润银行";
        } else if (Pattern.matches(s279, totalBankcode)) {
            return "葫芦岛市商业银行";
        } else if (Pattern.matches(s280, totalBankcode) || Pattern.matches(s281, totalBankcode)) {
            return "宜昌市商业银行";
        } else if (Pattern.matches(s282, totalBankcode) || Pattern.matches(s283, totalBankcode)) {
            return "杭州商业银行";
        } else if (Pattern.matches(s284, totalBankcode)) {
            return "苏州市商业银行";
        } else if (Pattern.matches(s285, totalBankcode)) {
            return "辽阳银行";
        } else if (Pattern.matches(s286, totalBankcode)) {
            return "洛阳银行";
        } else if (Pattern.matches(s287, totalBankcode) || Pattern.matches(s288, totalBankcode)) {
            return "焦作市商业银行";
        } else if (Pattern.matches(s289, totalBankcode)) {
            return "镇江市商业银行";
        } else if (Pattern.matches(s290, totalBankcode)) {
            return "法国兴业银行";
        } else if (Pattern.matches(s291, totalBankcode)) {
            return "大华银行";
        } else if (Pattern.matches(s292, totalBankcode)) {
            return "企业银行";
        } else if (Pattern.matches(s293, totalBankcode)) {
            return "华侨银行";
        } else if (Pattern.matches(s294, totalBankcode) || Pattern.matches(s295, totalBankcode) || Pattern.matches(s296, totalBankcode) || Pattern.matches(s297, totalBankcode)) {
            return "恒生银行";
        } else if (Pattern.matches(s298, totalBankcode)) {
            return "临沂商业银行";
        } else if (Pattern.matches(s299, totalBankcode)) {
            return "烟台商业银行";
        } else if (Pattern.matches(s300, totalBankcode) || Pattern.matches(s301, totalBankcode)) {
            return "齐鲁银行";
        } else if (Pattern.matches(s302, totalBankcode) || Pattern.matches(s303, totalBankcode)) {
            return "BC卡公司";
        } else if (Pattern.matches(s304, totalBankcode) || Pattern.matches(s305, totalBankcode) || Pattern.matches(s306, totalBankcode) || Pattern.matches(s307, totalBankcode)) {
            return "集友银行";
        } else if (Pattern.matches(s308, totalBankcode) || Pattern.matches(s309, totalBankcode) || Pattern.matches(s310, totalBankcode) || Pattern.matches(s311, totalBankcode)) {
            return "大丰银行";
        } else if (Pattern.matches(s312, totalBankcode) || Pattern.matches(s313, totalBankcode)) {
            return "AEON信贷财务亚洲有限公司";
        } else if (Pattern.matches(s314, totalBankcode)) {
            return "澳门BDA";
        } else {
            return "";
        }
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getCardType() {

        if (Pattern.matches(s1, totalBankcode) || Pattern.matches(s2, totalBankcode) || Pattern.matches(s4, totalBankcode) || Pattern.matches(s5, totalBankcode) || Pattern.matches(s6, totalBankcode) || Pattern.matches(s7, totalBankcode)
                || Pattern.matches(s14, totalBankcode) || Pattern.matches(s15, totalBankcode) || Pattern.matches(s16, totalBankcode)
                || Pattern.matches(s19, totalBankcode) || Pattern.matches(s20, totalBankcode) || Pattern.matches(s26, totalBankcode) || Pattern.matches(s27, totalBankcode) || Pattern.matches(s28, totalBankcode)
                || Pattern.matches(s34, totalBankcode) || Pattern.matches(s35, totalBankcode) || Pattern.matches(s36, totalBankcode) || Pattern.matches(s41, totalBankcode) || Pattern.matches(s42, totalBankcode) || Pattern.matches(s43, totalBankcode)
                || Pattern.matches(s47, totalBankcode) || Pattern.matches(s50, totalBankcode) || Pattern.matches(s51, totalBankcode) || Pattern.matches(s52, totalBankcode) || Pattern.matches(s53, totalBankcode)
                || Pattern.matches(s55, totalBankcode) || Pattern.matches(s56, totalBankcode) || Pattern.matches(s59, totalBankcode) || Pattern.matches(s61, totalBankcode) || Pattern.matches(s62, totalBankcode)
                || Pattern.matches(s65, totalBankcode) || Pattern.matches(s66, totalBankcode) || Pattern.matches(s67, totalBankcode) || Pattern.matches(s70, totalBankcode) || Pattern.matches(s71, totalBankcode)
                || Pattern.matches(s73, totalBankcode) || Pattern.matches(s74, totalBankcode) || Pattern.matches(s79, totalBankcode) || Pattern.matches(s80, totalBankcode)
                || Pattern.matches(s83, totalBankcode) || Pattern.matches(s84, totalBankcode) || Pattern.matches(s85, totalBankcode) || Pattern.matches(s87, totalBankcode) || Pattern.matches(s89, totalBankcode) || Pattern.matches(s90, totalBankcode)
                || Pattern.matches(s92, totalBankcode) || Pattern.matches(s93, totalBankcode) || Pattern.matches(s95, totalBankcode) || Pattern.matches(s97, totalBankcode) || Pattern.matches(s98, totalBankcode)
                || Pattern.matches(s100, totalBankcode) || Pattern.matches(s102, totalBankcode) || Pattern.matches(s104, totalBankcode) || Pattern.matches(s105, totalBankcode) || Pattern.matches(s106, totalBankcode)
                || Pattern.matches(s108, totalBankcode) || Pattern.matches(s110, totalBankcode) || Pattern.matches(s112, totalBankcode) || Pattern.matches(s115, totalBankcode) || Pattern.matches(s116, totalBankcode) || Pattern.matches(s117, totalBankcode)
                || Pattern.matches(s118, totalBankcode) || Pattern.matches(s120, totalBankcode) || Pattern.matches(s121, totalBankcode) || Pattern.matches(s123, totalBankcode) || Pattern.matches(s124, totalBankcode)
                || Pattern.matches(s125, totalBankcode) || Pattern.matches(s126, totalBankcode) || Pattern.matches(s128, totalBankcode) || Pattern.matches(s130, totalBankcode) || Pattern.matches(s131, totalBankcode) || Pattern.matches(s132, totalBankcode)
                || Pattern.matches(s133, totalBankcode) || Pattern.matches(s134, totalBankcode) | Pattern.matches(s135, totalBankcode) | Pattern.matches(s136, totalBankcode) || Pattern.matches(s138, totalBankcode) || Pattern.matches(s139, totalBankcode)
                || Pattern.matches(s140, totalBankcode) || Pattern.matches(s141, totalBankcode) || Pattern.matches(s144, totalBankcode) || Pattern.matches(s145, totalBankcode) || Pattern.matches(s146, totalBankcode)
                || Pattern.matches(s147, totalBankcode) || Pattern.matches(s148, totalBankcode) || Pattern.matches(s150, totalBankcode) || Pattern.matches(s151, totalBankcode) || Pattern.matches(s152, totalBankcode)
                || Pattern.matches(s153, totalBankcode) || Pattern.matches(s154, totalBankcode) || Pattern.matches(s155, totalBankcode) || Pattern.matches(s156, totalBankcode) || Pattern.matches(s157, totalBankcode) || Pattern.matches(s159, totalBankcode)
                || Pattern.matches(s160, totalBankcode) || Pattern.matches(s162, totalBankcode) || Pattern.matches(s164, totalBankcode) || Pattern.matches(s166, totalBankcode) || Pattern.matches(s167, totalBankcode)
                || Pattern.matches(s169, totalBankcode) || Pattern.matches(s170, totalBankcode) || Pattern.matches(s171, totalBankcode) || Pattern.matches(s172, totalBankcode) || Pattern.matches(s173, totalBankcode) || Pattern.matches(s174, totalBankcode) || Pattern.matches(s175, totalBankcode)
                || Pattern.matches(s176, totalBankcode) || Pattern.matches(s178, totalBankcode) || Pattern.matches(s179, totalBankcode) || Pattern.matches(s180, totalBankcode) || Pattern.matches(s181, totalBankcode) || Pattern.matches(s182, totalBankcode)
                || Pattern.matches(s183, totalBankcode) || Pattern.matches(s184, totalBankcode) || Pattern.matches(s185, totalBankcode) || Pattern.matches(s186, totalBankcode) || Pattern.matches(s187, totalBankcode) || Pattern.matches(s188, totalBankcode)
                || Pattern.matches(s189, totalBankcode) || Pattern.matches(s192, totalBankcode) || Pattern.matches(s193, totalBankcode) || Pattern.matches(s194, totalBankcode) || Pattern.matches(s197, totalBankcode) || Pattern.matches(s198, totalBankcode)
                || Pattern.matches(s201, totalBankcode) || Pattern.matches(s202, totalBankcode) || Pattern.matches(s203, totalBankcode) || Pattern.matches(s205, totalBankcode) || Pattern.matches(s206, totalBankcode) || Pattern.matches(s208, totalBankcode)
                || Pattern.matches(s209, totalBankcode) || Pattern.matches(s210, totalBankcode) || Pattern.matches(s212, totalBankcode) || Pattern.matches(s217, totalBankcode) || Pattern.matches(s219, totalBankcode)
                || Pattern.matches(s221, totalBankcode) || Pattern.matches(s222, totalBankcode) || Pattern.matches(s224, totalBankcode) || Pattern.matches(s225, totalBankcode) || Pattern.matches(s226, totalBankcode) || Pattern.matches(s229, totalBankcode)
                || Pattern.matches(s231, totalBankcode) || Pattern.matches(s233, totalBankcode) || Pattern.matches(s235, totalBankcode) || Pattern.matches(s238, totalBankcode) || Pattern.matches(s240, totalBankcode) || Pattern.matches(s242, totalBankcode)
                || Pattern.matches(s244, totalBankcode) || Pattern.matches(s245, totalBankcode) || Pattern.matches(s246, totalBankcode) || Pattern.matches(s247, totalBankcode) || Pattern.matches(s249, totalBankcode) || Pattern.matches(s251, totalBankcode)
                || Pattern.matches(s253, totalBankcode) || Pattern.matches(s256, totalBankcode) || Pattern.matches(s257, totalBankcode) || Pattern.matches(s259, totalBankcode) || Pattern.matches(s262, totalBankcode) || Pattern.matches(s264, totalBankcode)
                || Pattern.matches(s267, totalBankcode) || Pattern.matches(s268, totalBankcode) || Pattern.matches(s270, totalBankcode) || Pattern.matches(s271, totalBankcode) || Pattern.matches(s273, totalBankcode) || Pattern.matches(s275, totalBankcode) || Pattern.matches(s277, totalBankcode)
                || Pattern.matches(s279, totalBankcode) || Pattern.matches(s280, totalBankcode) || Pattern.matches(s282, totalBankcode) || Pattern.matches(s284, totalBankcode) || Pattern.matches(s285, totalBankcode) || Pattern.matches(s286, totalBankcode)
                || Pattern.matches(s287, totalBankcode) || Pattern.matches(s288, totalBankcode) || Pattern.matches(s289, totalBankcode) || Pattern.matches(s290, totalBankcode) || Pattern.matches(s291, totalBankcode) || Pattern.matches(s292, totalBankcode)
                || Pattern.matches(s293, totalBankcode) || Pattern.matches(s294, totalBankcode) || Pattern.matches(s295, totalBankcode) || Pattern.matches(s296, totalBankcode) || Pattern.matches(s298, totalBankcode) || Pattern.matches(s299, totalBankcode) || Pattern.matches(s300, totalBankcode)
                || Pattern.matches(s302, totalBankcode) || Pattern.matches(s304, totalBankcode) || Pattern.matches(s305, totalBankcode) || Pattern.matches(s308, totalBankcode) || Pattern.matches(s309, totalBankcode)
                || Pattern.matches(s312, totalBankcode) || Pattern.matches(s314, totalBankcode)) {
            return "储蓄卡";
        } else if (Pattern.matches(s3, totalBankcode) || Pattern.matches(s8, totalBankcode) || Pattern.matches(s9, totalBankcode) || Pattern.matches(s10, totalBankcode) || Pattern.matches(s17, totalBankcode) || Pattern.matches(s21, totalBankcode)
                || Pattern.matches(s29, totalBankcode) || Pattern.matches(s30, totalBankcode) || Pattern.matches(s31, totalBankcode) || Pattern.matches(s37, totalBankcode) || Pattern.matches(s38, totalBankcode)
                || Pattern.matches(s44, totalBankcode) || Pattern.matches(s45, totalBankcode) || Pattern.matches(s48, totalBankcode) || Pattern.matches(s49, totalBankcode) || Pattern.matches(s54, totalBankcode)
                || Pattern.matches(s57, totalBankcode) || Pattern.matches(s58, totalBankcode) || Pattern.matches(s60, totalBankcode) || Pattern.matches(s63, totalBankcode) || Pattern.matches(s68, totalBankcode) || Pattern.matches(s72, totalBankcode)
                || Pattern.matches(s75, totalBankcode) || Pattern.matches(s81, totalBankcode) || Pattern.matches(s82, totalBankcode) || Pattern.matches(s86, totalBankcode) || Pattern.matches(s88, totalBankcode)
                || Pattern.matches(s91, totalBankcode) || Pattern.matches(s94, totalBankcode) || Pattern.matches(s96, totalBankcode) || Pattern.matches(s99, totalBankcode) || Pattern.matches(s101, totalBankcode) || Pattern.matches(s103, totalBankcode)
                || Pattern.matches(s107, totalBankcode) || Pattern.matches(s109, totalBankcode) || Pattern.matches(s111, totalBankcode) || Pattern.matches(s113, totalBankcode) || Pattern.matches(s114, totalBankcode) || Pattern.matches(s119, totalBankcode)
                || Pattern.matches(s122, totalBankcode) || Pattern.matches(s127, totalBankcode) || Pattern.matches(s129, totalBankcode) | Pattern.matches(s137, totalBankcode) || Pattern.matches(s142, totalBankcode)
                || Pattern.matches(s158, totalBankcode) || Pattern.matches(s161, totalBankcode) || Pattern.matches(s163, totalBankcode) || Pattern.matches(s165, totalBankcode) || Pattern.matches(s167, totalBankcode)
                || Pattern.matches(s177, totalBankcode) || Pattern.matches(s191, totalBankcode) || Pattern.matches(s190, totalBankcode) || Pattern.matches(s195, totalBankcode) || Pattern.matches(s199, totalBankcode)
                || Pattern.matches(s204, totalBankcode) || Pattern.matches(s207, totalBankcode) || Pattern.matches(s211, totalBankcode) || Pattern.matches(s214, totalBankcode) || Pattern.matches(s216, totalBankcode)
                || Pattern.matches(s218, totalBankcode) || Pattern.matches(s220, totalBankcode) || Pattern.matches(s223, totalBankcode) || Pattern.matches(s228, totalBankcode) || Pattern.matches(s230, totalBankcode) || Pattern.matches(s232, totalBankcode)
                || Pattern.matches(s234, totalBankcode) || Pattern.matches(s236, totalBankcode) || Pattern.matches(s237, totalBankcode) || Pattern.matches(s239, totalBankcode) || Pattern.matches(s241, totalBankcode) || Pattern.matches(s243, totalBankcode)
                || Pattern.matches(s248, totalBankcode) || Pattern.matches(s250, totalBankcode) || Pattern.matches(s252, totalBankcode) || Pattern.matches(s254, totalBankcode) || Pattern.matches(ss254, totalBankcode) || Pattern.matches(s255, totalBankcode)
                || Pattern.matches(s258, totalBankcode) || Pattern.matches(s260, totalBankcode) || Pattern.matches(s261, totalBankcode) || Pattern.matches(s263, totalBankcode) || Pattern.matches(s265, totalBankcode) || Pattern.matches(s266, totalBankcode)
                || Pattern.matches(s269, totalBankcode) || Pattern.matches(s272, totalBankcode) || Pattern.matches(s274, totalBankcode) || Pattern.matches(s276, totalBankcode) || Pattern.matches(s278, totalBankcode) || Pattern.matches(s281, totalBankcode)
                || Pattern.matches(s283, totalBankcode) || Pattern.matches(s297, totalBankcode) || Pattern.matches(s301, totalBankcode) || Pattern.matches(s303, totalBankcode) || Pattern.matches(s306, totalBankcode)
                || Pattern.matches(s313, totalBankcode)) {
            return "信用卡";
        } else if (Pattern.matches(s11, totalBankcode) || Pattern.matches(s18, totalBankcode) || Pattern.matches(s22, totalBankcode) || Pattern.matches(s23, totalBankcode) || Pattern.matches(s32, totalBankcode) || Pattern.matches(s33, totalBankcode)
                || Pattern.matches(s39, totalBankcode) || Pattern.matches(s76, totalBankcode) || Pattern.matches(s196, totalBankcode) || Pattern.matches(s213, totalBankcode) || Pattern.matches(s215, totalBankcode)
                || Pattern.matches(s227, totalBankcode)) {
            return "准贷记卡";
        } else if (Pattern.matches(s12, totalBankcode) || Pattern.matches(s13, totalBankcode) || Pattern.matches(s24, totalBankcode) || Pattern.matches(s25, totalBankcode) || Pattern.matches(s40, totalBankcode)
                || Pattern.matches(s46, totalBankcode) || Pattern.matches(s64, totalBankcode) || Pattern.matches(s69, totalBankcode) || Pattern.matches(s77, totalBankcode) || Pattern.matches(s143, totalBankcode)
                || Pattern.matches(s149, totalBankcode) || Pattern.matches(s200, totalBankcode) || Pattern.matches(s307, totalBankcode) || Pattern.matches(s310, totalBankcode) || Pattern.matches(s311, totalBankcode)) {
            return "预付费卡";
        } else {
            return "未知";
        }
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}