var debugModeEnabled = 0;

// Ve zvl. pripadech je mozne pomoci teto promenne "pretypovat" rod jmena
var preferredGender = "0" // smi byt "0", "m", "ž", "s"

var patterns = [];
//
// Přídavná jména a zájmena
//
patterns.push([ "m", "-ký", "kého", "kému", "ký/kého", "ký", "kém", "kým", "-ké/-cí", "kých", "kým", "ké", "-ké/-cí", "kých", "kými" ])
patterns.push([ "m", "-rý", "rého", "rému", "rý/rého", "rý", "rém", "rým", "-ré/-ří", "rých", "rým", "ré", "-ré/-ří", "rých", "rými" ])
patterns.push([ "m", "-chý", "chého", "chému", "chý/chého", "chý", "chém", "chým", "-ché/-ší", "chých", "chým", "ché", "-ché/-ší", "chých", "chými" ])
patterns.push([ "m", "-hý", "hého", "hému", "hý/hého", "hý", "hém", "hým", "-hé/-zí", "hých", "hým", "hé", "-hé/-zí", "hých", "hými" ])
patterns.push([ "m", "-ý", "ého", "ému", "ý/ého", "ý", "ém", "ým", "-é/-í", "ých", "ým", "é", "-é/-í", "ých", "ými" ])
patterns.push([ "m", "-[aeěií]cí", "0cího", "0címu", "0cí/0cího", "0cí", "0cím", "0cím", "0cí", "0cích", "0cím", "0cí", "0cí", "0cích", "0cími" ])
patterns.push([ "ž", "-[aeěií]cí", "0cí", "0cí", "0cí", "0cí", "0cí", "0cí", "0cí", "0cích", "0cím", "0cí", "0cí", "0cích", "0cími" ])
patterns.push([ "s", "-[aeěií]cí", "0cího", "0címu", "0cí/0cího", "0cí", "0cím", "0cím", "0cí", "0cích", "0cím", "0cí", "0cí", "0cích", "0cími" ])
patterns.push([ "m", "-[bcčdhklmnprsštvzž]ní", "0ního", "0nímu", "0ní/0ního", "0ní", "0ním", "0ním", "0ní", "0ních", "0ním", "0ní", "0ní", "0ních", "0ními" ])
patterns.push([ "ž", "-[bcčdhklmnprsštvzž]ní", "0ní", "0ní", "0ní", "0ní", "0ní", "0ní", "0ní", "0ních", "0ním", "0ní", "0ní", "0ních", "0ními" ])
patterns.push([ "s", "-[bcčdhklmnprsštvzž]ní", "0ního", "0nímu", "0ní/0ního", "0ní", "0ním", "0ním", "0ní", "0ních", "0ním", "0ní", "0ní", "0ních", "0ními" ])

patterns.push([ "m", "-[i]tel", "0tele", "0teli", "0tele", "0tel", "0teli", "0telem", "0telé", "0telů", "0telům", "0tele", "0telé", "0telích", "0teli" ])
patterns.push([ "m", "-[í]tel", "0tele", "0teli", "0tele", "0tel", "0teli", "0telem", "átelé", "áteli", "átelům", "átele", "átelé", "átelích", "áteli" ])

patterns.push([ "s", "-é", "ého", "ému", "é", "é", "ém", "ým", "-á", "ých", "ým", "á", "á", "ých", "ými" ])
patterns.push([ "ž", "-á", "é", "é", "ou", "á", "é", "ou", "-é", "ých", "ým", "é", "é", "ých", "ými" ])
patterns.push([ "-", "já", "mne", "mně", "mne/mě", "já", "mně", "mnou", "my", "nás", "nám", "nás", "my", "nás", "námi" ])
patterns.push([ "-", "ty", "tebe", "tobě", "tě/tebe", "ty", "tobě", "tebou", "vy", "vás", "vám", "vás", "vy", "vás", "vámi" ])
patterns.push([ "-", "my", "", "", "", "", "", "", "my", "nás", "nám", "nás", "my", "nás", "námi" ])
patterns.push([ "-", "vy", "", "", "", "", "", "", "vy", "vás", "vám", "vás", "vy", "vás", "vámi" ])
patterns.push([ "m", "on", "něho", "mu/jemu/němu", "ho/jej", "on", "něm", "ním", "oni", "nich", "nim", "je", "oni", "nich", "jimi/nimi" ])
patterns.push([ "m", "oni", "", "", "", "", "", "", "oni", "nich", "nim", "je", "oni", "nich", "jimi/nimi" ])
patterns.push([ "ž", "ony", "", "", "", "", "", "", "ony", "nich", "nim", "je", "ony", "nich", "jimi/nimi" ])
patterns.push([ "s", "ono", "něho", "mu/jemu/němu", "ho/jej", "ono", "něm", "ním", "ona", "nich", "nim", "je", "ony", "nich", "jimi/nimi" ])
patterns.push([ "ž", "ona", "ní", "ní", "ji", "ona", "ní", "ní", "ony", "nich", "nim", "je", "ony", "nich", "jimi/nimi" ])
patterns.push([ "m", "ten", "toho", "tomu", "toho", "ten", "tom", "tím", "ti", "těch", "těm", "ty", "ti", "těch", "těmi" ])
patterns.push([ "ž", "ta", "té", "té", "tu", "ta", "té", "tou", "ty", "těch", "těm", "ty", "ty", "těch", "těmi" ])
patterns.push([ "s", "to", "toho", "tomu", "toho", "to", "tom", "tím", "ta", "těch", "těm", "ta", "ta", "těch", "těmi" ])

// výjimky (zvl. běžná slova])
patterns.push([ "m", "-bůh", "boha", "bohu", "boha", "bože", "bohovi", "bohem", "bozi/bohové", "bohů", "bohům", "bohy", "bozi/bohové", "bozích", "bohy" ])
patterns.push([ "m", "-pan", "pana", "panu", "pana", "pane", "panu", "panem", "páni/pánové", "pánů", "pánům", "pány", "páni/bohové", "pánech", "pány" ])
patterns.push([ "-", "-dveře", "", "", "", "", "", "", "dveře", "dveří", "dveřím", "dveře", "dveře", "dveřích", "dveřmi" ])
patterns.push([ "m", "-vztek", "vzteku", "vzteku", "vztek", "vzteku", "vzteku", "vztekem", "vzteky", "vzteků", "vztekům", "vzteky", "vzteky", "vztecích", "vzteky" ])
patterns.push([ "m", "-dotek", "doteku", "doteku", "dotek", "doteku", "doteku", "dotekem", "doteky", "doteků", "dotekům", "doteky", "doteky", "dotecích", "doteky" ])
patterns.push([ "ž", "-hra", "hry", "hře", "hru", "hro", "hře", "hrou", "hry", "her", "hrám", "hry", "hry", "hrách", "hrami" ])

//
// Spec. přídady skloňování(+předseda, srdce jako úplná výjimka)
//
patterns.push([ "m", "-[i]sta", "0sty", "0stovi", "0stu", "0sto", "0stovi", "0stou", "-0sté", "0stů", "0stům", "0sty", "0sté", "0stech", "0sty" ])
patterns.push([ "m", "-[o]sta", "0sty", "0stovi", "0stu", "0sto", "0stovi", "0stou", "-0stové", "0stů", "0stům", "0sty", "0sté", "0stech", "0sty" ])
patterns.push([ "m", "-předseda", "předsedy", "předsedovi", "předsedu", "předsedo", "předsedovi", "předsedou", "předsedové", "předsedů", "předsedům", "předsedy", "předsedové", "předsedech", "předsedy" ])
patterns.push([ "m", "-srdce", "srdce", "srdi", "sdrce", "srdce", "srdci", "srdcem", "srdce", "srdcí", "srdcím", "srdce", "srdce", "srdcích", "srdcemi" ])
patterns.push([ "m", "-[db]ce", "0ce", "0ci", "0ce", "0če", "0ci", "0cem", "0ci/0cové", "0ců", "0cům", "0ce", "0ci/0cové", "0cích", "0ci" ])
patterns.push([ "m", "-[jň]ev", "0evu", "0evu", "0ev", "0eve", "0evu", "0evem", "-0evy", "0evů", "0evům", "0evy", "0evy", "0evech", "0evy" ])
patterns.push([ "m", "-[lř]ev", "0evu/0va", "0evu/0vovi", "0ev/0va", "0eve/0ve", "0evu/0vovi", "0evem/0vem", "-0evy/0vové", "0evů/0vů", "0evům/0vům", "0evy/0vy", "0evy/0vové", "0evech/0vech", "0evy/0vy" ])

patterns.push([ "m", "-ů[lz]", "o0u/o0a", "o0u/o0ovi", "ů0/o0a", "o0e", "o0u", "o0em", "o-0y/o-0ové", "o0ů", "o0ům", "o0y", "o0y/o0ové", "o0ech", "o0y" ])

// výj. nůž (vzor muž)
patterns.push([ "m", "nůž", "nože", "noži", "nůž", "noži", "noži", "nožem", "nože", "nožů", "nožům", "nože", "nože", "nožích", "noži" ])

//
// vzor kolo
//
patterns.push([ "s", "-[bcčdghksštvzž]lo", "0la", "0lu", "0lo", "0lo", "0lu", "0lem", "-0la", "0el", "0lům", "0la", "0la", "0lech", "0ly" ])
patterns.push([ "s", "-[bcčdnsštvzž]ko", "0ka", "0ku", "0ko", "0ko", "0ku", "0kem", "-0ka", "0ek", "0kům", "0ka", "0ka", "0cích/0kách", "0ky" ])
patterns.push([ "s", "-[bcčdksštvzž]no", "0na", "0nu", "0no", "0no", "0nu", "0nem", "-0na", "0en", "0nům", "0na", "0na", "0nech/0nách", "0ny" ])
patterns.push([ "s", "-o", "a", "u", "o", "o", "u", "em", "-a", "", "ům", "a", "a", "ech", "y" ])

//
// vzor stavení
//
patterns.push([ "s", "-í", "í", "í", "í", "í", "í", "ím", "-í", "í", "ím", "í", "í", "ích", "ími" ])
//
// vzor děvče (če,dě,tě,ně,pě) výj.-také sele
//
patterns.push([ "s", "-[čďť][e]", "10te", "10ti", "10", "10", "10ti", "10tem", "1-ata", "1at", "1atům", "1ata", "1ata", "1atech", "1aty" ])
patterns.push([ "s", "-[pb][ě]", "10te", "10ti", "10", "10", "10ti", "10tem", "1-ata", "1at", "1atům", "1ata", "1ata", "1atech", "1aty" ])

//
// vzor žena
//
patterns.push([ "ž", "-[aeiouyáéíóúý]ka", "0ky", "0ce", "0ku", "0ko", "0ce", "0kou", "-0ky", "0k", "0kám", "0ky", "0ky", "0kách", "0kami" ])
patterns.push([ "ž", "-ka", "ky", "ce", "ku", "ko", "ce", "kou", "-ky", "ek", "kám", "ky", "ky", "kách", "kami" ])
patterns.push([ "ž", "-[bdghkmnptvz]ra", "0ry", "0ře", "0ru", "0ro", "0ře", "0rou", "-0ry", "0er", "0rám", "0ry", "0ry", "0rách", "0rami" ])
patterns.push([ "ž", "-ra", "ry", "ře", "ru", "ro", "ře", "rou", "-ry", "r", "rám", "ry", "ry", "rách", "rami" ])
patterns.push([ "ž", "-[tdbnvmp]a", "0y", "0ě", "0u", "0o", "0ě", "0ou", "-0y", "0", "0ám", "0y", "0y", "0ách", "0ami" ])
patterns.push([ "ž", "-cha", "chy", "še", "chu", "cho", "še", "chou", "-chy", "ch", "chám", "chy", "chy", "chách", "chami" ])
patterns.push([ "ž", "-[gh]a", "0y", "ze", "0u", "0o", "ze", "0ou", "-0y", "0", "0ám", "0y", "0y", "0ách", "0ami" ])
patterns.push([ "ž", "-ňa", "ni", "ně", "ňou", "ňo", "ni", "ňou", "-ně/ničky", "ň", "ňám", "ně/ničky", "ně/ničky", "ňách", "ňami" ])
patterns.push([ "ž", "-[šč]a", "0i", "0e", "0u", "0o", "0e", "0ou", "-0e/0i", "0", "0ám", "0e/0i", "0e/0i", "0ách", "0ami" ])
patterns.push([ "ž", "-a", "y", "e", "u", "o", "e", "ou", "-y", "", "ám", "y", "y", "ách", "ami" ])

// vz. píseň
patterns.push([ "ž", "-eň", "ně", "ni", "eň", "ni", "ni", "ní", "-ně", "ní", "ním", "ně", "ně", "ních", "němi" ])
patterns.push([ "ž", "-oň", "oně", "oni", "oň", "oni", "oni", "oní", "-oně", "oní", "oním", "oně", "oně", "oních", "oněmi" ])
patterns.push([ "ž", "-[ě]j", "0je", "0ji", "0j", "0ji", "0ji", "0jí", "-0je", "0jí", "0jím", "0je", "0je", "0jích", "0jemi" ])

//
// vzor růže
//
patterns.push([ "ž", "-ev", "ve", "vi", "ev", "vi", "vi", "ví", "-ve", "ví", "vím", "ve", "ve", "vích", "vemi" ])
patterns.push([ "ž", "-ice", "ice", "ici", "ici", "ice", "ici", "icí", "-ice", "ic", "icím", "ice", "ice", "icích", "icemi" ])
patterns.push([ "ž", "-e", "e", "i", "i", "e", "i", "í", "-e", "í", "ím", "e", "e", "ích", "emi" ])

//
// vzor píseň
//
patterns.push([ "ž", "-[eaá][jžň]", "10e/10i", "10i", "10", "10i", "10i", "10í", "-10e/10i", "10í", "10ím", "10e", "10e", "10ích", "10emi" ])
patterns.push([ "ž", "-[eayo][š]", "10e/10i", "10i", "10", "10i", "10i", "10í", "10e/10i", "10í", "10ím", "10e", "10e", "10ích", "10emi" ])
patterns.push([ "ž", "-[íy]ň", "0ně", "0ni", "0ň", "0ni", "0ni", "0ní", "-0ně", "0ní", "0ním", "0ně", "0ně", "0ních", "0němi" ])
patterns.push([ "ž", "-[íyý]ňe", "0ně", "0ni", "0ň", "0ni", "0ni", "0ní", "-0ně", "0ní", "0ním", "0ně", "0ně", "0ních", "0němi" ])
patterns.push([ "ž", "-[ťďž]", "0e", "0i", "0", "0i", "0i", "0í", "-0e", "0í", "0ím", "0e", "0e", "0ích", "0emi" ])
patterns.push([ "ž", "-toř", "toře", "toři", "toř", "toři", "toři", "toří", "-toře", "toří", "tořím", "toře", "toře", "tořích", "tořemi" ])

//
// vzor kost
//
patterns.push([ "ž", "-st", "sti", "sti", "st", "sti", "sti", "stí", "-sti", "stí", "stem", "sti", "sti", "stech", "stmi" ])
patterns.push([ "ž", "ves", "vsi", "vsi", "ves", "vsi", "vsi", "vsí", "vsi", "vsí", "vsem", "vsi", "vsi", "vsech", "vsemi" ])

//
//
// vzor Amadeus, Celsius, Kumulus, rektikulum, praktikum
//
patterns.push([ "m", "-[e]us", "0a", "0u/0ovi", "0a", "0e", "0u/0ovi", "0em", "0ové", "0ů", "0ům", "0y", "0ové", "0ích", "0y" ])
patterns.push([ "m", "-[i]us", "0a", "0u/0ovi", "0a", "0e", "0u/0ovi", "0em", "0ové", "0ů", "0ům", "0usy", "0ové", "0ích", "0usy" ])
patterns.push([ "m", "-[i]s", "0se", "0su/0sovi", "0se", "0se/0si", "0su/0sovi", "0sem", "0sy/0sové", "0sů", "0sům", "0sy", "0sy/0ové", "0ech", "0sy" ])
patterns.push([ "m", "výtrus", "výtrusu", "výtrusu", "výtrus", "výtruse", "výtrusu", "výtrusem", "výtrusy", "výtrusů", "výtrusům", "výtrusy", "výtrusy", "výtrusech", "výtrusy" ])
patterns.push([ "m", "trus", "trusu", "trusu", "trus", "truse", "trusu", "trusem", "trusy", "trusů", "trusům", "trusy", "trusy", "trusech", "trusy" ])
patterns.push([ "m", "-[aeioumpts][lnmrktp]us", "10u/10a", "10u/10ovi", "10us/10a", "10e", "10u/10ovi", "10em", "10y/10ové", "10ů", "10ům", "10y", "10y/10ové", "10ech", "10y" ])
patterns.push([ "s", "-[l]um", "0a", "0u", "0um", "0um", "0u", "0em", "0a", "0", "0ům", "0a", "0a", "0ech", "0y" ])
patterns.push([ "s", "-[k]um", "0a", "0u", "0um", "0um", "0u", "0em", "0a", "0", "0ům", "0a", "0a", "0cích", "0y" ])
patterns.push([ "s", "-[i]um", "0a", "0u", "0um", "0um", "0u", "0em", "0a", "0í", "0ům", "0a", "0a", "0iích", "0y" ])
patterns.push([ "s", "-[i]um", "0a", "0u", "0um", "0um", "0u", "0em", "0a", "0ejí", "0ům", "0a", "0a", "0ejích", "0y" ])
patterns.push([ "s", "-io", "0a", "0u", "0", "0", "0u", "0em", "0a", "0í", "0ům", "0a", "0a", "0iích", "0y" ])

//
// vzor sedlák
//

patterns.push([ "m", "-[aeiouyáéíóúý]r", "0ru/0ra", "0ru/0rovi", "0r/0ra", "0re", "0ru/0rovi", "0rem", "-0ry/-0rové", "0rů", "0rům", "0ry", "0ry/0rové", "0rech", "0ry" ])
// patterns.push([
// "m","-[aeiouyáéíóúý]r","0ru/0ra","0ru/0rovi","0r/0ra","0re","0ru/0rovi","0rem",
// "-0ry/-0ři","0rů","0rům","0ry","0ry/0ři", "0rech","0ry" ])
patterns.push([ "m", "-r", "ru/ra", "ru/rovi", "r/ra", "ře", "ru/rovi", "rem", "-ry/-rové", "rů", "rům", "ry", "ry/rové", "rech", "ry" ])
// patterns.push([ "m","-r", "ru/ra", "ru/rovi", "r/ra", "ře",
// "ru/rovi", "rem", "-ry/-ři", "rů","rům","ry", "ry/ři", "rech", "ry" ])
patterns.push([ "m", "-[bcčdnmprstvz]en", "0nu/0na", "0nu/0novi", "0en/0na", "0ne", "0nu/0novi", "0nem", "-0ny/0nové", "0nů", "0nům", "0ny", "0ny/0nové", "0nech", "0ny" ])
patterns.push([ "m", "-[dglmnpbtvzs]", "0u/0a", "0u/0ovi", "0/0a", "0e", "0u/0ovi", "0em", "-0y/0ové", "0ů", "0ům", "0y", "0y/0ové", "0ech", "0y" ])
patterns.push([ "m", "-[x]", "0u/0e", "0u/0ovi", "0/0e", "0i", "0u/0ovi", "0em", "-0y/0ové", "0ů", "0ům", "0y", "0y/0ové", "0ech", "0y" ])
patterns.push([ "m", "sek", "seku/seka", "seku/sekovi", "sek/seka", "seku", "seku/sekovi", "sekem", "seky/sekové", "seků", "sekům", "seky", "seky/sekové", "secích", "seky" ])
patterns.push([ "m", "výsek", "výseku/výseka", "výseku/výsekovi", "výsek/výseka", "výseku", "výseku/výsekovi", "výsekem", "výseky/výsekové", "výseků", "výsekům", "výseky", "výseky/výsekové", "výsecích", "výseky" ])
patterns.push([ "m", "zásek", "záseku/záseka", "záseku/zásekovi", "zásek/záseka", "záseku", "záseku/zásekovi", "zásekem", "záseky/zásekové", "záseků", "zásekům", "záseky", "záseky/zásekové", "zásecích", "záseky" ])
patterns.push([ "m", "průsek", "průseku/průseka", "průseku/průsekovi", "průsek/průseka", "průseku", "průseku/průsekovi", "průsekem", "průseky/průsekové", "průseků", "výsekům", "průseky", "průseky/průsekové", "průsecích", "průseky" ])
patterns.push([ "m", "-[cčšždnňmpbrstvz]ek", "0ku/0ka", "0ku/0kovi", "0ek/0ka", "0ku", "0ku/0kovi", "0kem", "-0ky/0kové", "0ků", "0kům", "0ky", "0ky/0kové", "0cích", "0ky" ])
patterns.push([ "m", "-[k]", "0u/0a", "0u/0ovi", "0/0a", "0u", "0u/0ovi", "0em", "-0y/0ové", "0ů", "0ům", "0y", "0y/0ové", "cích", "0y" ])
patterns.push([ "m", "-ch", "chu/cha", "chu/chovi", "ch/cha", "chu/cha", "chu/chovi", "chem", "-chy/chové", "chů", "chům", "chy", "chy/chové", "ších", "chy" ])
patterns.push([ "m", "-[h]", "0u/0a", "0u/0ovi", "0/0a", "0u/0a", "0u/0ovi", "0em", "-0y/0ové", "0ů", "0ům", "0y", "0y/0ové", "zích", "0y" ])
patterns.push([ "m", "-e[mnz]", "0u/0a", "0u/0ovi", "e0/e0a", "0e", "0u/0ovi", "0em", "-0y/0ové", "0ů", "0ům", "0y", "0y/0ové", "0ech", "0y" ])

//
//
// vzor muž
//
patterns.push([ "m", "-ec", "ce", "ci/covi", "ec/ce", "če", "ci/covi", "cem", "-ce/cové", "ců", "cům", "ce", "ce/cové", "cích", "ci" ])
patterns.push([ "m", "-[cčďšňřťž]", "0e", "0i/0ovi", "0e", "0i", "0i/0ovi", "0em", "-0e/0ové", "0ů", "0ům", "0e", "0e/0ové", "0ích", "0i" ])
patterns.push([ "m", "-oj", "oje", "oji/ojovi", "oj/oje", "oji", "oji/ojovi", "ojem", "-oje/ojové", "ojů", "ojům", "oje", "oje/ojové", "ojích", "oji" ])

// patterny pro přetypování rodu
patterns.push([ "m", "-[gh]a", "0y", "0ovi", "0u", "0o", "0ovi", "0ou", "0ové", "0ů", "0ům", "0y", "0ové", "zích", "0y" ])
patterns.push([ "m", "-[k]a", "0y", "0ovi", "0u", "0o", "0ovi", "0ou", "0ové", "0ů", "0ům", "0y", "0ové", "cích", "0y" ])
patterns.push([ "m", "-a", "y", "ovi", "u", "o", "ovi", "ou", "ové", "ů", "ům", "y", "ové", "ech", "y" ])

patterns.push([ "ž", "-l", "le", "li", "l", "li", "li", "lí", "le", "lí", "lím", "le", "le", "lích", "lemi" ])
patterns.push([ "ž", "-í", "í", "í", "í", "í", "í", "í", "í", "ích", "ím", "í", "í", "ích", "ími" ])
patterns.push([ "ž", "-[jř]", "0e", "0i", "0", "0i", "0i", "0í", "0e", "0í", "0ím", "0e", "0e", "0ích", "0emi" ])
patterns.push([ "ž", "-[č]", "0i", "0i", "0", "0i", "0i", "0í", "0i", "0í", "0ím", "0i", "0i", "0ích", "0mi" ])
patterns.push([ "ž", "-[š]", "0i", "0i", "0", "0i", "0i", "0í", "0i", "0í", "0ím", "0i", "0i", "0ích", "0emi" ])

patterns.push([ "s", "-[sljřň]e", "0ete", "0eti", "0e", "0e", "0eti", "0etem", "0ata", "0at", "0atům", "0ata", "0ata", "0atech", "0aty" ])
// patterns.push([ "ž","-cí", "cí", "cí", "cí", "cí", "cí", "cí", "cí", "cích",
// "cím", "cí", "cí", "cích", "cími" ])
// čaj, prodej, Ondřej, žokej
patterns.push([ "m", "-j", "je", "ji", "j", "ji", "ji", "jem", "je/jové", "jů", "jům", "je", "je/jové", "jích", "ji" ])
// Josef, Detlef, ... ?
patterns.push([ "m", "-f", "fa", "fu/fovi", "f/fa", "fe", "fu/fovi", "fem", "fy/fové", "fů", "fům", "fy", "fy/fové", "fech", "fy" ])
// zbroj, výzbroj, výstroj, trofej, neteř
// jiří, podkoní, ... ?
patterns.push([ "m", "-í", "ího", "ímu", "ího", "í", "ímu", "ím", "í", "ích", "ím", "í", "í", "ích", "ími" ])
// Hugo
patterns.push([ "m", "-go", "a", "govi", "ga", "ga", "govi", "gem", "gové", "gů", "gům", "gy", "gové", "zích", "gy" ])
// Kvido
patterns.push([ "m", "-o", "a", "ovi", "a", "a", "ovi", "em", "ové", "ů", "ům", "y", "ové", "ech", "y" ])

// doplňky
// některá pomnožná jména
patterns.push([ "?", "-[tp]y", "?", "?", "?", "?", "?", "?", "-0y", "0", "0ům", "0y", "0y", "0ech", "0ami" ])
patterns.push([ "?", "-[k]y", "?", "?", "?", "?", "?", "?", "-0y", "e0", "0ám", "0y", "0y", "0ách", "0ami" ])

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
	"sůl": { prefix: "sole", case4: "sůl" },
	"vůl": { prefix: "vol", case4: "vola" },
	"půl": { prefix: "půle", case4: "půli" },
	"hůl": { prefix: "hole", case4: "hůl" },
	"stůl": { prefix: "stol", case4: "stůl" },
	"líh": { prefix: "lih", case4: "líh" },
	"sníh": { prefix: "sněh", case4: "sníh" },
	"zář": { prefix: "záře", case4: "zář" },
	"svatozář": { prefix: "svatozáře", case4: "svatozář" },
	"kůň": { prefix: "koň", case4: "koně" },
	"tůň": { prefix: "tůňe", case4: "tůň" },
// --- !
	"prsten": { prefix: "prstýnek", case4: "prstýnku" },
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
	"skrýš": { prefix: "skrýše", case4: "skrýš" },
	"nehet": { prefix: "neht", case4: "nehet" },
	"veš": { prefix: "vš", case4: "veš" },
	"déšť": { prefix: "dešť", case4: "déšť" },
	"myš": { prefix: "myše", case4: "myš" }
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
v10.push("Ondřej")
v10.push("Andrej")
// v10.push("josef")
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

// v11 - zmena rodu na zensky
var v11 = [];
v11.push("vš")
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
v11.push("ves")
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

// v0 - nedořešené výjimky
var v0 = []
// v0.push("ondřej")
// v0.push("josef")
// v0.push("déšť")
v0.push("moře")
v0.push("Ester")
v0.push("Dagmar")
// v0.push("vejce")
v0.push("housle")
v0.push("šle")
v0.push("ovoce")
// v0.push("sleď")
v0.push("Zeus")
// v0.push("zbroj")
// v0.push("výzbroj")
// v0.push("výstroj")
// v0.push("obec")
// v0.push("konzervatoř")
// v0.push("digestoř")
v0.push("humus")
v0.push("muka")
v0.push("noe")
v0.push("Noe")
v0.push("Miriam")
v0.push("miriam")
// Je Nikola ženské nebo mužské jméno??? (podobně Sáva)

// v3 - různé odchylky ve skloňování
// - časem by bylo vhodné opravit
var v3 = [];
v3.push("jméno")
v3.push("myš")
v3.push("vězeň")
v3.push("sťežeň")
v3.push("oko")
v3.push("sole")
v3.push("šach")
v3.push("veš")
v3.push("myš")
v3.push("klášter")
v3.push("kněz")
v3.push("král")
v3.push("zď")
v3.push("sto")
v3.push("smrt")
v3.push("leden")
v3.push("len")
v3.push("les")
v3.push("únor")
v3.push("březen")
v3.push("duben")
v3.push("květen")
v3.push("červen")
v3.push("srpen")
v3.push("říjen")
v3.push("pantofel")
v3.push("žába")
v3.push("zoja")
v3.push("Zoja")
v3.push("Zoe")
v3.push("zoe")

function showMessage(text) {
	document.getElementById("message").innerHTML = text;
}

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
	text = text.toLowerCase()
	pattern = pattern.toLowerCase()
	var i = pattern.length
	var j = text.length

	if (i == 0 || j == 0) {
		return -1;
	}
	i--;
	j--;

	while (i >= 0 && j >= 0) {
		if (pattern.charAt(i) == "]") {
			i--;
			quit = 1;
			while (i >= 0 && pattern.charAt(i) != "[") {
				if (pattern.charAt(i) == text.charAt(j)) {
					quit = 0;
					if (placeholders) {
						placeholders.push(pattern.charAt(i));
					}
				}
				i--;
			}

			if (quit == 1) {
				return -1;
			}
		} else {
			if (pattern.charAt(i) == '-') {
				return j + 1;
			}
			if (pattern.charAt(i) != text.charAt(j)) {
				return -1;
			}
		}
		i--;
		j--;
	}
	if (i < 0 && j < 0) {
		return 0;
	}
	if (pattern.charAt(i) == '-') {
		return 0;
	}

	return -1
}

function Xdetene(text) {
	text = text.replace(/ďi/g, "di");
	text = text.replace(/ťi/g, "ti");
	text = text.replace(/ňi/g, "ni");
	text = text.replace(/ďe/g, "dě");
	text = text.replace(/ťe/g, "tě");
	text = text.replace(/ňe/g, "ně");
	return text;
}

function Xedeten(text) {
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
 * @param caseNumberIndex
 *            index within the pattern (gender, number/case)
 */
function declineSingleCase(caseNumberIndex, patternIndex, word) {
	if (patternIndex < 0 || patternIndex >= patterns.length || caseNumberIndex < 1 || caseNumberIndex > 14) {
		return "???";
	}

	var word3 = Xedeten(word);
	var placeholders = [];
	var suffixIndex = isPattern(patterns[patternIndex][1], word3, placeholders)
	if (suffixIndex < 0) {
		return "???";
	}

	var patternForCase = patterns[patternIndex][caseNumberIndex];
	if (patternForCase == "?") {
		return "?";
	}

	var rv = (!debugModeEnabled && caseNumberIndex == 1) ? /* 1. pad nemenime */
	Xdetene(word3) : leftStr(suffixIndex, word3) + '-' + replacePlaceholders(patternForCase, placeholders);

	if (debugModeEnabled) {
		// preskoceni filtrovani
		return rv;
	}

	// Formatovani zivotneho sklonovani

	var hyphenIndex = rv.indexOf("-");
	var slashIndex = rv.indexOf("/");

	if (hyphenIndex != -1 && slashIndex != -1) {
		if (isMasculineGenderAnimate()) {
			// "text-xxx/yyy" -> "textyyy"
			rv = leftStr(hyphenIndex, rv) + rightStr(slashIndex + 1, rv);
		} else {
			// "text-xxx/yyy" -> "text-xxx"
			rv = leftStr(slashIndex, rv);
		}
	}

	// vypusteni pomocnych znaku
	rv = rv.replace(/[\/\-]/, "");
	rv = Xdetene(rv);

	return rv;
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
	declinationResults.vocative = declineSingleCase(5, patternIndex, word);

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
function findStandardPattern(word) {
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
 * It writes global variables: preferredGender
 * 
 * @param word
 * @returns declination results {gender: "", vocative: ""}
 */
function declineWord(word) {
	var wordForDeclining = word;
	
	// if the word is in v1 exceptions get its prefix
	// (exceptions for the forth case)
	var umlautException = umlautExceptions[word];
	if (umlautException) {
		wordForDeclining = umlautException.prefix;
	}

	wordForDeclining = Xedeten(wordForDeclining);

	// Pretypovani rodu?
	if (v10.indexOf(wordForDeclining) >= 0) {
		preferredGender = "m";
	} else if (v11.indexOf(wordForDeclining) >= 0) {
		preferredGender = "ž";
	} else if (v12.indexOf(wordForDeclining) >= 0) {
		preferredGender = "s";
	}

	// Nalezeni patternu
	var patternIndex = findStandardPattern(wordForDeclining);
	if (patternIndex < 0) {
		showMessage("Chyba: pro toto slovo nebyl nalezen skloňovací vzor.");
		return {vocative: wordForDeclining};
	}

	showMessage(patterns[patternIndex]);
	
	// Vlastni sklonovani
	return declineByPattern(wordForDeclining, patternIndex);
}

//
// Funkce uzivatelskeho rozhrani
//
function onDecline() {
	var inputWords = document.ui.inputText.value.trim().replace(/\s+/, " ").split(" ");
	showMessage("");

	preferredGender = "0";

	var wordsGender = [];
	var wordsVocative = [];

	for ( var i = inputWords.length - 1; i >= 0; i--) {
		var inputWord = inputWords[i];

		// vysklonovani
		var declinationResults = declineWord(inputWord);

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

		wordsGender.push(gender);
		wordsVocative.push(declinationResults.vocative);
	}

	document.ui.gender.value = wordsGender.join(' ');
	document.ui.vocative.value = wordsVocative.join(' ');
}
