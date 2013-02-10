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
  // PÞ’davn‡ jmŽna a z‡jmena
  //
  val patterns = List(

    ("m", "-kù", "kù"),
    ("m", "-rù", "rù"),
    ("m", "-chù", "chù"),
    ("m", "-hù", "hù"),
    ("m", "-ù", "ù"),
    ("m", "-[aeži’]c’", "0c’"),
    ("ì", "-[aeži’]c’", "0c’"),
    ("s", "-[aeži’]c’", "0c’"),
    ("m", "-ìel", "ìeli"),
    ("m", "-kr‡l", "kr‡li"),
    ("m", "-[bc‹dhklmnprsätvzì]n’", "0n’"),
    ("ì", "-[bc‹dhklmnprsätvzì]n’", "0n’"),
    ("s", "-[bc‹dhklmnprsätvzì]n’", "0n’"),

    ("m", "-[i’]tel", "0tel"),

    ("s", "-Ž", "Ž"),
    ("ì", "-‡", "‡"),

    ("m", "-ó", "ó"),
    ("ì", "-ó", "ó"),

    // vùjimky (zvl. bžìn‡ slova),
    ("m", "-bóh", "boìe"),
    ("m", "-pan", "pane"),
    ("m", "-vztek", "vzteku"),
    ("m", "-dotek", "doteku"),
    ("ì", "-hra", "hro"),

    //
    // Spec. pÞ’dady skloËov‡n’(+pÞedseda, srdce jako œpln‡ vùjimka)
    //
    ("m", "-[i]sta", "0sto"),
    ("m", "-[o]sta", "0sto"),
    ("m", "-pÞedseda", "pÞedsedo"),
    ("m", "-srdce", "srdce"),
    ("m", "-[dbvr]ce", "0‹e"),
    ("m", "-[jË]ev", "0eve"),
    ("m", "-[lÞ]ev", "0eve/0ve"),

    ("m", "-ó[lz]", "o0e"),

    // vùj. nóì (vzor muì)
    ("m", "nóì", "noìi"),

    //
    // vzor kolo
    //
    ("s", "-[bc‹dghksätvzì]lo", "0lo"),
    ("s", "-[bc‹dnsätvzì]ko", "0ko"),
    ("s", "-[bc‹dksätvzì]no", "0no"),
    ("s", "-o", "o"),

    //
    // vzor staven’
    //
    ("s", "-’", "’"),
    //
    // vzor džv‹e (‹e,dž,tž,nž,pž) vùj.-takŽ sele
    //
    ("s", "-[‹“é][e]", "10"),
    ("s", "-[pb][ž]", "10"),

    //
    // vzor ìena
    //
    ("ì", "-[aeiouy‡Ž’—œù]ka", "0ko"),
    ("ì", "-ka", "ko"),
    ("ì", "-[bdghkmnptvz]ra", "0ro"),
    ("ì", "-ra", "ro"),
    ("ì", "-[tdbnvmp]a", "0o"),
    ("ì", "-cha", "cho"),
    ("ì", "-[gh]a", "0o"),
    ("ì", "-Ëa", "Ëo"),
    ("ì", "-[ä‹]a", "0o"),
    ("ì", "-a", "o"),

    // vz. p’seË
    ("ì", "-eË", "ni"),
    ("ì", "-oË", "oni"),
    ("ì", "-[ž]j", "0ji"),

    //
    // vzor róìe
    //
    ("ì", "-ev", "vi"),
    ("ì", "-ice", "ice"),
    ("ì", "-e", "e"),

    //
    // vzor p’seË
    //
    ("ì", "-[ea‡][jìË]", "10i"),
    ("ì", "-[eayo][ä]", "10i"),
    ("ì", "-[’y]Ë", "0ni"),
    ("ì", "-[’yù]Ëe", "0ni"),
    ("ì", "-[é“ì]", "0i"),
    ("ì", "-toÞ", "toÞi"),

    //
    // vzor kost
    //
    // most names ending at -st are masculine
    //( "ì", "-st", "sti"),
    ("ì", "-ves", "vsi"),
    ("m", "-p", "pe"),
    // TODO: how to choose gender for words like /p$/
    ("ì", "-p", "pi"),

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
    // Nicolas, Alois, Alojz, Az’z, Fikejz, Fikejs, Charles
    ("m", "-[aei’jy][sz]", "10i"),
    // Tomasz
    ("m", "-sz", "szi"),
    ("m", "vùtrus", "vùtruse"),
    ("m", "trus", "truse"),
    ("m", "-[aeioumpst][lnmrktp]us", "10e"),
    ("m", "-[acghkr][ou]s", "10si"),

    ("s", "-[ikl]um", "0um"),
    ("s", "-io", "0"),

    //
    // vzor sedl‡k
    //

    ("m", "-[aeiouy‡Ž’—œù]r", "0re"),
    ("m", "-r", "Þe"),
    //( "m", "-sven", "svene"),
    //( "m", "-ben", "bene"),
    //( "m", "-jelen", "jelene"),
    //( "m", "-prsten", "prstene"),
    //( "m", "-semen", "semene"),
    //( "m", "-[c‹dnmprstvz]en", "0ne"),
    ("m", "-pes", "pse"),
    ("m", "-[“éË]ez", "0ezi"),
    ("m", "-g", "gu"),
    ("m", "-[dl¸mnpbtvwzs]", "0e"),
    ("m", "-sex", "sexe"),
    ("m", "-x", "xi"),
    ("m", "sek", "seku"),
    ("m", "vùsek", "vùseku"),
    ("m", "z‡sek", "z‡seku"),
    ("m", "prósek", "próseku"),
    ("m", "œsek", "œseku"),
    ("m", "‹esnek", "‹esneku"),
    ("m", "fulnek", "fulneku"),
    ("m", "-[c‹äìdnËmpbrstvz]ek", "0ku"),
    ("m", "-ch", "chu"),
    ("m", "-ph", "phe"),
    ("m", "-th", "the"),
    ("m", "-[hkq]", "0u"),
    ("m", "-e[mnz]", "0e"),
    // Mathieu
    ("m", "-eu", "eu"),

    //
    //
    // vzor muì
    //
    ("m", "-ec", "‹e"),
    ("m", "-kóË", "koni"),
    ("m", "-[c‹“äËÞéì]", "0i"),
    ("m", "-oj", "oji"),

    // patterny pro pÞetypov‡n’ rodu
    ("m", "-[gh]a", "0o"),
    ("m", "-[k]a", "0o"),
    ("m", "-a", "o"),

    ("ì", "-l", "li"),
    ("ì", "-’", "’"),
    ("ì", "-ó[jÞ]", "o0i"),
    ("ì", "-[‹äjÞ]", "0i"),

    ("s", "-[sljÞË]e", "0e"),
    // ( "ì","-c’", "c’"),
    // ‹aj, prodej, OndÞej, ìokej
    ("m", "-j", "ji"),
    // Josef, Detlef, ... ?
    ("m", "-f", "fe"),
    // zbroj, vùzbroj, vùstroj, trofej, neteÞ
    // jiÞ’, podkon’, ... ?
    ("m", "-’", "’"),
    // Hugo, Kvido
    ("m", "-o", "o"),
    // Noe
    ("m", "-oe", "oe"),

    // Barklay, Vasiliy, Osprey, Leroy
    ("m", "-[aeiou]y", "0yi"),
    // pomnoìn‡ jmŽna, Indy, Marty
    ("?", "-y", "y"),
    ("?", "-i", "i"),
    ("?", "-œ", "œ"),

    // Thu -> Thuu
    ("m", "-u", "uu"),

    // George
    ("m", "-ge", "gi"),
    // Mike
    ("m", "-ke", "ku")

  //( "ì", "-d", "do"),
  //( "ì", "-dt", "dto"),
  //( "ì", "-th", "tho")

  )

  // Vùjimky:
  // v1 - pÞehl‡sky
  // : dól ... dol, stól ... stol, nóì ... noì, hól ... hole, pól ... póle
  // 1.p n‡hrada 4.p.
  val umlautExceptions = HashMap(
    "osel" -> HashMap("prefix" -> "osl", "case4" -> "osla"),
    "‹olek" -> HashMap("prefix" -> "‹olk", "case4" -> "‹olka"),
    "karel" -> HashMap("prefix" -> "karl", "case4" -> "karla"),
    "Karel" -> HashMap("prefix" -> "Karl", "case4" -> "Karla"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "pawel" -> HashMap("prefix" -> "pawl", "case4" -> "pawla"),
    "pawe¸" -> HashMap("prefix" -> "paw¸", "case4" -> "paw¸a"),
    "äavel" -> HashMap("prefix" -> "äavl", "case4" -> "äavla"),
    "Pavel" -> HashMap("prefix" -> "Pavl", "case4" -> "Pavla"),
    "Havel" -> HashMap("prefix" -> "Havl", "case4" -> "Havla"),
    "havel" -> HashMap("prefix" -> "havl", "case4" -> "havla"),
    "BoÞek" -> HashMap("prefix" -> "BoÞk", "case4" -> "BoÞka"),
    "boÞek" -> HashMap("prefix" -> "boÞk", "case4" -> "boÞka"),
    "Ludžk" -> HashMap("prefix" -> "Lu“k", "case4" -> "Lu“ka"),
    "ludžk" -> HashMap("prefix" -> "lu“k", "case4" -> "lu“ka"),
    "pes" -> HashMap("prefix" -> "ps", "case4" -> "psa"),
    "pytel" -> HashMap("prefix" -> "pytl", "case4" -> "pytel"),
    "ocet" -> HashMap("prefix" -> "oct", "case4" -> "octa"),
    "chlŽb" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "chleba" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "kel" -> HashMap("prefix" -> "kl", "case4" -> "kel"),
    "sopel" -> HashMap("prefix" -> "sopl", "case4" -> "sopel"),
    "posel" -> HashMap("prefix" -> "posl", "case4" -> "posla"),
    "dól" -> HashMap("prefix" -> "dol", "case4" -> "dól"),
    //"sól" -> HashMap( "prefix" -> "sole", "case4" -> "sól" ),
    "vól" -> HashMap("prefix" -> "vol", "case4" -> "vola"),
    "pól" -> HashMap("prefix" -> "póle", "case4" -> "póli"),
    "hól" -> HashMap("prefix" -> "hole", "case4" -> "hól"),
    "sól" -> HashMap("prefix" -> "soli", "case4" -> "sól"),
    "stól" -> HashMap("prefix" -> "stol", "case4" -> "stól"),
    "l’h" -> HashMap("prefix" -> "lih", "case4" -> "l’h"),
    "sn’h" -> HashMap("prefix" -> "snžh", "case4" -> "sn’h"),
    "z‡Þ" -> HashMap("prefix" -> "z‡Þe", "case4" -> "z‡Þ"),
    "svatoz‡Þ" -> HashMap("prefix" -> "svatoz‡Þe", "case4" -> "svatoz‡Þ"),
    "kóË" -> HashMap("prefix" -> "koË", "case4" -> "konž"),
    "tóË" -> HashMap("prefix" -> "tóËe", "case4" -> "tóË"),
    // --- !
    "Þ’jen" -> HashMap("prefix" -> "Þ’jn", "case4" -> "Þ’jen"),
    "duben" -> HashMap("prefix" -> "dubn", "case4" -> "duben"),
    "len" -> HashMap("prefix" -> "ln", "case4" -> "len"),
    "smrt" -> HashMap("prefix" -> "smré", "case4" -> "smrt"),
    "v’tr" -> HashMap("prefix" -> "vžtr", "case4" -> "v’tr"),
    "stupeË" -> HashMap("prefix" -> "stupË", "case4" -> "stupeË"),
    "peË" -> HashMap("prefix" -> "pË", "case4" -> "peË"),
    "cyklus" -> HashMap("prefix" -> "cykl", "case4" -> "cyklus"),
    "dvór" -> HashMap("prefix" -> "dvor", "case4" -> "dvór"),
    "ze“" -> HashMap("prefix" -> "z“", "case4" -> "ze“"),
    "œ‹et" -> HashMap("prefix" -> "œ‹t", "case4" -> "œ‹et"),
    "mr‡z" -> HashMap("prefix" -> "mraz", "case4" -> "mr‡z"),
    "hnój" -> HashMap("prefix" -> "hnoj", "case4" -> "hnój"),
    "lój" -> HashMap("prefix" -> "loj", "case4" -> "lój"),
    "skrùä" -> HashMap("prefix" -> "skrùäe", "case4" -> "skrùä"),
    "nehet" -> HashMap("prefix" -> "neht", "case4" -> "nehet"),
    "veä" -> HashMap("prefix" -> "vä", "case4" -> "veä"),
    "dŽäé" -> HashMap("prefix" -> "deäé", "case4" -> "dŽäé"),
    "vžzeË" -> HashMap("prefix" -> "vžzË", "case4" -> "vžzËe"),
    "stžìeË" -> HashMap("prefix" -> "stžìË", "case4" -> "stžìeË"),
    "p‡n" -> HashMap("prefix" -> "pan", "case4" -> "p‡na"),
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
    "sle“",
    "saäa",
    "deäé",
    "koË",
    //"chlast",
    //"plast",
    //"termoplast",
    "vžzeË",
    "séeìeË",
    "papeì",
    "“eda",
    "zeé",
    "h‡j",
    "lanùì",
    "sluha",
    "muì",
    "velmoì",
    "maéej",
    "maéej",
    "t‡ta",
    "kolega",
    "mluvka",
    "strejda",
    "polda",
    "moula",
    "ämoula",
    "slouha",
    "dr‡kula",
    //"test",
    //"rest",
    //"trest",
    //"arest",
    //"azbest",
    //"ametyst",
    //"chÞest",
    //"protest",
    //"kontest",
    //"motorest",
    //"most",
    //"host",
    "kÞ’ì",
    "stupeË",
    "peË",
    "‹aj",
    "prodej",
    "vùdej",
    "vùprodej",
    "“ej",
    "zlo“ej",
    "ìokej",
    "hranostaj",
    "dobro“ej",
    "darmo“ej",
    "‹aro“ej",
    "kolo“ej",
    "sprej",
    "displej",
    "aleä",
    "ambroì",
    "mroì",
    "tom‡ä",
    "luk‡ä",
    "tobi‡ä",
    "jiÞ’",
    "podkon’",
    "komoÞ’",
    "jirka",
    "ilja",
    "pepa",
    "joska",
    "ondÞej",
    "andrej",
    "metodžj",
    "mikul‡ä",
    "mikol‡ä",
    "kvido",
    "hugo",
    "oto",
    "otto",
    "alexej",
    "ivo",
    "bruno",
    "alois",
    "bartolomžj",
    "spr‡vce",
    "dozorce",
    "noe",
    "mimoË",
    "piìmoË",
    "brachyblast",
    "hlemùì“",
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
    "maruä",
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
    "rœt",
    "ann",
    "r‡chel",
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

    "deäé",
    "zte‹",
    "Þe‹",
    "kÞe‹",
    "kle‹",
    "maätal",
    "vä",
    "kancel‡Þ",
    "z‡vžj",
    "zvžÞ",
    "sbeÞ",
    "neteÞ",
    "rozkoä",
    // "myäa",
    "postel",
    "prdel",
    "koudel",
    "koupel",
    "ocel",
    "digestoÞ",
    "konzervatoÞ",
    "oratoÞ",
    "zbroj",
    "vùzbroj",
    "vùstroj",
    "trofej",
    "obec",
    "oj",
    "otep",
    "step",
    "sól"
  // "transmise",
  )

  // overriding gender to neutral
  var neutralWords = List(
    "nemluvnž",
    "slónž",
    "kózle",
    "sele",
    "osle",
    "zv’Þe",
    "kuÞe",
    "tele",
    "prase",
    "house",
    "vejce",
    "moÞe"
  )

  // moreExceptions - nedoÞeäenŽ vùjimky
  var moreExceptions = List(
    "ester",
    "dagmar",
    "housle",
    "äle",
    "ovoce",
    // "obec",
    "humus",
    "muka",
    "miriam"
  // Je Nikola ìenskŽ nebo muìskŽ jmŽno??? (podobnž S‡va, Sl‡va, Saäa),
  )

  // deviations - róznŽ odchylky ve skloËov‡n’
  // - ‹asem by bylo vhodnŽ opravit
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
  def indexOfSuffixByPattern(origPattern: String, origText: String): (Int, String) = {
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

  def unpalatalize(text: String) = {
    text.replaceAll("“i", "di")
      .replaceAll("éi", "ti")
      .replaceAll("Ëi", "ni")
      .replaceAll("“e", "dž")
      .replaceAll("ée", "tž")
      .replaceAll("Ëe", "nž")
  }

  def palatalize(text: String) = {
    text.replaceAll("di", "“i")
      .replaceAll("ti", "éi")
      .replaceAll("ni", "Ëi")
      .replaceAll("dž", "“e")
      .replaceAll("tž", "ée")
      .replaceAll("nž", "Ëe")
  }

  // 
  // Replace numeric placeholders with their values from a register.
  // 
  // @param text
  // @param placeholders
  // @returns {String}
  // 
  def replacePlaceholders(text: String, placeholders: String) = {
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
  def declineToVocative(patternIndex: Int, word: String) = {
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
  def leftStr(n: Int, text: String) = text.take(n)

  // - pravy retezec od indexu n (vcetne)
  def rightStr(n: Int, text: String) = text.drop(n)

  // 
  // Declines the word using a standard suffix patern.
  // 
  // @param word
  // @param patternIndex
  //            index of a declination pattern in the 'patterns' array
  // 
  def declineByPattern(word: String, patternIndex: Int): HashMap[String, String] = {
    if (patternIndex < 0 || patternIndex > patterns.length) {
      throw new IllegalArgumentException("patternIndex")
    }

    var lowerCaseWord = word.toLowerCase()

    if (moreExceptions.find(indexOfSuffixByPattern(_, lowerCaseWord)._1 >= 0).isDefined) {
      return HashMap(
        "message" -> "Toto slovo zat’m neum’me spr‡vnž vyskloËovat.",
        "vocative" -> word
      )
    }

    var message = ""
    var gender = patterns(patternIndex)._1
    var vocative = declineToVocative(patternIndex, word)

    if (deviations.find(indexOfSuffixByPattern(_, lowerCaseWord)._1 >= 0).isDefined) {
      message = "Pozor, v nžkterùch p‡dech nemus’ bùt skloËov‡n’ tohoto slova pÞesnŽ."
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
  def findStandardPattern(word: String, preferredGender: String): Int = {
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
      gender = "ì"
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
      result += ("message" -> "Nem‡me skloËovac’ vzor pro toto slovo.")
    }
    return result
  }

}