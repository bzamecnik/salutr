package com.salutr.declinator
import scala.collection.immutable.HashMap

/**
 * Based on a LGPL declinator written by Pavel Sedlak
 * ( http://pteryx.net/sklonovani.html).
 *
 * Refactored, fixed and ported to Scala by Bohumir Zamecnik.
 *
 * @author Bohumir Zamecnik
 * @author Pavel Sedlak
 */
class CzechVocativeDeclinator {

  class DeclinationException extends Exception

  private val isMasculineGenderAnimate = true

  private val patterns = Vector(

    // Přídavná jména a zájmena
    ("m", "-ký", "ký"),
    ("m", "-rý", "rý"),
    ("m", "-chý", "chý"),
    ("m", "-hý", "hý"),
    ("m", "-ý", "ý"),
    ("m", "-[aeěií]cí", "0cí"),
    ("ž", "-[aeěií]cí", "0cí"),
    ("s", "-[aeěií]cí", "0cí"),
    ("m", "-žel", "želi"),
    ("m", "-král", "králi"),
    ("m", "-[bcčdhklmnprsštvzž]ní", "0ní"),
    ("ž", "-[bcčdhklmnprsštvzž]ní", "0ní"),
    ("s", "-[bcčdhklmnprsštvzž]ní", "0ní"),

    ("m", "-[řšjíáyuoiea]te[ľĺl]", "1te0i"),
    ("m", "ortel", "orteli"),
    ("m", "-krtel", "krteli"),

    ("s", "-é", "é"),
    ("ž", "-á", "á"),

    ("m", "-ů", "ů"),
    ("ž", "-ů", "ů"),

    // výjimky (zvl. běžná slova),
    ("m", "-bůh", "bože"),
    ("m", "-pan", "pane"),
    ("m", "-vztek", "vzteku"),
    ("m", "-dotek", "doteku"),
    ("ž", "-hra", "hro"),

    //
    // Spec. přídady skloňování(+předseda, srdce jako úplná výjimka)
    //
    ("m", "-[io]sta", "0sto"),
    ("m", "-předseda", "předsedo"),
    ("m", "-srdce", "srdce"),
    ("m", "-[dbvr]ce", "0če"),
    ("m", "-[lřjňi]ev", "0eve"),

    ("m", "-ůz", "ůzi"),
    ("m", "-vůl", "vole"),
    ("m", "-ůl", "ůle"),

    // výj. nůž (vzor muž)
    ("m", "nůž", "noži"),

    //
    // vzor kolo
    //
    ("s", "-[bcčdghksštvzž]lo", "0lo"),
    ("s", "-[bcčdnsštvzž]ko", "0ko"),
    ("s", "-[bcčdksštvzž]no", "0no"),
    ("s", "-o", "o"),

    //
    // vzor stavení
    //
    ("s", "-í", "í"),
    //
    // vzor děvče (če,dě,tě,ně,pě) výj.-také sele
    //
    ("s", "-[čďť][e]", "10"),
    ("s", "-[pb][ě]", "10"),

    //
    // vzor žena
    //
    ("ž", "-[aeiouyáéíóúý]ka", "0ko"),
    ("ž", "-ka", "ko"),
    ("ž", "-[bdghkmnptvz]ra", "0ro"),
    ("ž", "-ra", "ro"),
    ("ž", "-[tdbnvmp]a", "0o"),
    ("ž", "-cha", "cho"),
    ("ž", "-[gh]a", "0o"),
    ("ž", "-ňa", "ňo"),
    ("ž", "-[šč]a", "0o"),
    ("ž", "-a", "o"),

    // vz. píseň
    ("ž", "-eň", "ni"),
    ("ž", "-oň", "oni"),
    ("ž", "-[ě]j", "0ji"),

    //
    // vzor růže
    //
    ("ž", "-ev", "vi"),
    ("ž", "-ice", "ice"),
    ("ž", "-e", "e"),

    //
    // vzor píseň
    //
    ("ž", "-[eaá][jžň]", "10i"),
    ("ž", "-[eayo][š]", "10i"),
    ("ž", "-[íy]ň", "0ni"),
    ("ž", "-[íyý]ňe", "0ni"),
    ("ž", "-[ťďž]", "0i"),
    ("ž", "-toř", "toři"),

    //
    // vzor kost
    //
    // most names ending at -st are masculine
    //( "ž", "-st", "sti"),
    ("ž", "-ves", "vsi"),
    ("m", "-p", "pe"),
    // TODO: how to choose gender for words like /p$/
    ("ž", "-p", "pi"),

    //
    //
    // vzor Amadeus, Celsius, Kumulus, rektikulum, praktikum
    //
    ("m", "-[ir]zeus", "0zee"),
    ("m", "Zeus", "Die"),
    ("m", "-[ei]us", "0e"),
    //( "0", "-oi[sx]", "oi0"]) // delacroix
    // Adams, Hans, Heinz
    ("m", "-[fmn][sz]", "10i"),
    // TODO: find out for which names use this greek declension
    // Archimedes -> Archimede (yes)
    // Nikolas -> Nikolasi (no)
    //( "m", "-[aeo]s", "e"),
    // Nicolas, Alois, Alojz, Azíz, Fikejz, Fikejs, Charles
    ("m", "-[aeiíjy][sz]", "10i"),
    // Tomasz
    ("m", "-sz", "szi"),
    ("m", "-výtrus", "výtruse"),
    ("m", "-petrus", "petre"),
    ("m", "-trus", "truse"),
    ("m", "-[aeioumpst][lnmrktp]us", "10e"),
    ("m", "-[acghkr][ou]s", "10si"),

    ("s", "-[ikl]um", "0um"),
    ("s", "-io", "0"),

    //
    // vzor sedlák
    //

    ("m", "-[aeiouyáéíóúý]r", "0re"),
    ("m", "-r", "ře"),
    //( "m", "-sven", "svene"),
    //( "m", "-ben", "bene"),
    //( "m", "-jelen", "jelene"),
    //( "m", "-prsten", "prstene"),
    //( "m", "-semen", "semene"),
    //( "m", "-[cčdnmprstvz]en", "0ne"),
    ("m", "-pes", "pse"),
    ("m", "-[ďťň]ez", "0ezi"),
    ("m", "-g", "gu"),
    //("m", "-os", "osi"),
    ("m", "-[dlłmnpbtvwzs]", "0e"),
    ("m", "-sex", "sexe"),
    ("m", "-x", "xi"),
    ("m", "-sek", "sku"),
    ("m", "sek", "seku"),
    ("m", "-výsek", "výseku"),
    ("m", "-zásek", "záseku"),
    ("m", "-průsek", "průseku"),
    ("m", "-úsek", "úseku"),
    ("m", "-česnek", "česneku"),
    ("m", "-fulnek", "fulneku"),
    ("m", "-[cčšždnňmpbrstvz]ek", "0ku"),
    ("m", "-ch", "chu"),
    ("m", "-ph", "phe"),
    ("m", "-th", "the"),
    // Hájek, ale ne Wanjek
    ("m", "-[aeiouyěá]je[hkq]", "1j0u"),
    ("m", "ale[hkq]", "ale0u"),
    // Bílek, ale ne Flek, Vývlek
    ("m", "-[jüúůýíěaeiouyá]le[hkq]", "1l0u"),
    ("m", "-[hkq]", "0u"),
    ("m", "-e[mnz]", "0e"),
    // Mathieu
    ("m", "-eu", "eu"),

    //
    //
    // vzor muž
    //
    // Kadlec
    ("m", "-dlec", "dleci"),
    ("m", "-švec", "ševče"),
    // Žrec
    ("m", "-[žgt]rec", "0reci"),
    // Blyznec
    ("m", "-znec", "zneci"),
    // Gondec
    ("m", "-ndec", "ndeci"),
    // Havlicec ~ Havlíček, Vincec ~ Vincek
    ("m", "-[ie]cec", "0cku"),
    // Zieleniec -> Zielenče, ne Zieleňče
    ("m", "-niec", "nče"),
    // Jedlowiec, 
    ("m", "-iec", "če"),
    ("m", "-ec", "če"),
    ("m", "-kůň", "koni"),
    ("m", "-[cčďšňřťž]", "0i"),
    ("m", "-oj", "oji"),

    // patterny pro přetypování rodu
    ("m", "-[gh]a", "0o"),
    ("m", "-[k]a", "0o"),
    ("m", "-a", "o"),

    ("ž", "-l", "li"),
    ("ž", "-í", "í"),
    ("ž", "-ů[jř]", "o0i"),
    ("ž", "-[čšjř]", "0i"),

    ("s", "-[sljřň]e", "0e"),
    // ( "ž","-cí", "cí"),
    // čaj, prodej, Ondřej, žokej
    ("m", "-j", "ji"),
    // Josef, Detlef, ... ?
    ("m", "-f", "fe"),
    // zbroj, výzbroj, výstroj, trofej, neteř
    // jiří, podkoní, ... ?
    ("m", "-í", "í"),
    // Hugo, Kvido
    ("m", "-o", "o"),
    // Noe
    ("m", "-oe", "oe"),

    // Barklay, Vasiliy, Osprey, Leroy, Nagy
    ("m", "-[aeioug]y", "0yi"),
    // pomnožná jména, Indy, Marty
    ("?", "-y", "y"),
    ("?", "-i", "i"),
    ("?", "-ú", "ú"),

    // Thu -> Thuu
    ("m", "-u", "uu"),

    // George
    ("m", "-ge", "gi"),
    // Mike
    ("m", "-ke", "ku")

  //( "ž", "-d", "do"),
  //( "ž", "-dt", "dto"),
  //( "ž", "-th", "tho")

  )

  // Výjimky:
  // v1 - přehlásky
  // : důl ... dol, stůl ... stol, nůž ... nož, hůl ... hole, půl ... půle
  // 1.p náhrada 4.p.
  private val umlautExceptionPrefixes = HashMap(
    "osel" -> "osl",
    "čolek" -> "čolk",
    "karel" -> "karl",
    "Karel" -> "Karl",
    "pavel" -> "pavl",
    "pawel" -> "pawl",
    "paweł" -> "pawł",
    "šavel" -> "šavl",
    "Pavel" -> "Pavl",
    "Havel" -> "Havl",
    "havel" -> "havl",
    "Bořek" -> "Bořk",
    "bořek" -> "bořk",
    "Luděk" -> "Luďk",
    "luděk" -> "luďk",
    "pes" -> "ps",
    "pytel" -> "pytl",
    "ocet" -> "oct",
    "chléb" -> "chleb",
    "chleba" -> "chleb",
    "pavel" -> "pavl",
    "kel" -> "kl",
    "sopel" -> "sopl",
    "posel" -> "posl",
    "důl" -> "dol",
    //"sůl" -> HashMap( "prefix" -> "sole" ,
    "vůl" -> "vol",
    "půl" -> "půle",
    "hůl" -> "hole",
    "sůl" -> "soli",
    "stůl" -> "stol",
    "líh" -> "lih",
    "sníh" -> "sněh",
    "zář" -> "záře",
    "svatozář" -> "svatozáře",
    "kůň" -> "koň",
    "tůň" -> "tůňe",
    // --- !
    "říjen" -> "říjn",
    "duben" -> "dubn",
    "len" -> "ln",
    "smrt" -> "smrť",
    "vítr" -> "větr",
    "stupeň" -> "stupň",
    "peň" -> "pň",
    "cyklus" -> "cykl",
    "dvůr" -> "dvor",
    "zeď" -> "zď",
    "účet" -> "účt",
    "mráz" -> "mraz",
    "hnůj" -> "hnoj",
    "lůj" -> "loj",
    "skrýš" -> "skrýše",
    "nehet" -> "neht",
    "veš" -> "vš",
    "déšť" -> "dešť",
    "vězeň" -> "vězň",
    "stěžeň" -> "stěžň",
    "pán" -> "pan",
    "nero" -> "neron",
    "cicero" -> "ciceron",
    "artemis" -> "artemida",
    "pallas" -> "pallada",
    "paris" -> "parid",
    "eric" -> "erik",
    "alec" -> "alek",
    "marc" -> "mark",
    "dominic" -> "dominik",
    "luc" -> "luk"
  )

  // TODO: use hash sets

  // overriding gender to masculine
  private var masculineWords = Set(
    "sleď",
    "saša",
    "dešť",
    "koň",
    //"chlast",
    //"plast",
    //"termoplast",
    "vězeň",
    "sťežeň",
    "papež",
    "ďeda",
    "zeť",
    "háj",
    "lanýž",
    "sluha",
    "muž",
    "velmož",
    "maťej",
    "maťej",
    "táta",
    "kolega",
    "mluvka",
    "strejda",
    "polda",
    "moula",
    "šmoula",
    "slouha",
    "drákula",
    //"test",
    //"rest",
    //"trest",
    //"arest",
    //"azbest",
    //"ametyst",
    //"chřest",
    //"protest",
    //"kontest",
    //"motorest",
    //"most",
    //"host",
    "kříž",
    "stupeň",
    "peň",
    "čaj",
    "prodej",
    "výdej",
    "výprodej",
    "ďej",
    "zloďej",
    "žokej",
    "hranostaj",
    "dobroďej",
    "darmoďej",
    "čaroďej",
    "koloďej",
    "sprej",
    "displej",
    "aleš",
    "ambrož",
    "mrož",
    "tomáš",
    "lukáš",
    "tobiáš",
    "jiří",
    "podkoní",
    "komoří",
    "jirka",
    "ilja",
    "pepa",
    "joska",
    "ondřej",
    "andrej",
    "metoděj",
    "mikuláš",
    "mikoláš",
    "kvido",
    "hugo",
    "oto",
    "otto",
    "alexej",
    "ivo",
    "bruno",
    "alois",
    "bartoloměj",
    "správce",
    "dozorce",
    "noe",
    "mimoň",
    "pižmoň",
    "brachyblast",
    "hlemýžď",
    "ezop",
    "ernest",
    "ernst",
    "horst",
    "george",
    "serge",
    "mike",
    "luke"
  )

  // overriding gender to feminine
  private var feminineWords = Set(
    "maruš",
    "miriam",
    "ester",
    "dagmar",
    "karin",
    "karen",
    "keren",
    "carin",
    "caren",
    "ceren",
    "ingrid",
    "sarah",
    "hannah",
    "elis",
    "elisabeth",
    "ellen",
    "elen",
    "hellen",
    "helen",
    "jenifer",
    "jennifer",
    "margit",
    "margot",
    "artemis",
    "pallas",
    "ruth",
    "rút",
    "ann",
    "ráchel",
    "rachel",
    "vivian",
    "carmen",
    "edith",
    "judith",
    "brigid",
    "adeltraud",
    "edeltraud",
    "edeltraudt",
    "ehrentraud",
    "gertraud",
    "helmtraud",
    "hermanntraud",
    "hildtraud",
    "hiltraud",
    "ingetraud",
    "inntraud",
    "irmtraud",
    "rohtraud",
    "rotraud",
    "rottraud",
    "valtraud",
    "waldtraud",
    "waltraud",
    "wiltraud",
    "edelgard",
    "edgard",
    "ehrengard",
    "elfgard",
    "ellengard",
    "elngard",
    "ermgard",
    "gard",
    "heidegard",
    "helgard",
    "hildegard",
    "imgard",
    "irmengard",
    "irmgard",
    "irmingard",
    "leodegard",
    "luitgard",
    "lutgard",
    "raingard",
    "reingard",
    "sonngard",
    "vegard",
    "abigail",
    "lynn",
    "lauren",
    "laureen",
    "mercedes",
    // TODO: the following names can be both male nad female
    "nikol",
    // the following names are male, but declined as female
    "manuel",
    "emanuel",
    "emannuel",
    "imanuel",
    "imannuel",

    "dešť",
    "zteč",
    "řeč",
    "křeč",
    "kleč",
    "maštal",
    "vš",
    "kancelář",
    "závěj",
    "zvěř",
    "sbeř",
    "neteř",
    "rozkoš",
    // "myša",
    "postel",
    "prdel",
    "koudel",
    "koupel",
    "ocel",
    "digestoř",
    "konzervatoř",
    "oratoř",
    "zbroj",
    "výzbroj",
    "výstroj",
    "trofej",
    "obec",
    "oj",
    "otep",
    "step",
    "sůl"
  // "transmise",
  )

  // overriding gender to neutral
  private var neutralWords = Set(
    "nemluvně",
    "slůně",
    "kůzle",
    "sele",
    "osle",
    "zvíře",
    "kuře",
    "tele",
    "prase",
    "house",
    "vejce",
    "moře"
  )

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
  private def indexOfSuffixByPattern(origPattern: String, origText: String): (Int, String) = {
    val text = origText.toLowerCase()
    val pattern = origPattern.toLowerCase()
    var patternIndex = pattern.length
    var wordIndex = text.length

    var placeholders = ""

    if (patternIndex == 0 || wordIndex == 0) {
      return (-1, placeholders)
    }
    patternIndex -= 1
    wordIndex -= 1

    while (patternIndex >= 0 && wordIndex >= 0) {
      if (pattern.charAt(patternIndex) == ']') {
        patternIndex -= 1
        var shouldExit = true
        while (patternIndex >= 0 && pattern.charAt(patternIndex) != '[') {
          if (pattern.charAt(patternIndex) == text.charAt(wordIndex)) {
            shouldExit = false
            placeholders += pattern.charAt(patternIndex)
          }
          patternIndex -= 1
        }

        if (shouldExit) {
          return (-2, placeholders)
        }
      } else {
        if (pattern.charAt(patternIndex) == '-') {
          return (wordIndex + 1, placeholders)
        }
        if (pattern.charAt(patternIndex) != text.charAt(wordIndex)) {
          return (-3, placeholders)
        }
      }
      patternIndex -= 1
      wordIndex -= 1
    }
    if (patternIndex < 0 && wordIndex < 0) {
      return (0, placeholders)
    }
    if (patternIndex > 0 && pattern.charAt(patternIndex) == '-') {
      return (0, placeholders)
    }

    return (-4, placeholders)
  }

  private def unpalatalize(text: String) = {
    text.replaceAll("ďi", "di")
      .replaceAll("ťi", "ti")
      .replaceAll("ňi", "ni")
      .replaceAll("ďe", "dě")
      .replaceAll("ťe", "tě")
      .replaceAll("ňe", "ně")
  }

  private def palatalize(text: String) = {
    text.replaceAll("di", "ďi")
      .replaceAll("ti", "ťi")
      .replaceAll("ni", "ňi")
      .replaceAll("dě", "ďe")
      .replaceAll("tě", "ťe")
      .replaceAll("ně", "ňe")
  }

  // 
  // Replace numeric placeholders with their values from a register.
  // 
  // @param text
  // @param placeholders
  // @returns {String}
  // 
  private def replacePlaceholders(text: String, placeholders: String) = {
    var replacedText = text
    // TODO
    if (placeholders.length > 0)
      replacedText = replacedText.replace('0', placeholders.charAt(0))
    if (placeholders.length > 1)
      replacedText = replacedText.replace('1', placeholders.charAt(1))
    if (placeholders.length > 2)
      replacedText = replacedText.replace('2', placeholders.charAt(2))
    replacedText
  }

  // 
  // Declines a word using a declination pattern into specified case and number.
  // 
  // Global variables: patterns
  // 
  // 
  private def declineToVocative(patternIndex: Int, word: String) = {
    if (patternIndex < 0 || patternIndex >= patterns.length) {
      throw new IllegalArgumentException("patternIndex")
    }

    val palatalizedWord = palatalize(word)
    val (suffixIndex, placeholders) = indexOfSuffixByPattern(patterns(patternIndex)._2, palatalizedWord)
    if (suffixIndex < 0) {
      throw new DeclinationException
    }

    var patternForCase = patterns(patternIndex)._3
    if (patternForCase == "?") {
      throw new DeclinationException
    }

    var result = leftStr(suffixIndex, palatalizedWord) + '-' + replacePlaceholders(patternForCase, placeholders)

    // animate declension

    var hyphenIndex = result.indexOf("-")
    var slashIndex = result.indexOf("/")

    if (hyphenIndex != -1 && slashIndex != -1) {
      if (isMasculineGenderAnimate) {
        // "text-xxx/yyy" -> "textyyy"
        result = leftStr(hyphenIndex, result) + rightStr(slashIndex + 1, result)
      } else {
        // "text-xxx/yyy" -> "text-xxx"
        result = leftStr(slashIndex, result)
      }
    }

    result = result.replaceAll("[\\/\\-]", "")
    unpalatalize(result)
  }

  //
  // Funkce pro praci s retezci
  //

  // - levy retezec do indexu n (bez tohoto indexu)
  private def leftStr(n: Int, text: String) = text.take(n)

  // - pravy retezec od indexu n (vcetne)
  private def rightStr(n: Int, text: String) = text.drop(n)

  // 
  // Declines the word using a standard suffix patern.
  // 
  // @param word
  // @param patternIndex
  //            index of a declination pattern in the 'patterns' array
  // 
  private def declineByPattern(word: String, patternIndex: Int): HashMap[String, String] = {
    if (patternIndex < 0 || patternIndex > patterns.length) {
      throw new IllegalArgumentException("patternIndex")
    }

    var gender = patterns(patternIndex)._1
    var vocative = declineToVocative(patternIndex, word)

    HashMap(
      "vocative" -> vocative,
      "gender" -> gender
    )
  }

  //
  // Finds the first matching standard declination pattern.
  // 
  // In case the preferred gender is set (preferredGender), only patterns of that
  // gender are considered.
  // 
  // @param word
  // @returns {Number} index of the first matching pattern
  // 
  private def findStandardPattern(word: String, preferredGender: String): Int = {
    for (i <- 0 until patterns.length) {
      if (((preferredGender == "0") ||
        (preferredGender == patterns(i)._1)) &&
        (indexOfSuffixByPattern(patterns(i)._2, word)._1 >= 0)) {
        return i
      }
    }
    return -1
  }

  // 
  // Declines a single word and returns the results.
  // 
  // This is the main declination API function.
  // 
  // @param word
  // @returns declination results {gender: "", vocative: ""}
  // 
  def declineWord(word: String, preferredGender: String): HashMap[String, String] = {
    // if the word is in umlaut exceptions get its prefix
    // (exceptions for the forth case)
    var wordForDeclining = umlautExceptionPrefixes.getOrElse(word.toLowerCase, word)

    wordForDeclining = palatalize(wordForDeclining)
    var lowerCaseWord = word.toLowerCase()

    var gender = if (preferredGender != null) preferredGender else "0"

    // gender overriding
    if (masculineWords.contains(lowerCaseWord)) {
      gender = "m"
    } else if (feminineWords.contains(lowerCaseWord)) {
      gender = "ž"
    } else if (neutralWords.contains(lowerCaseWord)) {
      gender = "s"
    }

    // too short words are not declined
    var patternIndex =
      if (word.length > 2)
        findStandardPattern(wordForDeclining, gender)
      else -1

    var result: HashMap[String, String] = null

    // declension itself
    if (patternIndex >= 0) {
      result = declineByPattern(wordForDeclining, patternIndex)
    } else {
      result = HashMap(
        "vocative" -> word,
        "gender" -> gender
      )
    }
    if (patternIndex >= 0) {
      result += ("pattern" -> patterns(patternIndex).toString)
    } else {
      result += ("message" -> "Nemáme skloňovací vzor pro toto slovo.")
    }
    return result
  }

}