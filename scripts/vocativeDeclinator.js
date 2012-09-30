
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
patterns.push([ "m", "-g", "gu"])
patterns.push([ "m", "-[dlmnpbtvzs]", "0e"])
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
	"pán": { prefix: "pan", case4: "pána" },
 };

// masculineWords - zmena rodu na muzsky
var masculineWords = [];
masculineWords.push("sleď")
masculineWords.push("saša")
masculineWords.push("dešť")
masculineWords.push("koň")
masculineWords.push("chlast")
masculineWords.push("plast")
masculineWords.push("termoplast")
masculineWords.push("vězeň")
masculineWords.push("sťežeň")
masculineWords.push("papež")
masculineWords.push("ďeda")
masculineWords.push("zeť")
masculineWords.push("háj")
masculineWords.push("lanýž")
masculineWords.push("sluha")
masculineWords.push("muž")
masculineWords.push("velmož")
masculineWords.push("maťej")
masculineWords.push("maťej")
masculineWords.push("táta")
masculineWords.push("kolega")
masculineWords.push("mluvka")
masculineWords.push("strejda")
masculineWords.push("polda")
masculineWords.push("moula")
masculineWords.push("šmoula")
masculineWords.push("slouha")
masculineWords.push("drákula")
masculineWords.push("test")
masculineWords.push("rest")
masculineWords.push("trest")
masculineWords.push("arest")
masculineWords.push("azbest")
masculineWords.push("ametyst")
masculineWords.push("chřest")
masculineWords.push("protest")
masculineWords.push("kontest")
masculineWords.push("motorest")
masculineWords.push("most")
masculineWords.push("host")
masculineWords.push("kříž")
masculineWords.push("stupeň")
masculineWords.push("peň")
masculineWords.push("čaj")
masculineWords.push("prodej")
masculineWords.push("výdej")
masculineWords.push("výprodej")
masculineWords.push("ďej")
masculineWords.push("zloďej")
masculineWords.push("žokej")
masculineWords.push("hranostaj")
masculineWords.push("dobroďej")
masculineWords.push("darmoďej")
masculineWords.push("čaroďej")
masculineWords.push("koloďej")
masculineWords.push("sprej")
masculineWords.push("displej")
masculineWords.push("aleš")
masculineWords.push("ambrož")
masculineWords.push("mrož")
masculineWords.push("tomáš")
masculineWords.push("lukáš")
masculineWords.push("tobiáš")
masculineWords.push("jiří")
masculineWords.push("podkoní")
masculineWords.push("komoří")
masculineWords.push("jirka")
masculineWords.push("ilja")
masculineWords.push("pepa")
masculineWords.push("joska")
masculineWords.push("ondřej")
masculineWords.push("andrej")
masculineWords.push("metoděj")
masculineWords.push("mikuláš")
masculineWords.push("mikoláš")
masculineWords.push("kvido")
masculineWords.push("hugo")
masculineWords.push("oto")
masculineWords.push("otto")
masculineWords.push("alexej")
masculineWords.push("ivo")
masculineWords.push("bruno")
masculineWords.push("alois")
masculineWords.push("bartoloměj")
masculineWords.push("správce")
masculineWords.push("dozorce")
masculineWords.push("noe")
masculineWords.push("mimoň")
masculineWords.push("pižmoň")
masculineWords.push("brachyblast")
masculineWords.push("hlemýžď")

// feminineWords - zmena rodu na zensky
var feminineWords = [];
feminineWords.push("dešť")
feminineWords.push("zteč")
feminineWords.push("řeč")
feminineWords.push("křeč")
feminineWords.push("kleč")
feminineWords.push("maštal")
feminineWords.push("vš")
feminineWords.push("kancelář")
feminineWords.push("závěj")
feminineWords.push("zvěř")
feminineWords.push("sbeř")
feminineWords.push("neteř")
feminineWords.push("rozkoš")
// feminineWords.push("myša")
feminineWords.push("postel")
feminineWords.push("prdel")
feminineWords.push("koudel")
feminineWords.push("koupel")
feminineWords.push("ocel")
feminineWords.push("digestoř")
feminineWords.push("konzervatoř")
feminineWords.push("oratoř")
feminineWords.push("zbroj")
feminineWords.push("výzbroj")
feminineWords.push("výstroj")
feminineWords.push("trofej")
feminineWords.push("obec")
feminineWords.push("miriam")
feminineWords.push("miriam")
feminineWords.push("ester")
feminineWords.push("dagmar")
feminineWords.push("elis")
feminineWords.push("maruš")
feminineWords.push("oj")
feminineWords.push("otep")
feminineWords.push("step")
feminineWords.push("sůl")

// feminineWords.push("transmise")

// overriding gender to neutral
var neutralWords = []
neutralWords.push("nemluvně")
neutralWords.push("slůně")
neutralWords.push("kůzle")
neutralWords.push("sele")
neutralWords.push("osle")
neutralWords.push("zvíře")
neutralWords.push("kuře")
neutralWords.push("tele")
neutralWords.push("prase")
neutralWords.push("house")
neutralWords.push("vejce")
neutralWords.push("moře")

// moreExceptions - nedořešené výjimky
var moreExceptions = []
moreExceptions.push("ester")
moreExceptions.push("dagmar")
moreExceptions.push("housle")
moreExceptions.push("šle")
moreExceptions.push("ovoce")
// moreExceptions.push("obec")
moreExceptions.push("humus")
moreExceptions.push("muka")
moreExceptions.push("miriam")
// Je Nikola ženské nebo mužské jméno??? (podobně Sáva, Sláva, Saša)

// deviations - různé odchylky ve skloňování
// - časem by bylo vhodné opravit
var deviations = [];
deviations.push("král")
deviations.push("prsten")
deviations.push("obec")

function addMessage(text) {
	document.getElementById("message").innerHTML += text + "<br>\n";
}

function showMessage(text) {
	document.getElementById("message").innerHTML = text;
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
				return -2;
			}
		} else {
			if (pattern.charAt(patternIndex) == '-') {
				return wordIndex + 1;
			}
			if (pattern.charAt(patternIndex) != text.charAt(wordIndex)) {
				return -3;
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

	return -4;
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

	var palatalizedWord = palatalize(word);
	var placeholders = [];
	var suffixIndex = isPattern(patterns[patternIndex][1], palatalizedWord, placeholders)
	if (suffixIndex < 0) {
		return "???";
	}

	var patternForCase = patterns[patternIndex][2];
	if (patternForCase == "?") {
		return "?";
	}

	var result = leftStr(suffixIndex, palatalizedWord) + '-' + replacePlaceholders(patternForCase, placeholders);

	if (isDebugModeEnabled()) {
		// no animate declension
		return result;
	}

	// animate declension

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

	var result = {};
	
	var lowerCaseWord = word.toLowerCase();
	for ( var i = 0; i < moreExceptions.length; i++) {
		if (isPattern(moreExceptions[i], lowerCaseWord) >= 0) {
			result.message = "Toto slovo zatím neumíme správně vyskloňovat.";
			result.vocative = word;
			return result;
		}
	}

	result.gender = patterns[patternIndex][0];

	result.vocative = declineToVocative(patternIndex, word);

	for ( var i = 0; i < deviations.length; i++) {
		if (isPattern(deviations[i], lowerCaseWord) >= 0) {
			result.message = "Pozor, v některých pádech nemusí být skloňování tohoto slova přesné.";
			break;
		}
	}

	return result;
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
	
	// if the word is in umlaut exceptions get its prefix
	// (exceptions for the forth case)
	var umlautException = umlautExceptions[word];
	if (umlautException) {
		wordForDeclining = umlautException.prefix;
	}

	wordForDeclining = palatalize(wordForDeclining);
	var lowerCaseWord = word.toLowerCase();

	if (!preferredGender) {
		preferredGender = "0";
	}
	var gender = preferredGender;
	
	// gender overriding
	if (masculineWords.indexOf(lowerCaseWord) >= 0) {
		gender = "m";
	} else if (feminineWords.indexOf(lowerCaseWord) >= 0) {
		gender = "ž";
	} else if (neutralWords.indexOf(lowerCaseWord) >= 0) {
		gender = "s";
	}

	var patternIndex = -1;
	// too short words are not declined
	if (word.length > 2) {
		patternIndex = findStandardPattern(wordForDeclining, gender);
	}

	var result = {};

	// declension itself
	if (patternIndex >= 0) {
		result = declineByPattern(wordForDeclining, patternIndex); 
	} else {
		result = {
			vocative: word,
			gender: gender,
		}
	}
	if (patternIndex >= 0) {
		result.pattern = patterns[patternIndex];
	} else {
		result.message = "Nemáme skloňovací vzor pro toto slovo."; 
	}
	return result;
}

function declineMultipleWords(inputWords, preferredGender) {
	var result = {
		wordsGender: [],
		wordsVocative: [],
		patterns: [],
		messages: []
	};

	for ( var i in inputWords) {
		var inputWord = inputWords[i];

		preferredGender = "0";
		
		var declinationResults = declineWord(inputWord, preferredGender);

		var gender = declinationResults.gender;

		if (gender.match(/^\?/) && preferredGender.match(/^[^?]/)) {
			// no declension is done when no pattern has been found
			declinationResults.vocative = inputWord;
		}

		if (gender) {
			gender.replace(/\?+/, "?");
		}

		result.wordsGender.push(gender);
		result.wordsVocative.push(declinationResults.vocative);
		if (declinationResults.pattern) {
			result.patterns.push(declinationResults.pattern);
		}
		if (declinationResults.message) {
			result.messages.push(declinationResults.message);
		}
	}
	return result;
}

function declinePhrase(wordsText) {
	var words = wordsText.trim().replace(/\s+/, " ").split(" ");
	var result = declineMultipleWords(words, getPreferredGender());

	return {
		gender: result.wordsGender.join(' '),
		vocative: result.wordsVocative.join(' '),
		patterns: result.patterns,
		messages: result.messages
	};
}

// Je mozne "pretypovat" rod jmena, hodnota smi byt "0", "m", "ž", "s".
function getPreferredGender() {
	return "0";
}

//
// Funkce uzivatelskeho rozhrani
//
function onDecline() {
	var inputText = document.ui.inputText.value;
	
	var result = declinePhrase(inputText);
	
	document.ui.gender.value = result.gender;
	document.ui.vocative.value = result.vocative;
	
	showMessage("");
	if (result.patterns && result.patterns.length > 0) {
		for (var i in result.patterns) {
			addMessage("pattern[" + i + "]: " + result.patterns[i]);
		}
	}
	if (result.messages) {
		for (var i in result.messages) {
			addMessage("messages[" + i + "]: " + result.messages[i]);
		}
	}
}
