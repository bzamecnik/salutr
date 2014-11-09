# Salutr

Skloňuje česká jména do 5. pádu pro oslovení.

Umí dokonce i jména složená z více částí, titulů, iniciálových písmen, apod.

Knihovna je psaná ve Scale a webová aplikace s pomocí frameworku Play. Dále je zde aplikace spustitelná přes CLI a refactoring původní JS implementace.

Inspirací byl skript od Pavla Sedláka (Pteryxe) [České skloňování / The czech declensions](http://www.pteryx.net/sklonovani.html). Původní javascriptová implementace, ačkoliv jde o amatérský software, je vcelku zajímavá, leč trpí mnoha praktickými nedostatky.

Prvním krokem byl dlouhý a drastický refaktoring - vhodné pojmenování symbolů, strukturování funkcí, odstranění globálních mutable proměnných, API, atd. Kromě toho byl omezen scope z všech pádů a čísel pouze na 5. pád jednotného čísla. Druhým krokem bylo kompletní přepsání do Scaly.

Kód lze použít třemi způsoby:

- jako knihovnu
- jako CLI aplikaci - main ve třídě VocativeDeclinatorApp.scala
- jako webovou aplikaci - přes Play

Aplikace byla úspěšně použita např. pro vyskloňování seznamu jmen a příjmení ze sčítání lidu od MVČR.

Je třeba podotknout, že skloňování není ve všech případech úplně 100%, ale cca pro 95% případů je v pořádku.

## Autor

- [Bohumír Zámečník](http://bohumirzamecnik.cz/) - refaktoring, port do Scaly, skloňování složených jmen, skripty pro manipulaci s daty
- [Pavel Sedlák](http://www.pteryx.net/) - původní skript, data do pravidel

## Licence

MIT, pokud není výslově uvedeno jinak

Prohlášení: Tento projekt byl použit pro zpracování dat ve společnosti Etnetera, ale vznikl čistě mimo ni. Takže platí licence MIT.

## TODO

- lepší dokumentace
- vyčistit kód
- testy
- separovat knihovnu, CLI, webovou aplikaci a JS implementaci
- přepsat zpracování pravidel - použít regex nebo trie
- opravovat pravidla pro chybné výsledky
- zkusit foneticky založené skloňování
