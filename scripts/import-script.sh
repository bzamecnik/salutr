iconv -f windows-1250 -t utf-8 < zjkr_win.csv > zjkr_utf8.csv
sed -e 's/^/"/' -e 's/;.*;;/",/' zjkr_utf8.csv > jmena_pocet.csv
mongoimport -d salutr -c names --type csv --headerline --file jmena_pocet.csv

