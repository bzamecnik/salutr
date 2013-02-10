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
  // Pޒdavn� jm�na a z�jmena
  //
  val patterns = List(

    ("m", "-k�", "k�"),
    ("m", "-r�", "r�"),
    ("m", "-ch�", "ch�"),
    ("m", "-h�", "h�"),
    ("m", "-�", "�"),
    ("m", "-[ae�i�]c�", "0c�"),
    ("�", "-[ae�i�]c�", "0c�"),
    ("s", "-[ae�i�]c�", "0c�"),
    ("m", "-�el", "�eli"),
    ("m", "-kr�l", "kr�li"),
    ("m", "-[bc�dhklmnprs�tvz�]n�", "0n�"),
    ("�", "-[bc�dhklmnprs�tvz�]n�", "0n�"),
    ("s", "-[bc�dhklmnprs�tvz�]n�", "0n�"),

    ("m", "-[i�]tel", "0tel"),

    ("s", "-�", "�"),
    ("�", "-�", "�"),

    ("m", "-�", "�"),
    ("�", "-�", "�"),

    // v�jimky (zvl. b��n� slova),
    ("m", "-b�h", "bo�e"),
    ("m", "-pan", "pane"),
    ("m", "-vztek", "vzteku"),
    ("m", "-dotek", "doteku"),
    ("�", "-hra", "hro"),

    //
    // Spec. pޒdady sklo�ov�n�(+p�edseda, srdce jako �pln� v�jimka)
    //
    ("m", "-[i]sta", "0sto"),
    ("m", "-[o]sta", "0sto"),
    ("m", "-p�edseda", "p�edsedo"),
    ("m", "-srdce", "srdce"),
    ("m", "-[dbvr]ce", "0�e"),
    ("m", "-[j�]ev", "0eve"),
    ("m", "-[l�]ev", "0eve/0ve"),

    ("m", "-�[lz]", "o0e"),

    // v�j. n�� (vzor mu�)
    ("m", "n��", "no�i"),

    //
    // vzor kolo
    //
    ("s", "-[bc�dghks�tvz�]lo", "0lo"),
    ("s", "-[bc�dns�tvz�]ko", "0ko"),
    ("s", "-[bc�dks�tvz�]no", "0no"),
    ("s", "-o", "o"),

    //
    // vzor staven�
    //
    ("s", "-�", "�"),
    //
    // vzor d�v�e (�e,d�,t�,n�,p�) v�j.-tak� sele
    //
    ("s", "-[���][e]", "10"),
    ("s", "-[pb][�]", "10"),

    //
    // vzor �ena
    //
    ("�", "-[aeiouy������]ka", "0ko"),
    ("�", "-ka", "ko"),
    ("�", "-[bdghkmnptvz]ra", "0ro"),
    ("�", "-ra", "ro"),
    ("�", "-[tdbnvmp]a", "0o"),
    ("�", "-cha", "cho"),
    ("�", "-[gh]a", "0o"),
    ("�", "-�a", "�o"),
    ("�", "-[�]a", "0o"),
    ("�", "-a", "o"),

    // vz. p�se�
    ("�", "-e�", "ni"),
    ("�", "-o�", "oni"),
    ("�", "-[�]j", "0ji"),

    //
    // vzor r��e
    //
    ("�", "-ev", "vi"),
    ("�", "-ice", "ice"),
    ("�", "-e", "e"),

    //
    // vzor p�se�
    //
    ("�", "-[ea�][j��]", "10i"),
    ("�", "-[eayo][�]", "10i"),
    ("�", "-[�y]�", "0ni"),
    ("�", "-[�y�]�e", "0ni"),
    ("�", "-[��]", "0i"),
    ("�", "-to�", "to�i"),

    //
    // vzor kost
    //
    // most names ending at -st are masculine
    //( "�", "-st", "sti"),
    ("�", "-ves", "vsi"),
    ("m", "-p", "pe"),
    // TODO: how to choose gender for words like /p$/
    ("�", "-p", "pi"),

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
    // Nicolas, Alois, Alojz, Az�z, Fikejz, Fikejs, Charles
    ("m", "-[aei�jy][sz]", "10i"),
    // Tomasz
    ("m", "-sz", "szi"),
    ("m", "v�trus", "v�truse"),
    ("m", "trus", "truse"),
    ("m", "-[aeioumpst][lnmrktp]us", "10e"),
    ("m", "-[acghkr][ou]s", "10si"),

    ("s", "-[ikl]um", "0um"),
    ("s", "-io", "0"),

    //
    // vzor sedl�k
    //

    ("m", "-[aeiouy������]r", "0re"),
    ("m", "-r", "�e"),
    //( "m", "-sven", "svene"),
    //( "m", "-ben", "bene"),
    //( "m", "-jelen", "jelene"),
    //( "m", "-prsten", "prstene"),
    //( "m", "-semen", "semene"),
    //( "m", "-[c�dnmprstvz]en", "0ne"),
    ("m", "-pes", "pse"),
    ("m", "-[���]ez", "0ezi"),
    ("m", "-g", "gu"),
    ("m", "-[dl�mnpbtvwzs]", "0e"),
    ("m", "-sex", "sexe"),
    ("m", "-x", "xi"),
    ("m", "sek", "seku"),
    ("m", "v�sek", "v�seku"),
    ("m", "z�sek", "z�seku"),
    ("m", "pr�sek", "pr�seku"),
    ("m", "�sek", "�seku"),
    ("m", "�esnek", "�esneku"),
    ("m", "fulnek", "fulneku"),
    ("m", "-[c���dn�mpbrstvz]ek", "0ku"),
    ("m", "-ch", "chu"),
    ("m", "-ph", "phe"),
    ("m", "-th", "the"),
    ("m", "-[hkq]", "0u"),
    ("m", "-e[mnz]", "0e"),
    // Mathieu
    ("m", "-eu", "eu"),

    //
    //
    // vzor mu�
    //
    ("m", "-ec", "�e"),
    ("m", "-k��", "koni"),
    ("m", "-[c�������]", "0i"),
    ("m", "-oj", "oji"),

    // patterny pro p�etypov�n� rodu
    ("m", "-[gh]a", "0o"),
    ("m", "-[k]a", "0o"),
    ("m", "-a", "o"),

    ("�", "-l", "li"),
    ("�", "-�", "�"),
    ("�", "-�[j�]", "o0i"),
    ("�", "-[��j�]", "0i"),

    ("s", "-[slj��]e", "0e"),
    // ( "�","-c�", "c�"),
    // �aj, prodej, Ond�ej, �okej
    ("m", "-j", "ji"),
    // Josef, Detlef, ... ?
    ("m", "-f", "fe"),
    // zbroj, v�zbroj, v�stroj, trofej, nete�
    // jiޒ, podkon�, ... ?
    ("m", "-�", "�"),
    // Hugo, Kvido
    ("m", "-o", "o"),
    // Noe
    ("m", "-oe", "oe"),

    // Barklay, Vasiliy, Osprey, Leroy
    ("m", "-[aeiou]y", "0yi"),
    // pomno�n� jm�na, Indy, Marty
    ("?", "-y", "y"),
    ("?", "-i", "i"),
    ("?", "-�", "�"),

    // Thu -> Thuu
    ("m", "-u", "uu"),

    // George
    ("m", "-ge", "gi"),
    // Mike
    ("m", "-ke", "ku")

  //( "�", "-d", "do"),
  //( "�", "-dt", "dto"),
  //( "�", "-th", "tho")

  )

  // V�jimky:
  // v1 - p�ehl�sky
  // : d�l ... dol, st�l ... stol, n�� ... no�, h�l ... hole, p�l ... p�le
  // 1.p n�hrada 4.p.
  val umlautExceptions = HashMap(
    "osel" -> HashMap("prefix" -> "osl", "case4" -> "osla"),
    "�olek" -> HashMap("prefix" -> "�olk", "case4" -> "�olka"),
    "karel" -> HashMap("prefix" -> "karl", "case4" -> "karla"),
    "Karel" -> HashMap("prefix" -> "Karl", "case4" -> "Karla"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "pawel" -> HashMap("prefix" -> "pawl", "case4" -> "pawla"),
    "pawe�" -> HashMap("prefix" -> "paw�", "case4" -> "paw�a"),
    "�avel" -> HashMap("prefix" -> "�avl", "case4" -> "�avla"),
    "Pavel" -> HashMap("prefix" -> "Pavl", "case4" -> "Pavla"),
    "Havel" -> HashMap("prefix" -> "Havl", "case4" -> "Havla"),
    "havel" -> HashMap("prefix" -> "havl", "case4" -> "havla"),
    "Bo�ek" -> HashMap("prefix" -> "Bo�k", "case4" -> "Bo�ka"),
    "bo�ek" -> HashMap("prefix" -> "bo�k", "case4" -> "bo�ka"),
    "Lud�k" -> HashMap("prefix" -> "Lu�k", "case4" -> "Lu�ka"),
    "lud�k" -> HashMap("prefix" -> "lu�k", "case4" -> "lu�ka"),
    "pes" -> HashMap("prefix" -> "ps", "case4" -> "psa"),
    "pytel" -> HashMap("prefix" -> "pytl", "case4" -> "pytel"),
    "ocet" -> HashMap("prefix" -> "oct", "case4" -> "octa"),
    "chl�b" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "chleba" -> HashMap("prefix" -> "chleb", "case4" -> "chleba"),
    "pavel" -> HashMap("prefix" -> "pavl", "case4" -> "pavla"),
    "kel" -> HashMap("prefix" -> "kl", "case4" -> "kel"),
    "sopel" -> HashMap("prefix" -> "sopl", "case4" -> "sopel"),
    "posel" -> HashMap("prefix" -> "posl", "case4" -> "posla"),
    "d�l" -> HashMap("prefix" -> "dol", "case4" -> "d�l"),
    //"s�l" -> HashMap( "prefix" -> "sole", "case4" -> "s�l" ),
    "v�l" -> HashMap("prefix" -> "vol", "case4" -> "vola"),
    "p�l" -> HashMap("prefix" -> "p�le", "case4" -> "p�li"),
    "h�l" -> HashMap("prefix" -> "hole", "case4" -> "h�l"),
    "s�l" -> HashMap("prefix" -> "soli", "case4" -> "s�l"),
    "st�l" -> HashMap("prefix" -> "stol", "case4" -> "st�l"),
    "l�h" -> HashMap("prefix" -> "lih", "case4" -> "l�h"),
    "sn�h" -> HashMap("prefix" -> "sn�h", "case4" -> "sn�h"),
    "z��" -> HashMap("prefix" -> "z��e", "case4" -> "z��"),
    "svatoz��" -> HashMap("prefix" -> "svatoz��e", "case4" -> "svatoz��"),
    "k��" -> HashMap("prefix" -> "ko�", "case4" -> "kon�"),
    "t��" -> HashMap("prefix" -> "t��e", "case4" -> "t��"),
    // --- !
    "ޒjen" -> HashMap("prefix" -> "ޒjn", "case4" -> "ޒjen"),
    "duben" -> HashMap("prefix" -> "dubn", "case4" -> "duben"),
    "len" -> HashMap("prefix" -> "ln", "case4" -> "len"),
    "smrt" -> HashMap("prefix" -> "smr�", "case4" -> "smrt"),
    "v�tr" -> HashMap("prefix" -> "v�tr", "case4" -> "v�tr"),
    "stupe�" -> HashMap("prefix" -> "stup�", "case4" -> "stupe�"),
    "pe�" -> HashMap("prefix" -> "p�", "case4" -> "pe�"),
    "cyklus" -> HashMap("prefix" -> "cykl", "case4" -> "cyklus"),
    "dv�r" -> HashMap("prefix" -> "dvor", "case4" -> "dv�r"),
    "ze�" -> HashMap("prefix" -> "z�", "case4" -> "ze�"),
    "��et" -> HashMap("prefix" -> "��t", "case4" -> "��et"),
    "mr�z" -> HashMap("prefix" -> "mraz", "case4" -> "mr�z"),
    "hn�j" -> HashMap("prefix" -> "hnoj", "case4" -> "hn�j"),
    "l�j" -> HashMap("prefix" -> "loj", "case4" -> "l�j"),
    "skr��" -> HashMap("prefix" -> "skr��e", "case4" -> "skr��"),
    "nehet" -> HashMap("prefix" -> "neht", "case4" -> "nehet"),
    "ve�" -> HashMap("prefix" -> "v�", "case4" -> "ve�"),
    "d���" -> HashMap("prefix" -> "de��", "case4" -> "d���"),
    "v�ze�" -> HashMap("prefix" -> "v�z�", "case4" -> "v�z�e"),
    "st��e�" -> HashMap("prefix" -> "st���", "case4" -> "st��e�"),
    "p�n" -> HashMap("prefix" -> "pan", "case4" -> "p�na"),
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
    "sle�",
    "sa�a",
    "de��",
    "ko�",
    //"chlast",
    //"plast",
    //"termoplast",
    "v�ze�",
    "s�e�e�",
    "pape�",
    "�eda",
    "ze�",
    "h�j",
    "lan��",
    "sluha",
    "mu�",
    "velmo�",
    "ma�ej",
    "ma�ej",
    "t�ta",
    "kolega",
    "mluvka",
    "strejda",
    "polda",
    "moula",
    "�moula",
    "slouha",
    "dr�kula",
    //"test",
    //"rest",
    //"trest",
    //"arest",
    //"azbest",
    //"ametyst",
    //"ch�est",
    //"protest",
    //"kontest",
    //"motorest",
    //"most",
    //"host",
    "kޒ�",
    "stupe�",
    "pe�",
    "�aj",
    "prodej",
    "v�dej",
    "v�prodej",
    "�ej",
    "zlo�ej",
    "�okej",
    "hranostaj",
    "dobro�ej",
    "darmo�ej",
    "�aro�ej",
    "kolo�ej",
    "sprej",
    "displej",
    "ale�",
    "ambro�",
    "mro�",
    "tom��",
    "luk��",
    "tobi��",
    "jiޒ",
    "podkon�",
    "komoޒ",
    "jirka",
    "ilja",
    "pepa",
    "joska",
    "ond�ej",
    "andrej",
    "metod�j",
    "mikul��",
    "mikol��",
    "kvido",
    "hugo",
    "oto",
    "otto",
    "alexej",
    "ivo",
    "bruno",
    "alois",
    "bartolom�j",
    "spr�vce",
    "dozorce",
    "noe",
    "mimo�",
    "pi�mo�",
    "brachyblast",
    "hlem��",
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
    "maru�",
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
    "r�t",
    "ann",
    "r�chel",
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

    "de��",
    "zte�",
    "�e�",
    "k�e�",
    "kle�",
    "ma�tal",
    "v�",
    "kancel��",
    "z�v�j",
    "zv��",
    "sbe�",
    "nete�",
    "rozko�",
    // "my�a",
    "postel",
    "prdel",
    "koudel",
    "koupel",
    "ocel",
    "digesto�",
    "konzervato�",
    "orato�",
    "zbroj",
    "v�zbroj",
    "v�stroj",
    "trofej",
    "obec",
    "oj",
    "otep",
    "step",
    "s�l"
  // "transmise",
  )

  // overriding gender to neutral
  var neutralWords = List(
    "nemluvn�",
    "sl�n�",
    "k�zle",
    "sele",
    "osle",
    "zv��e",
    "ku�e",
    "tele",
    "prase",
    "house",
    "vejce",
    "mo�e"
  )

  // moreExceptions - nedo�e�en� v�jimky
  var moreExceptions = List(
    "ester",
    "dagmar",
    "housle",
    "�le",
    "ovoce",
    // "obec",
    "humus",
    "muka",
    "miriam"
  // Je Nikola �ensk� nebo mu�sk� jm�no??? (podobn� S�va, Sl�va, Sa�a),
  )

  // deviations - r�zn� odchylky ve sklo�ov�n�
  // - �asem by bylo vhodn� opravit
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
    text.replaceAll("�i", "di")
      .replaceAll("�i", "ti")
      .replaceAll("�i", "ni")
      .replaceAll("�e", "d�")
      .replaceAll("�e", "t�")
      .replaceAll("�e", "n�")
  }

  def palatalize(text: String) = {
    text.replaceAll("di", "�i")
      .replaceAll("ti", "�i")
      .replaceAll("ni", "�i")
      .replaceAll("d�", "�e")
      .replaceAll("t�", "�e")
      .replaceAll("n�", "�e")
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
        "message" -> "Toto slovo zat�m neum�me spr�vn� vysklo�ovat.",
        "vocative" -> word
      )
    }

    var message = ""
    var gender = patterns(patternIndex)._1
    var vocative = declineToVocative(patternIndex, word)

    if (deviations.find(indexOfSuffixByPattern(_, lowerCaseWord)._1 >= 0).isDefined) {
      message = "Pozor, v n�kter�ch p�dech nemus� b�t sklo�ov�n� tohoto slova p�esn�."
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
      gender = "�"
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
      result += ("message" -> "Nem�me sklo�ovac� vzor pro toto slovo.")
    }
    return result
  }

}