
function isDebugModeEnabled() {
	return false;
}

var patterns = [];
//
// Přídavná jména a zájmena
//
patterns.push([ "m", "-ký", "ký"])
patterns.push([ "m", "-rý", "rý"])
patterns.push([ "m", "-chý", "chý"])
patterns.push([ "m", "-hý", "hý"])
patterns.push([ "m", "-ý", "ý"])
patterns.push([ "m", "-[aeěií]cí", "0cí"])
patterns.push([ "ž", "-[aeěií]cí", "0cí"])
patterns.push([ "s", "-[aeěií]cí", "0cí"])
patterns.push([ "m", "-žel", "želi"])
patterns.push([ "m", "-[bcčdhklmnprsštvzž]ní", "0ní"])
patterns.push([ "ž", "-[bcčdhklmnprsštvzž]ní", "0ní"])
patterns.push([ "s", "-[bcčdhklmnprsštvzž]ní", "0ní"])

patterns.push([ "m", "-[i]tel", "0tel"])
patterns.push([ "m", "-[í]tel", "0tel"])

patterns.push([ "s", "-é", "é"])
patterns.push([ "ž", "-á", "á"])

patterns.push([ "m", "-ů", "ů"])
patterns.push([ "ž", "-ů", "ů"])

// výjimky (zvl. běžná slova])
patterns.push([ "m", "-bůh", "bože"])
patterns.push([ "m", "-pan", "pane"])
patterns.push([ "m", "-vztek", "vzteku"])
patterns.push([ "m", "-dotek", "doteku"])
patterns.push([ "ž", "-hra", "hro"])

//
// Spec. přídady skloňování(+předseda, srdce jako úplná výjimka)
//
patterns.push([ "m", "-[i]sta", "0sto"])
patterns.push([ "m", "-[o]sta", "0sto"])
patterns.push([ "m", "-předseda", "předsedo"])
patterns.push([ "m", "-srdce", "srdce"])
patterns.push([ "m", "-[dbvr]ce", "0če"])
patterns.push([ "m", "-[jň]ev", "0eve"])
patterns.push([ "m", "-[lř]ev", "0eve/0ve"])

patterns.push([ "m", "-ů[lz]", "o0e"])

// výj. nůž (vzor muž)
patterns.push([ "m", "nůž", "noži"])

//
// vzor kolo
//
patterns.push([ "s", "-[bcčdghksštvzž]lo", "0lo"])
patterns.push([ "s", "-[bcčdnsštvzž]ko", "0ko"])
patterns.push([ "s", "-[bcčdksštvzž]no", "0no"])
patterns.push([ "s", "-o", "o"])

//
// vzor stavení
//
patterns.push([ "s", "-í", "í"])
//
// vzor děvče (če,dě,tě,ně,pě) výj.-také sele
//
patterns.push([ "s", "-[čďť][e]", "10"])
patterns.push([ "s", "-[pb][ě]", "10"])

//
// vzor žena
//
patterns.push([ "ž", "-[aeiouyáéíóúý]ka", "0ko"])
patterns.push([ "ž", "-ka", "ko"])
patterns.push([ "ž", "-[bdghkmnptvz]ra", "0ro"])
patterns.push([ "ž", "-ra", "ro"])
patterns.push([ "ž", "-[tdbnvmp]a", "0o"])
patterns.push([ "ž", "-cha", "cho"])
patterns.push([ "ž", "-[gh]a", "0o"])
patterns.push([ "ž", "-ňa", "ňo"])
patterns.push([ "ž", "-[šč]a", "0o"])
patterns.push([ "ž", "-a", "o"])

// vz. píseň
patterns.push([ "ž", "-eň", "ni"])
patterns.push([ "ž", "-oň", "oni"])
patterns.push([ "ž", "-[ě]j", "0ji"])

//
// vzor růže
//
patterns.push([ "ž", "-ev", "vi"])
patterns.push([ "ž", "-ice", "ice"])
patterns.push([ "ž", "-e", "e"])

//
// vzor píseň
//
patterns.push([ "ž", "-[eaá][jžň]", "10i"])
patterns.push([ "ž", "-[eayo][š]", "10i"])
patterns.push([ "ž", "-[íy]ň", "0ni"])
patterns.push([ "ž", "-[íyý]ňe", "0ni"])
patterns.push([ "ž", "-[ťďž]", "0i"])
patterns.push([ "ž", "-toř", "toři"])

//
// vzor kost
//
patterns.push([ "ž", "-st", "sti"])
patterns.push([ "ž", "-ves", "vsi"])
patterns.push([ "ž", "-p", "pi"])

//
//
// vzor Amadeus, Celsius, Kumulus, rektikulum, praktikum
//
patterns.push([ "m", "Zeus", "Die"])
patterns.push([ "m", "-[ei]us", "0e"])
patterns.push([ "m", "-[i]s", "0se/0si"])
patterns.push([ "m", "výtrus", "výtruse"])
patterns.push([ "m", "trus", "truse"])
patterns.push([ "m", "-[aeioumpts][lnmrktp]us", "10e"])
patterns.push([ "s", "-[ikl]um", "0um"])
patterns.push([ "s", "-io", "0"])

//
// vzor sedlák
//

patterns.push([ "m", "-[aeiouyáéíóúý]r", "0re"])
patterns.push([ "m", "-r", "ře"])
patterns.push([ "m", "-[bcčdnmprstvz]en", "0ne"])
patterns.push([ "m", "-pes", "pse"])
patterns.push([ "m", "-[ďťň]ez", "0ezi"])
patterns.push([ "m", "-[dglmnpbtvzs]", "0e"])
patterns.push([ "m", "-sex", "sexe"])
patterns.push([ "m", "-[x]", "0i"])
patterns.push([ "m", "sek", "seku"])
patterns.push([ "m", "výsek", "výseku"])
patterns.push([ "m", "zásek", "záseku"])
patterns.push([ "m", "průsek", "průseku"])
patterns.push([ "m", "úsek", "úseku"])
patterns.push([ "m", "česnek", "česneku"])
patterns.push([ "m", "fulnek", "fulneku"])
patterns.push([ "m", "-[cčšždnňmpbrstvz]ek", "0ku"])
patterns.push([ "m", "-ch", "chu"])
patterns.push([ "m", "-[hk]", "0u"])
patterns.push([ "m", "-e[mnz]", "0e"])

//
//
// vzor muž
//
patterns.push([ "m", "-ec", "če"])
patterns.push([ "m", "-kůň", "koni"])
patterns.push([ "m", "-[cčďšňřťž]", "0i"])
patterns.push([ "m", "-oj", "oji"])

// patterny pro přetypování rodu
patterns.push([ "m", "-[gh]a", "0o"])
patterns.push([ "m", "-[k]a", "0o"])
patterns.push([ "m", "-a", "o"])

patterns.push([ "ž", "-l", "li"])
patterns.push([ "ž", "-í", "í"])
patterns.push([ "ž", "-ů[jř]", "o0i"])
patterns.push([ "ž", "-[čšjř]", "0i"])

patterns.push([ "s", "-[sljřň]e", "0e"])
// patterns.push([ "ž","-cí", "cí"])
// čaj, prodej, Ondřej, žokej
patterns.push([ "m", "-j", "ji"])
// Josef, Detlef, ... ?
patterns.push([ "m", "-f", "fe"])
// zbroj, výzbroj, výstroj, trofej, neteř
// jiří, podkoní, ... ?
patterns.push([ "m", "-í", "í"])
// Hugo, Kvido
patterns.push([ "m", "-o", "o"])
// Noe
patterns.push([ "m", "-oe", "oe"])

// doplňky
// některá pomnožná jména
patterns.push([ "?", "-[tp]y", "?"])
patterns.push([ "?", "-[k]y", "?"])

// Výjimky:
// v1 - přehlásky
// : důl ... dol, stůl ... stol, nůž ... nož, hůl ... hole, půl ... půle

// 1.p náhrada 4.p.

// TODO: use a hashmap instead:
// var umlautExceptions = {
// "osel": {prefix: "osl", declined: "osla"},
// // ...
// }
var umlautExceptions = {
	"osel": { prefix: "osl", case4: "osla" },
	"čolek": { prefix: "čolk", case4: "čolka" },
	"karel": { prefix: "karl", case4: "karla" },
	"Karel": { prefix: "Karl", case4: "Karla" },
	"pavel": { prefix: "pavl", case4: "pavla" },
	"Pavel": { prefix: "Pavl", case4: "Pavla" },
	"Havel": { prefix: "Havl", case4: "Havla" },
	"havel": { prefix: "havl", case4: "havla" },
	"Bořek": { prefix: "Bořk", case4: "Bořka" },
	"bořek": { prefix: "bořk", case4: "bořka" },
	"Luděk": { prefix: "Luďk", case4: "Luďka" },
	"luděk": { prefix: "luďk", case4: "luďka" },
	"pes": { prefix: "ps", case4: "psa" },
	"pytel": { prefix: "pytl", case4: "pytel" },
	"ocet": { prefix: "oct", case4: "octa" },
	"chléb": { prefix: "chleb", case4: "chleba" },
	"chleba": { prefix: "chleb", case4: "chleba" },
	"pavel": { prefix: "pavl", case4: "pavla" },
	"kel": { prefix: "kl", case4: "kel" },
	"sopel": { prefix: "sopl", case4: "sopel" },
	"posel": { prefix: "posl", case4: "posla" },
	"důl": { prefix: "dol", case4: "důl" },
	//"sůl": { prefix: "sole", case4: "sůl" },
	"vůl": { prefix: "vol", case4: "vola" },
	"půl": { prefix: "půle", case4: "půli" },
	"hůl": { prefix: "hole", case4: "hůl" },
	"sůl": { prefix: "soli", case4: "sůl" },
	"stůl": { prefix: "stol", case4: "stůl" },
	"líh": { prefix: "lih", case4: "líh" },
	"sníh": { prefix: "sněh", case4: "sníh" },
	"zář": { prefix: "záře", case4: "zář" },
	"svatozář": { prefix: "svatozáře", case4: "svatozář" },
	"kůň": { prefix: "koň", case4: "koně" },
	"tůň": { prefix: "tůňe", case4: "tůň" },
// --- !
	"prsten": { prefix: "prsten", case4: "prsten" },
	"říjen": { prefix: "říjn", case4: "říjen" },
	"len": { prefix: "ln", case4: "len" },
	"smrt": { prefix: "smrť", case4: "smrt" },
	"vítr": { prefix: "větr", case4: "vítr" },
	"stupeň": { prefix: "stupň", case4: "stupeň" },
	"peň": { prefix: "pň", case4: "peň" },
	"cyklus": { prefix: "cykl", case4: "cyklus" },
	"dvůr": { prefix: "dvor", case4: "dvůr" },
	"zeď": { prefix: "zď", case4: "zeď" },
	"účet": { prefix: "účt", case4: "účet" },
	"mráz": { prefix: "mraz", case4: "mráz" },
	"hnůj": { prefix: "hnoj", case4: "hnůj" },
	"lůj": { prefix: "loj", case4: "lůj" },
	"skrýš": { prefix: "skrýše", case4: "skrýš" },
	"nehet": { prefix: "neht", case4: "nehet" },
	"veš": { prefix: "vš", case4: "veš" },
	"déšť": { prefix: "dešť", case4: "déšť" },
	"vězeň": { prefix: "vězň", case4: "vězňe" },
	"stěžeň": { prefix: "stěžň", case4: "stěžeň" },
 };

// v10 - zmena rodu na muzsky
var v10 = [];
v10.push("sleď")
v10.push("saša")
v10.push("Saša")
v10.push("dešť")
v10.push("koň")
v10.push("chlast")
v10.push("plast")
v10.push("termoplast")
v10.push("vězeň")
v10.push("sťežeň")
v10.push("papež")
v10.push("ďeda")
v10.push("zeť")
v10.push("háj")
v10.push("lanýž")
v10.push("sluha")
v10.push("muž")
v10.push("velmož")
v10.push("Maťej")
v10.push("maťej")
v10.push("táta")
v10.push("kolega")
v10.push("mluvka")
v10.push("strejda")
v10.push("polda")
v10.push("moula")
v10.push("šmoula")
v10.push("slouha")
v10.push("drákula")
v10.push("test")
v10.push("rest")
v10.push("trest")
v10.push("arest")
v10.push("azbest")
v10.push("ametyst")
v10.push("chřest")
v10.push("protest")
v10.push("kontest")
v10.push("motorest")
v10.push("most")
v10.push("host")
v10.push("kříž")
v10.push("stupeň")
v10.push("peň")
v10.push("čaj")
v10.push("prodej")
v10.push("výdej")
v10.push("výprodej")
v10.push("ďej")
v10.push("zloďej")
v10.push("žokej")
v10.push("hranostaj")
v10.push("dobroďej")
v10.push("darmoďej")
v10.push("čaroďej")
v10.push("koloďej")
v10.push("sprej")
v10.push("displej")
v10.push("Aleš")
v10.push("aleš")
v10.push("Ambrož")
v10.push("ambrož")
v10.push("Tomáš")
v10.push("Lukáš")
v10.push("Tobiáš")
v10.push("Jiří")
v10.push("tomáš")
v10.push("lukáš")
v10.push("tobiáš")
v10.push("jiří")
v10.push("podkoní")
v10.push("komoří")
v10.push("Jirka")
v10.push("jirka")
v10.push("Ilja")
v10.push("ilja")
v10.push("Pepa")
v10.push("Joska")
v10.push("Ondřej")
v10.push("Andrej")
v10.push("mikuláš")
v10.push("Mikuláš")
v10.push("Mikoláš")
v10.push("mikoláš")
v10.push("Kvido")
v10.push("kvido")
v10.push("Hugo")
v10.push("hugo")
v10.push("Oto")
v10.push("oto")
v10.push("Otto")
v10.push("otto")
v10.push("Alexej")
v10.push("alexej")
v10.push("Ivo")
v10.push("ivo")
v10.push("Bruno")
v10.push("bruno")
v10.push("Alois")
v10.push("alois")
v10.push("bartoloměj")
v10.push("Bartoloměj")
v10.push("správce")
v10.push("dozorce")
v10.push("Noe")
v10.push("mimoň")
v10.push("pižmoň")

// v11 - zmena rodu na zensky
var v11 = [];
v11.push("dešť")
v11.push("zteč")
v11.push("řeč")
v11.push("křeč")
v11.push("kleč")
v11.push("maštal")
v11.push("vš")
v11.push("kancelář")
v11.push("závěj")
v11.push("zvěř")
v11.push("sbeř")
v11.push("neteř")
v11.push("rozkoš")
// v11.push("myša")
v11.push("postel")
v11.push("prdel")
v11.push("koudel")
v11.push("koupel")
v11.push("ocel")
v11.push("digestoř")
v11.push("konzervatoř")
v11.push("oratoř")
v11.push("zbroj")
v11.push("výzbroj")
v11.push("výstroj")
v11.push("trofej")
v11.push("obec")
v11.push("Miriam")
v11.push("miriam")
v11.push("Ester")
v11.push("Dagmar")
v11.push("Elis")
v11.push("Maruš")
v11.push("oj")
v11.push("otep")
v11.push("step")
v11.push("sůl")

// v11.push("transmise")

// v12 - zmena rodu na stredni
var v12 = []
v12.push("nemluvně")
v12.push("slůně")
v12.push("kůzle")
v12.push("sele")
v12.push("osle")
v12.push("zvíře")
v12.push("kuře")
v12.push("tele")
v12.push("prase")
v12.push("house")
v12.push("vejce")
v12.push("moře")

// v0 - nedořešené výjimky
var v0 = []
v0.push("Ester")
v0.push("Dagmar")
v0.push("housle")
v0.push("šle")
v0.push("ovoce")
// v0.push("obec")
v0.push("humus")
v0.push("muka")
v0.push("Miriam")
v0.push("miriam")
// Je Nikola ženské nebo mužské jméno??? (podobně Sáva)

// v3 - různé odchylky ve skloňování
// - časem by bylo vhodné opravit
var v3 = [];
v3.push("král")
v3.push("prsten")
v3.push("obec")

function showMessage(text) {
	document.getElementById("message").innerHTML += text + "<br>\n";
}

// TODO: use regular expressions

//
// Fce isPattern vraci index pri shode koncovky (napr. isPattern("-lo","kolo"),
// isPattern("ko-lo","motovidlo"))
// nebo pri rovnosti slov (napr. isPattern("molo","molo").
// Jinak je navratova hodnota -1.
//
// @param placeholders Register for values to be replaced for placeholders
// defined in patterns.
// eg. "-[tp]y" -> "-0y" for matched character 't' results in placeholders[0] ==
// "t"
// Expected input value is an empty array.
function isPattern(pattern, text, placeholders) {
	text = text.toLowerCase();
	pattern = pattern.toLowerCase();
	var patternIndex = pattern.length;
	var wordIndex = text.length;

	if (patternIndex == 0 || wordIndex == 0) {
		return -1;
	}
	patternIndex--;
	wordIndex--;

	while (patternIndex >= 0 && wordIndex >= 0) {
		if (pattern.charAt(patternIndex) == "]") {
			patternIndex--;
			quit = 1;
			while (patternIndex >= 0 && pattern.charAt(patternIndex) != "[") {
				if (pattern.charAt(patternIndex) == text.charAt(wordIndex)) {
					quit = 0;
					if (placeholders) {
						placeholders.push(pattern.charAt(patternIndex));
					}
				}
				patternIndex--;
			}

			if (quit == 1) {
				return -1;
			}
		} else {
			if (pattern.charAt(patternIndex) == '-') {
				return wordIndex + 1;
			}
			if (pattern.charAt(patternIndex) != text.charAt(wordIndex)) {
				return -1;
			}
		}
		patternIndex--;
		wordIndex--;
	}
	if (patternIndex < 0 && wordIndex < 0) {
		return 0;
	}
	if (pattern.charAt(patternIndex) == '-') {
		return 0;
	}

	return -1;
}

function unpalatalize(text) {
	text = text.replace(/ďi/g, "di");
	text = text.replace(/ťi/g, "ti");
	text = text.replace(/ňi/g, "ni");
	text = text.replace(/ďe/g, "dě");
	text = text.replace(/ťe/g, "tě");
	text = text.replace(/ňe/g, "ně");
	return text;
}

function palatalize(text) {
	text = text.replace(/di/g, "ďi");
	text = text.replace(/ti/g, "ťi");
	text = text.replace(/ni/g, "ňi");
	text = text.replace(/dě/g, "ďe");
	text = text.replace(/tě/g, "ťe");
	text = text.replace(/ně/g, "ňe");
	return text;
}

/**
 * Replace numeric placeholders with their values from a register.
 * 
 * @param text
 * @param placeholders
 * @returns {String}
 */
function replacePlaceholders(text, placeholders) {
	var result = "";
	for ( var i = 0; i < text.length; i++) {
		var char = text.charAt(i);
		if (char == "0") {
			result += placeholders[0];
		} else if (char == "1") {
			result += placeholders[1];
		} else if (char == "2") {
			result += placeholders[2];
		} else {
			result += char;
		}
	}

	return result;
}

function isMasculineGenderAnimate() {
	return true;
}

/**
 * Declines a word using a declination pattern into specified case and number.
 * 
 * Global variables: patterns
 * 
 */
function declineToVocative(patternIndex, word) {
	if (patternIndex < 0 || patternIndex >= patterns.length) {
		return "???";
	}

	var word3 = palatalize(word);
	var placeholders = [];
	var suffixIndex = isPattern(patterns[patternIndex][1], word3, placeholders)
	if (suffixIndex < 0) {
		return "???";
	}

	var patternForCase = patterns[patternIndex][2];
	if (patternForCase == "?") {
		return "?";
	}

	var result = leftStr(suffixIndex, word3) + '-' + replacePlaceholders(patternForCase, placeholders);

	if (isDebugModeEnabled()) {
		// preskoceni filtrovani
		return result;
	}

	// Formatovani zivotneho sklonovani

	var hyphenIndex = result.indexOf("-");
	var slashIndex = result.indexOf("/");

	if (hyphenIndex != -1 && slashIndex != -1) {
		if (isMasculineGenderAnimate()) {
			// "text-xxx/yyy" -> "textyyy"
			result = leftStr(hyphenIndex, result) + rightStr(slashIndex + 1, result);
		} else {
			// "text-xxx/yyy" -> "text-xxx"
			result = leftStr(slashIndex, result);
		}
	}

	// vypusteni pomocnych znaku
	result = result.replace(/[\/\-]/, "");
	result = unpalatalize(result);

	return result;
}

//
// Funkce pro praci s retezci
//

// - levy retezec do indexu n (bez tohoto indexu)
function leftStr(n, text) {
	return text.substr(0, n);
}

// - pravy retezec od indexu n (vcetne)
function rightStr(n, text) {
	return text.substr(n);
}

/**
 * Declines the word using a standard suffix patern.
 * 
 * @param word
 * @param patternIndex
 *            index of a declination pattern in the 'patterns' array
 */
function declineByPattern(word, patternIndex) {
	if (patternIndex < 0 || patternIndex > patterns.length) {
		return null;
	}

	var declinationResults = {};
	
	// - seznam nedoresenych slov
	for ( var i = 0; i < v0.length; i++) {
		if (isPattern(v0[i], word) >= 0) {
			showMessage("Toto slovo zatím neumíme správně vyskloňovat.");
			declinationResults.vocative = word;
			return declinationResults;
		}
	}

	// nastaveni rodu
	declinationResults.gender = patterns[patternIndex][0];

	// vlastni sklonovani
	declinationResults.vocative = declineToVocative(patternIndex, word);

	// - seznam nepresneho sklonovani
	for ( var i = 0; i < v3.length; i++) {
		if (isPattern(v3[i], word) >= 0) {
			showMessage("Pozor, v některých pádech nemusí být skloňování tohoto slova přesné.");
			break;
		}
	}

	return declinationResults;
}

/**
 * Finds the first matching standard declination pattern.
 * 
 * In case the preferred gender is set (preferredGender), only patterns of that
 * gender are considered.
 * 
 * @param word
 * @returns {Number} index of the first matching pattern
 */
function findStandardPattern(word, preferredGender) {
	for ( var i = 0; i < patterns.length; i++) {
		if ((preferredGender == "0" || preferredGender == patterns[i][0]) && isPattern(patterns[i][1], word) >= 0) {
			break;
		}
	}

	if (i >= patterns.length) {
		return -1;
	}

	return i;
}

/**
 * Declines a single word and returns the results.
 * 
 * This is the main declination API function.
 * 
 * @param word
 * @returns declination results {gender: "", vocative: ""}
 */
function declineWord(word, preferredGender) {
	var wordForDeclining = word;
	
	// if the word is in v1 exceptions get its prefix
	// (exceptions for the forth case)
	var umlautException = umlautExceptions[word];
	if (umlautException) {
		wordForDeclining = umlautException.prefix;
	}

	wordForDeclining = palatalize(wordForDeclining);

	var gender = preferredGender;
	
	// Pretypovani rodu?
	if (v10.indexOf(wordForDeclining) >= 0) {
		gender = "m";
	} else if (v11.indexOf(wordForDeclining) >= 0) {
		gender = "ž";
	} else if (v12.indexOf(wordForDeclining) >= 0) {
		gender = "s";
	}

	// Nalezeni patternu
	var patternIndex = findStandardPattern(wordForDeclining, gender);

	showMessage(patternIndex >= 0 ? patterns[patternIndex] : "Nemáme skloňovací vzor pro toto slovo.");
	
	// Vlastni sklonovani
	var result;
	if (patternIndex >= 0) {
		result = declineByPattern(wordForDeclining, patternIndex); 
	} else {
		result = {
			vocative: wordForDeclining,
			gender: gender,
		}
	}
	return result;
}

function declineMultipleWords(inputWords, preferredGender) {
	var result = {
		wordsGender: [],
		wordsVocative: []
	};

	for ( var i = inputWords.length - 1; i >= 0; i--) {
		var inputWord = inputWords[i];

		preferredGender = "0";
		
		// vysklonovani
		var declinationResults = declineWord(inputWord, preferredGender);

		var gender = declinationResults.gender;

		// vynuceni rodu podle posledniho slova
		var isLastWord = i == inputWords.length - 1;
		if (isLastWord) {
			preferredGender = gender;
		} else if (gender.match(/^\?/) && gender.match(/^[^?]/)) {
			// pokud nenajdeme pattern tak nesklonujeme
			declinationResults.vocative = inputWord;
		}

		if (gender) {
			gender.replace(/\?+/, "?");
		}

		result.wordsGender.unshift(gender);
		result.wordsVocative.unshift(declinationResults.vocative);
	}
	return result;
}

// Je mozne "pretypovat" rod jmena, hodnota smi byt "0", "m", "ž", "s".
function getPreferredGender() {
	return "0";
}

//
// Funkce uzivatelskeho rozhrani
//
function onDecline() {
	var inputWords = document.ui.inputText.value.trim().replace(/\s+/, " ").split(" ");
	
	document.getElementById("message").innerHTML = "";

	var result = declineMultipleWords(inputWords, getPreferredGender());

	document.ui.gender.value = result.wordsGender.join(' ');
	document.ui.vocative.value = result.wordsVocative.join(' ');
}
