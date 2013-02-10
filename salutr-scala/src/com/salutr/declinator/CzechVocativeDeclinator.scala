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

  val isMasculineGenderAnimate = true

  //
  // Přídavná jména a zájmena
  //
  val patterns = List(

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

    ("m", "-[ií]tel", "0tel"),

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
    ("m", "-[i]sta", "0sto"),
    ("m", "-[o]sta", "0sto"),
    ("m", "-předseda", "předsedo"),
    ("m", "-srdce", "srdce"),
    ("m", "-[dbvr]ce", "0če"),
    ("m", "-[jň]ev", "0eve"),
    ("m", "-[lř]ev", "0eve/0ve"),

    ("m", "-ů[lz]", "o0e"),

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
    ("m", "výtrus", "výtruse"),
    ("m", "trus", "truse"),
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
    ("m", "-[dlłmnpbtvwzs]", "0e"),
    ("m", "-sex", "sexe"),
    ("m", "-x", "xi"),
    ("m", "sek", "seku"),
    ("m", "výsek", "výseku"),
    ("m", "zásek", "záseku"),
    ("m", "průsek", "průseku"),
    ("m", "úsek", "úseku"),
    ("m", "česnek", "česneku"),
    ("m", "fulnek", "fulneku"),
    ("m", "-[cčšždnňmpbrstvz]ek", "0ku"),
    ("m", "-ch", "chu"),
    ("m", "-ph", "phe"),
    ("m", "-th", "the"),
    ("m", "-[hkq]", "0u"),
    ("m", "-e[mnz]", "0e"),
    // Mathieu
    ("m", "-eu", "eu"),

    //
    //
    // vzor muž
    //
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

    // Barklay, Vasiliy, Osprey, Leroy
    ("m", "-[aeiou]y", "0yi"),
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
  val umlautExceptions = HashMap(
    "osel" -> HashMap("prefix" -> "osl", "case4" -> "osla"),
    "čolek" -> HashMap("prefix" -> "čolk", "case4" -> "čolka"),
    "karel" -> HashMap("prefix" -> "karl", "case4" -> "karla"),
    "Karel" -> HashMap("prefix" -> "Karl", "case4" -> "Karla"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "pawel" -> HashMap("prefix" -> "pawl", "case4" -> "pawla"),
    "paweł" -> HashMap("prefix" -> "pawł", "case4" -> "pawła"),
    "šavel" -> HashMap("prefix" -> "šavl", "case4" -> "šavla"),
    "Pavel" -> HashMap("prefix" -> "Pavl", "case4" -> "Pavla"),
    "Havel" -> HashMap("prefix" -> "Havl", "case4" -> "Havla"),
    "havel" -> HashMap("prefix" -> "havl", "case4" -> "havla"),
    "Bořek" -> HashMap("prefix" -> "Bořk", "case4" -> "Bořka"),
    "bořek" -> HashMap("prefix" -> "bořk", "case4" -> "bořka"),
    "Luděk" -> HashMap("prefix" -> "Luďk", "case4" -> "Luďka"),
    "luděk" -> HashMap("prefix" -> "luďk", "case4" -> "luďka"),
    "pes" -> HashMap("prefix" -> "ps", "case4" -> "psa"),
    "pytel" -> HashMap("prefix" -> "pytl", "case4" -> "pytel"),
    "ocet" -> HashMap("prefix" -> "oct", "case4" -> "octa"),
    "chléb" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "chleba" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "kel" -> HashMap("prefix" -> "kl", "case4" -> "kel"),
    "sopel" -> HashMap("prefix" -> "sopl", "case4" -> "sopel"),
    "posel" -> HashMap("prefix" -> "posl", "case4" -> "posla"),
    "důl" -> HashMap("prefix" -> "dol", "case4" -> "důl"),
    //"sůl" -> HashMap( "prefix" -> "sole", "case4" -> "sůl" ),
    "vůl" -> HashMap("prefix" -> "vol", "case4" -> "vola"),
    "půl" -> HashMap("prefix" -> "půle", "case4" -> "půli"),
    "hůl" -> HashMap("prefix" -> "hole", "case4" -> "hůl"),
    "sůl" -> HashMap("prefix" -> "soli", "case4" -> "sůl"),
    "stůl" -> HashMap("prefix" -> "stol", "case4" -> "stůl"),
    "líh" -> HashMap("prefix" -> "lih", "case4" -> "líh"),
    "sníh" -> HashMap("prefix" -> "sněh", "case4" -> "sníh"),
    "zář" -> HashMap("prefix" -> "záře", "case4" -> "zář"),
    "svatozář" -> HashMap("prefix" -> "svatozáře", "case4" -> "svatozář"),
    "kůň" -> HashMap("prefix" -> "koň", "case4" -> "koně"),
    "tůň" -> HashMap("prefix" -> "tůňe", "case4" -> "tůň"),
    // --- !
    "říjen" -> HashMap("prefix" -> "říjn", "case4" -> "říjen"),
    "duben" -> HashMap("prefix" -> "dubn", "case4" -> "duben"),
    "len" -> HashMap("prefix" -> "ln", "case4" -> "len"),
    "smrt" -> HashMap("prefix" -> "smrť", "case4" -> "smrt"),
    "vítr" -> HashMap("prefix" -> "větr", "case4" -> "vítr"),
    "stupeň" -> HashMap("prefix" -> "stupň", "case4" -> "stupeň"),
    "peň" -> HashMap("prefix" -> "pň", "case4" -> "peň"),
    "cyklus" -> HashMap("prefix" -> "cykl", "case4" -> "cyklus"),
    "dvůr" -> HashMap("prefix" -> "dvor", "case4" -> "dvůr"),
    "zeď" -> HashMap("prefix" -> "zď", "case4" -> "zeď"),
    "účet" -> HashMap("prefix" -> "účt", "case4" -> "účet"),
    "mráz" -> HashMap("prefix" -> "mraz", "case4" -> "mráz"),
    "hnůj" -> HashMap("prefix" -> "hnoj", "case4" -> "hnůj"),
    "lůj" -> HashMap("prefix" -> "loj", "case4" -> "lůj"),
    "skrýš" -> HashMap("prefix" -> "skrýše", "case4" -> "skrýš"),
    "nehet" -> HashMap("prefix" -> "neht", "case4" -> "nehet"),
    "veš" -> HashMap("prefix" -> "vš", "case4" -> "veš"),
    "déšť" -> HashMap("prefix" -> "dešť", "case4" -> "déšť"),
    "vězeň" -> HashMap("prefix" -> "vězň", "case4" -> "vězňe"),
    "stěžeň" -> HashMap("prefix" -> "stěžň", "case4" -> "stěžeň"),
    "pán" -> HashMap("prefix" -> "pan", "case4" -> "pána"),
    "nero" -> HashMap("prefix" -> "neron", "case4" -> "nerona"),
    "cicero" -> HashMap("prefix" -> "ciceron", "case4" -> "cicerona"),
    "artemis" -> HashMap("prefix" -> "artemida", "case4" -> "artemidu"),
    "pallas" -> HashMap("prefix" -> "pallada", "case4" -> "pallado"),
    "paris" -> HashMap("prefix" -> "parid", "case4" -> "parida"),
    "eric" -> HashMap("prefix" -> "erik", "case4" -> "erika"),
    "marc" -> HashMap("prefix" -> "mark", "case4" -> "marka"),
    "dominic" -> HashMap("prefix" -> "dominik", "case4" -> "dominika"),
    "luc" -> HashMap("prefix" -> "luk", "case4" -> "luca")
  )

  // TODO: use hash sets

  // overriding gender to masculine
  var masculineWords = List(
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
  var feminineWords = List(
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
  var neutralWords = List(
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

  // moreExceptions - nedořešené výjimky
  var moreExceptions = List(
    "ester",
    "dagmar",
    "housle",
    "šle",
    "ovoce",
    // "obec",
    "humus",
    "muka",
    "miriam"
  // Je Nikola ženské nebo mužské jméno??? (podobně Sáva, Sláva, Saša),
  )

  // deviations - různé odchylky ve skloňování
  // - časem by bylo vhodné opravit
  var deviations = List("obec")

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
    if (pattern.charAt(patternIndex) == '-') {
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

    var lowerCaseWord = word.toLowerCase()

    if (moreExceptions.find(indexOfSuffixByPattern(_, lowerCaseWord)._1 >= 0).isDefined) {
      return HashMap(
        "message" -> "Toto slovo zatím neumíme správně vyskloňovat.",
        "vocative" -> word
      )
    }

    var message = ""
    var gender = patterns(patternIndex)._1
    var vocative = declineToVocative(patternIndex, word)

    if (deviations.find(indexOfSuffixByPattern(_, lowerCaseWord)._1 >= 0).isDefined) {
      message = "Pozor, v některých pádech nemusí být skloňování tohoto slova přesné."
    }

    HashMap(
      "vocative" -> vocative,
      "gender" -> gender,
      "message" -> message
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
    var wordForDeclining = word

    // if the word is in umlaut exceptions get its prefix
    // (exceptions for the forth case)
    val umlautException = umlautExceptions.get(word)
    if (umlautException.isDefined) {
      wordForDeclining = umlautException.get("prefix")
    }

    wordForDeclining = palatalize(wordForDeclining)
    var lowerCaseWord = word.toLowerCase()

    var gender = if (preferredGender != null) preferredGender else "0"

    // gender overriding
    if (masculineWords.indexOf(lowerCaseWord) >= 0) {
      gender = "m"
    } else if (feminineWords.indexOf(lowerCaseWord) >= 0) {
      gender = "ž"
    } else if (neutralWords.indexOf(lowerCaseWord) >= 0) {
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