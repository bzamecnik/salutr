mongoexport -d salutr -c single_names_export -f nominative,vocative,gender,count --csv \
	| tr '¬Ąą' 'ŽĽľ' |grep -v 'ˇ' > single_names.csv

cut -d , -f 1 single_names.csv |iconv -f UTF-8 -t ASCII//TRANSLIT \
  | sed -e 's/[^a-zA-Z]//g' -e 's/^nominative$/nominativeAscii/' -e 's/^\(.*\)$/"\1"/' > single_names_ascii_name.csv
paste -d , single_names.csv single_names_ascii_name.csv > single_names_full.csv

#mongoimport -d salutr_db -c name_vocatives --headerline --type csv --file single_names_full.csv

zcat single_names_full.csv.gz |mongoimport -d salutr_db -c name_vocatives --headerline --type csv